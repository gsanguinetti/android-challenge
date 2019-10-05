plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "es.npatarino.android.gotchallenge"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 2
        versionName = "2.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.ktxCore)

    implementation(Libraries.appCompat)
    implementation(Libraries.materialDesign)
    implementation(Libraries.gson)
    implementation(Libraries.legacySupport)
    implementation(Libraries.picasso)

    testImplementation (TestLibraries.junit4)
}