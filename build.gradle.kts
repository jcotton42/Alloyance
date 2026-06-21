import org.slf4j.event.Level

plugins {
    idea
    alias(libs.plugins.modDevGradle)
    alias(libs.plugins.kotlin)
}

val modId: String by project
val modName: String by project
val modLicense: String by project
val modVersion: String by project
val modGroupId: String by project
val modAuthors: String by project
val modDescription: String by project
val modIssueTracker: String by project

val datagenOutput: String = "src/generated/resources"

tasks.withType<ProcessResources>().configureEach {
    val modReplacementProperties = mapOf(
        "mod_id" to modId,
        "mod_name" to modName,
        "mod_version" to modVersion,
        "mod_license" to modLicense,
        "mod_authors" to modAuthors,
        "mod_description" to modDescription,
        "mod_issue_tracker" to modIssueTracker,
        "minecraft_version_range" to "[${libs.versions.minecraft.get()}]",
        "neo_version_range" to "[${libs.versions.neoforge.get()},)",
        "kff_version" to libs.versions.kotlinForForge.get(),
    )
    inputs.properties(modReplacementProperties)
    filesMatching(listOf("META-INF/neoforge.mods.toml", "pack.mcmeta")) {
        expand(modReplacementProperties)
    }
}

version = modVersion
group = modGroupId

base.archivesName = modId

java.toolchain.languageVersion = JavaLanguageVersion.of(21)

sourceSets {
    main {
        resources {
            srcDir(datagenOutput)
        }
    }
}

 val datagen: SourceSet by sourceSets.creating

/**
 * Sets up a dependency configuration called 'localRuntime'.
 * This configuration should be used instead of 'runtimeOnly' to declare
 * a dependency that will be present for runtime testing but that is
 * "optional", meaning it will not be pulled by dependents of this mod.
 */
val localRuntime: Configuration by configurations.creating

val datagenImplementation: Configuration = configurations.getByName(datagen.implementationConfigurationName)

configurations.runtimeClasspath.configure {
    extendsFrom(localRuntime, datagenImplementation)
}

configurations {
    getByName(datagen.compileClasspathConfigurationName).extendsFrom(compileClasspath.get())
    getByName(datagen.runtimeClasspathConfigurationName).extendsFrom(runtimeClasspath.get())
    getByName(datagen.annotationProcessorConfigurationName).extendsFrom(annotationProcessor.get())
}

neoForge {
    version = libs.versions.neoforge.get()
    addModdingDependenciesTo(datagen)

    parchment {
        minecraftVersion = libs.versions.parchmentMinecraft
        mappingsVersion = libs.versions.parchment
    }

    runs {
        configureEach {
            systemProperty("neoforge.logging.markers", "REGISTRIES")
            logLevel = Level.DEBUG
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        register("client") {
            client()
        }

        register("server") {
            server()
            programArgument("--nogui")
        }

        register("data") {
            data()
            sourceSet = datagen
            // Specify the modid for data generation, where to output the resulting resource, and where to look for existing resources.
            programArguments.addAll(
                "--mod",
                modId,
                "--all",
                "--output",
                file(datagenOutput).path,
                "--existing",
                file("src/main/resources/").path
            )
        }
    }

    mods {
        create(modId) {
            sourceSet(sourceSets.main.get())
            sourceSet(datagen)
        }
    }
}

repositories {
    mavenLocal()
    exclusiveContent {
        forRepository { maven { url = uri("https://thedarkcolour.github.io/KotlinForForge/") } }
        filter { includeGroup("thedarkcolour") }
    }
    exclusiveContent {
        forRepository { maven { url = uri("https://maven.blamejared.com/") } }
        filter { includeGroup("mezz.jei") }
    }
    exclusiveContent {
        forRepository { maven { url = uri("https://maven.k-4u.nl/") } }
        filter { includeGroup("mcjty.theoneprobe") }
    }
    exclusiveContent {
        forRepository { maven { url = uri("https://maven.rover656.dev/releases") } }
        filter {
            includeGroup("com.enderio")
            includeGroup("dev.gigaherz.graph")
        }
    }
}

dependencies {
    implementation(libs.kotlinForForge)
    // datagen can use mod code
    datagenImplementation(sourceSets.main.get().output)

    // QOL Dev dependencies should use `localRuntime`. `runtimeOnly` is for stuff we actually want at runtime
    localRuntime(libs.top)

    // JEI
    compileOnly(libs.bundles.jei.api)
    runtimeOnly(libs.jei)

    datagenImplementation(libs.enderio)
}

// IDEA no longer automatically downloads sources/javadoc jars for dependencies, so we need to explicitly enable the behavior.
idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}

tasks {
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }
}
