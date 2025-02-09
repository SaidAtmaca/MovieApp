plugins {
    id("movie.android.library")
    id("movie.android.hilt")
}

android {
    namespace = "com.example.viewmodel"
}

dependencies {
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
}