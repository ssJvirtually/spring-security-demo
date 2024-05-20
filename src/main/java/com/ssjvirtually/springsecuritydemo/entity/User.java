package com.ssjvirtually.springsecuritydemo.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    
    @Column(name = "username",unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String roles;


    @Column(name = "created_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdOn;


    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
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

    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", role=" + roles + "]";
    }

}
