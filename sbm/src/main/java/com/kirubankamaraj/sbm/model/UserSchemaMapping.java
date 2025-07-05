package com.kirubankamaraj.sbm.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * @author kiruban-11285
 * @date 05/07/25
 */

@Entity
public class UserSchemaMapping {

    @Id
    @Column(name = "UserID")
    private Long userID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SchemaID", nullable = false)
    private Schema schema;

    @Column(name = "CusterIP")
    private String clusterIP;

    @Column(name = "SchemaName")
    private String schemaName;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public String getClusterIP() {
        return clusterIP;
    }

    public void setClusterIP(String clusterIP) {
        this.clusterIP = clusterIP;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }
}
