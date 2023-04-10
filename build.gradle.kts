/*
 *       ____           _       __    __     _____ __            ___
 *      / __ \_      __(_)___ _/ /_  / /_   / ___// /___  ______/ (_)___
 *     / / / / | /| / / / __ `/ __ \/ __/   \__ \/ __/ / / / __  / / __ \
 *    / /_/ /| |/ |/ / / /_/ / / / / /_    ___/ / /_/ /_/ / /_/ / / /_/ /
 *   /_____/ |__/|__/_/\__, /_/ /_/\__/   /____/\__/\__,_/\__,_/_/\____/
 *                    /____/
 *   Copyright (c) 2022-2023 Dwight Studio's Team <support@dwight-studio.fr>
 *
 *   This Source Code From is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at https://mozilla.org/MPL/2.0/ .
 *
 */

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
    implementation("de.gurkenlabs:litiengine:0.5.2")
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