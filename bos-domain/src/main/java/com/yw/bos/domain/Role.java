package com.yw.bos.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "auth_role", schema = "ssh_bos")
public class Role {
    private String id;
    private String name;
    private String code;
    private String description;
    private Collection<Function> functions = new HashSet<Function>();
    private Collection<User> users;

    public  Role(){

    }

    public Role(String roleId) {
        this.id = roleId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;
        if (code != null ? !code.equals(role.code) : role.code != null) return false;
        if (description != null ? !description.equals(role.description) : role.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @ManyToMany
    @JoinTable(name = "role_function",joinColumns = {@JoinColumn(name = "function_id")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public Collection<Function> getFunctions() {
        return this.functions;
    }

    public void setFunctions(Collection<Function> functions) {
        this.functions = functions;
    }

    @ManyToMany
    @JoinTable(name = "user_role",joinColumns = {@JoinColumn(name = "role_id")},inverseJoinColumns = {@JoinColumn(name = "user_id")})
    public Collection<User> getUsers() {
        return this.users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
