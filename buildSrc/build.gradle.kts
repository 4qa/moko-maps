/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("org.jetbrains.kotlin.jvm") version("1.4.31")
}

repositories {
    mavenCentral()
    google()
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    maven { url = uri("https://mapbox.bintray.com/mapbox") }

    jcenter {
        content {
            includeGroup("org.jetbrains.trove4j")
        }
    }
}

dependencies {
    implementation("dev.icerock:mobile-multiplatform:0.9.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30-dev-2689")
    implementation("com.android.tools.build:gradle:4.1.1")
}
