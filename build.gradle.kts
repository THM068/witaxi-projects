plugins {
    // Apply the groovy plugin to also add support for Groovy (needed for Spock)
    groovy

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}
allprojects {
    repositories {
        mavenCentral() // Shared repository configuration
    }
}


subprojects {

    repositories {
        mavenCentral()

    }
    dependencies {
        apply(plugin = "java")

        implementation("io.soabase.record-builder:record-builder-core:44")
        testImplementation("io.soabase.record-builder:record-builder-core:44")
        annotationProcessor("io.soabase.record-builder:record-builder-processor:44")


    }
}
