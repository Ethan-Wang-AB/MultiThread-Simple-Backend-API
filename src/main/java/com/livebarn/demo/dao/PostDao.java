/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livebarn.demo.dao;

import com.livebarn.demo.domains.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 845593
 */

@Repository("postDao")
    public interface PostDao extends JpaRepository<Post,Integer>{
    
    
    
}

