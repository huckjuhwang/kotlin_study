
## lec11. 접근제어

### 자바와 코틀린의 가시성 제어
![image](https://user-images.githubusercontent.com/47339929/177044501-1ce9d409-8130-40e0-9b99-0bbc6377c2fe.png)

- 코틀린에서는 패키지라는 개념을 접근제어에 사용하지 않는다.
- Java의 기본 접근 지시어는 `default`, Kotlin은 `Public`
- 코틀린은 `.kt` 파일에 변수, 함수, 클래스 여러개를 만들 수 있다.


#### 코틀린 파일의 접근제어
- public -> 기본값 어디서든 접근할 수 있다.
- protected -> 파일(최상단)에는 사용 불가능
- internal -> 같은 모듈에서만 접근 가능
- private -> 같은 파일 내에서만 접근 가능


- 생성자에 접근 지시어를 붙이려면, `constructor`를 써야한다.
-

### 코틀린 파일의 접근 제어
### 다양한 구성요소의 접근 제어
### Java와 Kotlin을 함께 사용할 경우 주의할 점

