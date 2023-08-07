/*
 * Copyright © 2023 Mark Raynsford <code@io7m.com> https://www.io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

plugins {
    id("org.jetbrains.kotlin.jvm").version("1.9.0").apply(false)
    id("maven-publish")
}

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        mavenCentral()
    }

    /*
     * Configure builds and tests for various project types.
     */

    when (extra["POM_PACKAGING"]) {
        "pom" -> {
            logger.info("Configuring $project $version as a pom project")
        }

        "jar" -> {
            logger.info("Configuring $project $version as a jar project")

            apply(plugin = "org.jetbrains.kotlin.jvm")
            apply(plugin = "maven-publish")

            /*
             * Configure Maven publishing. Artifacts are published to a local directory
             * so that they can be pushed to Maven Central in one step using brooklime.
             */

            val deployDirectory = "$rootDir/build/maven"
            tasks.named("clean") {
                doFirst {
                    delete(deployDirectory)
                }
            }

            fun property(name: String): String {
                return project.extra[name] as String
            }

            publishing {
                publications {
                    create<MavenPublication>("MavenPublication") {
                        groupId = property("GROUP")
                        artifactId = property("POM_ARTIFACT_ID")
                        version = property("VERSION_NAME")

                        pom {
                            name.set(property("POM_NAME"))
                            description.set(property("POM_DESCRIPTION"))
                            url.set(property("POM_URL"))
                            scm {
                                connection.set(property("POM_SCM_CONNECTION"))
                                developerConnection.set(property("POM_SCM_DEV_CONNECTION"))
                                url.set(property("POM_SCM_URL"))
                            }
                            licenses {
                                license {
                                    name.set(property("POM_LICENCE_NAME"))
                                    url.set(property("POM_LICENCE_URL"))
                                }
                            }
                        }
                        from(components["java"])
                    }
                }

                repositories {
                    maven {
                        url = uri(deployDirectory)
                    }
                }
            }

            /*
             * Configure JUnit tests.
             */

            tasks.named<Test>("test") {
                useJUnitPlatform()

                testLogging {
                    events("passed")
                }
            }
        }
    }
}
