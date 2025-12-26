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
    implementation("org.springframework:spring-context:7.0.1")
    implementation("org.springframework:spring-web:7.0.1")
    implementation("com.auth0:java-jwt:4.5.0")
}



tasks.test {
    useJUnitPlatform()
}