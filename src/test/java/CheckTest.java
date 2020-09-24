package test.java;
import main.java.com.check.algorithm.SimilarityApp;
import org.junit.Test;

/**
 * @Author: LinJH
 * @Date: 2020/9/24 19:21
 * @Version:0.0.1
 */
public class CheckTest {

    /**
     * 单元测试
     */
    @Test
    public void test01(){
        String[] paths = {
                "F:/workspace/orig.txt",
                "F:/workspace/orig_0.8_add.txt",
                "F:/result.txt"
        };
        SimilarityApp.main(paths);
    }
}
