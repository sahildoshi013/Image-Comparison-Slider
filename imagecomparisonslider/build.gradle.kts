plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    `maven-publish`
}

android {
    namespace = "com.swoopzi.lib.imagecomparisonslider"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    testImplementation(libs.testng)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifact("$buildDir/outputs/aar/${artifactId}-release.aar")

            groupId = "com.swoopzi.lib"
            artifactId = "imagecomparisonslider"
            version = "1.0.0"

            pom {
                name.set("Image Comparison Slider")
                description.set("The Image Comparison Slider is an Android library that allows users to easily compare two images by sliding between them")
                url.set("https://github.com/sahildoshi013/Image-Comparison-Slider")
                licenses {
                    license {
                        name.set("GNU General Public License v3.0")
                        url.set("https://choosealicense.com/licenses/gpl-3.0/")
                    }
                }
                developers {
                    developer {
                        id.set("sahildoshi013")
                        name.set("Sahil Doshi")
                        email.set("sahiljdoshi@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:github.com/sahildoshi013/Image-Comparison-Slider.git")
                    developerConnection.set("scm:git:ssh://github.com/sahildoshi013/Image-Comparison-Slider.git")
                    url.set("https://github.com/sahildoshi013/Image-Comparison-Slider")
                }
            }
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/sahildoshi013/Image-Comparison-Slider")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
}