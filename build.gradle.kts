group = "com.github.companiondelay"
version = "0.1.5"

plugins {
    `java-library`
    id("java")
    kotlin("jvm") version "1.3.61"
    `maven-publish`
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testImplementation("junit:junit:4.12")
}

tasks {
    clean {
        rootProject.buildDir
    }
    test {
        testLogging.showExceptions = true
        useJUnit()
        maxHeapSize = "1G"
    }
}