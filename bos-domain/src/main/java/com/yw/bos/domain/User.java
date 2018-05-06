package com.yw.bos.domain;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "t_user", schema = "ssh_bos")
public class User {
    private String id;
    private String username;
    private String password;
    private Double salary;
    private Date birthday;
    private String gender;
    private String station;
    private String telephone;
    private String remark;
    private Collection<Noticebill> noticebills;
    private Collection<Role> roles = new HashSet<Role>();

    public String getRoleNames(){
        String roleNames = "";
        for (Role role : roles) {
            roleNames += role.getName() + " ";
        }
        return roleNames;
    }

    public String getBirthdayString(){
        if (birthday !=null) {
            String format = new SimpleDateFormat("yyyy-MM-dd").format(birthday);
            return format;
        }else {
            return "暂无数据";
        }

    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "salary")
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "station")
    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (salary != null ? !salary.equals(user.salary) : user.salary != null) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        if (gender != null ? !gender.equals(user.gender) : user.gender != null) return false;
        if (station != null ? !station.equals(user.station) : user.station != null) return false;
        if (telephone != null ? !telephone.equals(user.telephone) : user.telephone != null) return false;
        if (remark != null ? !remark.equals(user.remark) : user.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (station != null ? station.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "user")
    public Collection<Noticebill> getNoticebills() {
        return this.noticebills;
    }

    public void setNoticebills(Collection<Noticebill> noticebills) {
        this.noticebills = noticebills;
    }

    @ManyToMany
    @JoinTable(name = "user_role",joinColumns = {@JoinColumn(name = "role_id")},inverseJoinColumns = {@JoinColumn(name = "user_id")})
    public Collection<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
