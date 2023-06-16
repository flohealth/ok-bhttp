package health.flo.network.bhttp.serializer

internal fun ByteArray.lengthPrefix(): ByteArray = size.variableLengthEncoded()

internal fun ByteArray.withLengthPrefix(): ByteArray = lengthPrefix() + this

internal fun emptySectionWithLengthPrefix(): ByteArray {
    return 0.variableLengthEncoded()
}
