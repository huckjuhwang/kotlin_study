/**
 * val : 변할 수 없는 상수, 초기화 이후에 값을 변경할 수 없음/ Java에서 final
 * var : 일반적인 변수에 해당함
 */
fun main(){
    var list_one = ArrayList<String>()
    var list = ArrayList<String>()
    list.add("하나")
    list.add("둘")
    list.add("셋")

    for(x in 0..list.size-1){
        println(list.get(x) + " ")
    }


    // 생성자 시점에서 초기화를 해주지 않으면 문법상 오류가 생긴다.
    val a: Int = 1
    val b = 1
    val c: Int = 100

    // 일반적인 변수
    var x = 5
    x += 10

    // 코틀린의 기본변수는 null을 가질 수 없다.
    val q: Int = 15
    //a = null   //  문법상 오류 발생

    // 이와 같이 물음표를 추가했을때만 null을 명시할수 있다.
    var w: Int? = null
    w = null


    /**
     * var와 val 모두 추론을 통해서 Int, String인지 확인이 가능하지만,
     * 실수의 여지가 존재하기 때문에 지정하는 것이 좋다.
     */

    printMessage("Hello")
    printMessageWithPrefix("Hello", "Log")
    printMessageWithPrefix("Hello")
}

// ABC의 객체를 에서 a를 return 하는 함수입니다.
// 이때 a는 Int이면서 null이 될수 있도록 물음표를 추가하였습니다.
// ABC의 객체의 .a를 return 하지만 abc가 null이면 null을 리턴합니다.
/**
    fun abc(abc: ABC?) : Int? {
        return abc?.a
    }
*/


// Any 사용하기
// Any는 Java의 Object에 해당하며, is는 instanceof와 같은 기능으로 동작합니다.
fun getStringLength(obj: Any): Int?{
    if (obj is String) {
        return obj.length
    }
    // String이 아니면 null을 리턴하게 된다.
    return null
}

// 위의 형태를 자바로 구현한다면?
/**
 int getStringLength(Object obj){
    if( obj instanceof String ){
        return obj.length;
    }
    return 0;
 }
*/

// is String이 아님을 나타낼때는 is앞에 "!" 간단하게 부정할 수 있습니다.
/**
 * Java *
 int getStringLength(Object obj){
    if( !(obj instanceof String)) return 0;
    return obj.length;
 }

 * Kotlin *
 fun getStringLength(obj: Any) : Int? {
    if( obj !is String ) return null
    return obj.length
 }
 */

// fun 함수명(변수명: 변수 타입): 리턴 타입 { return 값 }
fun printMessage(message: String): Unit{
    println(message)
}

fun printMessageWithPrefix(message: String, prefix: String = "Info") {
    println("[$prefix] $message")
}



// Loop 문법 정리
// java랑 거의 비슷하게 동작하고, fori or while도 비슷하게 동작합니다.
/**
 * Java *
ArrayList<Stirng> list = new ArrayList<>();
for(String s : list){
Log.d("TAG", "String : " + s);
}

 * Kotlin *
val list = ArrayList<String>()
for(s in list)
log.d("TAG", "String : " + s);
 */


// when 문법정리
// when 은 if문을 중첩하여 사용하지 않고 간단하게 Any와 함께 구현이 가능합니다.
/**
fun main(args: Array<String>) {
    cases("Hello") // String
    cases(1) // Int
    cases(System.currentTimeMillis()) // Long
    cases(MyClass()) // Not a string
    cases("hello") // Unknown
}
 */

// Any == Object
fun cases(obj: Any) {
    when (obj) {
        1 -> println("One")
        "Hello" -> println("Greeting")
        is Long -> println("Long")
        !is String -> println("Not a String")
        else -> println("Unknown")
    }
}


// ranges 문법정리
// 숫자의 범위를 지정하여 사용하는 방법이다.

/**
 * Java *
 for(int i = 1; i<=5; i++) {
    Log.i("TAG", "index : " + i);
 }

 * Kotlin *
 for(x in 1..5) {
    println(x)
 }
 */

