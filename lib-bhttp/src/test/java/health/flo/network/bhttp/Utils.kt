package health.flo.network.bhttp

import okhttp3.internal.parseHexDigit

fun ByteArray.hexEncodedString(): String {
    return this.joinToString(separator = "") { byte -> "%02x".format(byte) }
}

fun String.parseHexEncodedByteArray(): ByteArray {
    return List(length / 2) { index ->
        val indexInString = index * 2
        val parsedFirst = this[indexInString].parseHexDigit()
        val parsedSecond = this[indexInString + 1].parseHexDigit()

        ((parsedFirst shl 4) or parsedSecond).toByte()
    }.toByteArray()
}

fun String.parseHexEncodedByte(): Byte {
    val parsedFirst = this[0].parseHexDigit()
    val parsedSecond = this[1].parseHexDigit()

    return ((parsedFirst shl 4) or parsedSecond).toByte()
}
