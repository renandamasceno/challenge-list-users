plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.challenge.listusers"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.challenge.listusers"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.room:room-runtime:2.3.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.3.1")
    implementation("androidx.lifecycle:lifecycle-livedata:2.3.1")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.3.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    annotationProcessor("androidx.room:room-compiler:2.3.0")
    annotationProcessor("com.google.dagger:hilt-android-compiler:2.44")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.10")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.10")
    }
}