package com.kirubankamaraj.accounts.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author kiruban-11285
 * @date 10/07/25
 */

@Entity
@Table(name = "UserIDMaintainer")
public class UserIDMaintainer {

    @Id
    @Column(name = "UserID")
    private Long userID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
