plugins {
     alias(libs.plugins.android.application)
     alias(libs.plugins.kotlin.android)
     id("com.google.dagger.hilt.android")
     id("org.jetbrains.kotlin.kapt")
}

android {
     namespace = "com.amar.fetchdataflow"
     compileSdk = 35

     defaultConfig {
          applicationId = "com.amar.fetchdataflow"
          minSdk = 24
          targetSdk = 35
          versionCode = 1
          versionName = "1.0"
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
          sourceCompatibility = JavaVersion.VERSION_11
          targetCompatibility = JavaVersion.VERSION_11
     }
     kotlinOptions {
          jvmTarget = "11"
     }
}

dependencies {
     implementation(libs.androidx.core.ktx)
     implementation(libs.androidx.appcompat)
     implementation(libs.material)
     implementation(libs.retrofit)
     implementation(libs.converter.gson)
     implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
     implementation("com.google.dagger:hilt-android:2.56.1")
     implementation(libs.androidx.activity)
     implementation(libs.androidx.constraintlayout)
     kapt("com.google.dagger:hilt-android-compiler:2.56.1")
     implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
     testImplementation(libs.junit)
     androidTestImplementation(libs.androidx.junit)
     androidTestImplementation(libs.androidx.espresso.core)
}