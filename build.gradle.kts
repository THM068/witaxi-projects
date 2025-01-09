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

//plugins {
//    id("groovy")
//    id("com.github.johnrengelman.shadow") version "8.1.1"
//    id("io.micronaut.application") version "4.4.4"
//    id("io.micronaut.aot") version "4.4.4"
//}
//
//version = "0.1"
//group = "witaxi"
//
//repositories {
//    mavenCentral()
//}
//
//dependencies {
//    annotationProcessor("io.micronaut:micronaut-http-validation")
//    annotationProcessor("io.micronaut.micrometer:micronaut-micrometer-annotation")
//    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
//    annotationProcessor("io.micronaut.servlet:micronaut-servlet-processor")
//    implementation("io.micronaut:micronaut-management")
//    implementation("io.micronaut.micrometer:micronaut-micrometer-core")
//    implementation("io.micronaut.serde:micronaut-serde-jackson")
//    compileOnly("io.micronaut:micronaut-http-client")
//    runtimeOnly("ch.qos.logback:logback-classic")
//    testImplementation("io.micronaut:micronaut-http-client")
//
////    implementation(project(":witaxi-api-projects"))
//}
//
//
//application {
//    mainClass = "witaxi.Application"
//}
//java {
//    sourceCompatibility = JavaVersion.toVersion("21")
//    targetCompatibility = JavaVersion.toVersion("21")
//}
//
//
//graalvmNative.toolchainDetection = false
//
//micronaut {
//    runtime("tomcat")
//    testRuntime("spock2")
//    processing {
//        incremental(true)
//        annotations("witaxi.*")
//    }
//    aot {
//        // Please review carefully the optimizations enabled below
//        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
//        optimizeServiceLoading = false
//        convertYamlToJava = false
//        precomputeOperations = true
//        cacheEnvironment = true
//        optimizeClassLoading = true
//        deduceEnvironment = true
//        optimizeNetty = true
//        replaceLogbackXml = true
//    }
//}
//
//
//tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
//    jdkVersion = "21"
//}
//
//
