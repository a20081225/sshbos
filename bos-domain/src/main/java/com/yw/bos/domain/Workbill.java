package com.yw.bos.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 工单
 */
@Entity
@Table(name = "qp_workbill", schema = "ssh_bos")
public class Workbill {
    private String id;
    private String type;//工单类型:新、追、改、销
    private String pickstate;//取件状态:未取件、取件中、已取件
    private Timestamp buildtime;
    private Integer attachbilltimes;
    private String remark;
    private Noticebill noticebill;
    private Staff staff;

    public static final String  TYPE_1 = "新单";
    public static final String  TYPE_2 = "追单";
    public static final String  TYPE_3 = "改单";
    public static final String  TYPE_4 = "销单";

    public static final String  PICKSTATE_NO = "未取件";
    public static final String  PICKSTATE_RUNNING = "取件中";
    public static final String  PICKSTATE_YES = "已取件";

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "pickstate")
    public String getPickstate() {
        return pickstate;
    }

    public void setPickstate(String pickstate) {
        this.pickstate = pickstate;
    }

    @Basic
    @Column(name = "buildtime")
    public Timestamp getBuildtime() {
        return buildtime;
    }

    public void setBuildtime(Timestamp buildtime) {
        this.buildtime = buildtime;
    }

    @Basic
    @Column(name = "attachbilltimes")
    public Integer getAttachbilltimes() {
        return attachbilltimes;
    }

    public void setAttachbilltimes(Integer attachbilltimes) {
        this.attachbilltimes = attachbilltimes;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Workbill workbill = (Workbill) o;

        if (id != null ? !id.equals(workbill.id) : workbill.id != null) return false;
        if (type != null ? !type.equals(workbill.type) : workbill.type != null) return false;
        if (pickstate != null ? !pickstate.equals(workbill.pickstate) : workbill.pickstate != null) return false;
        if (buildtime != null ? !buildtime.equals(workbill.buildtime) : workbill.buildtime != null) return false;
        if (attachbilltimes != null ? !attachbilltimes.equals(workbill.attachbilltimes) : workbill.attachbilltimes != null)
            return false;
        if (remark != null ? !remark.equals(workbill.remark) : workbill.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (pickstate != null ? pickstate.hashCode() : 0);
        result = 31 * result + (buildtime != null ? buildtime.hashCode() : 0);
        result = 31 * result + (attachbilltimes != null ? attachbilltimes.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "noticebill_id", referencedColumnName = "id")
    public Noticebill getNoticebill() {
        return noticebill;
    }

    public void setNoticebill(Noticebill noticebill) {
        this.noticebill = noticebill;
    }

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
