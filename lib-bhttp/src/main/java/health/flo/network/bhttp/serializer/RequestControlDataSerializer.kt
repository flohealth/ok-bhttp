package health.flo.network.bhttp.serializer

import health.flo.network.bhttp.model.RequestControlData
import java.io.OutputStream

internal fun RequestControlData.binaryEncodeTo(outputStream: OutputStream) {
    val methodEncoded = method.binaryEncodedWithLengthPrefix()
    val schemeEncoded = scheme.binaryEncodedWithLengthPrefix()
    val authorityEncoded = authority.binaryEncodedWithLengthPrefix()
    val pathEncoded = path.binaryEncodedWithLengthPrefix()

    outputStream.write(methodEncoded)
    outputStream.write(schemeEncoded)
    outputStream.write(authorityEncoded)
    outputStream.write(pathEncoded)
}
