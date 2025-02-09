plugins {
    id("movie.android.library")
    id("movie.android.hilt")
}

android {
    namespace = "com.example.network"
}

dependencies {

    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.okhttp)
    implementation (libs.loggingInterceptor)

    implementation(libs.sandwich)
    implementation(libs.sandwich.retrofit)

}