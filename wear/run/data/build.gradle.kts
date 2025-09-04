plugins {
    alias(libs.plugins.runningapp.android.library)
}

android {
    namespace = "com.ukhanyov.wear.run.data"

    defaultConfig {
        minSdk = 30
    }
}

dependencies {
    implementation(libs.androidx.health.services.client)
    implementation(libs.bundles.koin)
}