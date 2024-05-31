plugins {
    alias(libs.plugins.runningapp.android.feature.ui)
}

android {
    namespace = "com.ukhanyov.auth.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.auth.domain)
}