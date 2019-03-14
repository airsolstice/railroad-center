package com.linxb.railroad.center;

import com.linxb.railroad.center.utils.AssertUtil;
import org.junit.Test;

public class AssertUtilTest {

    @Test
    public void testCheckNotNull1(){
        String str1 = "";
        AssertUtil.checkNull(str1);
        System.out.println("assert finish.");
    }

    @Test
    public void testCheckNotNull2(){
        String str1 = "A";
        AssertUtil.checkNull(str1);
        System.out.println("assert finish.");
    }

    @Test
    public void testCheckNotNull3(){
        try {
            String str1 = null;
            AssertUtil.checkNull(str1);
            System.out.println("assert finish.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testCheck1(){
        AssertUtil.check("2", "2", "string not equals.");
    }

    @Test
    public void testCheck2(){
        AssertUtil.check(2, 2, "string not equals.");
    }

    @Test
    public void testCheck3(){
        try {
            String expect = "2";
            String actual = "3";
            AssertUtil.check(expect, actual, "string not equals.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }

    }

    @Test
    public void testCheck4(){
        String expect = "2";
        AssertUtil.check(expect, "2", "string not equals.");
    }

    @Test
    public void testCheck5(){
        Integer expect = 2;
        Integer actual = 2;
        AssertUtil.check(expect, actual, "string not equals.");
    }

    @Test
    public void testCheck6(){
        Integer expect = 2;
        AssertUtil.check(expect, 2, "string not equals.");
    }

    @Test
    public void testCheck7(){
        try {
            String str = "2";
            AssertUtil.check(str.equals("3"), "string not equals.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

}
