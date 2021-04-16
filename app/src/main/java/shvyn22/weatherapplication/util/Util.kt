package shvyn22.weatherapplication.util

fun String.toStringTime(): String {
    return this.substring(this.indexOf("T") + 1, this.indexOf("."))
}

fun String.toStringDateTime(): String {
    return this.substring(0, this.indexOf("."))
}

fun String.normaliseDate(isShort: Boolean): String {
    val firstDiv = this.indexOf("-")
    val secondDiv = this.lastIndexOf("-")
    val year = this.substring(0, firstDiv)
    val month = this.substring(firstDiv + 1, secondDiv)
    val day = this.substring(secondDiv + 1)

    return if (isShort) "$day.$month" else "$day.$month.$year"
}