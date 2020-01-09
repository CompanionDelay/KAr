group = "com.github.companiondelay"
version = "0.1.4"

plugins {
    `java-library`
    kotlin("jvm") version "1.3.61"
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
    }
}