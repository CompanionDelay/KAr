group = "com.github.companiondelay"
version = "0.1.1"

plugins {
    `build-scan`
    kotlin("jvm") version "1.3.61"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib", "1.3.61"))
    testImplementation("junit:junit:4.12")
}

tasks {
    clean {
        rootProject.buildDir
    }
}