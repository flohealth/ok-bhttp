package health.flo.network.bhttp.serializer

import health.flo.network.bhttp.BhttpDefaults
import java.nio.charset.Charset

internal fun String.binaryEncodedWithLengthPrefix(charset: Charset = BhttpDefaults.charset): ByteArray {
    return this.toByteArray(charset)
        .withLengthPrefix()
}
