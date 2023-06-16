package health.flo.network.bhttp.deserializer

import health.flo.network.bhttp.BhttpDefaults
import java.nio.charset.Charset

internal fun ByteArrayReader.readString(charset: Charset = BhttpDefaults.charset): String {
    val length = readInt()
    val byteArray = read(length)

    return String(byteArray, charset)
}
