plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.ukhanyov.run.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
}