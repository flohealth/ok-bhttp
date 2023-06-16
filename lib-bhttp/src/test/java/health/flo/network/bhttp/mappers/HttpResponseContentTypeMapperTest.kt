package health.flo.network.bhttp.mappers

import health.flo.network.bhttp.model.FieldLine
import health.flo.network.bhttp.model.FieldSection
import okhttp3.MediaType.Companion.toMediaType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private const val TEST_MEDIA_TYPE = "application/json"

internal class HttpResponseContentTypeMapperTest {

    private val sut = HttpResponseContentTypeMapper()

    @Test
    fun `map should return null if no content type header found`() {
        val expected = null

        val actual = sut.map(FieldSection(emptyList()))

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `map should return content type from headers`() {
        val contentTypeHeader = FieldLine(
            name = "content-type",
            value = TEST_MEDIA_TYPE,
        )
        val expected = TEST_MEDIA_TYPE.toMediaType()

        val actual = sut.map(FieldSection(listOf(contentTypeHeader)))

        assertThat(actual).isEqualTo(expected)
    }
}
