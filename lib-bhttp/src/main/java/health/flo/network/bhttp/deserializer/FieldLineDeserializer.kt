package health.flo.network.bhttp.deserializer

import health.flo.network.bhttp.model.FieldLine

internal fun ByteArrayReader.readFieldLine() = FieldLine(
    name = readString(),
    value = readString(),
)
