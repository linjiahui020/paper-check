package main.java.com.check.algorithm;

import com.check.utils.IOUtils;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Author: LinJH
 * @Date: 2020/9/22 16:09
 * @Version:0.0.1
 */
public class SimilarityApp {

    //使用jieba分词库
    private static JiebaSegmenter segmenter = new JiebaSegmenter();

    //静态块，只加载一次，读取配置文件
    static {
        WordDictionary.getInstance().init(Paths.get("conf"));
    }

    public static void main(String[] args) {
        //起始时间戳
        long start = System.currentTimeMillis();
        //参数不是3个,输出错误信息
        if(args.length!=3){
            System.out.println("请输入3个参数");
            return;
        }
//        String rootPath = "F:/workspace/paper-check/src/main/resources";
//        System.err.println("源文件："+args[0]+"  比对文件："+args[1]);
        //第一个参数是源文件的绝对路径
        String orgStr = IOUtils.getStr(args[0]);
        //第二个参数是比对文件的绝对路径
        String desStr = IOUtils.getStr(args[1]);
        //返回null说明路径有错
        if(orgStr == null || desStr == null){
            System.err.println("打开文件出错  路径:"+ (orgStr != null ? " " : args[0]) + (desStr != null ? " " : args[1]));
            return;
        }
        //说明文件是空的
        if(orgStr.isEmpty() || desStr.isEmpty()){
            System.err.println("文件为空 路径:"+(orgStr.isEmpty() ? args[0] : "") + (desStr.isEmpty() ? args[1] : ""));
            return;
        }
        //使用jieba分词库对文件中的字符串根据正则表达式进行分词
        List<String> orgList = segmenter.sentenceProcess(orgStr.replaceAll("[`qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", ""));
        List<String> desList = segmenter.sentenceProcess(desStr.replaceAll("[`qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", ""));
//        System.out.println(Arrays.toString(desList.toArray()));
        File file = new File(args[2]);
        if(file.isDirectory()){
            System.out.println("输出目录为文件夹");
            return;
        }
        //执行余弦相似度算法
        double num = CosSimilarity.getSimilarity(orgList,desList);
        System.out.println("查重率为: "+num);
//        if(file.exists()){
//            System.out.println("输出文件已存在，请更改目录或者输出文件名字");
//            return;
//        }
        //通过io写入
        IOUtils.write(args[2],"查重率为:"+num);
        long end = System.currentTimeMillis();
        System.out.println("消耗时间:"+(end - start)+"ms");
//        try {
//            Thread.sleep(20 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
