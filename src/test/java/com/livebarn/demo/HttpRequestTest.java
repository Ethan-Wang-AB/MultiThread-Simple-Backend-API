/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livebarn.demo;

/**
 *
 * @author 845593
 */
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/ping",
                String.class)).contains("success");

    }

    @Test
    public void shouldReturnErrorMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/posts",
                String.class)).contains("error");

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/posts?direction=asc",
                String.class)).contains("error");

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/posts?&sort=123",
                String.class)).contains("error");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/posts?tags=tech&direction=sc",
                String.class)).contains("error");

    }

}
