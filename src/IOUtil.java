import java.io.*;

public class IOUtil {
    /**
     * io操作得到指定的text
     * @param path 得到的路径
     * @return 一个text
     * @throws IOException
     */
    public static String getText(String path) throws IOException {
        File file = new File(path);
        StringBuffer stringBuffer = new StringBuffer();
        //文件不存在就报错
        if(!file.exists()){
            throw new RuntimeException("文件名错误");
        }
        byte []datas = new byte[1024];
        InputStream inputStream = new FileInputStream(file);
        while(inputStream.read(datas)!=-1){
            stringBuffer.append(new String(datas));
            datas = new byte[1024];
        }
        return stringBuffer.toString();
    }

    /**
     *
     * @param path 写的路径
     * @param text 写入的文本
     * @throws IOException
     */
    public static void writeText(String path,String text) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path));
        outputStreamWriter.write(text);
        outputStreamWriter.flush();
        outputStreamWriter.close();
    }

    public static void main(String[] args) throws IOException {
        writeText("C:\\homework\\happy.txt","嘻嘻嘻");
    }
}
