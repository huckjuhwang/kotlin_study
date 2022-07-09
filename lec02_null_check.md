## lec02. null check

```kotlin
val str: String? = "ABC" 
str.length // 불가능 
str?.length // 가능
```
- null이 아니면 실행하고, null이면 실행하지 않는다.(그대로 null)

```kotlin
var str: String? = "ABC"
str?.length ?:0
```
- 앞의 연산 결과가 null이면 뒤의 값을 사용

### 정리
- 코틀린에서 null이 들어갈 수 있는 타입은 완전히 다르게 간주된다
    - 한번 null 검사를 하면 `non-null`임을 컴파일러가 알 수 있다.
      - null이 아닌 경우에만 호출되는 `Safe Call(?.)`있다.
      - null인 경우에만 호출하는 `Elvis연산자 (?:)`가 있다.
      - null이 절때 아닐때, `사용할 수 있는 널이 아님 단언(!!)`이 있다