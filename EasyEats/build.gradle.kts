import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.8.22"
}

group = "com.sorsix.finalproject"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql:42.6.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.1.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.rest-assured:rest-assured:5.3.1")
    //implementation("org.hibernate:hibernate-core:1.5.30") // Replace with the desired Hibernate version
    //implementation("org.hibernate:hibernate-entitymanager:1.5.30") // Replace with the desired Hibernate version
}

//application {
//    // Application configuration
//    mainClassName = "your.main.class.package.MainClass" // Replace with your main class package and name
//}
//
//// Hibernate configuration
//configurations {
//    hibernateDialect {
//        description = "The Hibernate dialect for PostgreSQL 9.5"
//        resolutionStrategy {
//            eachDependency {
//                if (requested.group == "org.hibernate" && requested.name.startsWith("hibernate-core")) {
//                    useVersion("x.x.x") // Replace with the desired Hibernate version
//                }
//            }
//        }
//    }
//}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
