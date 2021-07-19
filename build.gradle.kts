plugins {
    val kotlinVersion = "1.4.30"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.6.6"
}

group = "al.nya.shadowqwq"
version = "0.1.0"

repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public") // 阿里云国内代理仓库
    mavenCentral()
}
dependencies{
    implementation( files("./libs/MiraiPluginExtendsAPI-0.1.0.jar")) //You can change it
    implementation( "com.google.code.gson:gson:2.6.2")
}
