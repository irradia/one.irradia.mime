one.irradia.mime
===

![Travis (.org)](https://img.shields.io/travis/irradia/one.irradia.mime.svg?style=flat-square)
![Maven Central](https://img.shields.io/maven-central/v/one.irradia.mime/one.irradia.mime.svg?style=flat-square)
![Codacy grade](https://img.shields.io/codacy/grade/d0b7e91a88f640049bcaf706ae088d63.svg?style=flat-square)

![mime](./src/site/resources/mime.jpg?raw=true)

## Building

Install the Android SDK.

```
$ ./gradlew clean assembleDebug test
```

If the above fails, it's a bug. Report it!

## Using

Use the following Maven or Gradle dependencies, replacing `${LATEST_VERSION_HERE}` with
whatever is the latest version published to Maven Central:

```
<!-- API -->
<dependency>
  <groupId>one.irradia.mime</groupId>
  <artifactId>one.irradia.mime.api</artifactId>
  <version>${LATEST_VERSION_HERE}</version>
</dependency>

<!-- Default implementation -->
<dependency>
  <groupId>one.irradia.mime</groupId>
  <artifactId>one.irradia.mime.vanilla</artifactId>
  <version>${LATEST_VERSION_HERE}</version>
</dependency>
```

```
repositories {
  mavenCentral()
}

implementation "one.irradia.mime:one.irradia.mime.api:${LATEST_VERSION_HERE}"
implementation "one.irradia.mime:one.irradia.mime.vanilla:${LATEST_VERSION_HERE}"
```

Library code is encouraged to depend only upon the API package in order to give consumers
the freedom to use other implementations of the API if desired.

## Publishing Releases

Releases are published to Maven Central with the following invocation:

```
$ ./gradlew clean assembleDebug publish closeAndReleaseRepository
```

Consult the documentation for the [Gradle Signing plugin](https://docs.gradle.org/current/userguide/signing_plugin.html)
and the [Gradle Nexus staging plugin](https://github.com/Codearte/gradle-nexus-staging-plugin/) for
details on what needs to go into your `~/.gradle/gradle.properties` file to do the appropriate
PGP signing of artifacts and uploads to Maven Central.

## Semantic Versioning

All [irradia.one](https://www.irradia.one) packages obey [Semantic Versioning](https://www.semver.org)
once they reach version `1.0.0`.
