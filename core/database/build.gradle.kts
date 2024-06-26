plugins {
    alias(libs.plugins.runningapp.android.library)
    alias(libs.plugins.runningapp.android.room)
}

android {
    namespace = "com.ukhanyov.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
}