package com.check.algorithm;



import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LinJH
 * @Date: 2020/9/22 15:46
 * @Version:0.0.1
 */
public class CosSimilarity {
    public static double getSimilarity(String orgString, String destString) {
        Map<Character,int[]> algMap=new HashMap<>();
        for (int i = 0; i<orgString.length(); i++) {
            char d1 = orgString.charAt(i);
            int[] arr = algMap.get(d1);
            if (arr != null && arr.length == 2) {
                arr[0]++;
            } else {
                arr = new int[2];
                arr[0] = 1;
                arr[1] = 0;
                algMap.put(d1, arr);
            }
        }
        for (int i = 0; i<destString.length(); i++) {
            char d2 = destString.charAt(i);
            int[] fq = algMap.get(d2);
            if (fq != null && fq.length == 2) {
                fq[1]++;
            } else {
                fq = new int[2];
                fq[0] = 0;
                fq[1] = 1;
                algMap.put(d2, fq);
            }
        }
        double sqOrgStr = 0;
        double sqDesStr = 0;
        double denuminator = 0;
        for (Map.Entry entry : algMap.entrySet()) {
            int[] c = (int[]) entry.getValue();
            denuminator += c[0] * c[1];
            sqOrgStr += c[0] * c[0];
            sqDesStr += c[1] * c[1];
        }
        return denuminator / Math.sqrt(sqOrgStr * sqDesStr);
    }

}
