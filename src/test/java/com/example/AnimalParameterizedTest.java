package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnimalParameterizedTest {

    private static final List<String> EXPECTED_FOOD =
            List.of("Животные", "Птицы", "Рыба");

    static Stream<Arguments> lionSexData() {
        return Stream.of(
                Arguments.of("Самец", true),
                Arguments.of("Самка", false)
        );
    }

    static Stream<Arguments> kittensData() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(1),
                Arguments.of(3),
                Arguments.of(5)
        );
    }

    @ParameterizedTest(name = "Пол: {0}, наличие гривы: {1}")
    @MethodSource("lionSexData")
    void lionHasExpectedMane(
            String sex,
            boolean expectedMane
    ) throws Exception {
        Predator predator = new Feline();

        Lion lion = new Lion(sex, predator);

        if (expectedMane) {
            assertTrue(lion.doesHaveMane());
        } else {
            assertFalse(lion.doesHaveMane());
        }
    }

    @ParameterizedTest(name = "Количество котят: {0}")
    @MethodSource("kittensData")
    void felineReturnsPassedKittensCount(int kittensCount) {
        Feline feline = new Feline();

        assertEquals(
                kittensCount,
                feline.getKittens(kittensCount)
        );
    }

    @Test
    void felineReturnsMeatFood() throws Exception {
        Feline feline = new Feline();

        assertEquals(
                EXPECTED_FOOD,
                feline.eatMeat()
        );
    }

    @Test
    void catReturnsExpectedSound() {
        Cat cat = new Cat(new Feline());

        assertEquals("Мяу", cat.getSound());
    }

    @Test
    void catReturnsExpectedFood() throws Exception {
        Cat cat = new Cat(new Feline());

        assertEquals(
                EXPECTED_FOOD,
                cat.getFood()
        );
    }

    @Test
    void lionReturnsFoodFromInjectedPredator()
            throws Exception {
        Predator predator = new Feline();

        Lion lion = new Lion("Самец", predator);

        assertEquals(
                EXPECTED_FOOD,
                lion.getFood()
        );
    }

    @Test
    void lionReturnsKittensFromInjectedPredator()
            throws Exception {
        Predator predator = new Feline();

        Lion lion = new Lion("Самец", predator);

        assertEquals(
                1,
                lion.getKittens()
        );
    }

    @Test
    void lionRejectsInvalidSex() {
        Predator predator = new Feline();

        Exception exception = assertThrows(
                Exception.class,
                () -> new Lion("Неизвестно", predator)
        );

        assertEquals(
                "Используйте допустимые значения пола животного - самец или самка",
                exception.getMessage()
        );
    }
}