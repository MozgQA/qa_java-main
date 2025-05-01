package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Spy
    Feline feline;

    @Test
    public void eatMeat() throws Exception {
        doReturn(List.of("Животные", "Птицы", "Рыба"))
                .when(feline).getFood("Хищник");

        assertEquals(List.of("Животные", "Птицы", "Рыба"), feline.eatMeat());
        verify(feline).getFood("Хищник");
    }

    @Test
    public void testGetFamily() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void testGetKittensWithoutParams() {
        assertEquals(1, feline.getKittens());
    }

    @Test
    public void testGetKittensWithParams() {
        assertEquals(3, feline.getKittens(3));
    }
}
