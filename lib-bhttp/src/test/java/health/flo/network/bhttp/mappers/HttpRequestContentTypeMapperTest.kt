package health.flo.network.bhttp.mappers

import health.flo.network.bhttp.model.FieldLine
import okhttp3.MediaType.Companion.toMediaType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private const val HEADER_CONTENT_TYPE = "Content-Type"
private const val TEST_MEDIA_TYPE = "application/json; charset=UTF-8"

internal class HttpRequestContentTypeMapperTest {

    private val sut = HttpRequestContentTypeMapper()

    @Test
    fun `map should return a correct header`() {
        val contentType = TEST_MEDIA_TYPE.toMediaType()
        val expected = FieldLine(
            name = HEADER_CONTENT_TYPE,
            value = TEST_MEDIA_TYPE,
        )

        val actual = sut.map(contentType)

        assertThat(actual).isEqualTo(expected)
    }
}
