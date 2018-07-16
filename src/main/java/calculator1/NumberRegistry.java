/*
 * Copyright (c) 2016-2018. https://github.com/lqqqqf. All rights reserved.
 */

package calculator1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 此类用于把其他语言注册为阿拉伯数字，例如将"四"注册成4。<br>
 * <code>
 *     NumberRegistry numberRegistry = NumberRegistry.newInstance();
 *     numberRegistry.register("四", "4");
 *     numberRegistry.register("four", "4");
 * </code>
 */
public class NumberRegistry implements NumberConverter{

    private static final Logger LOG = LoggerFactory.getLogger(NumberRegistry.class);

    private static final NumberRegistry INSTANCE = new NumberRegistry();

    private Map<String, BigDecimal> numbers = new HashMap<>();
    private NumberRegistry() {

    }

    public static NumberRegistry newInstance() {
        return INSTANCE;
    }

    public void register(String symbol, String num) {
        if (NumberUtil.isDigit(num)) {
            numbers.put(symbol, new BigDecimal(num));
            LOG.debug("\"{}\"注册成功，对应的数字是{}", symbol, num);
        } else {
            LOG.debug("\"{}\"必须为数字", num);
        }
    }

    @Override
    public BigDecimal convert(String symbol) {
        BigDecimal ret = numbers.get(symbol);
        if (ret == null) {
            LOG.debug("\"{}\"没有注册", symbol);
        }
        return ret;
    }
}
