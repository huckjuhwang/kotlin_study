
## lec15. 배열과 컬렉션
### 배열
```kotlin
fun main(){
  val array = arrayOf(100, 200)

  for (i in array.indices) {
      println("${i} ${array[i]} ")
  }


  for ((idx, value) in array.withIndex()) {
    println("${idx} ${value} ")
  }
}
```
- array.indices는 0부터 마지막 index까지의 Range이다.
- withIndex()를 사용하면, 인덱스와 값을 한번에 가져올 수 있다.

### Collection - List, Set, Map
- 컬렉션을 만들어줄 때 불변인지, 가변인지를 설정해야 한다.
- `가변(Mutable) 컬렉션` : 컬렉션에 element를 추가, 삭제할 수 있다.
- `불변 컬렉션` : 컬렉션에 element를 추가, 삭제할 수 없다.
    - 불변 컬렉션이라 하더라도, Reference Type인 `Element`의 필드는 바꿀 수 있다.

#### List
```kotlin
fun main(){
  val numbers = listOf<Int>(100, 200)
  val emptyList = emptyList<Int>()
  
}
```
- listOf를 통해 불변리스트를 만들수 있고, emptyList<타입>()을 통해서 빈리스트 생성도 가능하다.
```kotlin
fun main() {
    val emptyList = emptyList<Int>()
}

private fun useNumbers(numbers: List<Int>) {
}
```
- 타입을 추론 가능하다면 생략도 가능하다.
```kotlin
fun main() {
    val numbers = listOf(100, 200)
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
```

#### 가변리스트를 가져오고 싶다면?
```kotlin
val numbers = mutableListOf(100, 200)
```

#### 간단한 TIP
- 우선 불변리스트를 만들고, 필요한 경우 가변리스트로 만들자.

#### Set
```kotlin
var numbers = setOf(100, 200)
var numbers2 = mutableSetOf(100, 200)
```

#### Map
```kotlin
val oldMap = mutableSetOf<Int, String>()
oldMap[1] = "MONDAY"
oldMap[2] = "TUESDAY"

// 중위 호출
// mapOf(1 to "MONDAY", 2 to "TUESDAY")

for (key in oldMap.keys) {
  println(key)
  println(oldMap[key])
}

for ((key, value) in oldMap.entries) {
  println(key)
  println(value)
}
```
- 데이터 삽입 방법은 2가지가 있다.


### 컬렉션의 null 가능성

#### List<Int?>
- 리스트에는 null이 들어갈 수 있지만, 리스트는 null이 절때 아니다
#### List< Int >?  
- 리스트에는 null이 들어갈 수 없고, 리스트는 null일수도 있다.
#### List<Int?>?
- 리스트에 null이 들어갈수 있고, 리스트가 null일 수도 있다.
-