import java.io.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EasyCompaired {
    private static String regxpForHtml = "<([^>]*)>";
    static int orginlength =0;
    static int matchlength =0;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String orginPath = sc.next();
        String copyPath = sc.next();
        String outputPath = sc.next();
        List<String> orgin = new EasyCompaired().getSentenses("C:\\homework\\orig.txt");
        List<String> copy = new EasyCompaired().getSentenses("C:\\homework\\orig_0.8_add.txt");
        compaired(orgin,copy);
//        for(String a:orgin){
//            System.out.println(a);
//            System.out.println(1);
//        }
    }
    public List<String> getSentenses(String path) throws IOException {

        File file = new File(path);
        StringBuffer stringBuffer = new StringBuffer();
        if(!file.exists()){
            throw new RuntimeException("文件名错误");
        }

        byte datas [] = new byte[1024];
        int data = -1;
        InputStream inputStream = new FileInputStream(file);
        while((data = inputStream.read(datas))!=-1){
            stringBuffer.append(new String(datas));
            datas =  new byte[1024];
        }
        String stringItems[] =  filterHtml(stringBuffer.toString())
                .replaceAll("\\s*|\t|\r|\n","")//去空格
                .split("[，。！？；]");//划分
        LinkedList<String>  result = new LinkedList<>();
        for(int i =0;i<stringItems.length-1;i++){
            System.out.println(stringItems[i]);
            result.add(stringItems[i]);
        }
        return result;
    }

    /**
     * 过滤去<>标签
     * @param str
     * @return 返回一个没有标签的字符串
     */
    public static String filterHtml(String str) {
        Pattern pattern = Pattern.compile(regxpForHtml);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = matcher.find();
        while (result) {
            matcher.appendReplacement(sb, "");
            result = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 逐句比较
     * @param orgin 原文的句子
     * @param copy 要比较文章的句子
     * @return
     */
    public static double compaired(List orgin,List copy){
        orginlength = orgin.size();
        for (int i = 0; i < orgin.size(); i++) {
            for (int j = 0; j < copy.size(); j++) {
                //单句假设重复度超过0.8那么就是重复语句
                if(getSimilarityRatio((String)orgin.get(i),(String)copy.get(j))){
                    System.out.println(orgin.get(i));
                    System.out.println(copy.get(j));
                    matchlength++;
                    break;
                }
            }
        }
        System.out.println("匹配高重的语句数目:"+matchlength+"  总长度："+orginlength+"\n"+(float)matchlength/orginlength);
        return 0;
    }

    /**
     * 算出两个字符串之间最少改动多少次能相互得到
     * @param orig
     * @param copy
     * @return
     */
    public static double aTob(String orig,String copy){
        int d[][]; // 矩阵
        int n = orig.length();
        int m = copy.length();
        int i; // 遍历str1的
        int j; // 遍历str2的
        char ch1; // str1的
        char ch2; // str2的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        for (i = 1; i <= n; i++) {
            ch1 = orig.charAt(i - 1);
            for (j = 1; j <= m; j++) {
                ch2 = copy.charAt(j - 1);
                //相同距离是0
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                //动态转移方程
                d[i][j] = getMin(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1]+ temp);
            }
        }
        return d[n][m];
    }
    public static boolean getSimilarityRatio(String str, String target)
    {
        //return (1 - (float) aTob(str, target) / Math.max(str.length(), target.length()))>0.75;
        float move =  (float)aTob(str,target);
        int lendif = Math.abs(str.length() - target.length());
        return (lendif+move)/(str.length()+lendif)<0.5;
    }

    public static int  getMin(int a,int b,int c){
        return (a = a < b ? a : b) < c ? a : c;
    }
}
