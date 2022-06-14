plugins {
    id("groovy")
//    id("java-library")
//    id("java-gradle-plugin")
//    id("maven-publish")
    id("org.jetbrains.kotlin.jvm")
}
apply {
    from(rootProject.file("maven-publish-simple.gradle.kts"))
}
//apply from: rootProject.file('quality.gradle')

//gradlePlugin {
//    plugins {
//        //插件名,每一个插件都可以有
//        wallePlugin {
//            id = "walle"
//            implementationClass = "com.meituan.android.walle.GradlePlugin"
//        }
//    }
//}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}


dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
//    implementation fileTree(dir: "./src/main/libs", include: ['*.jar'])
    implementation("commons-io:commons-io:2.5")
    implementation("commons-codec:commons-codec:1.11")
    implementation("org.apache.commons:commons-lang3:3.5")
    implementation("com.android.tools.build:gradle:7.2.1")
    implementation("org.bouncycastle:bcpkix-jdk15on:1.56")
    implementation("org.bouncycastle:bcprov-jdk15on:1.56")
    implementation("org.json:json:20080701")
    implementation("com.meituan.android.walle:payload_writer:1.1.7")
//    implementation 'com.meituan.android.walle:payload_reader:1.1.7'
//    implementation project(':payload_writer')
    implementation("com.google.code.gson:gson:2.9.0")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

//configurations.all {
//    resolutionStrategy.cacheChangingModulesFor 0, 'seconds'
//}

tasks.create<Jar>("sourcesJar") {
    from(sourceSets.main.get().allSource)
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    archiveClassifier.set("sources")
}