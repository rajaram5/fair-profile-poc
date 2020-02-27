# fair-profile-poc
Proof of concept for a profile based FAIR Data Point implementation.

## Introduction
This is a proof of concept project to get a feel for the viability of a 'runtime' composition of metadata providing components. 'Runtime' in this case should be interpreted as 'application boot time', not 'an undetermined amount of time after the application boot'.

### [`spring-boot-starter`](spring-boot-starter)
Bootstrap and glue projects for running a FDP.

### [`example-app`](example-app)
Example application using the `fair-spring-boot-starter` to run a FAIR app.

## Quickstart
First install the boot starter and autoconfigure projects locally:
```
cd spring-boot-starter
mvn install
```

Then run the example application:
```
cd ../example-app
mvn spring-boot:run
```

The example app is now running at [http://localhost:8080/fdp](http://localhost:8080/fdp).
