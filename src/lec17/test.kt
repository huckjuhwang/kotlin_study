package lec17


fun main() {
    val fruitsInList: List<List<Fruit>> = listOf(
        listOf(
            Fruit(1L, "사과", 1000L, 1500L),
            Fruit(2L, "사과", 1000L, 1500L),
            Fruit(3L, "사과", 1500L, 1500L),
            Fruit(4L, "사과", 1000L, 1500L),
        ),
        listOf(
            Fruit(5L, "바나나", 1500L, 1500L),
            Fruit(6L, "바나나", 1000L, 1500L),
            Fruit(7L, "바나나", 1000L, 1500L),
        ),
        listOf(
            Fruit(8L, "수박", 10000L, 10000L),
        )
    )


}

val List<Fruit>.samePriceFilter: List<Fruit>
    get() = this.filter(Fruit::isSamePrice)


data class Fruit(
    val id: Long,
    val name: String,
    val factoryPrice: Long,
    val currentPrice: Long,
) {
    val isSamePrice: Boolean
        get() = factoryPrice == currentPrice
}