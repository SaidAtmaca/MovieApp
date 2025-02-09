
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import movieapp.configureKotlinAndroid
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<BaseAppModuleExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
                defaultConfig.minSdk=26
                defaultConfig.versionCode = 1
                defaultConfig.versionName = "1.0"
                defaultConfig.testInstrumentationRunner="androidx.test.runner.AndroidJUnitRunner"
                defaultConfig.vectorDrawables {
                    useSupportLibrary = true
                }
                @Suppress("UnstableApiUsage")
                testOptions.animationsDisabled = true

                buildTypes.getByName("release"){
                    isMinifyEnabled = false
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }


                buildFeatures {
                    compose = true
                }
                composeOptions {
                    kotlinCompilerExtensionVersion ="1.5.5"
                }
                packaging {
                    resources {
                        exclude("/META-INF/{AL2.0,LGPL2.1}")
                    }
                }
                buildFeatures {
                    buildConfig = true
                    viewBinding= true
                }

            }

            extensions.getByType<KotlinAndroidProjectExtension>().apply {
                configureKotlinAndroid(this)
            }



        }
    }

}
