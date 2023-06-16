package health.flo.network.bhttp.model

private const val INFORMATIONAL_STATUS_CODE_FIRST = 100
private const val INFORMATIONAL_STATUS_CODE_LAST = 199

internal data class ResponseControlData(
    val statusCode: Int,
)

internal val ResponseControlData.isInformational: Boolean
    get() = statusCode in INFORMATIONAL_STATUS_CODE_FIRST..INFORMATIONAL_STATUS_CODE_LAST
