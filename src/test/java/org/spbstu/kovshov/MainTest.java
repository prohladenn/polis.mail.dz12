package org.spbstu.kovshov;

import org.junit.jupiter.api.Test;
import org.spbstu.kovshov.MyExeptions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    void test_A() throws Exception {
        double test = Main.Calculate("3*-((-2+5)-(-(5/-1)))");
        assertEquals(6, test);
    }

    @Test
    void test_B() throws Exception {
        double test = Main.Calculate("-2*-4");
        assertEquals(8, test);
    }

    @Test
    void test_C() throws Exception {
        double test = Main.Calculate("-2*-4/-1+-10");
        assertEquals(-18, test);
    }

    @Test
    void test_D() throws Exception {
        double test = Main.Calculate("-2*(-4/-1)--10");
        assertEquals(2, test);
    }

    @Test
    void test_E() {
        boolean isException = false;
        try {
            Main.Calculate("1/(-4+(-2*-2))");
        } catch (DenominatorIsZeroException e) {
            isException = true;
        } catch (Exception ignored) {
        }
        assertTrue(isException);
    }

    @Test
    void test_F() throws Exception {
        double test = Main.Calculate("2+-6/x", -3);
        assertEquals(4, test);
    }

    @Test
    void test_G() throws Exception {
        double test = Main.Calculate("2+6*x-y", 2, 4);
        assertEquals(10, test);
    }

    @Test
    void test_H() throws Exception {
        double test = Main.Calculate("2+x/y-z", 15, 5, 1);
        assertEquals(4, test);
    }

    @Test
    void test_I() throws Exception {
        double test = Main.Calculate("5 * ( x - 2 ) + 3", 5);
        assertEquals(18, test);
    }

    @Test
    void test_J() throws Exception {
        double test = Main.Calculate("-3 * x + 4", 5);
        assertEquals(-11, test);
    }

    @Test
    void test_K() {
        boolean isException = false;
        try {
            Main.Calculate("");
        } catch (EmptyInputException e) {
            isException = true;
        } catch (Exception ignored) {
        }
        assertTrue(isException);
    }

    @Test
    void test_L() {
        boolean isException = false;
        try {
            Main.Calculate("                 ");
        } catch (EmptyInputException e) {
            isException = true;
        } catch (Exception ignored) {
        }
        assertTrue(isException);
    }

    @Test
    void test_M() throws Exception {
        double test = Main.Calculate("2.55*10");
        assertEquals(25.5, test);
    }

    @Test
    void test_N() {
        boolean isException = false;
        try {
            Main.Calculate("(2-2))+4");
        } catch (InvalidBraketsException e) {
            isException = true;
        } catch (Exception ignored) {
        }
        assertTrue(isException);
    }

    @Test
    void test_O() {
        boolean isException = false;
        try {
            Main.Calculate("x*y*z", 99999999, 99999999, 99999999);
        } catch (IntegerOverflow e) {
            isException = true;
        } catch (Exception ignored) {
        }
        assertTrue(isException);
    }

    @Test
    void test_P() {
        boolean isException = false;
        try {
            Main.Calculate("1+c-2");
        } catch (UnknownSymbolException e) {
            isException = true;
        } catch (Exception ignored) {
        }
        assertTrue(isException);
    }

    @Test
    void test_Q() {
        boolean isException = false;
        try {
            Main.Calculate("1+1*/-1");
        } catch (ExtraOperatorException e) {
            isException = true;
        } catch (Exception ignored) {
        }
        assertTrue(isException);
    }

    @Test
    void test_R() {
        boolean isException = false;
        try {
            Main.Calculate("1+x*/*/y+++++1*/-1", 12, 4);
        } catch (ExtraOperatorException e) {
            isException = true;
        } catch (Exception ignored) {
        }
        assertTrue(isException);
    }

    @Test
    void test_S() throws Exception {
        double test = Main.Calculate("2^z+x^y", 3, 3, 10);
        assertEquals(1051, test);
    }

    @Test
    void test_T() {
        boolean isException = false;
        try {
            double test = Main.Calculate("-2^z+x^y", 3, 3, 10.5);
        } catch (BadExponentiationException e) {
            isException = true;
        } catch (Exception ignored) {
        }
        assertTrue(isException);
    }

    @Test
    void clearTest() throws Exception {
        double test = Main.Calculate("x+y^z"/*СТРОКА С ПРИМЕРОМ*/, 5, 2, 6/*ПЕРЕМЕННЫЕ*/);
        assertEquals(69/*ОЖИДАЕМЫЙ ОТВЕТ*/,test);
    }

}
