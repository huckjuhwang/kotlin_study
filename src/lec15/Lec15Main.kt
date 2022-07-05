package lec15

fun main() {
    val numbers = mutableListOf(100, 200)
    // 하나를 가져오기
    println(numbers[0])

    // for each
    for (number in numbers) {
        println(number)
    }

    // 전통적 for문
    for ((index, number) in numbers.withIndex()) {
        println("$index $number")
    }
}

