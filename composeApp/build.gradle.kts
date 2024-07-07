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
    wasmJs {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
        binaries.executable()
    }
    
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    jvm("desktop")

    
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
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(projects.shared)
        }

        iosMain.dependencies {

        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation("io.github.chozzle:compose-macos-theme:0.4.2")
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation("io.github.chozzle:compose-macos-theme:0.4.2")
        }
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


//    sourceSets["main"].withConvention(KotlinSourceSet::class) {
////        kotlin.include("**/myTests/*.kt")
//        kotlin.exclude("org/test/testkmp/ui")
//    }

//    sourceSets["main"] {
////        main {
//            java {
//                srcDirs("src/main/java", "src/main/kotlin")
//                exclude("**/ExcludedFile.kt", "**/excludedDirectory/**")
//            }
////        }
//    }

//    sourceSets["main"].kotlin.srcDirs()

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

//tasks.register<JavaExec>("run") {
//    group = "application"
////    main = "MainKt"
//    classpath = sourceSets["main"].runtimeClasspath
//}



//tasks.withType<Copy> {
//    exclude("org/test/testkmp/ui/**")
//}

//tasks.withType(KotlinCompile::class.java).configureEach {
//    this.exclude("org/test/testkmp/ui/Components2.kt")
//}
//
tasks.withType<KotlinCompile> {
    exclude("org/test/testkmp/uipreview/**")
}


//tasks.register("copyAndModifyFiles") {
//    doLast {
//        val projectDir = projectDir.toPath()
//        val sourceDir = projectDir.resolve("src/androidMain/kotlin/org/test/testkmp/uipreview")
//        val destinationDir = projectDir.resolve("src/commonMain/kotlin/ui")
//
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
//                val destinationPath = destinationDir.resolve(relativePath)
//
//                Files.createDirectories(destinationPath.parent)
//
//                val content = Files.readAllLines(sourcePath).joinToString("\n")
//                val modifiedContent = content
//                    .replace("package org.test.testkmp.uipreview","package ui")
//                    .replace("import androidx.compose.ui.tooling.preview.Preview", "")
//                    .replace("import org.test.testkmp.uipreview", "import ui")
//                    .replace("@Preview", "")
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
//    dependsOn("copyAndModifyFiles")
//}






