package health.flo.network.bhttp.serializer

import health.flo.network.bhttp.RequestDataFactory.knownLengthRequestWithBody
import health.flo.network.bhttp.RequestDataFactory.knownLengthRequestWithBodyHex
import health.flo.network.bhttp.hexEncodedString
import health.flo.network.bhttp.model.KnownLengthRequest
import health.flo.test.extensions.toDynamicTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestFactory

internal class KnownLengthRequestSerializerKtTest {

    @TestFactory
    fun `binaryEncoded should encode request properly`() = listOf(
        SerializerTestData(knownLengthRequestWithBody(), knownLengthRequestWithBodyHex()),
    ).toDynamicTests { (request, hex) ->
        val actual = request.binaryEncoded()

        assertThat(actual.hexEncodedString()).isEqualTo(hex)
    }

    private data class SerializerTestData(
        val request: KnownLengthRequest,
        val encodedHex: String,
    )
}
