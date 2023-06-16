package health.flo.network.bhttp.serializer

import health.flo.network.bhttp.NumberOutOfRangeException
import health.flo.network.bhttp.hexEncodedString
import health.flo.test.extensions.toDynamicTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows

internal class NumberSerializersKtTest {

    @TestFactory
    fun `variableLengthEncoded should return number encoded in the appropriate format`() = listOf(
        NumberTestData(0, "00"),
        NumberTestData(9, "09"),
        NumberTestData(29, "1d"),
        NumberTestData(63, "3f"),
        NumberTestData(64, "4040"),
        NumberTestData(15293, "7bbd"),
        NumberTestData(16383, "7fff"),
        NumberTestData(16384, "80004000"),
        NumberTestData(494878333, "9d7f3e7d"),
        NumberTestData(1073741823, "bfffffff"),
        NumberTestData(1073741824, "c000000040000000"),
        NumberTestData(151288809941952652, "c2197c5eff14e88c"),
        NumberTestData(4611686018427387903, "ffffffffffffffff"),
    ).toDynamicTests { (number, encoded) ->
        val actual = number.variableLengthEncoded()

        assertThat(actual.hexEncodedString()).isEqualTo(encoded)
    }

    @TestFactory
    fun `variableLengthEncoded should throw NumberOutOfRangeException when number is not in range`() = listOf(
        -1,
        -151288809941952652,
        4611686018427387904L,
    ).toDynamicTests { number ->
        assertThrows<NumberOutOfRangeException> {
            number.variableLengthEncoded()
        }
    }

    private data class NumberTestData(
        val number: Number,
        val encoded: String,
    )
}
