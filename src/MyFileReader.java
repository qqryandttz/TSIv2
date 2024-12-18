import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/*
 * 要实现的功能，首先是给一个文件路径，可以一行一行地读取
 * 每引用一次返回一行内容的字符串
 * 并且这一行开头不能是空白，并且这一行不能是空白，并且如果开头是//则跳过
 */
class MyFileReader {

    private String filePath;
    private FileInputStream fileInputStream;
    private BufferedReader bufferedReader;

    /*
     * 使用示例
     */
    public static void main(String[] args) {

        MyFileReader reader = new MyFileReader(
                "B:\\1_project\\5_自写代码\\_200自写java\\TSIv2工作区\\TSIv2\\res\\TSIv2\\setting.txt");
        String Line;

        while ((Line = reader.readNonBlankLine()) != null) {
            System.out.println(Line);
        }
        reader.close();
    }

    MyFileReader(String Path) {
        this.filePath = Path;
        try {
            fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "无法访问文件！(请联系我)", "警告", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*
     * 仅在readNonBlankLine()中被使用
     */
    private String readLine() {
        String line;
        try {
            line = bufferedReader.readLine();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 从文件中读取非空白行且不是注释行
     * 返回读取到的非空白行字符串，如果到达文件末尾或没有更多非空白行则返回null
     */
    String readNonBlankLine() {
        String line;
        while ((line = this.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty() && !line.startsWith("//")) {
                return line;
            }
        }
        return null;
    }

    /**
     * 关闭文件读取器，释放资源
     */
    void close() {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
