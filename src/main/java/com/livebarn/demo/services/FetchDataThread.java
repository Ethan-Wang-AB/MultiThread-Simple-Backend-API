/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livebarn.demo.services;

import com.livebarn.demo.domains.Post_t;
import com.livebarn.demo.domains.Posts;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;

/**
 *
 * @author 845593
 */
public class FetchDataThread implements Runnable{
    
        private static HashMap<Integer,Post_t> map=new HashMap<>();
        private FetchData fetchData;
        private String tag;
        
        
        
        public FetchDataThread(String tag){
        this.tag=tag;
        }

    @Override
    public void run() {
            try {
             System.out.println("Thread run() is playing "+tag);

                Posts post=FetchData.fetchData(tag);
                putIntoMap(post);
                
            } catch (SSLException ex) {
                Logger.getLogger(FetchDataThread.class.getName()).log(Level.SEVERE, null, ex);
            }


    }
    
      public  static synchronized void putIntoMap(Posts posts) {
        for(Post_t p:posts.getPosts()){
         if(!map.containsKey(p.getId())){
         map.put(p.getId(), p);
         }
            
            
        }
        
        
    }
      
      public static  HashMap<Integer,Post_t>  getMap(){
      
          
          return map;
      }
    
}
