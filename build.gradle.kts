plugins {
    // Apply the groovy plugin to also add support for Groovy (needed for Spock)
    groovy

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

repositories {
    mavenCentral()
}

subprojects {

    repositories {
        mavenCentral()

    }
}
