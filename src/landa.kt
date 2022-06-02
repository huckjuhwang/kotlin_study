// 1. Lamda
// 람다식은 우리가 마치 value 처럼 다룰 수 있는 익명함수이다.
//   1) 메소드의 파라미터로 넘겨줄수 있다. fun max( a: Int )
//   2) return 값으로 사용할 수 가 있다.

// 람다의 기본정의
// val lamdaName : Type = {argumentList -> codeBody}


// (input type) -> (output type)
// type 추론이 가능하기 때문에, number가 자동으로 Int로 지정된다.
val square : (Int) -> (Int) = {number -> number*number }

// 람다에선 무조건 마지막에 있는 값이 리턴 값이다.1
val nameAge = {name : String, age : Int ->
    "my name is ${name} I`m ${age}"
}


fun main(){
    println(square(3))
    println(nameAge("dante", 25))

    val a = "dante said : "
    println(pizzaIsGreat(a))

    println(extendString("dante", 25))
    println(calculateGrade(97))


    var lamda = {number : Double ->
        number == 4.3213
    }

    println(invokeLamda(lamda))
    println(invokeLamda{it > 5.22})

}


// 확장함수
// 이 클래스는 좋지만, 조금 확장하고 싶어
// String class를 확장하여 사용함
val pizzaIsGreat : String.() -> String = {
    this + " Pizza is the best"
}


fun extendString(name : String, age : Int) : String {
    // 확장함수의 매개변수가 하나이기 때문에, it으로 접근이 가능하다
    val introduceMyself : String.(Int) -> String = {"I am ${this} and ${it} years old"}
    return name.introduceMyself(age)
}


// 람다의 return
// 람다는 마지막 줄이 리턴이 된다.
// ( input type ) -> output type
val calculateGrade : (Int) -> String = {
    when (it) {
        in 0..40 -> "fail"
        in 41..70 -> "pass"
        in 71..100 -> "perfect"
        else -> "Error"
    }
}


// 람다를 표현하는 여러가지 방식
// 1) 메소드의 파라미터로 넘겨줄수 있다. fun max( a: Int )
fun invokeLamda(lamda: (Double) -> Boolean): Boolean {
    return lamda(5.2343)
}
