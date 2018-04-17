package org.spbstu.kovshov.MyExeptions;

public class DenominatorIsZeroException extends Exception {
    public DenominatorIsZeroException() {
        super("Деление на 0");
    }
}
