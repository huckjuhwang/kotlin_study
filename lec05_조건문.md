

## lec05. 조건문

### Expression & Statement
```kotlin

 import java.lang.IllegalArgumentException
 
 fun validateScoreIsNotNegative(score: Int) {
    if(score < 0){
        throw IllegalArgumentException("${score}는 0보다 작을수 없습니다.")
    }
} 

fun getPassOrFail(score: int): String{
  if (score >= 50) {
      return "P"
  }else {
      return "F"
  }
}
```
- 동일한점도 있지만, 다른점도 존재한다.
- JAVA에서 if-else는 `Statement`이지만, Kotlin에서는 `Expression`이다.

    * `statement` : 프로그램의 문장, 하나의 값으로 도출되지 않는다.
    * `Expresstion`: 하나의 값으로 도출되는 문장

```java
// in JAVA
String grade = if(score >= 50){     // 오류 문장!!
    "P";
}else{
    "F"
}
```
- Java에서는 하나의 값으로 취급하지 않기 때문에 오류가 발생한다.
- 때문에, Java에서는 `3항 연산자를 사용하여 해결`한다.
```kotlin
fun getPassOrFail(score: int): String{
  return if (score >= 50) {
    "P"
  }else {
    "F"
  }
}
```
- 코틀린에서는 if문 전체를 리턴할 수 있다.
- `IF-ELSE가 Expression`이기 때문에(3항 연산자 같은 느낌)
- 따라서, `코틀린에서는 3항연산자가 존재하지 않는다.`

```kotlin

fun getGrade(score: Int) String{
  return if (score >= 90) {
      "A"
  }else if (score >= 80) {
      "B"
  }else if (score >= 70) {
      "C"
  }else{
      "D"
  }
}
```
###Tip!!
- 어떤 값이 특정 범위에 포함되어 있는지, 포함되어 있지 않은지
```java
if(0 <= score && score <= 100)
```

```kotlin
// score가 1~100사이 범위에 있지 않은 경우
if( score !in 1..100 ){
  throw IllegalArgumentException("${score}는 0부터 100사이의 값이 아닙니다.")
}
```

### switch와 when

```text
when(값){
    조건부 -> 어떠한 구문
    조건부 -> 어떠한 구문
    else-> 어떠한 구문
}
```
- 조건부에는 어떠한, `expression`이라도 들어갈 수 있다. (EX. `is` Type)
```java
private boolean startsWithA(Object obj){
  if(obj instanceof String){
    return (String)obj.startsWith("A");
  }else { 
    return false;
  }
}
```

```kotlin
fun startsWithA(obj: Any): Boolean {
    return when(obj){
        is String -> obj.startsWith("A")
        else -> false
    }
}
```

- 여러개의 조건을 동일하게 검증할 수 있다.
```kotlin
fun judgeNumber(number: Int){
  when (number) {
    1, 0, -1 -> println("어디서 많이 본 숫자입니다.")
    else -> println("1, 0, -1이 아닙니다.")
  }
}
```

```kotlin
fun judgeNumber2(number: Int){
  when {
    number == 0 -> println("주어진 숫자는 0입니다")
    number % 2 == 0 -> println("주어진 숫자는 짝수 입니다.")
    else -> println("주어진 숫자는 홀수입니다.")
  } 
}
```
- when은 `enum Class` or `sealed class`와 함께 사용할 경우, 더욱더 진가를 발휘한다.

```kotlin
fun getGradeWithSwitch(score: Int): String{
  return when (score / 10) {
      in 90..99 -> "A"
      in 80..89 -> "B"
      in 70..79 -> "C"
      else -> "D"
  }
}
```

### 정리
- `if / if-else / if-else if-else` 모두 문법이 동일하다
- 단, kotlin에서는 `Expression`으로 취급된다. ( 따라서, 삼항연산자가 존재하지 않는다 )

