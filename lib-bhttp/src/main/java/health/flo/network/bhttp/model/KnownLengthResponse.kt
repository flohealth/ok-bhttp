package health.flo.network.bhttp.model

internal class KnownLengthResponse(
    val informationalResponses: List<KnownLengthInformationalResponse>,
    val controlData: ResponseControlData,
    val headerSection: FieldSection,
    val content: ByteArray,
    val trailerSection: FieldSection,
) : Message {

    override val framingIndicator: Int
        get() = FRAMING_INDICATOR

    companion object {

        const val FRAMING_INDICATOR = 1
    }
}
