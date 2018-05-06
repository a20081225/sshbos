package com.yw.bos.web.action;

import com.yw.bos.domain.Region;
import com.yw.bos.domain.Subarea;
import com.yw.bos.service.ISubareaService;
import com.yw.bos.utils.FileUtils;
import com.yw.bos.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * 分区管理
 */
@Controller
@Scope("prototype")
public class SubareaAciton extends BaseAction<Subarea> {

    @Autowired
    private ISubareaService subareaService;
    //添加
    public String add(){
        subareaService.save(model);
        return LIST;
    }

    //分页
    public String pageQuery(){
        //过滤条件
        DetachedCriteria dc = pageBean.getDetachedCriteria();
        String addresskey = model.getAddresskey();
        if (StringUtils.isNotBlank(addresskey)){
            dc.add(Restrictions.like("addresskey","%"+ addresskey +"%"));
        }
        Region region = model.getRegion();
        if (region != null){
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            dc.createAlias("region","r");
            if (StringUtils.isNotBlank(province)){
                dc.add(Restrictions.like("r.province","%"+ province +"%"));
            }
            if (StringUtils.isNotBlank(city)){
                dc.add(Restrictions.like("r.city","%"+ city +"%"));
            }
            if (StringUtils.isNotBlank(district)){
                dc.add(Restrictions.like("r.district","%"+ district +"%"));
            }
        }
        subareaService.pageQuery(pageBean);
        this.makeJson(pageBean,new String[]{"currentPage","pageSize","detachedCriteria","decidedzone","subareas"});
        return NONE;
    }

    //导出
    public String exportXls() throws IOException {
        List<Subarea> list =  subareaService.findAll();
        //内存中建立
        HSSFWorkbook workbook = new HSSFWorkbook();
        //建立标签页
        HSSFSheet sheet = workbook.createSheet("分区数据");
        //创建标题行
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("分区编号");
        headRow.createCell(1).setCellValue("开始编号");
        headRow.createCell(2).setCellValue("结束编号");
        headRow.createCell(3).setCellValue("位置信息");
        headRow.createCell(4).setCellValue("省市区");
        for (Subarea subarea : list) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(subarea.getId());
            dataRow.createCell(1).setCellValue(subarea.getStartnum());
            dataRow.createCell(2).setCellValue(subarea.getEndnum());
            dataRow.createCell(3).setCellValue(subarea.getPosition());
            dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
        }
        //流下载
        String filename = "分区数据.xls";
        String type = ServletActionContext.getServletContext().getMimeType(filename);
        ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
        ServletActionContext.getResponse().setContentType(type);
        String agent = ServletActionContext.getRequest().getHeader("User-Agent");
        filename = FileUtils.encodeDownloadFilename(filename,agent);
        ServletActionContext.getResponse().setHeader("content-disposition","attachment;filename=" + filename);
        workbook.write(outputStream);
        return NONE;
    }

    //下拉菜单
    public String listajax(){
        List<Subarea> list = subareaService.findNotAssociation();
        this.makeJson(list,new String[]{"decidedzone","region"});
        return NONE;
    }

    private String decidedzoneId;

    public String getDecidedzoneId() {
        return decidedzoneId;
    }

    public void setDecidedzoneId(String decidedzoneId) {
        this.decidedzoneId = decidedzoneId;
    }

    //查询关联分区
    public String findByDecidedzoneId(){
        List<Subarea> list =  subareaService.findByDecidedzoneId(decidedzoneId);
        this.makeJson(list,new String[]{"decidedzone","subareas"});
        return NONE;
    }

    //区域分布图
    public String findSubaresByProvince(){
        List<Object> list = subareaService.findSubaresByProvince();
        this.makeJson(list,new String[]{});
        return NONE;
    }

}
