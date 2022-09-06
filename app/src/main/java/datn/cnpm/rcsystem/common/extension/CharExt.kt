package datn.cnpm.rcsystem.common.extension

fun Char.isAlphabet(): Boolean {
    return this in 'a'..'z' || this in 'A'..'Z'
}

fun Char.isNumeric(): Boolean {
    return this in '0'..'9'
}