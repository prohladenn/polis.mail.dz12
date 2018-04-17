package org.spbstu.kovshov.Operations;

import org.spbstu.kovshov.MyExeptions.IntegerOverflow;

public class Multiply extends Operation {

    public Multiply(double l, double r) {
        super(l, r);
    }

    @Override
    public double operation() throws IntegerOverflow {
        long lo = (long) (l * r);
        if (lo > Integer.MAX_VALUE){
            throw new IntegerOverflow();
        }
        return l * r;
    }
}
