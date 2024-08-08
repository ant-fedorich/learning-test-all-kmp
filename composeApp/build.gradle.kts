import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude
import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.include
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.isRegularFile

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)

//    wasmJs {
//        moduleName = "composeApp"
//        browser {
//            commonWebpackConfig {
//                outputFileName = "composeApp.js"
//                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
//                    static = (static ?: mutableListOf()).apply {
//                        // Serve sources to debug inside browser
//                        add(project.projectDir.path)
//                    }
//                }
//            }
//        }
//        binaries.executable()
//    }

//    js {
//
//    }
    
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
//        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }

        iosMain.dependencies {
//            exclude(compose.preview)
        }

        commonMain.dependencies {
            implementation(projects.shared)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
//            implementation(compose.preview)
            implementation(compose.components.resources)
//            implementation(compose.preview)
//            implementation(compose.components.uiToolingPreview)
//            implementation(compose.components.uiToolingPreview)
        }

//        val wasmJsMain by getting

//        jsMain.dependencies {
//
//        }
    }
}

// Helper function to determine if the current platform is Android
fun Project.isAndroid(): Boolean {
    return this.extensions.findByType<KotlinMultiplatformExtension>()?.targets?.any { it.platformType.name == "android" } ?: false
}

android {
    namespace = "org.test.testkmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.test.testkmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }

}
dependencies {
    implementation(libs.androidx.ui.android)
}


compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.test.testkmp"
            packageVersion = "1.0.0"
        }
    }
}

//tasks.withType<KotlinCompile> {
//    exclude("org/test/testkmp/previewios/**")
//    exclude("org/test/testkmp/previewweb/**")
//}


//tasks.register("copyAndModifyIOSFiles") {
//    doLast {
//        val projectDir = projectDir.toPath()
//        val sourceDir = projectDir.resolve("src/androidMain/kotlin/org/test/testkmp/previewios")
//        val destinationDirOld = projectDir.resolve("src/commonMain/kotlin")
//        val destinationDir = projectDir.resolve("/Users/eltonio/Documents/AndroidProjects/Learning/Test-all-kmp/shared/src/iosMain/kotlin")
//
//
//
//
////        val projectDir = projectDir.toPath()
//        println("Project Dir: $projectDir")
//        println("Source Dir: $sourceDir")
//        println("Destination Dir: $destinationDir")
//
////        if (Files.exists(sourcePath)) {
//            // Ensure the destination directory exists
//
//        Files.createDirectories(destinationDir)
//        Files.walk(sourceDir).use { paths ->
//            val files = paths.filter { Files.isRegularFile(it) && it.toString().endsWith(".kt") }
//            files.forEach { sourcePath ->
//                val relativePath = sourceDir.relativize(sourcePath)
//                // Define the new filename dynamically excluding ".kt"
//                val originalFileName = sourcePath.fileName.toString()
//                val fileNameWithoutExtension = originalFileName.substringBeforeLast(".")
//                val fileExtension = originalFileName.substringAfterLast(".", "")
//                val newFileNameWithoutExtension = fileNameWithoutExtension.replace("ScreenIOSPreview", "TestShared.ios")
//                val newFileName = "$newFileNameWithoutExtension.$fileExtension"
//
//                val destinationPath = destinationDir.resolve(newFileName)
//
//
//                Files.createDirectories(destinationPath.parent)
//
//                val content = Files.readAllLines(sourcePath).joinToString("\n")
//
//                /**
//                 * +rename to TestShared.ios.kt
//                 * + delete androidx.compose.ui.tooling.preview.Preview
//                 * + delete @Preview
//                 * +delete package org.test.testkmp.previewios
//                 * + add actual
//                 * + rename to MainScreenShared
//                 * */
//
//                val modifiedContent = content
//                    .replace("import androidx.compose.ui.tooling.preview.Preview", "")
//                    .replace("@Preview", "")
//                    .replace("package org.test.testkmp.previewios","")
//                    .replace("fun MainScreenIOS()","actual fun MainScreenShared()")
//
//                Files.write(destinationPath, modifiedContent.toByteArray())
//
//                println("Copied and modified file: $sourcePath to $destinationPath")
//            }
//        }
//    }
//}
//
//tasks.named("preBuild") {
//    dependsOn("copyAndModifyIOSFiles")
//}






