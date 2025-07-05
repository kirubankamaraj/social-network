package com.kirubankamaraj.sbm.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * @author kiruban-11285
 * @date 05/07/25
 */

@Entity
public class Schema {

    @Id
    @Column(name = "SchemaID")
    private Long schemaID;

    @Column(name = "SchemaName")
    private String schemaName;

    @Column(name = "ClusterIP")
    private String clusterIP;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "Password")
    private String password;

    public Long getSchemaID() {
        return schemaID;
    }

    public void setSchemaID(Long schemaID) {
        this.schemaID = schemaID;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getClusterIP() {
        return clusterIP;
    }

    public void setClusterIP(String clusterIP) {
        this.clusterIP = clusterIP;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
