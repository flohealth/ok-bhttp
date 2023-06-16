package health.flo.network.bhttp.deserializer

internal fun ByteArrayReader.readContent(): ByteArray {
    val length = readInt()

    return read(length)
}
