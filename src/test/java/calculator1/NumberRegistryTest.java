/*
 * Copyright (c) 2016-2018. https://github.com/lqqqqf. All rights reserved.
 */

package calculator1;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class NumberRegistryTest {

    @Test
    public void newInstance() {
        assertTrue(NumberRegistry.newInstance() == NumberRegistry.newInstance());
    }

    @Test
    public void testConvert() {
        NumberRegistry numberRegistry = NumberRegistry.newInstance();
        numberRegistry.register("十", "10");
        assertThat(numberRegistry.convert("十"), equalTo(new BigDecimal(10)));
        assertThat(numberRegistry.convert("九"), equalTo(null));
        numberRegistry.register("九", "9");
        assertThat(numberRegistry.convert("九"), equalTo(new BigDecimal(9)));
    }
}