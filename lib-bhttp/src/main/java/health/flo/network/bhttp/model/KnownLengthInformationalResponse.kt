package health.flo.network.bhttp.model

internal data class KnownLengthInformationalResponse(
    val controlData: ResponseControlData,
    val fieldSection: FieldSection,
)
