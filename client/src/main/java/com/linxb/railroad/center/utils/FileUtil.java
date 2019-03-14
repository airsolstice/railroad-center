package com.linxb.railroad.center.utils;

import com.linxb.railroad.center.RailroadCenterException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUtil {

    /**
     * interface of processing per line.
     */
    public interface ProcessCallable {
        void process(String line, int num);
    }

    /**
     * read file line by line.
     *
     * @param path
     * @param callable
     */
    public static void readLine(String path, ProcessCallable callable) {
        AssertUtil.checkNull(callable);
        File file = new File(path);
        AssertUtil.check(file.exists(), "file is not exist.");

//        FileInputStream fis = null;
//        BufferedReader reader = null;
        int num = 0;

        try (FileInputStream fis = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
            String line;
            while ((line = reader.readLine()) != null) {
                num++;
                // skip empty line.
                if (StringUtil.isBlank(line)) {
                    continue;
                }
                // callback
                callable.process(line, num);
            }
        } catch (IOException e) {
            throw new RailroadCenterException(e);
        }

//        try {
//            fis = new FileInputStream(file);
//            reader = new BufferedReader(new InputStreamReader(fis));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                num++;
//                // skip empty line.
//                if (StringUtil.isBlank(line)) {
//                    continue;
//                }
//                // callback
//                callable.process(line, num);
//            }
//
//        } catch (IOException e) {
//            throw new RailroadCenterException(e);
//        } finally {
//            close(fis);
//            close(reader);
//        }
    }

    /**
     * parse config from line string.
     *
     * @param path
     * @return
     */
    public static Map<String, Object> parseConfig(String path, String regex) {
        AssertUtil.checkNull(regex);
        Map<String, Object> conf = new HashMap<>();

        readLine(path, (line, num) -> {
            String[] kv = line.split(regex);
            AssertUtil.check(kv.length == 2, "this line is not a key-value string.");
            conf.put(kv[0], kv[1]);
        });

        return conf;
    }

    /**
     * close resource.
     *
     * @param closeable
     */
    private static void close(Closeable closeable) {

        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
