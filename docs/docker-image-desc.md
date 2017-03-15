# Short Description
This API allows patients to preview the redacted clinical document based on their consent.

# Full Description

# Supported Source Code Tags and Current `Dockerfile` Link

[`1.17.0 (latest)`](https://github.com/bhits/try-policy-api/releases/tag/1.17.0), [`1.16.0`](https://github.com/bhits/try-policy-api/releases/tag/1.16.0), [`1.13.0`](https://github.com/bhits/try-policy-api/releases/tag/1.13.0)

[`Current Dockerfile`](https://github.com/bhits/try-policy-api/blob/master/tryPolicy/src/main/docker/Dockerfile)

For more information about this image, the source code, and its history, please see the [GitHub repository](https://github.com/bhits/try-policy-api).

# What is Try My Policy?

Try My Policy is a service that enables patients to preview the redacted version of their uploaded clinical document based on the privacy preferences of the consent. Try My Policy calls Document Segmentation Service API ([DSS](https://github.com/bhits/dss-api)) to (1) segment the patient's clinical document, in the template prescribed by C-CDA-R1, C-CDA-R2 and HITSP C32 and (2) highlight the sections that will be removed in accordance to the patient's consent. Try My Policy transforms the response from DSS into a full XHTML file and provides the Base 64 encoded file in the response JSON. This API is currently utilized in Patient Portal UI for patients to try their policies on their uploaded documents.

For more information and related downloads for Consent2Share, please visit [Consent2Share](https://bhits.github.io/consent2share/).
# How to use this image


## Start a Try My Policy instance

Be sure to familiarize yourself with the repository's [README.md](https://github.com/bhits/try-policy-api) file before starting the instance.

`docker run  --name try-policy -d bhits/try-policy:latest <additional program arguments>`

*NOTE: In order for this API to fully function as a microservice in the Consent2Share application, it is required to setup the dependency microservices and support level infrastructure. Please refer to the [Consent2Share Deployment Guide](https://github.com/bhits/consent2share/releases) in the corresponding Consent2Share release for instructions to setup the Consent2Share infrastructure.*

## Configure

The Spring profiles `application-default` and `docker` are activated by default when building images.

This API can run with the default configuration which is from three places: `bootstrap.yml`, `application.yml`, and the data which the [`Configuration Server`](https://github.com/bhits/config-server) reads from the `Configuration Data Git Repository`. Both `bootstrap.yml` and `application.yml` files are located in the class path of the running application.

We **recommend** overriding the configuration as needed in the `Configuration Data Git Repository`, which is used by the `Configuration Server`.

Also, [Spring Boot](https://projects.spring.io/spring-boot/) supports other ways to override the default configuration to configure the API for a certain deployment environment. 

The following is an example to override the default database password:

`docker run -d bhits/try-policy:latest --spring.datasource.password=strongpassword`

## Environment Variables

When you start the Try My Policy image, you can edit the configuration of the Try My Policy instance by passing one or more environment variables on the command line. 

### JAR_FILE

This environment variable is used to setup which jar file will run. you need mount the jar file to the root of container.

`docker run --name try-policy -e JAR_FILE="try-policy-latest.jar" -v "/path/on/dockerhost/try-policy-latest.jar:/try-policy-latest.jar" -d bhits/try-policy:latest`

### JAVA_OPTS 

This environment variable is used to setup JVM argument, such as memory configuration.

`docker run --name try-policy -e "JAVA_OPTS=-Xms512m -Xmx700m -Xss1m" -d bhits/try-policy:latest`

### DEFAULT_PROGRAM_ARGS 

This environment variable is used to setup an application argument. The default value of is "--spring.profiles.active=application-default, docker".

`docker run --name try-policy -e DEFAULT_PROGRAM_ARGS="--spring.profiles.active=application-default,ssl,docker" -d bhits/try-policy:latest`

# Supported Docker versions

This image is officially supported on Docker version 1.12.1.

Support for older versions (down to 1.6) is provided on a best-effort basis.

Please see the [Docker installation documentation](https://docs.docker.com/engine/installation/) for details on how to upgrade your Docker daemon.

# License

View [license](https://github.com/bhits/try-policy-api/blob/master/LICENSE) information for the software contained in this image.

# User Feedback

## Documentation 

Documentation for this image is stored in the [bhits/try-policy-api](https://github.com/bhits/try-policy-api) GitHub repository. Be sure to familiarize yourself with the repository's README.md file before attempting a pull request.

## Issues

If you have any problems with or questions about this image, please contact us through a [GitHub issue](https://github.com/bhits/try-policy-api/issues).