plugins {
    id("movie.android.library")
    id("movie.android.hilt")
    id("movie.android.library.compose")
}

android {
    namespace = "com.example.presentation"
}

dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:viewmodel"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.lottie.compose)

    implementation(libs.lottie.compose.v660)

    implementation(libs.coil.compose)

    implementation(libs.accompanist.pager.indicators)


}