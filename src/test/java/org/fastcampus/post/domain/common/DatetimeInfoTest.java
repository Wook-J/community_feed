package org.fastcampus.post.domain.common;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/*
* PostContent test 이전에 상속받은 Content에 DatetimeInfo가 있어서 먼저 테스트 진행
* */
class DatetimeInfoTest {

    @Test
    void givenCreated_whenUpdated_thenTimeAndStateAreUpdated() throws InterruptedException{
        // given
        DatetimeInfo dateTimeInfo = new DatetimeInfo();
        LocalDateTime localDateTime = dateTimeInfo.getDatetime();

        // 약간 대기해서 시간 차이 확보
        Thread.sleep(5);

        // when
        dateTimeInfo.updateEditDatetime();

        // then
        assertTrue(dateTimeInfo.isEdited());
        assertTrue(dateTimeInfo.getDatetime().isAfter(localDateTime));
        // 두 호출 사이에 시간이 너무 짧아서 같은 값 반환..?
        // assertNotEquals(localDateTime, dateTimeInfo.getDatetime());
    }
}
