group = "com.github.companiondelay"
version = "0.1.2"

plugins {
    kotlin("jvm") version "1.3.61"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit:junit:4.12")
}

tasks {
    clean {
        rootProject.buildDir
    }
}