/*
 * Copyright (c) 2016-2018. https://github.com/lqqqqf. All rights reserved.
 */

package caculator2;

import java.math.BigDecimal;

/**
 * 算法类,用于扩展{@link Expression expression}之间的算法
 */
public interface Computation {

    /**
     * 计算<code>a</code>与<code>b</code>的值
     * @param a 用于计算的数
     * @param b 用于计算的数
     * @return 计算结果
     */
    BigDecimal handle(BigDecimal a, BigDecimal b);
}
