package com.challenge.listusers.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;
    private String username;
    private String password;
    private String image;
    private String address;
    private String email;
    private Date dateBirth;
    private int gender;
    private String cpfCnpj;

    public User() {
    }

    public User(String name, String username, String password, String image, String address,
                String email, Date dateBirth, int gender, String cpfCnpj) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.image = image;
        this.address = address;
        this.email = email;
        this.dateBirth = dateBirth;
        this.gender = gender;
        this.cpfCnpj = cpfCnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
