package com.linxb.railroad.center.utils;

/**
 * @Description directed strategy implement.
 * @Date 2019/02/28
 * @Author linxb
 * @Email airsolstice@outlook.com
 */
public class StringUtil {

    /**
     * whether or not be blank string.
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.equals("");
    }

    /**
     * whether or not be letter.
     *
     * @param letter
     * @return
     */
    public static boolean isLetter(String letter) {
        AssertUtil.check(!isBlank(letter), "letter is NULL.");
        char lt = letter.charAt(0);
        if ((lt <= 'Z' && lt >= 'A')
                || (lt <= 'z' && lt >= 'a')) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * convert 26 letters to index.
     *
     * @param letter
     * @return
     */
    public static int mapLetter2Index(String letter) {
        return !isLetter(letter) ? -1 : (int) letter.toUpperCase().charAt(0) - 65;
    }

    /**
     * convert index to 26 letters.
     * if index is not between 1 and 26, throw a out of bound exception.
     *
     * @param index
     * @return
     */
    public static String mapIndex2Letter(int index) {
        return (char) (index + 65) + "";
    }

    /**
     * split string, the result array is equal-length.
     *
     * @param str
     * @param regex
     * @param len   if length is less than 0, it indicates that the element is not equals-length.
     * @return
     */
    public static String[] splitEqualLength(String str, String regex, int len) {
        AssertUtil.check(!isBlank(str), "value is blank.");
        AssertUtil.checkNull(regex);
        String[] arr = str.split(regex);

        for (String s : arr) {
            AssertUtil.check(len == -1 || (s.trim().length() == len), "format error.");
        }

        return arr;
    }

    /**
     * count number of edges in route plan string.
     *
     * @param text
     * @param key
     * @return
     */
    public static int count(String text, String key) {
        return StringUtil.splitEqualLength(text, key, -1).length - 1;
    }

}
