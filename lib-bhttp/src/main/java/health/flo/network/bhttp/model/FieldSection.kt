package health.flo.network.bhttp.model

internal data class FieldSection(
    val fieldLines: List<FieldLine>,
) {

    companion object {

        fun empty() = FieldSection(emptyList())
    }
}

internal operator fun FieldSection.plus(linesToAdd: Iterable<FieldLine>) = FieldSection(fieldLines + linesToAdd)
