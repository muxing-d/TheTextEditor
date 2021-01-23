import java.io.*;

/**
 * 该类是文件管理类，负责文件的读取和保存
 */
public class FileManage {
    /**
     *读取文件内容，以字符串的形式返回
     */
    public String read(File file) {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file)));
            int len = (int) file.length();
            char[] buffer = new char[len];
            br.read(buffer, 0, len);
            br.close();
            return new String(buffer);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *将写在编辑器里的内容保存在相应的文件里
     */
    public void save(File file, String text) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(file)));
            bw.write(text);
            bw.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }

    }

}
