package health.flo.network.bhttp.mappers

import health.flo.network.bhttp.model.FieldLine
import health.flo.network.bhttp.model.FieldSection
import okhttp3.Headers

internal class HttpHeadersMapper {

    fun map(headers: Headers): FieldSection {
        val fieldLines = headers.map { (headerName, headerValue) -> FieldLine(headerName, headerValue) }

        return FieldSection(fieldLines)
    }
}
