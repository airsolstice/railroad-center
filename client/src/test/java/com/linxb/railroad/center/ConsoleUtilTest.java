package com.linxb.railroad.center;

import com.linxb.railroad.center.utils.ConsoleUtil;
import org.junit.Test;

public class ConsoleUtilTest {

    @Test
    public void testInput(){
        ConsoleUtil.input();
    }

    @Test
    public void testOutput(){
        ConsoleUtil.output(11, 102);
    }


}
