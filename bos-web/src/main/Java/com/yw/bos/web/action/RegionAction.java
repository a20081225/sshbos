package com.yw.bos.web.action;

import com.yw.bos.domain.Region;
import com.yw.bos.service.IRegionService;
import com.yw.bos.utils.PinYin4jUtils;
import com.yw.bos.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 区域管理
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{

    @Autowired
    private IRegionService regionService;

    private File regionFile;

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

    //导入
    public String importXls ()throws Exception {
        List<Region> regionList = new ArrayList<Region>();
        //poi解析
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
        HSSFSheet hssfSheet = workbook.getSheetAt(0);
        int rowNum;
        String id;
        String province;
        String city;
        String district;
        String postcode;
        String info;
        String[] headByString;
        String shortcode;
        String citycode;
        for (Row row : hssfSheet) {
            rowNum = row.getRowNum();
            if (rowNum == 0){
                continue;
            }
            id = row.getCell(0).getStringCellValue();
            province = row.getCell(1).getStringCellValue();
            city = row.getCell(2).getStringCellValue();
            district = row.getCell(3).getStringCellValue();
            postcode = row.getCell(4).getStringCellValue();
            Region region = new Region();
            region.setId(id);
            region.setProvince(province);
            region.setCity(city);
            region.setDistrict(district);
            region.setPostcode(postcode);

            province = province.substring(0, province.length() - 1);
            city = city.substring(0, city.length() - 1);
            district = district.substring(0, district.length() - 1);
            info = province + city + district;
            headByString = PinYin4jUtils.getHeadByString(info);
            shortcode = StringUtils.join(headByString);
            citycode = PinYin4jUtils.hanziToPinyin(city, "");
            region.setShortcode(shortcode);
            region.setCitycode(citycode);

            regionList.add(region);
        }
        //批量保存
        regionService.saveBatch(regionList);
        return NONE;
    }

    //分页
    public String pageQuery(){
        regionService.pageQuery(pageBean);
        this.makeJson(pageBean,new String[]{"currentPage","pageSize","detachedCriteria","subareas"});
        return NONE;
    }

    //添加
    public String add(){
        regionService.save(model);
        return LIST;
    }

    private String q;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String listajax(){
        List<Region> list = null;
        if (StringUtils.isNotBlank(q)){
            list = regionService.findListByQ(q);
        }else {
            list = regionService.findAll();
        }
        this.makeJson(list,new String[]{"subareas"});
        return NONE;
    }
}
