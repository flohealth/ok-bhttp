package health.flo.network.bhttp.mappers

import okhttp3.HttpUrl

internal class HttpAuthorityMapper {

    fun map(httpUrl: HttpUrl): String {
        return "${httpUrl.host}:${httpUrl.port}"
    }
}
