package lec01


fun main(){
    // variable
    var number1: Long = 10L
    number1 = 4L

//    Val cannot be reassigned!!!
    val number2: Long = 10L
//    number2 = 5L

    // null값을 넣어주기 위해 ?를 사용할 수 있다.
    var number3: Long? = 10000
    number3 = null

    var person = Person("name")
}

data class Person(
    var name: String,
)
