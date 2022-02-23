### SPRING BACK-END ASSESSMENT - livebarn
#### Introduction
This project is used to generate collected non-duplicate collections of posts including different tags, by acquire json from a given url with only one tag allowed in url. 

#### Author and Development Environment
The author is Ethan Wang, who is 4th semester(last semester)  student at Southern Alberta Institute of Technology, majoring in Information Technology--Software Development
This assessment is done during the developing Capstone project, preparing mid-terms (five relevent courses in last semester), and preparing other assessments as request. 

The time for doing this assessment is short and limited.

Developing process is including learning new technique from internet and utilize them in this assessment.

In-memory H2 also added, and it would insert data to H2 database after every client request to the other api


#### Built With
Framework--Spring boot 
IDE--Netbeans

#### How to use
1. First Step
Using Java IDE and open the project, and run it.
2. Second Step
Use url: localhost:8080/api/ping    to test connectivity
3. Third Step
Use url: localhost:8080/api/posts?tags={tags}&sortBy={sortBy}&direction={direction} to acquire list from the given API.
4. Tip 1
{tags} is required, with the format like "science,tech", using comma to seperate different tags.
5. Tip 2
Default of sortBy is id or it must be a valid value in [id, popularity,likes,reads]. If not, it will give an error.
6. Tip 3
Default of direction is asc or it must be a valid value in [{]asc,desc]. If not, it will give an error.
