package com.example.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TqsStackTest {

    TqsStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new TqsStack<>();
    }

    @AfterEach
    void tearDown() {
        stack = null;
    }

    @Test
    @DisplayName("Pop on empty test")
    void tqsStack_popOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> stack.tqsStack_pop());
    }

    @Test
    @DisplayName("Peek on empty test")
    void tqsStack_peekOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> stack.tqsStack_peek());
    }

    @Test
    @DisplayName("Size 0 on construction test")
    void tqsStack_emptyOnConstruction() {
        assertEquals(0, stack.tqsStack_size());
    }

    @Test
    @DisplayName("Is empty on construction test")
    void tqsStack_isEmptyOnConstruction() {
        assertTrue(stack.tqsStack_isEmpty());
    }

    @ParameterizedTest(name="{0} size after pushing {0} elements")
    @CsvSource({
            "2",
            "1",
            "3",
            "7"
    })
    void tqsStack_sizeAfterPushing(int size) {
        for(int i = 0; i < size; i++){
            stack.tqsStack_push(i * 4 + 20);
        }
        assertEquals(size, stack.tqsStack_size());
    }

    @ParameterizedTest(name="Pushing {0} and popping {0} returns {0}")
    @CsvSource({
            "4",
            "20",
            "0",
            "7"
    })
    void tqsStack_PopsPushed(int value) {
        stack.tqsStack_push(value);
        assertEquals(value, stack.tqsStack_pop());
    }

    @ParameterizedTest(name="Pushing {0} and peeking {0} returns {0} size stays the same")
    @CsvSource({
            "0",
            "96",
            "8",
            "1"
    })
    void tqsStack_peekSize(int value) {
        stack.tqsStack_push(value);
        assertEquals(value, stack.tqsStack_peek());
        assertEquals(1, stack.tqsStack_size());
    }

    @ParameterizedTest(name="{0} size stack has 0 size after popping {0} elements ")
    @CsvSource({
            "10",
            "5",
            "18",
            "2"
    })
    void tqsStack_sizeAfterPushingAndPoping(int size) {
        for(int i = 0; i < size; i++){
            stack.tqsStack_push(i * 4 + 20);
        }
        assertEquals(size, stack.tqsStack_size());
        for(int i = 0; i < size; i++){
            stack.tqsStack_pop();
        }
        assertEquals(0, stack.tqsStack_size());
    }
}