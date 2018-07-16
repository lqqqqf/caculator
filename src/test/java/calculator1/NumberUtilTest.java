/*
 * Copyright (c) 2016-2018. https://github.com/lqqqqf. All rights reserved.
 */

package calculator1;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NumberUtilTest {

    @Test
    public void isDigit() {
        System.out.println(new BigDecimal("3E+1").divide(new BigDecimal(2)));
        String[] nums = new String[]{"1", "-2", "3.4", "-0.8"};
        for (String num: nums) {
            assertTrue(NumberUtil.isDigit(num));
        }

        String[] nonNums = new String[]{"1l", "-if", "3f.4", "-0.8&&", "发"};
        for (String num: nonNums) {
            assertFalse(NumberUtil.isDigit(num));
        }
    }
}