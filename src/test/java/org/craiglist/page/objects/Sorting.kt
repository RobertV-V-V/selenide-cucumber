package org.craiglist.page.objects

enum class Sorting(val selector: String) {
    lowest_price("priceasc"),
    highest_price("pricedsc"),
    newest("date"),
    relevant("rel"),
    upcoming("upcoming")
}