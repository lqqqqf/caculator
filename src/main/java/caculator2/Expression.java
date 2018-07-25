/*
 * Copyright (c) 2016-2018. https://github.com/lqqqqf. All rights reserved.
 */

package caculator2;

import java.math.BigDecimal;

/**
 * 用于进行运算，可以根据不同的{@link Computation computation}进行不同的运算。
 * @see Computation
 */
public class Expression {

    private BigDecimal val;

    public Expression(BigDecimal val) {
        assertValNotBeNull(val);
        this.val = val;
    }

    public Expression(Long val) {
        assertValNotBeNull(val);
        this.val = new BigDecimal(val);
    }

    public Expression(String val) {
        assertValNotBeNull(val);
        this.val = new BigDecimal(val);
    }

    private void assertValNotBeNull(Object val) {
        if (val == null) {
            throw new IllegalArgumentException("值不能为null");
        }
    }

    /**
     * 根据不同的{@link Computation computation}计算不同的结果
     * @param e 被计算的数
     * @param computation 算法
     * @return 计算结果
     */
    public Expression communicate(Expression e, Computation computation) {
        this.val = computation.handle(this.val, e.getVal());
        return this;
    }

    public BigDecimal getVal() {
        return this.val;
    }

    public void setVal(String val) {
        assertValNotBeNull(val);
        this.val = new BigDecimal(val);
    }

    public void setVal(Long val) {
        assertValNotBeNull(val);
        this.val = new BigDecimal(val);
    }

    public void setVal(BigDecimal val) {
        assertValNotBeNull(val);
        this.val = val;
    }


    public void clear() {
        setVal(BigDecimal.ZERO);
    }
}
