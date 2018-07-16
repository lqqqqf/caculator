/*
 * Copyright (c) 2016-2018. https://github.com/lqqqqf. All rights reserved.
 */

package calculator1;

import java.math.BigDecimal;

/**
 * 转换成数字。
 */
public interface NumberConverter {


    BigDecimal convert(String symbol);
}
