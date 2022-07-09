
## lec 08.함수
### 함수 선언 문법
- 두 정수를 받아 더 큰 정수를 반환하는 예제
```kotlin
fun max(a: Int, b: Int): Int {
  return if (a > b) {
      a
  }else {
      b
  }
}
```
- 함수가 하나의 결과값이면 block 대신 = 사용 가능
```kotlin
fun max(a: Int, b: Int): Int =
  if (a > b) {
      a
  }else {
      b
  }
```

- 리턴 타입을 추론가능하기 때문에, 제거가능
- 한 줄로 변경 가능
- 만약 block {}을 사용하는 경우에는 반환 타입이 Unit이 아니면, 명시적으로 작성해 주어야 합니다!
```kotlin
fun max(a: Int, b: Int) = if (a > b) a else b
```
- 함수는 클래스 안에 있을 수도, 파일 최상단에 있을 수도 있습니다.
- 또한, 한 파일 안에 여러 함수들이 있을 수도 있습니다.
### default parameter
- 주어진 문자열을 N번 출력하는 예제

```java
public void repeat(String str, int num, boolean useNewLine){
  for(int i =1; i<=num; i++){
      if( useNewLine ){
          soutln(str);
      }else {
          sout(str);
      }
  }
}

public void repeat(String str, int num){
  repeat(str, num, true);
}

public void repeat(String str){
    repeat(str, 3, true);
}
```

```kotlin
fun repeat(
  str: String,
  num: Int = 3,
  useNewLine: Boolean = true,
){
  for (i in 1..num) {
    if (useNewLine) {
        println(str)
    }else{
        print(str)
    }
  }
}
```
- 밖에서 파라미터를 넣어주지 않으면, 기본값을 사용하자.
- 물론, 코틀린에도, 오버로딩 기능은 존재한다.

### named argument
- repeat을 호출할 때, num은 3을 그대로 쓰고, useNewLine은 false를 쓰고 싶다!
- 어떻게 사용하지?
```kotlin
fun main() {
    repeat("Hello world", useNewLine = false)
}

fun repeat(
  str: String,
  num: Int = 3,
  useNewLine: Boolean = true,
){
  for (i in 1..num) {
    if (useNewLine) {
        println(str)
    }else{
        print(str)
    }
  }
}
```
- 매개변수 이름을 통해 직접 지정, 지정되지 않은 매개변수는 기본값 사용
- 이를통해, builder를 직접 만들지 않고, builder의 장점을 가지게 된다.
```kotlin
fun main() {
  printNameAndGender(name="dante", gender="man")
}

fun printNameAndGender(name: Stirng, gender: Stirng) {
  println(name)
  println(gender)
}
```
- kotlin에서 Java함수를 가져다 사용할 때는, named argument를 사용할 수 없다.

### 같은 타입의 여러 파라미터 받기(가변인자)
- 문자열 N을 받아 출력하는 예제
```java
public static void printAll(String... strings){
    for(String str: strings){
        sout(str);
    }    
}
```
- Java에서는 타입 ...을 사용했을때, 가변인자를 사용하는것을 인지
```kotlin
fun main() {
  printALl("A", "B", "C")

  val array = arrayOf("A", "B", "C")
  printALl(*array)
}

fun printALl(vararg strings: String) {
  for (str in strings) {
      println(str)
  }
}
```
- ...을 타입 뒤에 쓰는 대신 제일 앞에 `vararg`를 적어주어야 한다.
- 배열을 바로 넣는대신 스프레드 연산자를 (`*`)를 붙여주어야한다.

