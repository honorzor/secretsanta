import java.net.Inet4Address
import java.net.InetAddress
import java.net.InetSocketAddress

val runtime = Runtime.getRuntime();
val parsedArgs = parseArgs()

val profile = getArg("profile")
val version = "${getArg("version")}_${profile}"
val needRun = getArg("needRun")

val dockerImageName = "jsaymynamej/secret-santa-api:${version}"

buildVersion()
dockerPush()
runIfNeed()
parseArgs()

fun runIfNeed() {
    if (needRun.toBoolean()) {
        exec(commands = listOf(
                "docker", "run", "-d", "-p", "8081:8081", "${dockerImageName}"
        ))
    }
}

fun buildVersion() {
    exec(commands = listOf(
            "sh", "./gradlew", "clean", "build", "-P", "version=${version}")
    )
}

fun dockerPush() {
    exec(commands = listOf(
            "docker", "build", "--build-arg", "PROFILE=${profile}", "--build-arg", "VERSION=${version}", "-t", "${dockerImageName}", ".")
    )
    exec(commands = listOf(
            "docker", "push", "${dockerImageName}"
    ))
}

fun exec(commands: List<String>) {
    ProcessBuilder(commands)
            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
            .start()
            .waitFor()
}

fun getArg(name: String): String {
    return parsedArgs[name] ?: ""
}

fun parseArgs(): Map<String, String> {
    val mapWithArgs = args.map {
        it.split("=")[0] to it.split("=")[1]
    }.toMap()
    return mapWithArgs
}
