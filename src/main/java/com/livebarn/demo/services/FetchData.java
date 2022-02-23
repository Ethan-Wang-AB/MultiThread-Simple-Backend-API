/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livebarn.demo.services;



import com.livebarn.demo.dao.PostDao;
import com.livebarn.demo.dao.TagDao;
import com.livebarn.demo.domains.Post;
import com.livebarn.demo.domains.Posts;
import com.livebarn.demo.domains.Success;
import com.livebarn.demo.domains.Tag;

import exceptions.FailedLinkException;
import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.SSLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheConfig;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.resources.LoopResources;


/**
 *
 * @author 845593
 */
@Component
@CacheConfig(cacheNames = "livebarnCache")
public class FetchData{
  private static String url = "api.hatchways.io/assessment/blog/";
  @Autowired
  private PostDao postDao;
  @Autowired
  private TagDao tagDao;

    public static void setUrl(String url) {
        FetchData.url = url;
    }
    private static String ping = "ping";
    private static String post0 = url + "posts/";
    private static Map<String, WebClient> webClientMap = new ConcurrentHashMap<String, WebClient>();

    private static HttpClient httpClient;
    
  
    public static Success testLink() throws FailedLinkException {
        try {

            httpClient=createHttpClient();
            Success success = WebClient.builder()
                    .clientConnector(new ReactorClientHttpConnector(httpClient))
                    .build().get().uri(url + ping).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Success.class).block();
            return success;
        } catch (Exception e) {
            throw new FailedLinkException();
        }

    }
    @Cacheable
    public static Posts fetchData(String tags) throws SSLException {
        
               System.out.println("Fetch data is implementing "+tags);

         httpClient=createHttpClient();
         
        try {

            Posts posts = WebClient.builder()
                    .clientConnector(new ReactorClientHttpConnector(httpClient))
                    .build().get().uri(uriBuilder
                            -> uriBuilder.path(post0).queryParam("tag", tags).build()).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Posts.class).block();
            
        //    System.out.println("Fetch data is implementing "+posts.getPosts().length+"  "+posts.getPosts()[0].getAuthor());

            return posts;
        } catch (Exception e) {
            e.printStackTrace();
            WebClient.builder()
                    .clientConnector(new ReactorClientHttpConnector(httpClient))
                    .build()
                    .post()
                    .uri(uriBuilder
                            -> uriBuilder.path(post0).queryParam("tag", tags).build()).accept(MediaType.APPLICATION_JSON).retrieve()
                    .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals, response -> response.bodyToMono(String.class).map(Exception::new)).bodyToMono(Error.class);
        }
        return null;
    }

     public static HttpClient createHttpClient() throws SSLException {
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
            ConnectionProvider provider = ConnectionProvider.builder("wc-").maxConnections(30)
                .maxIdleTime(Duration.ofSeconds(30)).maxLifeTime(Duration.ofSeconds(30))
                .pendingAcquireTimeout(Duration.ofSeconds(30)).build();
                    LoopResources loop = LoopResources.create("loop-", 20, 20, true);

        HttpClient httpClient = HttpClient.create(provider).secure(sslSpec -> sslSpec.sslContext(sslContext)).runOn(loop).
                option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000).doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(30)).addHandlerLast(new WriteTimeoutHandler(20)));
        

        return httpClient;
    }
     
     public void insert(Posts posts) {
        System.out.println("Start insertion");
         Post post = new Post();
         String tags[];
         Tag tag=new Tag();
        for (int i = 0; i < posts.getPosts().length; i++) {
           try{ 
            post.setAuthor(posts.getPosts()[i].getAuthor());
            post.setAuthorId(posts.getPosts()[i].getAuthorId());
            post.setLikes(posts.getPosts()[i].getLikes());
            post.setPopularity(posts.getPosts()[i].getPopularity());
            post.setReads(posts.getPosts()[i].getReads());
            post.setId(posts.getPosts()[i].getId());
            
               System.out.println("Posts check tags"+posts.getPosts()[i].getTags());                     
            postDao.save(post);
             System.out.println("Posts after save "+post.getReads());
            
            for(String t:posts.getPosts()[i].getTags()){
                
                tag.setPostId(post.getId());
                tag.setTag(t);
                tagDao.save(tag);
                 System.out.println("Tags after save"+ tag.getTag());

            
                
            }
            
            
           }catch(Exception e){
            e.printStackTrace();
            }
        }
     }

}

