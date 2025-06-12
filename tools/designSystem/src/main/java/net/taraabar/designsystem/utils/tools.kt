package net.taraabar.designsystem.utils


const val persianNumbers = "۰۱۲۳۴۵۶۷۸۹"
const val numbers = "0123456789"

fun String.toEnNumber() =
    map { if (it in persianNumbers) numbers[persianNumbers.indexOf(it)] else it }.joinToString("")

fun String.toPersianNumber() =
    map { if (it in numbers) persianNumbers[numbers.indexOf(it)] else it }.joinToString("")


fun String.priceStyle(separatorCharacter: String? = ","): String {
    return if (this.isEmpty() || this.isBlank() || this.length == 1)
        "0"
    else
        this
            .toBigInteger()
            .toString()
            .dropLast(1)
            .reversed()
            .chunked(3)
            .joinToString(separatorCharacter!!)
            .reversed()
            .toPersianNumber()

}