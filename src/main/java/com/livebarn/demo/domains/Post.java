/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livebarn.demo.domains;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author 845593
 */
@Entity
@Table(name = "post")
public class Post {
    @Column(name = "author")
    private String author;
    @Column(name = "authorId")
    private Integer authorId;
    @Id
    private Integer id;
    @Column(name = "likes")
    private Integer likes;
    @Column(name = "popularity")
    private Double popularity;
    @Column(name = "reads")
    private Integer reads;
    @OneToMany(mappedBy = "tag")
    private List<Tag> tags;

    public Post(String author, Integer authorId, Integer id, Integer likes, Double popularity, Integer reads, List<Tag> tags) {
        this.author = author;
        this.authorId = authorId;
        this.id = id;
        this.likes = likes;
        this.popularity = popularity;
        this.reads = reads;
        this.tags = tags;
    }

    public Post() {
super();    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getReads() {
        return reads;
    }

    public void setReads(Integer reads) {
        this.reads = reads;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
