/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livebarn.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.livebarn.demo.domains.Post_t;
import com.livebarn.demo.services.FetchDataPoolService;



import java.io.IOException;



import java.util.HashMap;
import javax.net.ssl.SSLException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.livebarn.demo.domains.Error;
import com.livebarn.demo.domains.Posts;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author 845593
 */
@Controller
public class PostsController {
  
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/api/posts")
    public void getPosts(@RequestParam(value = "tags", required = false) String tags, @RequestParam(value = "sortBy", required = false) String sortBy, @RequestParam(value = "direction", required = false) String direction, Model model,
         HttpServletResponse response) throws IOException, SSLException, InterruptedException {
        HashMap<Integer, Post_t> map;
        if (tags == null || tags.trim().equals("")) {
            response.setStatus(400);
            response.getWriter().print(objectMapper.writeValueAsString(new Error("Tags parameter is required")));
            return;
        } else if (direction != null && !direction.equals("asc") && !direction.equals("desc")) {
            response.setStatus(400);
            response.getWriter().print(objectMapper.writeValueAsString(new Error("direction parameter is invalid")));
            return;
        } else if (sortBy != null && !sortBy.equals("id") && !sortBy.equals("reads") && !sortBy.equals("likes") && !sortBy.equals("popularity")) {
            response.setStatus(400);
            response.getWriter().print(objectMapper.writeValueAsString(new Error("sortBy parameter is invalid")));
            return;
        } else {

        
            if(sortBy==null || sortBy.trim().equals("")){
            sortBy="";
            }
             if(direction==null || direction.trim().equals("")){
            sortBy="";
            }
            Posts post=new Posts();
            post.setPosts(FetchDataPoolService.getResults(tags, sortBy, direction));
            response.setStatus(200);
            response.getWriter().println(objectMapper.writeValueAsString(post));
            FetchDataPoolService.clearmap();
            return;

        }

    }
}
