
// class name != file name 상이해도 상관 없다.
// 자바와 다른점중에 하나이다.
//class Human constructor(name: String){

/**
 * java

 class Person {
    public Person(String name){ }
    public Person(String name, int age) { }
 }
 */


// 주 생성자
open class Human (val name: String = "Anonymous"){
    // 부 생성자 ( 항상 부생성자는 주생성자의 내용을 위임 받아야한다. ) ==> 오버로딩
    constructor(name: String, age: Int) : this(name){
        println("my name is ${name}, ${age} years old")
    }

    // 주생성자의 일부이기 때문에 제일 먼저 수행이 된다.
    init {
        println("new human has been born!!")
    }

    fun eatingCake(){
        println("This is so YUMMYY~~~~")
    }

    open fun singASong(){
        println("lalala")
    }
}

// kotlin은 final class이기 때문에 같은 클래스에 있더라도 접근이 안된다.
// 따라서 호출하려는 class에 open을 해줘야한다.
class Koean : Human() {

    // 오버라이딩
    override fun singASong(){
        super.singASong()
        println("라라라")
        println("my name is ${name}")
    }

}

fun main(){
    var human = Human("dante")
    human.eatingCake()
    println("this human`s name is ${human.name}")
    println()

    var human2 = Human()
    println("this human`s name is ${human2.name}")
    println()


    var mom = Human("kai", 52)

    var koean = Koean()
    koean.singASong()
}