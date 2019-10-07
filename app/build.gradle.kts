plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    kotlin("kapt")
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "es.npatarino.android.gotchallenge"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 2
        versionName = "2.0"
        multiDexEnabled = true

        buildConfigField("String", "SERVER_BASE_URL",
                "\"https://project-8424324399725905479.firebaseio.com/\"")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.ktx)
    implementation(Libraries.stetho)

    implementation(Libraries.koinAndroid)
    implementation(Libraries.koinAndroidLifecycle)
    implementation(Libraries.koinAndroidViewModel)
    implementation(Libraries.koinTest)

    implementation(Libraries.rxKotlin)

    implementation(Libraries.multidex)
    implementation(Libraries.appCompat)
    implementation(Libraries.androidxCore)
    implementation(Libraries.lifecycle)
    implementation(Libraries.materialDesign)
    implementation(Libraries.groupie)
    implementation(Libraries.groupieAndroidExt)
    implementation(Libraries.picasso)

    implementation(Libraries.gson)
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitGsonConverter)
    implementation(Libraries.retrofitRxSupport)
    implementation(Libraries.okhttp3)

    implementation(Libraries.room)
    implementation(Libraries.rxRoom)

    kapt(Libraries.roomCompiler)

    testImplementation(TestLibraries.junit4)
    testImplementation(Libraries.koinTest)
    testImplementation(TestLibraries.mockito)
    testImplementation(TestLibraries.lifecycleTesting)
}