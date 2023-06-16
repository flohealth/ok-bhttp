package health.flo.network.bhttp.model

import health.flo.network.bhttp.model.IntegerLength.BYTE
import health.flo.network.bhttp.model.IntegerLength.INT
import health.flo.network.bhttp.model.IntegerLength.LONG
import health.flo.network.bhttp.model.IntegerLength.SHORT
import health.flo.network.bhttp.parseHexEncodedByte
import health.flo.test.extensions.toDynamicTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestFactory

internal class IntegerLengthTest {

    @TestFactory
    fun `readLength should return a correct integer length`() = listOf(
        lengthTestData("00", BYTE),
        lengthTestData("09", BYTE),
        lengthTestData("1d", BYTE),
        lengthTestData("3f", BYTE),
        lengthTestData("4040", SHORT),
        lengthTestData("7bbd", SHORT),
        lengthTestData("7fff", SHORT),
        lengthTestData("80004000", INT),
        lengthTestData("9d7f3e7d", INT),
        lengthTestData("bfffffff", INT),
        lengthTestData("c000000040000000", LONG),
        lengthTestData("c2197c5eff14e88c", LONG),
        lengthTestData("ffffffffffffffff", LONG),
    ).toDynamicTests { (input, expected) ->
        val actual = IntegerLength.readLength(input)

        assertThat(actual).isEqualTo(expected)
    }

    private fun lengthTestData(hexByte: String, length: IntegerLength) =
        IntegerLengthTestData(
            input = hexByte.parseHexEncodedByte(),
            length = length,
        )

    private data class IntegerLengthTestData(
        val input: Byte,
        val length: IntegerLength,
    )
}
