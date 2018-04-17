package org.spbstu.kovshov.Operations;

import org.spbstu.kovshov.MyExeptions.BadExponentiationException;

public class Pow extends Operation {

    public Pow(double l, double r) {
        super(l, r);
    }

    @Override
    public double operation() throws BadExponentiationException {
        if (l < 0 && r != (int)r){
            throw new BadExponentiationException();
        }
        return Math.pow(l, r);
    }

}
