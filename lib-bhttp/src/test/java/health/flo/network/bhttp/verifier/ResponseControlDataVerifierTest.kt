package health.flo.network.bhttp.verifier

import health.flo.network.bhttp.ResponseDataFactory.responseControlData
import health.flo.network.bhttp.UnknownStatusCodeException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class ResponseControlDataVerifierTest {

    @ParameterizedTest
    @ValueSource(ints = [100, 200, 403, 404, 500])
    fun `verified should return the same instance when status code is correct`(statusCode: Int) {
        val expected = responseControlData(statusCode = statusCode)

        val actual = expected.verified()

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [-5, 0, 1, 99, 600, 99999])
    fun `verified should throw UnknownStatusCodeException when status code is incorrect`(statusCode: Int) {
        val controlData = responseControlData(statusCode = statusCode)

        assertThrows<UnknownStatusCodeException> { controlData.verified() }
    }
}
