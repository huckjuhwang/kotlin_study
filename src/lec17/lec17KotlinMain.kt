package lec17

//
//fun main() {
//    val fruits = listOf(
//        Fruit("사과", 1000),
//        Fruit("사과", 2000),
//        Fruit("사과", 3000),
//        Fruit("바나나", 4000),
//        Fruit("바나나", 5000),
//        Fruit("바나나", 6000),
//        Fruit("수박", 11000),
//    )
//
//
//    // 람다를 만드는 방법 1
//    val isApple: (Fruit) -> Boolean = fun(fruit: Fruit): Boolean {
//        return fruit.name == "사과"
//    }
//    // 람다를 만드는 방법 2
//    val isApple2: (Fruit) -> Boolean = { fruit : Fruit -> fruit.name == "사과"}
//
//    // 람다를 호출하는 방법 1
//    println(isApple(fruits[0]))
//    // 람다를 호출하는 방법 2
//    println(isApple2.invoke(fruits[0]))
//
//
//    val filterFru = filterFruits(fruits) { fruit: Fruit -> fruit.name == "사과" }
//    for (fruit in filterFru) {
//        println(fruit.name + fruit.price)
//    }
//
//
//}
//
//private fun filterFruits(
//    fruits: List<Fruit>, filter: (Fruit) -> Boolean
//): List<Fruit> {
//    val results = mutableListOf<Fruit>()
//    for (fruit in fruits) {
//        if (filter(fruit)) {
//            results.add(fruit)
//        }
//    }
//    return results
//}
//
//
//private fun filterFru(
//    fruits: List<Fruit>
//): List<Fruit> {
//    return fruits.filter { fruit -> fruit.name == "사과" }
//}