# Adesso-task 
___

* After you start project, you can use swagger-ui to test end-point.

## Swagger UI
```shell script
localhost:8080/swagger-ui
```

## Example Usage of Swagger

Use Country Codes Alpha-2 format for country input. https://earthquake.usgs.gov/fdsnws/event/1/ api returns locations of earthquakes according to following parameters: 
 - latitude & longitude & maxradius/ maxradiuskm.

Thus, I define countries in Enums like: https://developers.google.com/public-data/docs/canonical/countries_csv.

 - Default value of 'maxradius' is '5'.(in src/main/java/com.adessotask/service/EarthquakeService). To distinct "No Earthquakes were recorded past {x} days" message more easily.


#### Example  GET /earthquakes values:
```shell script
country: "US"
days: "10"
```
___
## Running the JVM docker image:

```shell script
 ./mvnw package
```
 Then, build the image with:

```shell script
 docker build -f src/main/docker/Dockerfile.jvm -t quarkus/task-jvm .
```

 Then run the container using:

```shell script
 docker run -i --rm -p 8080:8080 quarkus/task-jvm
```

## Running the application in dev mode
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory. Be aware that it’s not an _über-jar_ as
the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/adesso-task-1.0-SNAPSHOT-runner`
