plugins {
    alias(libs.plugins.runningapp.jvm.library)
    alias(libs.plugins.runningapp.jvm.junit5)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    implementation(projects.core.domain)

    testImplementation(projects.core.test)
}