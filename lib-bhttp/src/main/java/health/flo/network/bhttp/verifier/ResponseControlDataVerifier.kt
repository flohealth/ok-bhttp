package health.flo.network.bhttp.verifier

import health.flo.network.bhttp.UnknownStatusCodeException
import health.flo.network.bhttp.model.ResponseControlData

private const val STATUS_CODE_FIRST = 100
private const val STATUS_CODE_LAST = 599

@Throws(UnknownStatusCodeException::class)
internal fun ResponseControlData.verified(): ResponseControlData {
    if (statusCode !in STATUS_CODE_FIRST..STATUS_CODE_LAST) {
        throw UnknownStatusCodeException(statusCode)
    }

    return this
}
