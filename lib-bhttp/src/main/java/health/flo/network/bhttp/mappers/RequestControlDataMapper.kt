package health.flo.network.bhttp.mappers

import health.flo.network.bhttp.model.RequestControlData
import okhttp3.Request

internal class RequestControlDataMapper(
    private val httpAuthorityMapper: HttpAuthorityMapper = HttpAuthorityMapper(),
    private val httpPathAndQueryMapper: HttpPathAndQueryMapper = HttpPathAndQueryMapper(),
) {

    /**
     * Url example: https://www.google.com/search?q=polar%20bears
     *
     * scheme: https
     * authority: www.google.com:433
     * path: /search?q=polar%20bears
     */
    fun map(request: Request): RequestControlData {
        return RequestControlData(
            method = request.method,
            scheme = request.url.scheme,
            authority = httpAuthorityMapper.map(request.url),
            path = httpPathAndQueryMapper.map(request.url),
        )
    }
}
