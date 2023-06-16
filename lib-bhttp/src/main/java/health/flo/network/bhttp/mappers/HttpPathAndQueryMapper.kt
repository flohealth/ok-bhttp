package health.flo.network.bhttp.mappers

import okhttp3.HttpUrl

internal class HttpPathAndQueryMapper {

    fun map(httpUrl: HttpUrl): String {
        val path = httpUrl.encodedPath
        val query = when (httpUrl.encodedQuery.isNullOrBlank()) {
            true -> ""
            false -> "?${httpUrl.encodedQuery}"
        }

        return path + query
    }
}
