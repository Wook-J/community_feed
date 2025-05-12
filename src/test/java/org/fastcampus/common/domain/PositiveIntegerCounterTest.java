package org.fastcampus.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PositiveIntegerCounterTest {  // Test 클래스에서는 public을 빼는 것이 보통임

    @Test
    void givenCreated_whenIncrease_thenCountIsOne(){
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.increase();

        // then
        assertEquals(1, counter.getCount());
    }

    @Test
    void givenCreatedAndIncreased_whenDecrease_thenCountIsZero(){
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increase();

        // when
        counter.decrease();

        // then
        assertEquals(0, counter.getCount());
    }

    @Test
    void givenCreated_whenDecrease_thenCountIsZero(){
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.decrease();

        // then
        assertEquals(0, counter.getCount());
    }
}
