package org.spbstu.kovshov.Operations;

public class Add extends Operation {

    public Add(double l, double r) {
        super(l, r);
    }

    @Override
    public double operation() {
        return l + r;
    }
}

