plugins {
	java
	id("org.springframework.boot") version "3.5.8"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "io.yigitucun.eventflow"
version = "0.0.1-SNAPSHOT"
description = "Event Service"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2025.0.0"

dependencies {
	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-amqp")
		implementation("org.springframework.boot:spring-boot-starter-validation")
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.5")
		implementation("org.springframework.cloud:spring-cloud-starter-config")
		implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
		implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
		implementation(project(":mvc-lib"))
		developmentOnly("org.springframework.boot:spring-boot-devtools")
		runtimeOnly("org.postgresql:postgresql")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.5")
		testImplementation("org.springframework.amqp:spring-rabbit-test")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	}

	dependencyManagement {
		imports {
			mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}