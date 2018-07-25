/*
 * Copyright (c) 2016-2018. https://github.com/lqqqqf. All rights reserved.
 */

package caculator2;

import java.math.BigDecimal;

public class Plus implements Computation {

    @Override
    public BigDecimal handle(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }
}
