# fair-profile-poc
Proof of concept for a profile based FAIR Data Point implementation.

## Introduction
This is a proof of concept project to get a feel for the viability of a 'runtime' composition of metadata providing components. 'Runtime' in this case should be interpreted as 'application boot time', not 'an undetermined amount of time after the application boot'.

### [`spring-boot-starter`](spring-boot-starter)
Bootstrap and glue projects for running a FDP.

### [`example-app`](example-app)
Example application using the `fair-spring-boot-starter` to run a FAIR app.

## Quickstart
Prerequisites are a couple of external, non-published artifacts:
```
git clone -b develop https://github.com/kburger/rdf-transmog && cd rdf-transmog && mvn -q install && cd ..
git clone -b develop  https://github.com/kburger/rdf-kaleidoscope && cd rdf-kaleidoscope && mvn -q install && cd ..
git clone -b develop https://github.com/kburger/shape2java && cd shape2java && mvn -q install && cd ..
git clone -b develop https://github.com/kburger/shape2java-maven-plugin && cd shape2java-maven-plugin && mvn -q install -Dinvoker.skip=true && cd ..
git clone -b develop https://github.com/kburger/transmog-shape2java-plugin && cd transmog-shape2java-plugin && mvn -q install -Dinvoker.skip=true && cd ..
git clone -b develop https://github.com/kburger/request-url-resolver && cd request-url-resolver && mvn -q install && cd ..
```

Install the boot starter and autoconfigure projects locally:
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
