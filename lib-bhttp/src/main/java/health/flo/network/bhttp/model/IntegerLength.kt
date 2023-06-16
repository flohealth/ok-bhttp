package health.flo.network.bhttp.model

import kotlin.experimental.and
import kotlin.experimental.or

/**
 * Position of the length bits in a byte: 0bXX000000.
 */
internal enum class IntegerLength(
    val lengthInBytes: Int,
    private val lengthCode: Byte,
) {

    BYTE(1, 0b00),
    SHORT(2, 0b01),
    INT(4, 0b10),
    LONG(8, 0b11);

    companion object {

        fun readLength(byte: Byte): IntegerLength {
            // all this code looks quite strange, it's definitely can be simplified,
            // but unfortunately kotlin do not support literals as 0b11000000 for Byte
            // and do not have shl/shr/ushr implementation for Byte
            fun IntegerLength.isMaskMatches(byte: Byte) = byte.and(bitMaskOfLength) == bitMaskOfLength

            return when {
                LONG.isMaskMatches(byte) -> LONG
                INT.isMaskMatches(byte) -> INT
                SHORT.isMaskMatches(byte) -> SHORT
                else -> BYTE
            }
        }
    }

    private val bitMaskOfLength: Byte
        get() = lengthCode shl 6

    fun setLengthBits(to: Byte) = to.or(bitMaskOfLength)
}

private infix fun Byte.shl(bitCount: Int): Byte = (toInt() shl bitCount).toByte()
