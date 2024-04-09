@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            // Do not change the username below. It should always be "mapbox" (not your username).
            credentials.username = "mapbox"
            // Use the secret token stored in gradle.properties as the password
            credentials.password = providers.gradleProperty("MAPBOX_DOWNLOADS_TOKEN").get()
            authentication.create<BasicAuthentication>("basic")
        }
    }
}

rootProject.name = "LocationMockery"
include(":app")
include(":shell")
include(":library:design")
include(":feature:login:contract")
include(":feature:login:impl")
include(":feature:map:contract")
include(":feature:map:impl")
include(":feature:item-list:contract")
include(":feature:item-list:impl")
include(":feature:item-details:contract")
include(":feature:item-details:impl")
