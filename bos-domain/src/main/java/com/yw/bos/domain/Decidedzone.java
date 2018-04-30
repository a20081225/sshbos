package com.yw.bos.domain;

import javax.persistence.*;
import java.util.Collection;
/**
 * 定区
 */
@Entity
@Table(name = "bc_decidedzone", schema = "ssh_bos")
public class Decidedzone {
    private String id;
    private String name;
//    private String staffId;
    private Staff staff;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Basic
//    @Column(name = "staff_id",insertable = false, updatable = false)
//    public String getStaffId() {
//        return staffId;
//    }
//
//    public void setStaffId(String staffId) {
//        this.staffId = staffId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Decidedzone that = (Decidedzone) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//        if (staffId != null ? !staffId.equals(that.staffId) : that.staffId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (staffId != null ? staffId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @OneToMany(mappedBy = "decidedzone")
    public Collection<Subarea> getSubareas() {
        return subareas;
    }

    public void setSubareas(Collection<Subarea> subareas) {
        this.subareas = subareas;
    }
}
