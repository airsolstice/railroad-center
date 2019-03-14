package com.linxb.railroad.center;

import com.linxb.railroad.center.utils.FileUtil;
import org.junit.Test;

import java.util.Map;

import static com.linxb.railroad.center.utils.FileUtil.parseConfig;

public class FileUtilTest {

    @Test
    public void restReadLine() {
        FileUtil.ProcessCallable callable = (line, num) -> System.out.println(num + ". " + line);
        try {
            FileUtil.readLine("", callable);// blank
        } catch (Exception e) {
            System.out.println("invalid path");
        }
        try {
            FileUtil.readLine(null, callable);// empty
        } catch (Exception e) {
            System.out.println("invalid path");
        }

        try {
            FileUtil.readLine("a", callable);// file not exist
        } catch (Exception e) {
            System.out.println("invalid path");
        }
        try {
            FileUtil.readLine("D:\\workspace\\gitee\\RailroadCenter\\graph.txt", null);// callable is null.
        } catch (Exception e) {
            System.out.println("invalid path");
        }

        FileUtil.readLine("D:\\workspace\\gitee\\RailroadCenter\\graph.txt", callable);

    }

    @Test
    public void testParseConfig(){

        try {
            Map<String, Object> conf = FileUtil.parseConfig("D:\\workspace\\gitee\\RailroadCenter\\graph.txt", "-");
        } catch (Exception e) {
            System.out.println("invalid regex.");
        }

        Map<String, Object> conf = FileUtil.parseConfig("D:\\workspace\\gitee\\RailroadCenter\\graph.txt", "=");
        System.out.println(conf.get("graph"));
        System.out.println(conf.get("size"));
    }

}
