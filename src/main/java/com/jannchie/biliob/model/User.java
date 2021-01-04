package com.jannchie.biliob.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 * @author jannchie
 */
@Document(collection = "user")
@JsonInclude(Include.NON_NULL)
public class User {
    @Id
    private ObjectId id;
    private String ip;
    private String uid;
    private String token;
    @NotBlank(message = "用户ID不能为空")
    @Length(min = 2, max = 50, message = "账号最长为50位，最短为两位")
    private String name;
    @Length(min = 6, message = "密码至少为6位")
    private String password;
    private String role;
    private String title;
    private ArrayList<Long> favoriteAid;
    private ArrayList<Long> favoriteMid;
    private Double credit;
    private Double exp;
    private Integer rank;
    @NotBlank(message = "用户ID不能为空")
    @NotNull
    @NotBlank
    @Length(max = 50, min = 2, message = "昵称最长为50位，最短为2位")
    private String nickName;
    @Email(message = "邮箱格式错误")
    @NotBlank(message = "邮箱不能为空")
    private String mail;
    private Boolean ban;
    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.credit = 0D;
        this.exp = 0D;
        this.title = "";
    }
    public User(ObjectId id) {
        this.id = id;
    }

    public User() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getBan() {
        return ban;
    }

    public void setBan(Boolean ban) {
        this.ban = ban;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = id.toHexString();
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Double getExp() {
        return exp;
    }

    public void setExp(Double exp) {
        this.exp = exp;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ArrayList<Long> getFavoriteMid() {
        return favoriteMid;
    }

    public void setFavoriteMid(ArrayList<Long> favoriteMid) {
        this.favoriteMid = favoriteMid;
    }

    public ArrayList<Long> getFavoriteAid() {
        return favoriteAid;
    }

    public void setFavoriteAid(ArrayList<Long> favoriteAid) {
        this.favoriteAid = favoriteAid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
