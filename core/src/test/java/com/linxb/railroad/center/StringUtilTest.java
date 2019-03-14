package com.linxb.railroad.center;

import com.linxb.railroad.center.utils.StringUtil;
import org.junit.Test;

public class StringUtilTest {

    @Test
    public void testStringIsBlank(){
        String str1 = "";
        System.out.println("expect is true, actual is "+ StringUtil.isBlank(str1));
        String str2 = null;
        System.out.println("expect is true, actual is "+ StringUtil.isBlank(str2));
        String str3 = " ";
        System.out.println("expect is false, actual is "+ StringUtil.isBlank(str3));
        String str4 = "test";
        System.out.println("expect is false, actual is "+ StringUtil.isBlank(str4));
    }

    @Test
    public void testStringIsLetter(){
        String str1 = "A";
        System.out.println("expect is true, actual is " + StringUtil.isLetter(str1));
        String str2 = "a";
        System.out.println("expect is true, actual is " + StringUtil.isLetter(str2));
        String str3 = "1";
        System.out.println("expect is false, actual is " + StringUtil.isLetter(str3));
        String str4 = "A1";
        System.out.println("expect is true, actual is " + StringUtil.isLetter(str4));
        String str5 = "1A";
        System.out.println("expect is false, actual is " + StringUtil.isLetter(str5));

    }

    @Test
    public void testMapLetter2Index(){
        String str1 = "A";
        System.out.println("expect is 0, actual is " + StringUtil.mapLetter2Index(str1));
        String str2 = "a";
        System.out.println("expect is 0, actual is " + StringUtil.mapLetter2Index(str2));
        String str3 = "1";
        System.out.println("expect is -1, actual is " + StringUtil.mapLetter2Index(str3));
        String str4 = " ";
        System.out.println("expect is -1, actual is " + StringUtil.mapLetter2Index(str4));
        String str6 = "Za";
        System.out.println("expect is 25, actual is " + StringUtil.mapLetter2Index(str6));
        try {
            String str5 = "";
            System.out.println("expect is throwable, actual is " + StringUtil.mapLetter2Index(str5));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }

    }

    @Test
    public void testSplitEqualLength(){
        String str1 = "AB1, BC3";
        StringUtil.splitEqualLength(str1, ",", 3);

        try {
            String str2 = "ABd1, BC3";
            StringUtil.splitEqualLength(str2, ",", 3);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }

        try {
            String str3 = "ABd,BC3 ";
            StringUtil.splitEqualLength(str3, ",", 3);
        } catch (Exception e) {
            System.out.println("normal result is throwing a exception.");
            e.printStackTrace();
        }
    }

    @Test
    public void testMapIndex2Letter(){
        int i1 = 0;
        System.out.println("expect is A, actual is " + StringUtil.mapIndex2Letter(i1));
        int i2 = 1;
        System.out.println("expect is B, actual is " + StringUtil.mapIndex2Letter(i2));
    }

    @Test
    public void testCount(){
        // count  string
        String text = "aa-bb-cc-dd";
        int count = StringUtil.count(text, "-");

        System.out.println("expect is 3 actual is " + count);
    }

    @Test
    public void testCount1(){
        // count  string
        String text = "aaasdasda-bb-cc-dd";
        int count = StringUtil.count(text, "-");

        System.out.println("expect is 3 actual is " + count);
    }


    @Test
    public void testCount2(){
        // count  string
        String text = "a2222-bb-cc-dd";
        int count = StringUtil.count(text, "-");

        System.out.println("expect is 3 actual is " + count);
    }

}
