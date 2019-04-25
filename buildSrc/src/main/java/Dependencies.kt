@file:Suppress("unused")

object Versions {

    const val gradle = "3.4.0"
    const val gradleVersionsPlugin = "0.20.0"
    const val jacoco = "0.8.3"
    const val kotlin = "1.3.30"
    const val buildTools = "28.0.3"

    // Android libraries
    const val appCompat = "1.1.0-alpha04"
    const val androidx = "1.1.0-alpha04"
    const val androidxKtx = "1.1.0-alpha05"
    const val design = "1.0.0-rc01"
    const val cardview = "1.0.0"
    const val multiDex = "2.0.0"
    const val constraintLayout = "1.1.3"
    const val playServices = "15.0.1"

    // Third party libraries
    const val dagger = "2.21"
    const val assistedInject = "0.3.3"
    const val retrofit = "2.5.0"
    const val okhttp = "3.12.1" // version 3.13.0 bumps min SDK to API 21, so don't update
    const val rxjava = "2.2.7"
    const val rxkotlin = "2.3.0"
    const val rxAndroid = "2.1.1"
    const val reactiveNetwork = "3.0.2"
    const val jetbrainsAnnotations = "17.0.0"

    // Unit Testing
    const val robolectric = "3.8"
    const val junit = "4.12"
    const val mockito = "2.24.5"
    const val mockitoKotlin = "1.6.0"

    // Development
    const val leakCanary = "1.6.3"

    // Image loading
    const val glide = "4.7.1"

    // Vector Animations
    const val lottie = "2.7.0" // version 2.8.0 requires androidx, so don't update

    // Database
    const val room = "2.0.0-rc01"

    // Quality
    const val checkstyle = "8.18"
    const val ktlint = "0.30.0"

    // Arch components
    const val archComponents = "2.0.0-rc01"

    // Android tools
    const val androidTools = "26.3.1"
    const val androidUiAutomator = "2.1.3"

    // Flexbox
    const val flexbox = "1.0.0"
}

object ProjectDependencies {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val jacoco = "org.jacoco:org.jacoco.core:${Versions.jacoco}"
    const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersionsPlugin}"
}

object MainApplicationDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinKtx = "androidx.core:core-ktx:${Versions.androidxKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val multiDex = "androidx.multidex:multidex:${Versions.multiDex}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardview}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val constraintLayoutSolver = "androidx.constraintlayout:constraintlayout-solver:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.androidx}"
    const val design = "com.google.android.material:material:${Versions.design}"
    const val androidAnnotations = "androidx.annotation:annotation:${Versions.androidx}"
    const val supportV4 = "androidx.legacy:legacy-support-v4:${Versions.androidx}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val assistedInjectAnnotations =
        "com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.assistedInject}"
    const val assistedInjectProcessor =
        "com.squareup.inject:assisted-inject-processor-dagger2:${Versions.assistedInject}"
    const val flexbox = "com.google.android:flexbox:${Versions.flexbox}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotations = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val rxjava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    const val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomRx = "androidx.room:room-rxjava2:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val archComponentLifeCycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.archComponents}"
    const val archComponentLifeCycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.archComponents}"
    const val lintapi = "com.android.tools.lint:lint-api:${Versions.androidTools}"
    const val lintchecks = "com.android.tools.lint:lint-checks:${Versions.androidTools}"
    const val reactiveNetwork = "com.github.pwittchen:reactivenetwork-rx2:${Versions.reactiveNetwork}"
    const val jetbrainsAnnotations = "org.jetbrains:annotations:${Versions.jetbrainsAnnotations}"
    const val percentLibrary = "androidx.percentlayout:percentlayout:${Versions.androidx}"
}

object UnitTestingDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val robolectricMultidex = "org.robolectric:shadows-multidex:${Versions.robolectric}"
    const val junit = "junit:junit:${Versions.junit}"
    const val mockito = "org.mockito:mockito-inline:${Versions.mockito}"
    const val archComponentCoreTesting = "androidx.arch.core:core-testing:${Versions.archComponents}"
}

object DevelopmentDependencies {
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    const val leakCanaryFragment = "com.squareup.leakcanary:leakcanary-support-fragment:${Versions.leakCanary}"
    const val uiAutomator = "com.android.support.test.uiautomator:uiautomator-v18:${Versions.androidUiAutomator}"
}

object QualityDependencies {
    const val checkstyle = "com.puppycrawl.tools:checkstyle:${Versions.checkstyle}"
    const val ktlint = "com.github.shyiko:ktlint:${Versions.ktlint}"
}