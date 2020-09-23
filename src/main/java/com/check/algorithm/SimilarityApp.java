package com.check.algorithm;

import com.check.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Author: LinJH
 * @Date: 2020/9/22 16:09
 * @Version:0.0.1
 */
public class SimilarityApp {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //参数不是3个,输出错误信息
        if(args.length!=3){
            System.out.println("请输入3个参数");
            return;
        }
//        String rootPath = "F:/workspace/paper-check/src/main/resources";
//        System.err.println("源文件："+args[0]+"  比对文件："+args[1]);
        String orgStr = IOUtils.getStr(args[0]);
        String desStr = IOUtils.getStr(args[1]);
        if(orgStr == null || desStr == null){
            System.err.println("打开文件出错  路径:"+ (orgStr != null ? "" : args[0]) + (desStr != null ? "" : args[1]));
            return;
        }
        if(orgStr.isEmpty() || desStr.isEmpty()){
            System.err.println("文件为空 路径:"+(orgStr.isEmpty() ? args[0] : "") + (desStr.isEmpty() ? args[1] : ""));
            return;
        }
        double num = CosSimilarity.getSimilarity(orgStr,desStr);
        System.out.println("查重率为: "+num);
        File file = new File(args[2]);
        if(file.isDirectory()){
            System.out.println("输出目录为文件夹");
            return;
        }
        if(file.exists()){
            System.out.println("输出文件已存在，请更改目录或者输出文件名字");
            return;
        }
        IOUtils.write(args[2],String.valueOf(num));
        long end = System.currentTimeMillis();
        System.out.println("消耗时间:"+(end - start)+"ms");
        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
