package com.jannchie.biliob.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author jannchie
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@org.springframework.data.mongodb.core.mapping.Document(collection = "author_list")
public class AuthorGroup {
    @Id
    private String id;
    private List<Author> authorList = new ArrayList<>();
    private List<String> tagList = new ArrayList<>();
    private User creator;
    private User maintainer;
    private List<User> editors;
    private String desc;
    private String name;
    private List<UserStarAuthorGroup> starList;
    private Date createTime;
    private Date updateTime;
    private Date forkTime;
    private boolean stared;
    private Integer stars;
    private Integer authors;

    public AuthorGroup() {

    }

    public AuthorGroup(String name, String desc, List<String> tag, ObjectId userId) {
        this.name = name;
        creator = new User(userId);
        maintainer = creator;
        this.tagList = tag;
        this.desc = desc;
        this.updateTime = Calendar.getInstance().getTime();
        this.createTime = Calendar.getInstance().getTime();
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public boolean isStared() {
        return stared;
    }

    public void setStared(boolean b) {
        this.stared = b;
    }

    public User getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(User maintainer) {
        this.maintainer = maintainer;
    }

    public List<UserStarAuthorGroup> getStarList() {
        return starList;
    }

    public void setStarList(List<UserStarAuthorGroup> starList) {
        this.starList = starList;
    }

    public Date getForkTime() {
        return forkTime;
    }

    public void setForkTime(Date forkTime) {
        this.forkTime = forkTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getEditors() {
        return editors;
    }

    public void setEditors(List<User> editors) {
        this.editors = editors;
    }

    public Integer getAuthors() {
        return authors;
    }

    public void setAuthors(Integer authors) {
        this.authors = authors;
    }
}
