package com.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Account {
    private int id;
    private String login;
    private String name;
    private String pass;

//    private Set<Category> categories = new HashSet<Category>(0);


    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
    @Column(name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "account")
//    public Set<Category> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(Set<Category> categories) {
//        this.categories = categories;
//    }

    public Account() {
    }

    public Account(String login, String name, String pass) {
        this.login = login;
        this.name = name;
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
//                ", categories=" + categories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return //id == account.id &&
//                Objects.equals(login, account.login) &&
                Objects.equals(name, account.name) //&&
//                Objects.equals(pass, account.pass)
                ;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, login, name, pass);
    }
}
