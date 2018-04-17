package org.spbstu.kovshov.Operations;

public class Subtract extends Operation {

    public Subtract(double l, double r) {
        super(l, r);
    }

    @Override
    public double operation() {
        return l - r;
    }
}
