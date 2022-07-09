## 이모저모


### 1. Type Alias와 as import
```kotlin
fun filterFruits(fruits: List<Fruit>, filter: (Fruit) -> Boolean) {
    
}
```
- 파라미터가 많아 진다면, filter가 너무 길어질 가능성이 있다.

```kotlin
typealias FruitFilter = (Fruit) -> Boolean

fun filterFruits(fruits: List<Fruit>, filter: FruitFilter) {
    
}
```
- typealias 키워드를 활용하여, 전체 타입을 쓰는게 아닌 별칭으로 사용할 수 이따.

```kotlin
data class UltraSuperGuardianTribe(
    val name: String
)

typealias USGTMap = Map<String, UltraSuperGuardianTribe>
```
- 이름 긴 클래스를 컬렉션에 사용할 때도 간단히 줄일 수 있다.

### 2. as import
- 다른 패키지에 같은 이름 함수를 동시에 가져오고 싶다면?? <br>
`as import` : 어떤 클래스나 함수를 임포트 할 때, 이름을 바꾸는 기능
```kotlin
import com.lec19.a

fun printHelloWorld(){
    println("hello A")
}
```
```kotlin
import com.lec19.b

fun printHelloWorld(){
    println("hello B")
}
```

```kotlin
import lec19.a.printHelloWorld as printHelloWorldA
import lec19.b.printHelloWorld as printHelloWorldB

fun main(){
    printHelloWorldA()
    printHelloWorldB()
}
```
- 한 파일 내에서 같은 메소드를 import 가능하다


### 3. 구조분해와 componentN 함수
```kotlin
val person = Person("최태현", 100)
val (name, age) = person
```

```kotlin
val person = Person("최태현", 100)
val name = person.component1()
val age = person.component2()
```
- component1은 첫번째 프로퍼티를 가져오고, 2는 두번째 프로퍼티를 가져온다. (`componentN함수`)
- `name, age`를 인식해서 넣어주는 것이 아닌 순서대로 들어가는 것 이다.
- Data Class가 아닌데, 구조분해를 사용하고 싶다면, componentN함수를 직접 구현도 가능하다.

```kotlin
val map = mapOf(1 to "A", 2 to "B")
for ((key, value) in map.entries) {
    
}
```
- 이 문법 역시 구조분해 입니다!


### Jump와 Label
- `return / break / continue `
- 자바와 완전히 동일하지만 다른상황이 있다.
```kotlin
fun main() {
    val numbers = listOf(1, 2, 3)
    numbers.map { number -> number + 1 }
        .forEach{ number -> println(number) }
    // 2,3,4
}
```


```kotlin
fun main() {
    val numbers = listOf(1, 2, 3)
    numbers.map { number -> number + 1 }
        .forEach{ number -> println(number) }
    // 2,3,4
}
```
- forEach에서는 continue, break를 사용할 수 없다.
```kotlin
fun main() {
    val numbers = listOf(1, 2, 3)

    for (number in numbers) {
        if (number == 2) {
            break
        }
    }
    
    // break
    run {
        numbers.forEach{ number ->
            if (number == 2) {
                return@run
            }
        }
    }
    
    // continue
    numbers.forEach{ number ->
        if (number == 2) {
            return@forEach
        }
    }
}
```
- 만약 꼭사용해야 한다면, 이와 같이 사용할 수 있다.
- 하지만, 이렇게 사용하는 것 보단, 익숙한 for문을 사용하는 것이 좋다.

### Label

- 특정 expression에 `라벨이름@`를 붙여 하나의 라벨로 간주하고, `break, continue, return`

```kotlin
abc@ for (i in 1..100) {
    for (j in 1..100) {
        if (j == 2) {
            break@abc
        }
        println("${i} ${j}")
        // 1 1만 출력이 된다.
    }
}
```
- 라벨을 사용한 Jump는 사용하지 않는 것이 강력 추천..!!

### TakeIf 와 TakeUnless
```kotlin
fun getNumberOrNull(): Int? {
    return if (number <= 0) {
        null
    } else {
        number
    }
}
```
- kotlin에서는 method chaning을 위한 특이한 함수가 제공된다.
```kotlin
fun getNumberOrNullV2(): Int? {
    return number.takeIf {it > 0}
}
```
- 주어진 조건을 만족하면 그 값이, 그렇지 않으면 null이 반환된다.

```kotlin
fun getNumberOrNullV3(): Int? {
    return number.takeUnless {it <= 0}
}
```
- 주어진 조건을 만족하지 않으면 그 값이, 그렇지 않다면 null이 반환된다.