plugins {
    alias(libs.plugins.runningapp.android.library)
}

android {
    namespace = "com.ukhanyov.analytics.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.analytics.domain)
}