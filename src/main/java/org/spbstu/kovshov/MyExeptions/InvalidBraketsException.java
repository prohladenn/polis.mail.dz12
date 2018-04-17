package org.spbstu.kovshov.MyExeptions;

public class InvalidBraketsException extends Exception {
    public InvalidBraketsException() {
        super("Неверно сбалансированы скобки");
    }
}