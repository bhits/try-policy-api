# Short Description
This API allows patients to preview the redacted clinical document based on their consent.

# Full Description

# Supported Tags and Respective `Dockerfile` Links

[`1.16.0`](https://github.com/bhits/try-policy-api/blob/master/tryPolicy/src/main/docker/Dockerfile),[`latest`](https://github.com/bhits/try-policy-api/blob/master/tryPolicy/src/main/docker/Dockerfile)[(1.16.0/Dockerfile)](https://github.com/bhits/try-policy-api/blob/master/tryPolicy/src/main/docker/Dockerfile)

For more information about this image, the source code, and its history, please see the [GitHub repository](https://github.com/bhits/try-policy-api).

# What is Try My Policy?

Try My Policy is a service that enables patients to preview the redacted version of their uploaded clinical document based on the privacy preferences of the consent. Try My Policy calls Document Segmentation Service API ([DSS](https://github.com/bhits/dss-api)) to (1) segment the patient's clinical document, in the template prescribed by C-CDA-R1, C-CDA-R2 and HITSP C32 and (2) highlight the sections that will be removed in accordance to the patient's consent. Try My Policy transforms the response from DSS into a full XHTML file and provides the Base 64 encoded file in the response JSON. This API is currently utilized in Patient Portal UI for patients to try their policies on their uploaded documents.

For more information and related downloads for Consent2Share, please visit [Consent2Share](https://bhits.github.io/consent2share/).
# How to use this image


## Start a Try My Policy instance

Be sure to familiarize yourself with the repository's [README.md](https://github.com/bhits/try-policy-api) file before starting the instance.

`docker run  --name try-policy -d bhits/try-policy:latest <additional program arguments>`

*NOTE: In order for this API to fully function as a microservice in the Consent2Share application, it is required to setup the dependency microservices and support level infrastructure. Please refer to the [Consent2Share Deployment Guide]() for instructions to setup the Consent2Share infrastructure.*


## Configure

This API runs with a [default configuration](https://github.com/bhits/try-policy-api/blob/master/tryPolicy/src/main/resources/application.yml) that is primarily targeted for the development environment.  The Spring profile `docker` is actived by default when building images. [Spring Boot](https://projects.spring.io/spring-boot/) supports several methods to override the default configuration to configure the API for a certain deployment environment. 

Here is example to override default database password:

`docker run -d bhits/try-policy:latest --spring.datasource.password=strongpassword`

## Using a custom configuration file

To use custom `application.yml`, mount the file to the docker host and set the environment variable `spring.config.location`.

`docker run -v "/path/on/dockerhost/C2S_PROPS/tryPolicy/application.yml:/java/C2S_PROPS/tryPolicy/application.yml" -d bhits/try-policy:tag --spring.config.location="file:/java/C2S_PROPS/tryPolicy/"`

## Environment Variables

When you start the Try My Policy image, you can edit the configuration of the Try My Policy instance by passing one or more environment variables on the command line. 

### JAR_FILE

This environment variable is used to setup which jar file will run. you need mount the jar file to the root of container.

`docker run --name try-policy -e JAR_FILE="try-policy-latest.jar" -v "/path/on/dockerhost/try-policy-latest.jar:/try-policy-latest.jar" -d bhits/try-policy:latest`

### JAVA_OPTS 

This environment variable is used to setup JVM argument, such as memory configuration.

`docker run --name try-policy -e "JAVA_OPTS=-Xms512m -Xmx700m -Xss1m" -d bhits/try-policy:latest`

### DEFAULT_PROGRAM_ARGS 

This environment variable is used to setup application arugument. The default value of is "--spring.profiles.active=docker".

`docker run --name try-policy -e DEFAULT_PROGRAM_ARGS="--spring.profiles.active=ssl,docker" -d bhits/try-policy:latest`

# Supported Docker versions

This image is officially supported on Docker version 1.12.1.

Support for older versions (down to 1.6) is provided on a best-effort basis.

Please see the [Docker installation documentation](https://docs.docker.com/engine/installation/) for details on how to upgrade your Docker daemon.

# License

View [license]() information for the software contained in this image.

# User Feedback

## Documentation 

Documentation for this image is stored in the [bhits/try-policy-api](https://github.com/bhits/try-policy-api) GitHub repository. Be sure to familiarize yourself with the repository's README.md file before attempting a pull request.

## Issues

If you have any problems with or questions about this image, please contact us through a [GitHub issue](https://github.com/bhits/try-policy-api/issues).