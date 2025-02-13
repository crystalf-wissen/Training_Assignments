package com;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class CalculatorMainTest{
    @Test
    public void testAdd(){
        Calculator c2 = new Calculator();
        assertEquals(4,c2.add(2,2),"The sum of 2 and 2 is not resulting in 4");
        assertEquals(9,c2.add(5,4),"The sum of 4 and 4 is not resulting in 8");
    }


}