/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livebarn.demo.services;

import com.livebarn.demo.comparators.IdComparator;
import com.livebarn.demo.comparators.LikesComparator;
import com.livebarn.demo.comparators.PopularityComparator;
import com.livebarn.demo.comparators.ReadsComparator;
import com.livebarn.demo.domains.Post_t;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLException;


/**
 *
 * @author 845593
 */

public class FetchDataPoolService {

    private final static int MAX_T = 5;
    private static HashMap<Integer, Post_t> map;


    private static final IdComparator IDCOMPARATOR=new IdComparator();


    private static final ReadsComparator READSCOMPARATOR=new ReadsComparator();

    
    private static final LikesComparator LIKESCOMPARATOR=new LikesComparator();

   private static final PopularityComparator POPULARITYCOMPARATOR=new PopularityComparator();

    public static HashMap<Integer, Post_t> fetchData(String tags) throws SSLException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        
        
        if (tags.contains(",")) {
            System.out.println("pool is started " + Calendar.getInstance().getTime());
      
       
            String tag[] = tags.split(",");
            List<Runnable> list = new ArrayList<>();
            for (String s : tag) {
                list.add(new FetchDataThread(s));
            }

            list.forEach((r) -> {
                pool.execute(r);
            });
           pool.shutdown();
   
           while (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
			
		}
           return FetchDataThread.getMap();
      
        } else {
            FetchData.fetchData(tags);

            FetchDataThread.putIntoMap(FetchData.fetchData(tags));

        }
        
   
        
        return FetchDataThread.getMap();
          
    }

    public static Post_t[] getResults(String tags, String sortBy, String direction) throws SSLException, InterruptedException {
        map = fetchData(tags);
        Collection values = map.values();
        //map.clear();
        Post_t[] array = new Post_t[map.size()];
        values.toArray(array);
        System.out.println("Array length "+array.length);
        if (sortBy != null && sortBy.trim().equals("") || sortBy.trim().equals("id")) {
            if (direction == null || direction.trim().equals("") || direction.trim().equals("asc")) {
                Arrays.sort(array, IDCOMPARATOR);

            } else {
                Arrays.sort(array, IDCOMPARATOR.reversed());
            }
            return array;

        }else if(sortBy.trim().equals("reads")){
             if (direction == null || direction.trim().equals("") || direction.trim().equals("asc")) {
                Arrays.sort(array, READSCOMPARATOR);
                
            } else {
                Arrays.sort(array, READSCOMPARATOR.reversed());
            }
             return array;
        
        }else if(sortBy.trim().equals("likes")){
             if (direction == null || direction.trim().equals("") || direction.trim().equals("asc")) {
                Arrays.sort(array, LIKESCOMPARATOR);
                
            } else {
                Arrays.sort(array, LIKESCOMPARATOR.reversed());
            }
             return array;
        
        }else{
                if (direction == null || direction.trim().equals("") || direction.trim().equals("asc")) {
                Arrays.sort(array, POPULARITYCOMPARATOR);
                
            } else {
                Arrays.sort(array, POPULARITYCOMPARATOR.reversed());
            }
           
             return array;
        
        }
        

    }
    
    public static void clearmap(){
    map.clear();
    }

}
