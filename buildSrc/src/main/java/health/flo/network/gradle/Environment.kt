package health.flo.network.gradle

object Environment {

    val isCI: Boolean
        get() = System.getenv()["CI"] != null
}

fun isReleaseBranch(): Boolean {
    val branch = System.getenv("GITHUB_REF_NAME")

    return branch != null && (branch == "main")
}
