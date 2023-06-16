package health.flo.network.bhttp.serializer

import health.flo.network.bhttp.hexEncodedString
import health.flo.test.extensions.toDynamicTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestFactory

internal class StringSerializerKtTest {

    @TestFactory
    fun `binaryEncodedWithLengthPrefix should return encoded string with length prefix`() = listOf(
        StringTestData("https", "05" + "6874747073"),
        StringTestData("www.example.com", "0f" + "7777772e6578616d706c652e636f6d"),
        StringTestData("", "00"),
    ).toDynamicTests { (string, encoded) ->
        val actual = string.binaryEncodedWithLengthPrefix()

        assertThat(actual.hexEncodedString()).isEqualTo(encoded)
    }

    private data class StringTestData(
        val string: String,
        val encoded: String,
    )
}
