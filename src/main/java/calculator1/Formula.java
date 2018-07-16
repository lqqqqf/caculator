/*
 * Copyright (c) 2016-2018. https://github.com/lqqqqf. All rights reserved.
 */

package calculator1;

import java.math.BigDecimal;

/**
 * 提供内联计算，例如：<p>4+5*6，4*（3+4）。</p>
 * @see Calculator
 */
public interface Formula {

    BigDecimal perform(Calculator calculator);
}
