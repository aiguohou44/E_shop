package com.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Category {
    private int id;
    private String type;
    private Byte hot;
    private Account account;

    public Category() {
    }

    public Category(int id, String type, Byte hot, Account account) {
        this.id = id;
        this.type = type;
        this.hot = hot;
        this.account = account;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "hot")
    public Byte getHot() {
        return hot;
    }

    public void setHot(Byte hot) {
        this.hot = hot;
    }


//    配置级联查询

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aid")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", hot=" + hot +
                ", account=" + account +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                Objects.equals(type, category.type) &&
                Objects.equals(hot, category.hot) &&
                Objects.equals(account, category.account);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, hot, account);
    }
}
