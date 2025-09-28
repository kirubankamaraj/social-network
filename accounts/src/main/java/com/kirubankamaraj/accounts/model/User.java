package com.kirubankamaraj.accounts.model;


import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author kiruban-11285
 * @date 05/07/25
 */

@Entity
@Table(name = "User")
@JsonRootName("user")
public class User {

    @Id
    @Column(name = "UserID")
    private Long userID;

    @Column(name = "UserName")
    private String userNama;

    @Column(name = "DisplayName")
    private String displayName;

    @Column(name = "Bio")
    private String bio;

    @Column(name = "BirthDate")
    private Long birthDate;

    @Column(name = "ImageID")
    private Long imageID;
    
    @Column(name = "CreatedTime")
    private Long joinedDate;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserNama() {
        return userNama;
    }

    public void setUserNama(String userNama) {
        this.userNama = userNama;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }

    public Long getImageID() {
        return imageID;
    }

    public void setImageID(Long imageID) {
        this.imageID = imageID;
    }

    public Long getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Long joinedDate) {
        this.joinedDate = joinedDate;
    }
}
