plugins {
    alias(libs.plugins.runningapp.android.feature.ui)
}

android {
    namespace = "com.ukhanyov.analytics.presentation"
}

dependencies {
    implementation(projects.analytics.domain)
}