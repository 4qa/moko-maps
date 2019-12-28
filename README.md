![moko-maps](https://user-images.githubusercontent.com/5010169/71351401-27c14d80-25a6-11ea-9183-17821f6d4212.png)  
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0) [![Download](https://api.bintray.com/packages/icerockdev/moko/moko-maps/images/download.svg) ](https://bintray.com/icerockdev/moko/moko-maps/_latestVersion) ![kotlin-version](https://img.shields.io/badge/kotlin-1.3.61-orange)

# Mobile Kotlin maps module
This is a Kotlin Multiplatform library that provides controls of maps to common code.

## Table of Contents
- [Features](#features)
- [Requirements](#requirements)
- [Versions](#versions)
- [Installation](#installation)
- [Usage](#usage)
- [Samples](#samples)
- [Set Up Locally](#setup-locally)
- [Contributing](#contributing)
- [License](#license)

## Features
- **** - ;

## Requirements
- Gradle version 5.4.1+
- Android API 16+
- iOS version 9.0+

## Versions
- kotlin 1.3.61
  - 0.1.0
  - 0.1.1
  - 0.2.0

## Installation
root build.gradle  
```groovy
allprojects {
    repositories {
        maven { url = "https://dl.bintray.com/icerockdev/moko" }
    }
}
```

project build.gradle
```groovy
dependencies {
    commonMainApi("dev.icerock.moko:maps:0.2.0")
    commonMainApi("dev.icerock.moko:maps-google:0.2.0")
}

kotlin {
    targets
        .filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>()
        .flatMap { it.binaries }
        .filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.Framework>()
        .forEach { framework ->
            val frameworks = listOf("Base", "Maps").map { frameworkPath ->
                project.file("../ios-app/Pods/GoogleMaps/$frameworkPath/Frameworks").path.let { "-F$it" }
            }

            framework.linkerOpts(frameworks)
        }
}
```

settings.gradle  
```groovy
enableFeaturePreview("GRADLE_METADATA")
```

project Podfile
```ruby
pod 'GoogleMaps', '3.7.0'

# GoogleMaps is static library that already linked in moko-maps-google. Remove duplicated linking.
post_install do |installer|
  host_targets = installer.aggregate_targets.select { |aggregate_target|
    aggregate_target.name.include? "Pods-"
  }
  
  host_targets.each do |host_target|
    host_target.xcconfigs.each do |config_name, config_file|
      config_file.frameworks.delete("GoogleMaps")
      config_file.frameworks.delete("GoogleMapsBase")
      config_file.frameworks.delete("GoogleMapsCore")
      
      xcconfig_path = host_target.xcconfig_path(config_name)
      config_file.save_as(xcconfig_path)
    end
  end
end
```

## Usage
TODO

## Samples
Please see more examples in the [sample directory](sample).

## Set Up Locally 
- The [maps directory](maps) contains the base classes for all maps providers;
- The [maps-google directory](maps-google) contains the Google Maps implementation;
- In [sample directory](sample) contains sample apps for Android and iOS; plus the mpp-library connected to the apps;
- For local testing use the `:maps:publishToMavenLocal :maps-google:publishToMavenLocal` gradle task - so that sample apps use the locally published version.
```bash
./gradlew -PlibraryPublish :maps:publishToMavenLocal # build core classes
(cd sample/ios-app && pod install) && ./gradlew -PprovidersPublish :maps-google:publishToMavenLocal # install pods with GoogleMaps (required for cinterop of maps-google) and build GoogleMaps integration lib 
./gradlew :sample:mpp-library:syncMultiPlatformLibraryDebugFrameworkIosX64 # try build sample
```

## Contributing
All development (both new features and bug fixes) is performed in the `develop` branch. This way `master` always contains the sources of the most recently released version. Please send PRs with bug fixes to the `develop` branch. Documentation fixes in the markdown files are an exception to this rule. They are updated directly in `master`.

The `develop` branch is pushed to `master` on release.

For more details on contributing please see the [contributing guide](CONTRIBUTING.md).

## License
        
    Copyright 2019 IceRock MAG Inc.
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
