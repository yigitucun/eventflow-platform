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
    api(project(":core-lib"))
    implementation("org.springframework.boot:spring-boot-starter-web:4.0.0")
    implementation("org.springframework.boot:spring-boot-starter-aop:4.0.0-M2")

}

tasks.test {
    useJUnitPlatform()
}