plugins {
    id("java")
}

group = "org.zed.snakez"
version = "1.0-SNAPSHOT"




dependencies {
    implementation("org.jline:jline:3.25.0")
    implementation("org.jline:jline-terminal-jna:3.25.0")
}

tasks {
    jar {
    dependsOn("copyDependencies")
        manifest.attributes["Main-Class"] = "org.zed.snakez.Main"
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from({
            configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
        })
    }
}

tasks.register<Copy>("copyDependencies") { 
    from(configurations.runtimeClasspath)
    into(layout.buildDirectory.dir("runtimeClasspath"))
}


repositories {
    mavenCentral()
}
