package org.spbstu.kovshov.MyExeptions;

public class UnknownSymbolException extends Exception {
    public UnknownSymbolException(char symbol) {
        super("Неизвестный символ " + symbol);
    }
}
