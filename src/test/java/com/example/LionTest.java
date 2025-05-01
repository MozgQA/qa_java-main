package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class LionTest {

    @Mock
    private Feline feline;

    private final String sex;
    private final Boolean expectedHasMane;

    public LionTest(String sex, Boolean expectedHasMane) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Самец", true},
                {"Самка", false}
        });
    }

    @Test
    public void testConstructorValidSex() throws Exception {
        Lion lion = new Lion(sex, feline);
        assertNotNull(lion);
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    @Test(expected = Exception.class)
    public void testConstructorInvalidSex() throws Exception {
        new Lion("Неизвестно", feline);
    }

    @Test
    public void testGetKittensMinMax() throws Exception {
        when(feline.getKittens()).thenReturn(1);
        Lion lion = new Lion("Самец", feline);
        assertEquals(1, lion.getKittens());

        when(feline.getKittens()).thenReturn(10);
        lion = new Lion("Самец", feline);
        assertEquals(10, lion.getKittens());
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = Arrays.asList("Мясо", "Рыба");
        when(feline.getFood("Хищник")).thenReturn(expectedFood);
        Lion lion = new Lion("Самка", feline);

        List<String> actualFood = lion.getFood();

        assertEquals(expectedFood, actualFood);
        verify(feline, times(1)).getFood("Хищник");
    }
}
