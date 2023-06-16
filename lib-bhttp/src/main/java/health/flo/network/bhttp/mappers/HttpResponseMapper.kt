package health.flo.network.bhttp.mappers

import health.flo.network.bhttp.model.KnownLengthResponse
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

internal class HttpResponseMapper(
    private val httpResponseCodeToMessageMapper: HttpResponseCodeToMessageMapper = HttpResponseCodeToMessageMapper(),
    private val httpResponseContentTypeMapper: HttpResponseContentTypeMapper = HttpResponseContentTypeMapper(),
) {

    fun map(knownLengthResponse: KnownLengthResponse, protocol: Protocol, request: Request): Response {
        val builder = Response.Builder()
            .code(knownLengthResponse.controlData.statusCode)
            .message(httpResponseCodeToMessageMapper.map(knownLengthResponse.controlData.statusCode))
            .protocol(protocol)
            .request(request)

        knownLengthResponse.headerSection.fieldLines.forEach { header ->
            builder.addHeader(header.name, header.value)
        }

        val contentType = httpResponseContentTypeMapper.map(knownLengthResponse.headerSection)
        val body = knownLengthResponse.content.toResponseBody(contentType)
        builder.body(body)

        return builder.build()
    }
}
