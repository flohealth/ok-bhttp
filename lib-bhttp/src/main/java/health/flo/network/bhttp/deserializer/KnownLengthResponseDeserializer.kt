package health.flo.network.bhttp.deserializer

import health.flo.network.bhttp.model.KnownLengthInformationalResponse
import health.flo.network.bhttp.model.KnownLengthResponse
import health.flo.network.bhttp.model.ResponseControlData
import health.flo.network.bhttp.model.isInformational

internal fun ByteArrayReader.readKnownLengthResponse(): KnownLengthResponse {
    var responseControlData: ResponseControlData = readResponseControlData()
    val informationalResponses = buildList<KnownLengthInformationalResponse> {
        while (responseControlData.isInformational) {
            KnownLengthInformationalResponse(
                controlData = responseControlData,
                fieldSection = readFieldSection(),
            ).let(::add)
            responseControlData = readResponseControlData()
        }
    }

    return KnownLengthResponse(
        informationalResponses = informationalResponses,
        controlData = responseControlData,
        headerSection = readFieldSection(),
        content = readContent(),
        trailerSection = readFieldSection(),
    )
}
