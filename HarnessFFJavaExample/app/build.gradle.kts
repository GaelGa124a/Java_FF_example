/*
 * Configuración del proyecto para integrar Harness Feature Flags
 */

plugins {
    // Aplicar plugins necesarios
    application
}

repositories {
    // Usar Maven Central para resolver dependencias
    mavenCentral()
}

dependencies {
    // Harness Feature Flags SDK
    implementation("io.harness:ff-java-server-sdk:1.8.0")

    // Logging dependencies compatibles con Java 8
    implementation("ch.qos.logback:logback-classic:1.2.11") // Compatible con Java 8
    implementation("org.apache.logging.log4j:log4j-api:2.13.3")
    implementation("org.apache.logging.log4j:log4j-core:2.13.3")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.13.3")

    // Dependencias de prueba (JUnit)
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

application {
    mainClass.set("io.harness.ff.examples.GettingStarted")

    // Pasar explícitamente el valor de FF_API_KEY
    applicationDefaultJvmArgs = listOf("-DFF_API_KEY=${System.getenv("FF_API_KEY")}")
}


tasks.named<Test>("test") {
    // Usar JUnit Platform para las pruebas
    useJUnitPlatform()
}
