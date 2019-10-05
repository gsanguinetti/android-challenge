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

object Libraries {
    private object Versions {
        const val appCompat         = "1.1.0"
        const val gson              = "2.8.5"
        const val ktx               = "1.1.0"
        const val materialDesign    = "1.0.0"
        const val legacySupport     = "1.0.0"
        const val picasso           = "2.5.2"
    }

    const val kotlinStdLib          = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat             = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val materialDesign        = "com.google.android.material:material:${Versions.materialDesign}"
    const val legacySupport         = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    const val gson                  = "com.google.code.gson:gson:${Versions.gson}"
    const val picasso               = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val ktxCore               = "androidx.core:core-ktx:${Versions.ktx}"
}

object TestLibraries {
    private object Versions {
        const val junit4            = "4.12"
    }
    const val junit4                = "junit:junit:${Versions.junit4}"
}