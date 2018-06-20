plugins {
	java
}

group = "spd-meetup"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}
apply{
	plugin("java")
	plugin("idea")
	plugin("org.springframework.boot")
	plugin("io.spring.dependency-management")
}

dependencies {
	compile("org.springframework.boot:spring-boot-starter-web")
	compile("net.rakugakibox.spring.boot:orika-spring-boot-starter:1.6.0")
	compile("org.springframework.boot:spring-boot-starter-data-jpa")
	compile("com.h2database:h2")
	testCompile("junit:junit")
	testCompile("org.springframework.boot:spring-boot-starter-test")
}

configure<JavaPluginConvention> {
	sourceCompatibility = JavaVersion.VERSION_1_8
}

buildscript{
	dependencies{
		classpath("org.springframework.boot:spring-boot-gradle-plugin:2.0.3.RELEASE")
	}
}