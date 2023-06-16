package health.flo.network.bhttp.model

internal class KnownLengthRequest(
    val requestControlData: RequestControlData,
    val headerSection: FieldSection,
    val content: ByteArray,
    val trailerSection: FieldSection,
) : Message {

    override val framingIndicator: Int
        get() = FRAMING_INDICATOR

    companion object {

        const val FRAMING_INDICATOR = 0
    }
}
