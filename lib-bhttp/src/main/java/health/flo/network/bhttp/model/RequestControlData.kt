package health.flo.network.bhttp.model

internal data class RequestControlData(
    val method: String,
    val scheme: String,
    val authority: String,
    val path: String,
)
