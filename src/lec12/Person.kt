package lec12


fun main() {
    println(Singleton.a)    // 0
    Singleton.a += 10
    println(Singleton.a)    // 10
}

object Singleton{
    var a: Int= 0
}

class Person private constructor(
    var name: String,
    var age: Int
) {

    companion object {
        val MIN_AGE = 1
        fun newBaby(name: String): Person {
            return Person(name, MIN_AGE)
        }
    }
}
