package org.fastcampus.user.application.dto;

public record CreateUserRequestDto(String name, String profileImageUrl) {

    /*
    * 불변(immutable) 데이터 객체를 간결하게 정의할 수 있도록 도와주는 Java 14부터 도입된 새로운 클래스 타입
    * 주로 DTO (Data Transfer Object)나 VO (Value Object) 같은 순수 데이터 보관용 객체를 만들 때 사용
    *
    * 다음과 같은 기능 자동 생성
    * - private final 필드
    * - 생성자 (public Person(String name, int age))
    * - getter 메서드 (name(), age())
    * - equals(), hashCode(), toString() 메서드
    *
    * 사용 시 유의사항
    * - final 이므로 상속이 불가능
    * - 모든 필드는 반드시 생성자에서 초기화되어야 하며, 초기값 없이 선언 불가
    * - 기본적으로 값 기반의 비교 (equals, hashCode)를 제공하므로, 값만 같으면 동등하다고 판단
    * */
}
