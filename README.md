# Project Manager

## Overview

This repo is a Java Spring MVC project, a tool for teams amd project manager to mange their projects.

## Requirements

- Java (version>=17)
- JDK

## Setup

Maven is used to build this project.
Change Database configurations in src/main/resources/application.properties

```
spring.datasource.username=<YOUR_DB_USERNAME>
spring.datasource.password=<YOUR_DB_PASSWORD>
```

## How to run

- Run with maven : `./mvnw spring-boot:run`
- Visit `http://localhost:8080`

## Demo

Check out a mini demo [here](demo/the-project-manager-demo.webm)
