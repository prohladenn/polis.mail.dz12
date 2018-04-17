package org.spbstu.kovshov.Operations;

import org.spbstu.kovshov.MyExeptions.DenominatorIsZeroException;

public class Divide extends Operation {

    public Divide(double l, double r) {
        super(l, r);
    }

    @Override
    public double operation() throws Exception {
        if(r == 0){
            throw new DenominatorIsZeroException();
        }
        return l / r;
    }
}
