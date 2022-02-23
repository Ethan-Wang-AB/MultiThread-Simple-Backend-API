/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livebarn.demo.comparators;

import com.livebarn.demo.domains.Post_t;
import java.util.Comparator;
import org.springframework.stereotype.Component;

/**
 *
 * @author 845593
 */
@Component
public class LikesComparator implements Comparator<Post_t> {
         @Override
    public int compare(Post_t o1, Post_t o2) {
        if(o1.getLikes()<o2.getLikes()){
        return -1;
        }
        else if(o1.getLikes()>o2.getLikes()){
        return 1;
        }else return 0;
    }

    @Override
    public Comparator<Post_t> reversed() {
        return Comparator.super.reversed(); //To change body of generated methods, choose Tools | Templates.
    }
}
