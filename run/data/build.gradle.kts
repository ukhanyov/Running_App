plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.ukhanyov.run.data"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(projects.core.domain)
    implementation(projects.core.database)
    implementation(projects.run.domain)
}