/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livebarn.demo.domains;

import java.io.Serializable;

/**
 *
 * @author 845593
 */
public class Posts implements Serializable{
    private Post_t[] posts;


    public Post_t[] getPosts() {
        return posts;
    }

    public void setPosts(Post_t[] posts) {
        this.posts = posts;
    }
    
}
