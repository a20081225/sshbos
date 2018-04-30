package com.yw.bos.domain;

import javax.persistence.*;
/**
 * 分区
 */
@Entity
@Table(name = "bc_subarea", schema = "ssh_bos")
public class Subarea {
    private String id;
//    private String decidedzoneId;
//    private String regionId;
    private String addresskey;
    private String startnum;
    private String endnum;
    private String single;
    private String position;
    private Decidedzone decidedzone;
    private Region region;

    public String getSubareaid(){
        return id;
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "decidedzone_id",insertable = false, updatable = false)
//    public String getDecidedzoneId() {
//        return decidedzoneId;
//    }
//
//    public void setDecidedzoneId(String decidedzoneId) {
//        this.decidedzoneId = decidedzoneId;
//    }
//
//    @Basic
//    @Column(name = "region_id",insertable = false, updatable = false)
//    public String getRegionId() {
//        return regionId;
//    }
//
//    public void setRegionId(String regionId) {
//        this.regionId = regionId;
//    }

    @Basic
    @Column(name = "addresskey")
    public String getAddresskey() {
        return addresskey;
    }

    public void setAddresskey(String addresskey) {
        this.addresskey = addresskey;
    }

    @Basic
    @Column(name = "startnum")
    public String getStartnum() {
        return startnum;
    }

    public void setStartnum(String startnum) {
        this.startnum = startnum;
    }

    @Basic
    @Column(name = "endnum")
    public String getEndnum() {
        return endnum;
    }

    public void setEndnum(String endnum) {
        this.endnum = endnum;
    }

    @Basic
    @Column(name = "single")
    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    @Basic
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subarea subarea = (Subarea) o;

        if (id != null ? !id.equals(subarea.id) : subarea.id != null) return false;
//        if (decidedzoneId != null ? !decidedzoneId.equals(subarea.decidedzoneId) : subarea.decidedzoneId != null)
//            return false;
//        if (regionId != null ? !regionId.equals(subarea.regionId) : subarea.regionId != null) return false;
        if (addresskey != null ? !addresskey.equals(subarea.addresskey) : subarea.addresskey != null) return false;
        if (startnum != null ? !startnum.equals(subarea.startnum) : subarea.startnum != null) return false;
        if (endnum != null ? !endnum.equals(subarea.endnum) : subarea.endnum != null) return false;
        if (single != null ? !single.equals(subarea.single) : subarea.single != null) return false;
        if (position != null ? !position.equals(subarea.position) : subarea.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (decidedzoneId != null ? decidedzoneId.hashCode() : 0);
//        result = 31 * result + (regionId != null ? regionId.hashCode() : 0);
        result = 31 * result + (addresskey != null ? addresskey.hashCode() : 0);
        result = 31 * result + (startnum != null ? startnum.hashCode() : 0);
        result = 31 * result + (endnum != null ? endnum.hashCode() : 0);
        result = 31 * result + (single != null ? single.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    @ManyToOne
    public Decidedzone getDecidedzone() {
        return decidedzone;
    }

    public void setDecidedzone(Decidedzone decidedzone) {
        this.decidedzone = decidedzone;
    }

    @ManyToOne
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
