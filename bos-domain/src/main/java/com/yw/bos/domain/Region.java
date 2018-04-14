package com.yw.bos.domain;

import javax.persistence.*;
import java.util.Collection;
/**
 * 区域
 */
@Entity
@Table(name = "bc_region", schema = "ssh_bos")
public class Region {
    private String id;
    private String province;
    private String city;
    private String district;
    private String postcode;
    private String shortcode;
    private String citycode;
    private Collection<Subarea> subareas;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "district")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "postcode")
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "shortcode")
    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    @Basic
    @Column(name = "citycode")
    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (id != null ? !id.equals(region.id) : region.id != null) return false;
        if (province != null ? !province.equals(region.province) : region.province != null) return false;
        if (city != null ? !city.equals(region.city) : region.city != null) return false;
        if (district != null ? !district.equals(region.district) : region.district != null) return false;
        if (postcode != null ? !postcode.equals(region.postcode) : region.postcode != null) return false;
        if (shortcode != null ? !shortcode.equals(region.shortcode) : region.shortcode != null) return false;
        if (citycode != null ? !citycode.equals(region.citycode) : region.citycode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (shortcode != null ? shortcode.hashCode() : 0);
        result = 31 * result + (citycode != null ? citycode.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "region")
    public Collection<Subarea> getSubareas() {
        return subareas;
    }

    public void setSubareas(Collection<Subarea> subareas) {
        this.subareas = subareas;
    }
}
