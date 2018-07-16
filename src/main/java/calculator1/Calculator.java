/*
 * Copyright (c) 2016-2018. https://github.com/lqqqqf. All rights reserved.
 */

package calculator1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * 提供"数字"的四则运算，例如：<p>四+5=9；four+5=9;</p>四则算法固定。
 */
public class Calculator implements Cloneable {

    private NumberConverter numberRegistry;
    private BigDecimal result = BigDecimal.ZERO;
    private final Logger LOG = LoggerFactory.getLogger(Calculator.class);
    //三位有效数
    private final int SCALE = 3;

    public Calculator(NumberConverter numberRegistry) {
        if (numberRegistry == null) {
            throw new IllegalArgumentException("NumberConverter can not be null.");
        }
        this.numberRegistry = numberRegistry;
    }


    public Calculator offset(String num) {
        this.result = getNumber(num);
        return this;
    }

    public Calculator offset(double num) {
        this.result = new BigDecimal(num);
        return this;
    }


    public Calculator plus(double num) {
        this.result = this.result.add(new BigDecimal(num));
        return this;
    }

    public Calculator plus(String num) {
        this.result = this.result.add(getNumber(num));
        return this;
    }

    public Calculator plus(Formula formula) {
        try {
            Calculator c = (Calculator) clone();
            c.reset();
            this.result = this.result.add(formula.perform(c));
        } catch (CloneNotSupportedException e) {
            LOG.debug("", e);
        }
        return this;
    }


    public Calculator minus(String num) {
        this.result = this.result.subtract(getNumber(num));
        return this;
    }

    public Calculator minus(double num) {
        this.result = this.result.subtract(new BigDecimal(num));
        return this;
    }

    public Calculator minus(Formula formula) {
        try {
            Calculator c = (Calculator) clone();
            c.reset();
            this.result = this.result.subtract(formula.perform(c));
        } catch (CloneNotSupportedException e) {
            LOG.debug("", e);
        }
        return this;
    }

    public Calculator multiply(String num) {
        this.result = result.multiply(getNumber(num));
        return this;
    }

    public Calculator multiply(double num) {
        this.result = this.result.stripTrailingZeros().multiply(new BigDecimal(num));
        return this;
    }

    public Calculator multiply(Formula formula) {
        try {
            Calculator c = (Calculator) clone();
            c.reset();
            this.result = this.result.multiply(formula.perform(c));
        } catch (CloneNotSupportedException e) {
            LOG.debug("", e);
        }
        return this;
    }

    public Calculator divide(String num) {
        this.result = result.divide(getNumber(num), SCALE, BigDecimal.ROUND_HALF_DOWN);
        return this;
    }

    public Calculator divide(double num) {
        this.result = this.result.divide(new BigDecimal(num), SCALE,  BigDecimal.ROUND_HALF_DOWN);
        return this;
    }

    public Calculator divide(Formula formula) {
        try {
            Calculator c = (Calculator) clone();
            c.reset();
            this.result = this.result.divide(formula.perform(c), SCALE,  BigDecimal.ROUND_HALF_DOWN);
        } catch (CloneNotSupportedException e) {
            LOG.debug("", e);
        }
        return this;
    }

    public String getResult() {
        return this.result.stripTrailingZeros().toString();
    }

    public BigDecimal getBigDecimalResult() {
        return this.result.stripTrailingZeros();
    }

    /**
     * 重置为0
     */
    public void reset() {
        this.result = BigDecimal.ZERO;
    }

    private BigDecimal getNumber(String num) {
        BigDecimal ret;
        if (NumberUtil.isDigit(num)) {
            ret = new BigDecimal(num);
        } else {
            ret = numberRegistry.convert(num);
        }
        return ret;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
