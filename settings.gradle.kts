/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

pluginManagement{
    repositories{
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        maven { url = uri("https://mapbox.bintray.com/mapbox") }
    }
}

include(":maps")
include(":maps-google")
include(":maps-mapbox")
//include(":sample:android-app")
include(":sample:mpp-library")
