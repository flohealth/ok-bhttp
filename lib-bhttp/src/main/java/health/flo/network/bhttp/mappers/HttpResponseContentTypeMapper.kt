package health.flo.network.bhttp.mappers

import health.flo.network.bhttp.model.FieldSection
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType

private const val HEADER_CONTENT_TYPE = "Content-Type"

internal class HttpResponseContentTypeMapper {

    fun map(headers: FieldSection): MediaType? {
        return headers.fieldLines.firstOrNull { header -> header.name.equals(HEADER_CONTENT_TYPE, ignoreCase = true) }
            ?.value
            ?.toMediaType()
    }
}
