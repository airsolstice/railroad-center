package com.linxb.railroad.center.utils;

import com.linxb.railroad.center.RailroadCenterException;

/**
 * @Description assert util.
 * @Date 2019/02/28
 * @Author linxb
 * @Email airsolstice@outlook.com
 */
public class AssertUtil {

    /**
     * assert the object whether or not be null.
     * if object is null, will throw a RailroadCenterException.
     *
     * @param obj
     */
    public static void checkNull(Object obj) {
        check(obj != null, "object is NULL.");
    }

    /**
     * assert actual value whether or not be equals to expect value,
     * if be not equals, throw a RailroadCenterException.
     *
     * @param expect
     * @param actual
     * @param err
     */
    public static void check(Object expect, Object actual, String err) {

        if (expect == null) {
            throw new RailroadCenterException(err);
        }

        if (!expect.equals(actual)) {
            throw new RailroadCenterException(err);
        }

    }

    /**
     * according to a judgement, if value is false, throw a RailroadCenterException.
     *
     * @param judge
     * @param err
     */
    public static void check(boolean judge, String err) {
        if (!judge) {
            throw new RailroadCenterException(err);
        }
    }
}
