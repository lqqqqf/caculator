/*
 * Copyright (c) 2016-2018. https://github.com/lqqqqf. All rights reserved.
 */

package calculator2;

import caculator2.Expression;
import caculator2.Minus;
import caculator2.Plus;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;

public class ExpressionTest {

    @Test
    public void test() {
        Expression a = new Expression(new BigDecimal(4));
        Assert.assertThat(a.communicate(new Expression(new BigDecimal(3)), new Plus()).getVal().intValue(), is(7));
        a.setVal(4L);
        Assert.assertThat(a.communicate(new Expression(5L), new Minus()).getVal().intValue(), is(-1));
        a.setVal(4L);
        Assert.assertThat(a.communicate(new Expression(3L), new Plus()).communicate(new Expression(2L), new Minus()).getVal().intValue(), is(5));
    }
}
