group = "com.github.companiondelay"
version = "0.1.0"

plugins {
    `build-scan`
    kotlin("jvm") version "1.3.60"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib", "1.2.31"))
    testImplementation("junit:junit:4.12")
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"

    publishAlways()
}

tasks {
    clean {
        rootProject.buildDir
    }
}