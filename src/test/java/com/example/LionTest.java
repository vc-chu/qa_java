package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    private Predator predatorMock;

    @Test
    void testLionMale() throws Exception {
        // Настраиваем mock: при вызове eatMeat() вернуть список еды
        when(predatorMock.eatMeat()).thenReturn(List.of("Мясо", "Рыба"));

        // Создаём Lion с подставным Predator
        Lion lion = new Lion("Самец", predatorMock);

        // Проверяем, что лев самец и у него есть грива
        assertTrue(lion.doesHaveMane());

        // Проверяем, что getFood() возвращает замоканное значение
        List<String> food = lion.getFood();
        assertEquals(List.of("Мясо", "Рыба"), food);

        // Проверяем, что метод eatMeat() был вызван ровно 1 раз
        verify(predatorMock, times(1)).eatMeat();
    }

    @Test
    void testLionFemale() throws Exception {
        // Настраиваем mock для самки
        when(predatorMock.eatMeat()).thenReturn(List.of("Мясо"));

        Lion lion = new Lion("Самка", predatorMock);

        // Проверяем, что у самки нет гривы
        assertFalse(lion.doesHaveMane());

        List<String> food = lion.getFood();
        assertEquals(List.of("Мясо"), food);
    }

    @Test
    void testInvalidSex() {
        // Проверяем, что некорректный пол выбрасывает исключение
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion("Неизвестно", predatorMock);
        });

        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }

    @Test
    void testGetKittensWithFeline() throws Exception {
        // Если нужно тестировать getKittens(), создаём мок Feline
        Feline felineMock = mock(Feline.class);
        when(felineMock.getKittens()).thenReturn(5);

        Lion lion = new Lion("Самец", felineMock);

        // getKittens() работает только если predator это Feline
        assertEquals(5, lion.getKittens());

        verify(felineMock, times(1)).getKittens();
    }


}