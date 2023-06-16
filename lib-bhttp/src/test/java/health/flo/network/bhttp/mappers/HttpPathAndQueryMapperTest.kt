package health.flo.network.bhttp.mappers

import health.flo.test.extensions.toDynamicTests
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestFactory

private const val TEST_URL_PATH = "https://www.google.com/search"
private const val TEST_PATH = "/search"

private const val TEST_URL_PATH_WITH_QUERY = "https://www.google.com/search?q=polar%20bears"
private const val TEST_PATH_WITH_QUERY = "/search?q=polar%20bears"

private const val TEST_URL_NO_PATH = "https://www.google.com"
private const val TEST_NO_PATH = "/"

private const val TEST_URL_QUERY = "https://www.google.com/?q=polar%20bears"
private const val TEST_QUERY = "/?q=polar%20bears"

internal class HttpPathAndQueryMapperTest {

    private val sut = HttpPathAndQueryMapper()

    @TestFactory
    fun `map should return correct path`() = listOf(
        TestData(TEST_URL_PATH, TEST_PATH),
        TestData(TEST_URL_PATH_WITH_QUERY, TEST_PATH_WITH_QUERY),
        TestData(TEST_URL_NO_PATH, TEST_NO_PATH),
        TestData(TEST_URL_QUERY, TEST_QUERY),
    ).toDynamicTests { (url, pathWithQuery) ->
        val httpUrl = url.toHttpUrl()

        val actual = sut.map(httpUrl)

        assertThat(actual).isEqualTo(pathWithQuery)
    }

    private data class TestData(
        val url: String,
        val pathWithQuery: String,
    )
}
