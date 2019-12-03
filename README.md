# lastminute
Technical Test for Lastminute

# Solution

For this test I have decided to use the following technologies:

| Technology            | Version         | 
| :-------------------- |:---------------:|
| Java                  | 11              |
| SpringBoot            | 2.1.8.RELEASE   | 
| Maven                 | 3.6.2           |
| Git                   | 2.21.0          |
| JUnit                 | 5.3.1           |


And I have used the following tools for application development: 
 

| Tool            | Version         | 
| :-------------------- |:---------------:|
| Spring Tool Suite     | 4               | 
| Git-GUI               | 2.21.0          | 

# Considerations

- I have considered that the input is a String.
- I decided to print the Receipt in the System.out. Before printing I created an Object that contents all the information for the printing. 
- Every functionality has been tested, every Service and the Controller.
- I have used a property file as a source for getting information and parameters for this solution. It could be used another source like xml, data base , etc.
- For the development of the application I have followed the `SOLID` principles to be able to create an efficient code..
- I have created a branch named 'develop' in the project where I have been uploading the advances until I reach the final version that has been integrated with the 'master' branch in the finalization of the development.
- I have kept the three stages: build, docker and acceptance. 

## .travis.yml
```.travis.yml

language: java
os:
    - linux
jdk:
    - openjdk11 
branches:
  only:
    - master
    - develop
script: 
    - mvn install -DskipTests=true -Dmaven.javadoc.skip=true -B -V
    - mvn test -B

```