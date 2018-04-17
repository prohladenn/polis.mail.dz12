package org.spbstu.kovshov.Operations;

import org.spbstu.kovshov.MyExeptions.DenominatorIsZeroException;

abstract class Operation {

    double l;
    double r;

    Operation(double l, double r) {
        this.l = l;
        this.r = r;
    }

    public abstract double operation() throws DenominatorIsZeroException, Exception;
}
