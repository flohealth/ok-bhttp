package health.flo.network.bhttp.serializer

import health.flo.network.bhttp.model.FieldLine

internal fun FieldLine.binaryEncoded(): ByteArray {
    return name.binaryEncodedWithLengthPrefix() + value.binaryEncodedWithLengthPrefix()
}
