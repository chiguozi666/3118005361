import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
    private static String regxpForHtml = "<([^>]*)>";
    /**
     * 读取文件，过滤，并且获得分了句子的list。
     * @param text 要处理的文本
     * @return 一个过滤后，分句处理完的list
     * @throws IOException
     */

    public static List<String> getSentenses(String text){
        String []stringItems =  filterHtml(text)
                .replaceAll("\\s*|\t|\r|\n","")//去空格
                .split("[，。！？；]");//划分句子,“我喜欢吃，鸡扒！还有酱香饼。”分出来是[我喜欢吃，鸡扒，还有酱香饼]
        LinkedList<String> result = new LinkedList<>();
        for(int i =0;i<stringItems.length-1;i++){
            result.add(stringItems[i]);
        }
        return result;
    }
    /**
     * 过滤去<>标签
     * @param str 需要处理的字符串
     * @return 返回一个没有标签的字符串
     */
    private static String filterHtml(String str) {
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
}
