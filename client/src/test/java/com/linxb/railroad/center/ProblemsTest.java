package com.linxb.railroad.center;

import com.linxb.railroad.center.utils.AssertUtil;
import com.linxb.railroad.center.utils.Problems;
import org.junit.Test;

public class ProblemsTest {

    @Test
    public void testGet1() {
        try {
            Problems.get(13);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGet2() {
        Problems.get(6);
    }

    @Test
    public void testSize() {
        AssertUtil.check(10, Problems.size(), "size must be 10.");
    }

}
