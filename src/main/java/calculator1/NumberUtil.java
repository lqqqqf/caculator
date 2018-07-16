/*
 * Copyright (c) 2016-2018. https://github.com/lqqqqf. All rights reserved.
 */

package calculator1;

import java.util.regex.Pattern;

public abstract class NumberUtil {

    private static final Pattern DIGIT = Pattern.compile("(^[-\\+]?\\d*$)|(^[-\\+]?\\d+\\.\\d+$)");

    /**
     * 检查是否为数字。
     * @param digit digit.
     * @return
     */
    public static Boolean isDigit(String digit) {
        return DIGIT.matcher(digit).matches();
    }
}
