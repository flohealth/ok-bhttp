package health.flo.network.bhttp

import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response

interface OkHttpBinarySerializer {

    fun serialize(request: Request): RequestBinaryData

    fun deserialize(binaryData: ResponseBinaryData, protocol: Protocol, request: Request): Response

    companion object {

        fun create(): OkHttpBinarySerializer =
            OkHttpBinarySerializerBhttp()
    }
}
