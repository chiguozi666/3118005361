import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MainApplicationTest {
    @BeforeClass
    public static void beforeTest(){
        System.out.println("junit单元测试开始");
    }

    @AfterClass
    public static void afterTest(){
        System.out.println("junit单元测试结束");
    }

    /**
     * 测试 正常的测试
     */
    @Test
    public void testForAdd(){
        MainApplication.run("C:\\homework\\orig.txt","C:\\homework\\orig_0.8_add.txt","C:\\homework\\happy.txt");
    }

    /**
     * 测试 删除orig_0.8_del.txt
     */
    @Test
    public void testForDel(){
        MainApplication.run("C:\\homework\\orig.txt","C:\\homework\\orig_0.8_del.txt","C:\\homework\\happy.txt");
    }

    /**
     * 测试 分割orig_0.8_dis_1.txt
     */
    @Test
    public void testForDis1(){
        MainApplication.run("C:\\homework\\orig.txt","C:\\homework\\orig_0.8_dis_1.txt","C:\\homework\\happy.txt");
    }

    /**
     * 测试 分割orig_0.8_dis_10.txt
     */
    @Test
    public void testForDis10(){
        MainApplication.run("C:\\homework\\orig.txt","C:\\homework\\orig_0.8_dis_10.txt","C:\\homework\\happy.txt");
    }

    /**
     * 测试 分割orig_0.8_dis_15.txt
     */
    @Test
    public void testForDis15(){
        MainApplication.run("C:\\homework\\orig.txt","C:\\homework\\orig_0.8_dis_15.txt","C:\\homework\\happy.txt");
    }

    /**
     * 测试 传入的文件路径错误
     */
    @Test
    public void testFororigpath(){
        MainApplication.run("don't run","C:\\homework\\orig_0.8_dis_15.txt","C:\\homework\\happy.txt");
    }


}
