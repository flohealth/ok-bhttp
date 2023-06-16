package health.flo.network.bhttp.mappers

import health.flo.network.bhttp.BodyDataFactory.TEST_BODY
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private const val TEST_MEDIA_TYPE = "application/json"

internal class HttpBodyMapperTest {

    private val sut = HttpBodyMapper()

    @Test
    fun `map should return byte array with the request's body`() {
        val mediaType = TEST_MEDIA_TYPE.toMediaType()
        val body = TEST_BODY.toRequestBody(mediaType)

        val actual = sut.map(body)

        assertThat(String(actual)).isEqualTo(TEST_BODY)
    }
}
