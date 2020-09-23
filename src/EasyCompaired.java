
import java.util.List;

public class EasyCompaired {
    static int orginlength =0;
    static int matchlength =0;

    /**
     * 逐句比较
     * @param orgin 原文的句子
     * @param copy 要比较文章的句子
     * @return 处理句子
     */
    public static double compaired(List orgin,List copy){
        orginlength = orgin.size();
        for (int i = 0; i < orgin.size(); i++) {
            for (int j = 0; j < copy.size(); j++) {
                //单句假设重复度超过0.8那么就是重复语句
                if(isSimilarity((String)orgin.get(i),(String)copy.get(j))){
                    System.out.println(orgin.get(i));
                    System.out.println(copy.get(j));
                    matchlength++;
                    break;
                }
            }
        }
        System.out.println("匹配高重的语句数目:"+matchlength+"  总长度："+orginlength+"\n"+(float)matchlength/orginlength);
        return (float)matchlength/orginlength;
    }

    /**
     * 算出两个字符串之间最少改动多少次能相互得到
     * @param orig 原文
     * @param copy 字符串
     * @return 返回orig和copy最少经过多少次变换得到
     */
    private static double getDistance(String orig, String copy){
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
            d[i][0] = i;//初始化第0列的距离
        }
        for (j = 0; j <= m; j++) {
            d[0][j] = j;//初始化第0行的距离
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
                //d[i][j] 的值由前面相邻的两个格子，以及左上对角线的得到，
                d[i][j] = getMin(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1]+ temp);
            }
        }
        return d[n][m];
    }

    /**
     * 判断str和target是否相似
     * @param str 原句子
     * @param target 目标句子
     * @return 是否相似
     */
    private static boolean isSimilarity(String str, String target)
    {
        //return (1 - (float) aTob(str, target) / Math.max(str.length(), target.length()))>0.75;
        float move =  (float) getDistance(str,target);
        int lendif = Math.abs(str.length() - target.length());
        return (lendif+move)/(str.length()+lendif)<0.5;
    }

    /**
     * 得到abc中的最小值
     * @param a 其中一个值
     * @param b 其中一个值
     * @param c 其中一个值
     * @return 最小的值
     */
    private static int getMin(int a,int b,int c){
        return (a = a < b ? a : b) < c ? a : c;
    }
}
