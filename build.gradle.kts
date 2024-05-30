plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
}

tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation("org.openapitools:jackson-databind-nullable:0.2.6")

	implementation("org.mapstruct:mapstruct:1.6.0.Beta1")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.0.Beta1")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.security:spring-security-test:6.0.2")
	implementation("org.springframework.boot:spring-boot-configuration-processor")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server:3.1.0")

	implementation("io.jsonwebtoken:jjwt-api:0.11.2") // Add JJWT API dependency
	implementation("io.jsonwebtoken:jjwt-impl:0.11.2") // Add JJWT Implementation dependency
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.2")

	implementation("org.slf4j:slf4j-api:1.7.32")
	implementation("ch.qos.logback:logback-classic:1.2.6")
	//faker and instancio + assertj
	implementation("org.instancio:instancio-junit:3.6.0")
	implementation("net.javacrumbs.json-unit:json-unit-assertj:3.2.2")
	implementation("net.datafaker:datafaker:2.0.2")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
