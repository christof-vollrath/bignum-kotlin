plugins {
    kotlin("jvm") version "2.1.20"
}

group = "net.taobits"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin Standard Library
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Kotest (Test Framework)
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0") // Test runner
    testImplementation("io.kotest:kotest-assertions-core:5.8.0") // Assertions
    testImplementation("io.kotest:kotest-property:5.8.0") // Property-based testing
    testImplementation("io.kotest:kotest-framework-datatest:5.8.0") // Data-driven testing (withData)

    // JUnit 5 Platform (required for Gradle to run tests)
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}