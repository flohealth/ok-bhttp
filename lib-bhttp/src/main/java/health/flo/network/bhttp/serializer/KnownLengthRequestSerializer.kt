package health.flo.network.bhttp.serializer

import health.flo.network.bhttp.model.KnownLengthRequest
import java.io.ByteArrayOutputStream

internal fun KnownLengthRequest.binaryEncoded(): ByteArray {
    val outputStream = ByteArrayOutputStream()

    outputStream.write(framingIndicator.variableLengthEncoded())
    requestControlData.binaryEncodeTo(outputStream)
    outputStream.write(headerSection.binaryEncoded())
    outputStream.write(content.lengthPrefix())
    outputStream.write(content)
    outputStream.write(trailerSection.binaryEncoded())

    return outputStream.toByteArray()
}
