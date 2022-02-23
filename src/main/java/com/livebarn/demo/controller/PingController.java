/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livebarn.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livebarn.demo.domains.Success;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author 845593
 */
@Controller
public class PingController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Success success;
    @GetMapping("/api/ping")
    public void getPing(HttpServletResponse response) throws IOException{
    response.setStatus(200);
    response.getWriter().print(objectMapper.writeValueAsString(success));
    }
    
}
