plugins {
    id("java")
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

application{
    mainClass.set("trading_app.Main")
}
tasks.withType<JavaExec>{
    standardInput = System.`in`
}
repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}
tasks.withType<Jar>{
    archiveBaseName = "trading-app"
    manifest{
        attributes["Main-Class"] = "trading_app.Main"
    }
}