package com.check.utils;

import java.io.*;

/**
 * @Author: LinJH
 * @Date: 2020/9/22 16:26
 * @Version:0.0.1
 */
public class IOUtils {

    /**
     * 输入路径获取文件中的字符串
     * @param path
     * @return
     */
    public static String getStr(String path){
        StringBuilder sb = new StringBuilder();
        File file = new File(path);
        if(!file.exists()){
            return null;
        }
        try (FileReader fis = new FileReader(file); BufferedReader reader = new BufferedReader(fis)){
            String line;
            while((line = reader.readLine()) != null){
                sb.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 传入输出文件路径和文本内容，写入到文件中
     * @param path
     * @param data
     */
    public static void write(String path,String data){
        try(FileWriter fw = new FileWriter(path)){
            fw.write(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
