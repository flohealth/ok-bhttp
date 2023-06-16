package health.flo.network.bhttp.deserializer

import health.flo.network.bhttp.model.ResponseControlData
import health.flo.network.bhttp.verifier.verified

internal fun ByteArrayReader.readResponseControlData(): ResponseControlData {
    val statusCode = readInt()

    return ResponseControlData(statusCode).verified()
}
