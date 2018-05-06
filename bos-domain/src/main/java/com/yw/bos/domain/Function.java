package com.yw.bos.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "auth_function", schema = "ssh_bos")
public class Function {
    private String id;
    private String name;
    private String code;
    private String description;
    private String page;
    private String generatemenu;//是否生成菜单，1：是 0：否
    private Integer zindex;
//    private String pid;
    private Function parentFunction;//上级权限
    private Collection<Function> children;//下级权限
    private Collection<Role> roles;//权限对应的角色


    public String getpId(){
        if(parentFunction == null){
            return "0";
        }
        return parentFunction.getId();
    }

    public Function() {
    }

    public Function(String fId) {
        this.id = fId;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "page")
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Basic
    @Column(name = "generatemenu")
    public String getGeneratemenu() {
        return generatemenu;
    }

    public void setGeneratemenu(String generatemenu) {
        this.generatemenu = generatemenu;
    }

    @Basic
    @Column(name = "zindex")
    public Integer getZindex() {
        return zindex;
    }

    public void setZindex(Integer zindex) {
        this.zindex = zindex;
    }

//    @Basic
//    @Column(name = "pid")
//    public String getPid() {
//        return pid;
//    }
//
//    public void setPid(String pid) {
//        this.pid = pid;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Function function = (Function) o;

        if (id != null ? !id.equals(function.id) : function.id != null) return false;
        if (name != null ? !name.equals(function.name) : function.name != null) return false;
        if (code != null ? !code.equals(function.code) : function.code != null) return false;
        if (description != null ? !description.equals(function.description) : function.description != null)
            return false;
        if (page != null ? !page.equals(function.page) : function.page != null) return false;
        if (generatemenu != null ? !generatemenu.equals(function.generatemenu) : function.generatemenu != null)
            return false;
        if (zindex != null ? !zindex.equals(function.zindex) : function.zindex != null) return false;
//        if (pid != null ? !pid.equals(function.pid) : function.pid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (page != null ? page.hashCode() : 0);
        result = 31 * result + (generatemenu != null ? generatemenu.hashCode() : 0);
        result = 31 * result + (zindex != null ? zindex.hashCode() : 0);
//        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "id")
    public Function getParentFunction() {
        return parentFunction;
    }

    public void setParentFunction(Function parentFunction) {
        this.parentFunction = parentFunction;
    }

    @OneToMany(mappedBy = "parentFunction")
    public Collection<Function> getChildren() {
        return children;
    }

    public void setChildren(Collection<Function> children) {
        this.children = children;
    }

    @ManyToMany
    @JoinTable(name = "role_function",joinColumns = {@JoinColumn(name = "function_id")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public Collection<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
