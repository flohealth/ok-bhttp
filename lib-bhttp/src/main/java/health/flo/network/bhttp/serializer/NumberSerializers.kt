package health.flo.network.bhttp.serializer

import health.flo.network.bhttp.NumberOutOfRangeException
import health.flo.network.bhttp.model.IntegerLength
import health.flo.network.bhttp.model.IntegerLength.BYTE
import health.flo.network.bhttp.model.IntegerLength.INT
import health.flo.network.bhttp.model.IntegerLength.LONG
import health.flo.network.bhttp.model.IntegerLength.SHORT
import java.nio.ByteBuffer

/**
 * According to BHTTP spec [https://datatracker.ietf.org/doc/rfc9292/]
 * Variable-Length Integer Encoding from QUIC should be used
 * [https://datatracker.ietf.org/doc/html/draft-ietf-quic-transport-16#section-16]
 */
internal fun Number.variableLengthEncoded(): ByteArray {
    return when (this.toLong()) {
        // 0-63, 1 byte: 2 bits for length id (00) + 6 usable bits
        in 0..63 -> this.toByte().encodeWithLength()
        // 0-16383, 2 bytes: 2 bits for length id (01) + 14 usable bits
        in 64..16383 -> this.toShort().encodeWithLength()
        // 0-1073741823, 4 bytes: 2 bits for length id (10) + 30 usable bits
        in 16384..1073741823 -> this.toInt().encodeWithLength()
        // 0-4611686018427387903, 8 bytes: 2 bits for length id (11) + 62 usable bits
        in 1073741824L..4611686018427387903L -> this.toLong().encodeWithLength()
        else -> throw NumberOutOfRangeException()
    }
}

private fun Byte.encodeWithLength(length: IntegerLength = BYTE): ByteArray {
    return encodeWithLength(length, ByteBuffer::put)
}

private fun Short.encodeWithLength(length: IntegerLength = SHORT): ByteArray {
    return encodeWithLength(length, ByteBuffer::putShort)
}

private fun Int.encodeWithLength(length: IntegerLength = INT): ByteArray {
    return encodeWithLength(length, ByteBuffer::putInt)
}

private fun Long.encodeWithLength(length: IntegerLength = LONG): ByteArray {
    return encodeWithLength(length, ByteBuffer::putLong)
}

private inline fun <reified T : Number> T.encodeWithLength(
    length: IntegerLength,
    put: ByteBuffer.(T) -> ByteBuffer,
): ByteArray {
    return ByteBuffer.allocate(length.lengthInBytes)
        .put(this)
        .array()
        .setLengthBits(length)
}

private fun ByteArray.setLengthBits(length: IntegerLength): ByteArray = this.apply {
    this[0] = length.setLengthBits(to = this[0])
}
