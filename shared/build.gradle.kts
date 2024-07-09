import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import java.nio.file.Files

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
    }
    
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    jvm()
    
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
    }
}

android {
    namespace = "org.test.testkmp.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
dependencies {
    implementation(libs.androidx.material3.android)
//    implementation(libs.androidx.material3.desktop)
}

/*
tasks.register("copyAndModifyIOSFiles") {
    doLast {
        val projectDir = projectDir.toPath()
        val sourceDirOld = projectDir.resolve("src/androidMain/kotlin/org/test/testkmp/previewios")
        val sourceDir = projectDir.resolve("/Users/eltonio/Documents/AndroidProjects/Learning/Test-all-kmp/composeApp/src/androidMain/kotlin/org/test/testkmp/previewios")
        val destinationDirOld = projectDir.resolve("src/commonMain/kotlin")
        val destinationDir = projectDir.resolve("/Users/eltonio/Documents/AndroidProjects/Learning/Test-all-kmp/shared/src/iosMain/kotlin")




//        val projectDir = projectDir.toPath()
        println("Project Dir: $projectDir")
        println("Source Dir: $sourceDir")
        println("Destination Dir: $destinationDir")

//        if (Files.exists(sourcePath)) {
        // Ensure the destination directory exists

        Files.createDirectories(destinationDir)
        Files.walk(sourceDir).use { paths ->
            val files = paths.filter { Files.isRegularFile(it) && it.toString().endsWith(".kt") }
            files.forEach { sourcePath ->
                val relativePath = sourceDir.relativize(sourcePath)
                // Define the new filename dynamically excluding ".kt"
                val originalFileName = sourcePath.fileName.toString()
                val fileNameWithoutExtension = originalFileName.substringBeforeLast(".")
                val fileExtension = originalFileName.substringAfterLast(".", "")
                val newFileNameWithoutExtension = fileNameWithoutExtension.replace("ScreenIOSPreview", "TestShared.ios")
                val newFileName = "$newFileNameWithoutExtension.$fileExtension"

                val destinationPath = destinationDir.resolve(newFileName)


                Files.createDirectories(destinationPath.parent)

                val content = Files.readAllLines(sourcePath).joinToString("\n")

                */
/**
                 * +rename to TestShared.ios.kt
                 * + delete androidx.compose.ui.tooling.preview.Preview
                 * + delete @Preview
                 * +delete package org.test.testkmp.previewios
                 * + add actual
                 * + rename to MainScreenShared
                 * *//*


                val modifiedContent = content
                    .replace("import androidx.compose.ui.tooling.preview.Preview", "")
                    .replace("@Preview", "")
                    .replace("package org.test.testkmp.previewios","")
                    .replace("fun MainScreenIOS()","actual fun MainScreenShared()")

                Files.write(destinationPath, modifiedContent.toByteArray())

                println("Copied and modified file: $sourcePath to $destinationPath")
            }
        }
    }
}

tasks.named("preBuild") {
    dependsOn("copyAndModifyIOSFiles")
}
*/
