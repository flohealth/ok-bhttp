package health.flo.network.bhttp

import health.flo.network.bhttp.deserializer.ByteArrayReader
import health.flo.network.bhttp.deserializer.readInt
import health.flo.network.bhttp.deserializer.readKnownLengthResponse
import health.flo.network.bhttp.mappers.HttpRequestMapper
import health.flo.network.bhttp.mappers.HttpResponseMapper
import health.flo.network.bhttp.model.KnownLengthResponse
import health.flo.network.bhttp.serializer.binaryEncoded
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response

internal class OkHttpBinarySerializerBhttp(
    private val httpRequestMapper: HttpRequestMapper = HttpRequestMapper(),
    private val httpResponseMapper: HttpResponseMapper = HttpResponseMapper(),
) : OkHttpBinarySerializer {

    override fun serialize(request: Request): RequestBinaryData {
        return httpRequestMapper.map(request)
            .binaryEncoded()
            .let(::RequestBinaryData)
    }

    override fun deserialize(binaryData: ResponseBinaryData, protocol: Protocol, request: Request): Response {
        val reader = ByteArrayReader.fromByteArray(binaryData.data)

        val knownLengthResponse = when (val framingIndicator = reader.readInt()) {
            KnownLengthResponse.FRAMING_INDICATOR -> reader.readKnownLengthResponse()
            else -> throw UnknownFramingIndicatorException(
                framingIndicator
            )
        }

        return httpResponseMapper.map(knownLengthResponse, protocol, request)
    }
}
