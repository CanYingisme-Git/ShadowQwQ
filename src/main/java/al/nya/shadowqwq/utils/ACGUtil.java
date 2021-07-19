package al.nya.shadowqwq.utils;

import al.nya.shadowqwq.ShadowQwQ;
import com.google.gson.Gson;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class ACGUtil {

    public static String download(){
        //http://img.xjh.me/random_img.php
        String picAPI ="http://img.xjh.me/random_img.php";
        String token="v32Eo2Tw+qWI/eiKW3D8ye7l19mf1NngRLushO6CumLMHIO1aryun0/Y3N3YQCv/TqzaO/TFHw4=";
        String uuid = getUUID();
        String json = uuid+".json";
        try {
            downLoadFromUrl(picAPI+"?return=json",json,"./acg",token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonFile = readJson("./acg/"+json);
        Gson gson = new Gson();
        ACGInfo acgInfo = gson.fromJson(jsonFile,ACGInfo.class);
        String img = uuid+".jpg";
        try {
            downLoadFromUrl("http:"+acgInfo.getImg(),img,"./acg",token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "./acg/"+img;
    }
    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        return str;
    }
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath,String toekn) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        conn.setRequestProperty("lfwywxqyh_token",toekn);

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }


        ShadowQwQ.INSTANCE.getLogger().info(url+" download success");

    }
    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
    public static String readJson(String path) {
        String pathname = path; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        String s = "";
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                s = s + line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
