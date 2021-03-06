import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
    kotlin("jvm") version "1.3.21"
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

val arrowVersion = "0.10.2"
dependencies {
    compile(kotlin("stdlib"))
    compile("io.arrow-kt:arrow-core-data:$arrowVersion")
    compile("io.arrow-kt:arrow-mtl:$arrowVersion")
    compile("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.2")
    compile("io.github.microutils:kotlin-logging:1.7.8")
    runtime("org.slf4j:slf4j-simple:1.7.28")

    // need this at compile level for chapter 8
    compile("io.kotlintest:kotlintest-runner-junit5:3.3.2")
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlinx")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.suppressWarnings = true
}

ktlint {
    verbose.set(true)
    disabledRules.set(
        setOf(
            "comment-spacing",
            "filename",
            "import-ordering",
            "no-line-break-before-assignment"
        )
    )
}

test.dependsOn("ktlintCheck")
