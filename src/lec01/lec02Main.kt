package lec01

import java.lang.IllegalArgumentException

fun main(){

    var str :String? = null
//    Kotlin: Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type String?
//    println(str.length)

    println(str?.length)
    startWithA5(str)
}


fun startWithA1(str: String): Boolean {
    return str.startsWith("A")
}

fun startWithA2(str: String?): Boolean {
    return str?.startsWith("A")
        ?: throw IllegalArgumentException("null check")
}

fun startWithA3(str: String?): Boolean {
    return str?.startsWith("A")
        ?: false
}

// 리턴값에 null을 허용해준다.
fun startWithA4(str: String?): Boolean? {
    return str?.startsWith("A")
}


// str은 null일수가 없다.
fun startWithA5(str: String?): Boolean? {
    // 런타임 시점에 NPE발생!!
    return str!!.startsWith("A")
}
