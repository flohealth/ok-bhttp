package health.flo.network.bhttp

import health.flo.network.bhttp.model.FieldLine
import health.flo.network.bhttp.model.FieldSection
import health.flo.network.bhttp.model.KnownLengthRequest
import health.flo.network.bhttp.model.RequestControlData
import health.flo.network.bhttp.utils.emptyByteArray

internal object RequestDataFactory {

    fun knownLengthRequestTrimmed() = KnownLengthRequest(
        requestControlData = requestControlData(),
        headerSection = headerSection(),
        content = emptyByteArray(),
        trailerSection = FieldSection.empty(),
    )

    private fun requestControlData() = RequestControlData(
        method = "GET",
        scheme = "https",
        authority = "www.example.com",
        path = "/hello.txt",
    )

    private fun headerSection() = FieldSection(
        listOf(
            FieldLine("Accept-Language", "en, mi"),
            FieldLine("User-Agent", "curl/7.16.3 libcurl/7.16.3 OpenSSL/0.9.7l zlib/1.2.3"),
        ),
    )

    fun knownLengthRequestWithBody() = KnownLengthRequest(
        requestControlData = requestControlData(),
        headerSection = headerSection(),
        content = body(),
        trailerSection = FieldSection.empty(),
    )

    private fun body(): ByteArray = "This is a test.".toByteArray(BhttpDefaults.charset)

    fun knownLengthRequestWithBodyHex(): String = listOf(
        // Framing indicator:
        "00",

        // Request control data:

        "03", // Method length (3)
        "474554", // Method (GET)
        "05", // Scheme length (5)
        "6874747073", // Scheme (https)
        "0f", // Authority length (15)
        "7777772e6578616d706c652e636f6d", // Authority (www.example.com)
        "0a", // Path length (10)
        "2f68656c6c6f2e747874", // Path(/hello.txt)

        // Known-length heading field section:
        "4057", // Section length (87)

        // Field line:
        "0f", // Name length(15)
        "4163636570742d4c616e6775616765", // Name(Accept-Language)
        "06", // Value length (6)
        "656e2c206d69", // Value(en, mi)

        // Field line:
        "0a", // Name length(10)
        "557365722d4167656e74", // Name(User-Agent)
        "34", // Value length(52)
        // Value(curl/7.16.3 libcurl/7.16.3 OpenSSL/0.9.7l zlib/1.2.3)
        "6375726c2f372e31362e33206c69626375726c2f372e31362e33204f70656e53534c2f302e392e376c207a6c69622f312e322e33",

        // Content
        "0f", // Length (15)
        "54686973206973206120746573742e", // Content (This is a test.)

        // Known-length trailing field section
        "00",
    ).joinToString(separator = "")
}
