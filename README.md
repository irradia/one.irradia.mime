one.irradia.mime
===

[![Build Status](https://img.shields.io/github/workflow/status/irradia/one.irradia.mime/Main)](https://github.com/irradia/one.irradia.mime/actions?query=workflow%3Amain)
[![Maven Central](https://img.shields.io/maven-central/v/one.irradia.mime/one.irradia.mime.api.svg?style=flat-square)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22one.irradia.mime%22)
[![Maven Central (snapshot)](https://img.shields.io/nexus/s/https/oss.sonatype.org/one.irradia.mime/one.irradia.mime.api.svg?style=flat-square)](https://oss.sonatype.org/content/repositories/snapshots/one.irradia.mime/)
[![Codacy Badge](https://img.shields.io/codacy/grade/d0b7e91a88f640049bcaf706ae088d63.svg?style=flat-square)](https://www.codacy.com/app/github_79/one.irradia.mime?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=irradia/one.irradia.mime&amp;utm_campaign=Badge_Grade)
[![Codecov](https://img.shields.io/codecov/c/github/irradia/one.irradia.mime.svg?style=flat-square)](https://codecov.io/gh/irradia/one.irradia.mime)
[![Gitter](https://badges.gitter.im/irradia-org/community.svg)](https://gitter.im/irradia-org/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

![mime](./src/site/resources/mime.jpg?raw=true)

## Features

* Parse RFC 2045 MIME type strings
* Full positional/lexical information to pinpoint errors in data
* ISC license
* High coverage automated test suite
* [OSGi](http://www.osgi.org)-ready

## Building

Install the Android SDK. The package has no dependencies on the Android API
and is therefore usable in non-Android projects.

```
$ ./gradlew clean assemble check
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

## Semantic Versioning

All [irradia.one](https://www.irradia.one) packages obey [Semantic Versioning](https://www.semver.org)
once they reach version `1.0.0`.
