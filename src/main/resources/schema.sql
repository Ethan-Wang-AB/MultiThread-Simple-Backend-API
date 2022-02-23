/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  845593
 * Created: Feb 18, 2022
 */

DROP TABLE IF EXISTS posts;
  


CREATE TABLE post (
  id INT  PRIMARY KEY,
  author VARCHAR(250) ,
  authorId INT ,
  likes INT ,
  popularity DOUBLE,
  reads INT
);

CREATE TABLE tag(
id  INT PRIMARY KEY,
tag VARCHAR(250) ,
postId INT,
FOREIGN KEY (`postId`)
  REFERENCES `post` (`id`)
);
