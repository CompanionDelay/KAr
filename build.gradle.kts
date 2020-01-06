group = "com.github.companiondelay"
version = "0.1.0"

plugins {
    `build-scan`
    kotlin("jvm") version "1.3.61"
}

repositories {
    jcenter()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib", "1.3.61"))
    testImplementation("junit:junit:4.12")
    implementation("com.github.CompanionDelay:KAr:$version")
}

tasks {
    clean {
        rootProject.buildDir
    }
}