package org.spbstu.kovshov.MyExeptions;

public class EmptyInputException extends Exception {
    public EmptyInputException() {
        super("Пустой ввод");
    }
}
