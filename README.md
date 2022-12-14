# jaxrs-wordsoup-game
# Containerized Java REST Microservice alphabetSoup

This project is a Word Soup minigame, deployed as a a REST service on the Java Enterprise Edition platform.

The end result is a container that runs a REST service running on an web server. The project builds a [Docker](https://www.docker.com) container that runs a [JAX-RS](https://jcp.org/en/jsr/detail?id=339) [RESTful](https://en.wikipedia.org/wiki/Representational_state_transfer) web service using the [Tomcat 9](http://tomcat.apache.org/) web server and a [Java 8 JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html).

## Build

To build the project, you will need the [Maven](https://maven.apache.org/) build tool. [Docker](https://www.docker.com/) should be already running. Then run:

    mvn clean install             # create the "fat jar"
    docker build -t rest-soup .   # put fat jar into container

## Run/Usage

To run the container with the service exposed on `http://localhost:8080`,

    docker run -p 8080:8080 rest-soup

Then visit `http://localhost:8080/jaxrsdemo/alphabetSoup/hello` to get a trivial Hello, World message.

## Description

This engine randomly generates a soup of letters, with the possibility that these words are located horizontally, vertically and diagonally left to right or right to left, top to bottom or from bottom to top. Another parameter to take into account in the generation is that the word soup can be of different sizes in both width and height without the need for the itself is square, these two values ​​(width and height) cannot be less than 15 letters and greater than 80.

There are a total of four service endpoints:

```POST http://localhost:8080/jaxrsdemo/alphabetSoup/``` - create and save a new puzzle. The input is a simple JSON formatted object:
```json
{
    "w":15,
    "h":15,
    "ltr":true,
    "rtl":false,
    "ttb":true,
    "btt":false,
    "d":false
}
```
This creates a alphabetSoup and return a json that indicates the autogenerated id for the soup that was just created in UUID format:
```json
{
    "id":"8b3ab6e9-95d1-4500-a523-4c4c3c4d079f"
}
```
In case of error:
```json
{
    "message":"Error message"
}
```

```GET http://localhost:8080/jaxrsdemo/alphabetSoup/list/{id}``` - return the list of words in the alphabet soup in json format:

```json
["amor","cartera","mano","bufanda","ciudad","pomelo","esfera","parlante","hoja","casco"]
```

```GET http://localhost:8080/jaxrsdemo/alphabetSoup/view/{id}``` - return the current state of wordSoup in plain text format:

```text
i p b c a n c p q n t b s c l 
B U F A N D A j a m o r x m o 
v g j m j q i r p d x d m b b 
o e m r w m u k o q s C k v H 
y P A R L A N T E e o I w z O 
f q M A N O n x r i k U s t J 
w y r l g k j w p g h D j i A 
h h q E S F E R A h b A i k r 
m x g r s a f k j q m D p j p 
g d d i e k z i P O M E L O z 
e z c t p k C A R T E R A l d 
C A S C O k m r f q h w l d q 
h y o l u w y v g q z i z f c 
k p g v p v y i e u x i s w r 
k c o b m p i m f u v q q c h
```

Note that words already found("cartera", "mano", "bufanda", "ciudad", "pomelo" "esfera", "parlante", "hoja", "casco") are capitalized.

```PUT http://localhost:8080/jaxrsdemo/alphabetSoup/{id}``` - return a json with a message indicating if the found word is correct or not and modify the state of the wordsearch.

Receives a json with the initial and final position of the word (indices start at 0). For example, to find the word "amor" in the previous soup, we pass the following json:

```json
{
    "sr":1,
    "sc":8,
    "er":1,
    "ec":11
}
```
Then return a json with a message indicating if the found word is correct.
```json
{
    "message":"Correcta"
}
```
