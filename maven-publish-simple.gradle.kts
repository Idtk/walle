apply(plugin = "maven-publish")

fun getReleaseRepositoryUrl(): String {
    return property("RELEASE_REPOSITORY_URL") as? String
        ?: "http://app-gitlab.devops.guchele.cn:8081/repository/maven-releases/"
}

fun getSnapshotRepositoryUrl(): String {
    return property("SNAPSHOT_REPOSITORY_URL") as? String
        ?: "http://app-gitlab.devops.guchele.cn:8081/repository/maven-snapshots/"
}

val version_curr = property("VERSION_NAME") as? String ?: ""

//fun getArtifactId (): String {
//    val properties = java.util.Properties()
//    val file:File = project.file("gradle.properties")
//    properties.load(file.newDataInputStream())
//    return properties.getProperty("POM_ARTIFACT_ID")
//}

afterEvaluate {
    configure<PublishingExtension> {
        publications {
            create<MavenPublication>("maven") {
                groupId = property("GROUP") as? String ?: ""
//                artifactId = getArtifactId()
                artifactId = "plugin"
                version = version_curr
                from(components.findByName("java"))
                artifact(tasks.findByName("sourcesJar"))
            }
        }
        repositories {
            maven {
                isAllowInsecureProtocol = true
                val releasesRepoUrl = getReleaseRepositoryUrl()
                val snapshotsRepoUrl = getSnapshotRepositoryUrl()
                url =
                    uri(if (version_curr.endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
                credentials {
                    username = property("NEXUS_USERNAME") as? String ?: ""
                    password = property("NEXUS_PASSWORD") as? String ?: ""
                }
            }
        }
    }
}