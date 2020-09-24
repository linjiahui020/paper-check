package main.java.com.check.algorithm;



import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: LinJH
 * @Date: 2020/9/22 15:46
 * @Version:0.0.1
 */
public class CosSimilarity {

    /**
     * 余弦相似度算法
     * @param orgList
     * @param destList
     * @return
     */
    public static double getSimilarity(List<String> orgList,List<String> destList) {
        //保存词语及其重复情况的map
        Map<String,int[]> algMap=new HashMap<>();
        //先遍历源文件
        for (String org : orgList) {
            int[] arr = algMap.get(org);
            //数组不为空，说明之前已经创建过，直接下标0的value++
            if (arr != null && arr.length == 2) {
                arr[0]++;
            } else {
                //数组为空，说明之前没创建过，创建一个新数组
                arr = new int[2];
                arr[0] = 1;
                arr[1] = 0;
                algMap.put(org, arr);
            }
        }
        //遍历比对文件
        for (String dest : destList) {
            int[] arr = algMap.get(dest);
            //数组不为空，说明之前已经创建过，直接下标0的value++
            if (arr != null && arr.length == 2) {
                arr[1]++;
            } else {
                //数组为空，说明之前没创建过，创建一个新数组
                arr = new int[2];
                arr[0] = 0;
                arr[1] = 1;
                algMap.put(dest, arr);
            }
        }
        //源文件的方差
        double sqOrgStr = 0;
        //比对文件的方差
        double sqDesStr = 0;
        //分子
        double numerator = 0;
        for (Map.Entry entry : algMap.entrySet()) {
            int[] c = (int[]) entry.getValue();
            numerator += c[0] * c[1];
            sqOrgStr += c[0] * c[0];
            sqDesStr += c[1] * c[1];
        }
        //返回余弦
        return numerator / Math.sqrt(sqOrgStr * sqDesStr);
    }

}
