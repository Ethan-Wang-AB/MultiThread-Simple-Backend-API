/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livebarn.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.livebarn.demo.FetchDataTest.mockBackEnd;
import com.livebarn.demo.domains.Post_t;
import com.livebarn.demo.domains.Posts;
import com.livebarn.demo.services.FetchData;
import java.io.IOException;
import javax.net.ssl.SSLException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

/**
 *
 * @author 845593
 */
@ExtendWith(MockitoExtension.class)
public class FetchDataTest {
    
    @Autowired
    private ObjectMapper objectMapper=new ObjectMapper();

    public static MockWebServer mockBackEnd;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s",
                mockBackEnd.getPort());
       FetchData.setUrl(baseUrl);
       
    }

    @Test
    void fetchDataFromAPITest() throws SSLException, JsonProcessingException {
     
        Posts post = new Posts();
        Post_t[] posts = new Post_t[1];
        Post_t post_test = new Post_t();
        post_test.setAuthor("abc");
        post_test.setAuthorId(1);
        post_test.setLikes(123);
        post_test.setPopularity(456.56);
        post_test.setReads(789);
        post_test.setTags(new String[]{"tech", "science"});
        posts[0]=post_test;
        post.setPosts(posts);
         
  
        mockBackEnd.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(post)));
        Posts testResult=FetchData.fetchData("tech");
        assertEquals(1,testResult.getPosts()[0].getId());

    }

}
