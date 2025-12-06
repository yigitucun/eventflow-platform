plugins {
    id("java-library")
}

group = "io.yigitucun.eventflow"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.security:spring-security-core:7.0.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.5.8")
}

tasks.test {
    useJUnitPlatform()
}