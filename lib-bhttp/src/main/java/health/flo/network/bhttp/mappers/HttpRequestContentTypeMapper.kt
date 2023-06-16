package health.flo.network.bhttp.mappers

import health.flo.network.bhttp.model.FieldLine
import okhttp3.MediaType

private const val HEADER_CONTENT_TYPE = "Content-Type"

internal class HttpRequestContentTypeMapper {

    fun map(contentType: MediaType): FieldLine = FieldLine(
        name = HEADER_CONTENT_TYPE,
        value = contentType.toString(),
    )
}
