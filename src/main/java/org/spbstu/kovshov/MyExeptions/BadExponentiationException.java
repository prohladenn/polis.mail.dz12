package org.spbstu.kovshov.MyExeptions;

public class BadExponentiationException extends Exception {
    public BadExponentiationException() {
        super("Возведение отрицательного числа в нецелую степень");
    }
}
