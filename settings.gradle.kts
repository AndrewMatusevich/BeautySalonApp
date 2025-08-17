pluginManagement {
    repositories {
        google ()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BeautySalonApp"
include(":app")
include(":data_api")
include(":data_impl")
include(":domain_models")
include(":core:ui")
include(":core:navigation")
include(":core:firebase")
include(":features:auth_api")
include(":features:auth_impl")
include(":features:booking_api")
include(":features:booking_impl")
include(":features:calendar_api")
include(":features:calendar_impl")
include(":features:master_api")
include(":features:master_impl")
include(":features:admin_api")
include(":features:admin_impl")
include(":features:profile_api")
include(":features:profile_impl")
include(":features:client_api")
include(":features:client_impl")
