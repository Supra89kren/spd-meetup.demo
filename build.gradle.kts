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
	testCompile("junit:junit")
}

configure<JavaPluginConvention> {
	sourceCompatibility = JavaVersion.VERSION_1_8
}

buildscript{
	dependencies{
		classpath("org.springframework.boot:spring-boot-gradle-plugin:2.0.3.RELEASE")
	}
}