pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        exclusiveContent {
            forRepository {
                maven {
                    name = "NeoForge"
                    url = uri("https://maven.neoforged.net/releases")
                }
            }
            filter { includeGroupAndSubgroups("net.neoforged") }
        }
    }
}