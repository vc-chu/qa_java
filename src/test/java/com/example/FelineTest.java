package com.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FelineTest {

    @Test
    void testGetFamily() {
        Feline feline = new Feline();

        String family = feline.getFamily();

        assertEquals("Кошачьи", family);
    }

    @Test
    void testGetKittensDefault() {
        Feline feline = new Feline();

        int kittens = feline.getKittens();

        assertEquals(1, kittens);
    }

    @Test
    void testGetKittensWithParameter() {
        Feline feline = new Feline();

        int kittens = feline.getKittens(5);

        assertEquals(5, kittens);
    }

    @Test
    void testGetKittensWithZero() {
        Feline feline = new Feline();

        int kittens = feline.getKittens(0);

        assertEquals(0, kittens);
    }

    @Test
    void testGetKittensWithNegative() {
        Feline feline = new Feline();

        int kittens = feline.getKittens(-3);

        assertEquals(-3, kittens);
    }

    @Test
    void testEatMeat() throws Exception {
        Feline feline = new Feline();

        List<String> food = feline.eatMeat();

        // Проверка зависит от реализации getFood() в классе Animal
        // Если getFood("Хищник") возвращает список, проверяем, что он не пустой
        assertNotNull(food);
        assertFalse(food.isEmpty());
    }
}
