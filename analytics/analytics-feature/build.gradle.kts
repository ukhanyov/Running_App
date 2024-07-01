plugins {
    alias(libs.plugins.runningapp.android.dynamic.feature)
}

android {
    namespace = "com.runningapp.analytics.analytics_feature"
}

dependencies {
    implementation(project(":app"))

    api(projects.analytics.presentation)
    implementation(projects.analytics.domain)
    implementation(projects.analytics.data)
    implementation(projects.core.database)
}