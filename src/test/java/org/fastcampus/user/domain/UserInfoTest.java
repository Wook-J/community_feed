package org.fastcampus.user.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class UserInfoTest {

    @Test
    void givenNameAndProfileImage_whenCreated_thenThrowNothing(){
        // given
        String name = "abcd";
        String profileImage = "";

        // when
        // then
        assertDoesNotThrow(() -> new UserInfo(name, profileImage));
    }

    @Test
    void givenBlankNameAndProfileImage_whenCreated_thenThrowError(){
        // given
        String name = "";
        String profileImage = "";

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, profileImage));
    }

    // 작은 내부 객체들이 먼저 검증이 되어야 큰 객체를 테스트할 때에도 빠르게 작성할 수 있음
    // 테스트 중복이 줄어듦
}
