package health.flo.network.bhttp.mappers

import okhttp3.RequestBody
import okio.buffer
import okio.sink
import java.io.ByteArrayOutputStream

internal class HttpBodyMapper {

    fun map(body: RequestBody): ByteArray {
        val length = body.contentLength().toInt()

        if (length <= 0) {
            return byteArrayOf()
        }

        val stream = ByteArrayOutputStream(length)

        stream.sink()
            .buffer()
            .use { bufferedSink ->
                body.writeTo(bufferedSink)
                bufferedSink.flush()

                return stream.toByteArray()
            }
    }
}
