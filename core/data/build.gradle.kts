plugins {
    id("movie.android.library")
    id("movie.android.hilt")
    id("movie.android.room")
}
android {
    namespace = "com.example.data"


}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.loggingInterceptor)

    implementation(libs.sandwich)
    implementation(libs.sandwich.retrofit)
}