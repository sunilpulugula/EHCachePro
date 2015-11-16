package com.ehcache.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:psunil1278@gmail.com">Sunil Kumar</a>
 * @since 16/11/15
 */
public class User implements Serializable{

    private Integer userid;
    private String username;
    private String password;
    private String role;
    private Integer tenantid;

    public User(Integer userid, String username, String password, String role, Integer tenantid) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.role = role;
        this.tenantid = tenantid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getTenantid() {
        return tenantid;
    }

    public void setTenantid(Integer tenantid) {
        this.tenantid = tenantid;
    }
}
