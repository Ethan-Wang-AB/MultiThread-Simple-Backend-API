/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livebarn.demo.domains;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author 845593
 */
public class Post_t implements Serializable{
    private String author;
    private Integer authorId;
    private Integer id;
    private Integer likes;
    private Double popularity;
    private Integer reads;
    private String tags[];

    public String[] getTags() {
        return tags;
    }

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

    public String getTag() {
        String ss="["+tags[0];
         for(int i=1;i<tags.length;i++){
         ss=ss+","+tags[i];
         }       
                ss=ss+"]";
        return ss;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    
    

}
