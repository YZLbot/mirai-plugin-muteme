plugins {
    val kotlinVersion = "1.8.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.16.0"
}

group = "takeoff0518"
version = "0.2.0"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}
