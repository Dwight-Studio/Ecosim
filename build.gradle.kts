plugins {
    id("java")
}

group = "fr.dwightstudio.ecosim"
version = "0.1-ALPHA"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("de.gurkenlabs:litiengine:0.5.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Copy>("natives") {
    for (dep in configurations.runtimeClasspath.get().files) {
        from(zipTree(dep).files)
        include("**/*.dll", "**/*.so", "**/*.jnilib", "**/*.dylib")
        into(File(buildDir, "libs"))
    }
}

tasks.named("build") {
    dependsOn("natives")
}