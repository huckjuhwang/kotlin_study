/**
 *  기본 문법 정리
 *  https://kotlinlang.org/docs/basic-syntax.html#functions
 *
 */

fun main(args: Array<String>){
    println("Hello world!")
    println(sum(3, 5))
    printSum(3, 5)
}

/**
 *  2개의 parameters and Int return type
fun sum(a: Int, b: Int) : Int{
return a+b;
}
 */

fun sum(a: Int, b: Int) = a + b

/**
 * : Unit은 void랑 같은 역할
 * : Unit은 생략해도 문제없이 동작한다.
 */
fun printSum(a: Int, b: Int) : Unit {
    println("sum of $a and $b is ${a + b}")
}




