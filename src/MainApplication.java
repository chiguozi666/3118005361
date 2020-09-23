import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainApplication {
    public static void run(String origpath,String copypath,String outpath){
        try{
            List<String> orgin = TextUtil.getSentenses(IOUtil.getText(origpath));
            List<String> copy = TextUtil.getSentenses(IOUtil.getText(copypath));
            IOUtil.writeText(outpath,"重复率是:"+EasyCompaired.compaired(orgin,copy));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入原文本 样例C:\\homework\\orig.txt");
        String orginPath = sc.next();
        System.out.println("请输入要检验的文本 样例C:\\homework\\orig_0.8_add.txt");
        String copyPath = sc.next();

        //List<String> orgin = new EasyCompaired().getSentenses("C:\\homework\\orig.txt");
        //List<String> copy = new EasyCompaired().getSentenses("C:\\homework\\orig_0.8_add.txt");
        List<String> orgin = TextUtil.getSentenses(IOUtil.getText(orginPath));
        List<String> copy = TextUtil.getSentenses(IOUtil.getText(copyPath));
        System.out.println("请输入要输出的位置 样例C:\\homework\\happy.txt");
        String outputPath = sc.next();
        IOUtil.writeText(outputPath,"重复率是:"+EasyCompaired.compaired(orgin,copy));
    }
}
