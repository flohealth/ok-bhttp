package health.flo.network.bhttp.deserializer

import health.flo.network.bhttp.model.FieldSection

internal fun ByteArrayReader.readFieldSection(): FieldSection {
    val length = readInt()

    if (length == 0) {
        return FieldSection(emptyList())
    }

    val fieldLinesReader = slice(length)
    val fieldLines = buildList {
        while (fieldLinesReader.canRead) {
            add(fieldLinesReader.readFieldLine())
        }
    }

    return FieldSection(fieldLines)
}
