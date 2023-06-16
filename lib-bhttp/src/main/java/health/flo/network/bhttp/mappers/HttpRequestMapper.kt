package health.flo.network.bhttp.mappers

import health.flo.network.bhttp.model.FieldSection.Companion.empty
import health.flo.network.bhttp.model.KnownLengthRequest
import health.flo.network.bhttp.model.plus
import okhttp3.Request

internal class HttpRequestMapper(
    private val requestControlDataMapper: RequestControlDataMapper = RequestControlDataMapper(),
    private val httpHeadersMapper: HttpHeadersMapper = HttpHeadersMapper(),
    private val httpBodyMapper: HttpBodyMapper = HttpBodyMapper(),
    private val httpRequestContentTypeMapper: HttpRequestContentTypeMapper = HttpRequestContentTypeMapper(),
) {

    fun map(request: Request): KnownLengthRequest {
        val content: ByteArray = request.body?.let(httpBodyMapper::map) ?: byteArrayOf()
        val contentTypeHeader = request.body?.contentType()
            ?.let(httpRequestContentTypeMapper::map)

        return KnownLengthRequest(
            requestControlData = requestControlDataMapper.map(request),
            headerSection = httpHeadersMapper.map(request.headers) + listOfNotNull(contentTypeHeader),
            content = content,
            trailerSection = empty(),
        )
    }
}
