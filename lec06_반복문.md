
## lec 06. 반복문

```kotlin
fun main() {
  // 숫자가 들어있는 리스트를 하나씩 출력하는 예제
  val number = listOf(1L, 2L, 3L)
  for(number in numbers){
      println(number)
  }
  
  // 1부터 3까지 출력하는 반복문 
  for(i in 1..3){
      pruntln(i)
  }

  // 내려가는 경우
  for (i in 3 downTo 1) {
      println(i)
  }
  
  // 2칸씩 올라가는 경우?
  for (i in 1..5 step 2) {
      println(i)
  }

  /**
   * 1..5 step 2
   * 1부터 5까지 등차가 2인 등차수열이 생성된다.
   */
}
```
- Kotlin에서 `전통적인 for문은 등차수열`을 사용한다.
- while문과 do-while문은 java와 완전히 일치한다.
