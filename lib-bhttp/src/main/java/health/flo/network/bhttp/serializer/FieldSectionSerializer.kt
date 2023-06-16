package health.flo.network.bhttp.serializer

import health.flo.network.bhttp.model.FieldSection
import java.io.ByteArrayOutputStream

internal fun FieldSection.binaryEncoded(): ByteArray {
    val outputStream = ByteArrayOutputStream()
    fieldLines.forEach { fieldLine -> outputStream.write(fieldLine.binaryEncoded()) }

    return outputStream.toByteArray()
        .withLengthPrefix()
}
