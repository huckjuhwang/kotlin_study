# kotlin_study
코틀린 기본문법을 학습하기 위한 레파지토리입니다.


## lec01. var vs val

var = 수정가능한 변수
val(final) = 수정 불가능한 변수

## 정리
- val 컬렉션에는 element 를 추가할 수 있다.
- 타입을 명시적으로 작성하지 않아도, 타입이 추론된다.
- 모든 변수를 우선 val로 만들고 꼭 필요한 경우 var로 변경한다(꿀팁)
- kotlin에서의 primitive type, reference type은 하나의 타입으로 구분하며, 즉, 프로그래머가 boxing /]() unboxing을 고려하지 않아도 되도록 코틀린이 알아서 상황에 따라서 바꿔서 사용해준다. 
- null이 들어갈 수 있는 변수는 타입 뒤에 ? 를 붙여주어야 한다. (아예 다른 타입으로 간주)
- 객체를 인스턴스화 할때 new를 붙이지 않아야한다.

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

## 정리
- 코틀린에서 null이 들어갈 수 있는 타입은 완전히 다르게 간주된다
  - 한번 null 검사를 하면 non-null임을 컴파일러가 알 수 있다.
- null이 아닌 경우에만 호출되는 Safe Call(`?.`)있다.
- null인 경우에만 호출하는 Elvis연산자 (`?:`)가 있다.
- null이 절때 아닐때, 사용할 수 있는 널이 아님 단언(`!!`)이 있다