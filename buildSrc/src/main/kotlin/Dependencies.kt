const val kotlinVersion = "1.3.21"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "3.4.2"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"

}

object AndroidSdk {
    const val min = 16
    const val compile = 28
    const val target = compile
}

private object Versions {
    const val appCompat         = "1.1.0"
    const val gson              = "2.8.5"
    const val ktx               = "1.1.0"
    const val materialDesign    = "1.0.0"
    const val legacySupport     = "1.0.0"
    const val picasso           = "2.5.2"
    const val koin              = "2.0.1"
    const val archComponents    = "2.0.0"
    const val room              = "2.1.0"
    const val rxKotlin          = "2.1.0"
    const val androidxCore      = "1.0.0"
    const val groupie           = "2.3.0"
    const val retrofit          = "2.5.0"
    const val retrofitGsonConv  = "2.1.0"
    const val okhttp3           = "3.11.0"
    const val jUnit             = "4.12"
    const val mockito           = "1.5.0"
    const val multidex          = "2.0.0"
    const val stetho            = "1.5.1"
}

object Libraries {

    const val kotlinStdLib          = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val ktx                   = "androidx.core:core-ktx:${Versions.ktx}"
    const val rxKotlin              = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val multidex              = "com.android.support:multidex:${Versions.multidex}"
    const val stetho                = "com.facebook.stetho:stetho:${Versions.stetho}"

    // Androidx
    const val appCompat             = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val androidxCore          = "androidx.core:core:${Versions.androidxCore}"
    const val lifecycle             = "androidx.lifecycle:lifecycle-extensions:${Versions.archComponents}"

    // Ui
    const val materialDesign        = "com.google.android.material:material:${Versions.materialDesign}"
    const val groupie               = "com.xwray:groupie:${Versions.groupie}"
    const val groupieAndroidExt     = "com.xwray:groupie-kotlin-android-extensions:${Versions.groupie}"
    const val picasso               = "com.squareup.picasso:picasso:${Versions.picasso}"

    // Koin DI
    const val koinAndroid           = "org.koin:koin-android:${Versions.koin}"
    const val koinAndroidLifecycle  = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinAndroidViewModel  = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koinTest              = "org.koin:koin-test:${Versions.koin}"

    //Networking
    const val gson                  = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofit              = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGsonConv}"
    const val retrofitRxSupport     = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val okhttp3               = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"

    //Room
    const val room                  = "android.arch.persistence.room:runtime:${Versions.room}"
    const val roomCompiler          = "androidx.room:room-compiler:${Versions.room}"
    const val rxRoom                = "androidx.room:room-rxjava2:${Versions.room}"
}

object TestLibraries {
    const val junit4                = "junit:junit:${Versions.jUnit}"
    const val mockito               = "com.nhaarman:mockito-kotlin:${Versions.mockito}"
    const val lifecycleTesting      = "androidx.arch.core:core-testing:${Versions.archComponents}"
}