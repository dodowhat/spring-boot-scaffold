package com.example.scaffold.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "admin_roles")
public class AdminRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(unique = true)
    @Size(min = 3, max = 64)
    @Pattern(regexp = "^[A-Za-z0-9]{3,}$", message = "must be alpha or(and) numbers")
    private String name;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @ManyToMany(mappedBy = "roles")
    private Set<AdminUser> users;

    @ManyToMany
    @JoinTable(
            name = "admin_action_admin_role",
            joinColumns = @JoinColumn(name = "admin_role_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_action_id")
    )
    private Set<AdminAction> actions;

    public AdminRole(){}

    public AdminRole(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AdminUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AdminUser> users) {
        this.users = users;
    }

    public Set<AdminAction> getActions() {
        return actions;
    }

    public void setActions(Set<AdminAction> actions) {
        this.actions = actions;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public boolean isAdmin() {
        return getName().equals("admin");
    }
}
