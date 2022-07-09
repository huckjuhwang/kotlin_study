
## lec07. 예외처리
```java
private int parseIntOrThrow(@NotNull String str){
    try{
        return Integer.parseInt(str);
    } catch(NumberFormatException e) {
        throw new IlleagArgumentException(String.format("주어진 숫자는 숫자가 아닙니다.");)    
    }    
}
```

```kotlin
fun parseIntOrThrow(str: String): Int {
  try {
    return str.toInt()
  } catch (e: NumberFormatException) {
    throw IllegalArgumentException("주어진 ${str}는 숫자가 아닙니다.")   
  }
}
```

- 주어진 문자열을 정수로 변경하는 예제 실패하면 null을 반환
```kotlin
fun parseIntOrThrow2(str: String): Int {
  return try { 
    str.toInt()
  } catch (e: NumberFormatException) {
    null 
  }
}
```

### checked Exception, Unchecked Exception
- Kotlin에서는 Checked Exception과 Unchecked Exception을 구분하지 않습니다.
- 모두 `Unchecked Exception`입니다.
- try catch finally 구문은 문법적으로 완전히 동일하다.
    - kotlin에서는 try catch가 `Expression`이다.
- kotlin에서는 `try with resources`구문이 없다. 대신 코틀린의 언어적 특징을 활용해 close를 호출해 준다.
