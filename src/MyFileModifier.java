import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyFileModifier {

    /*
     * 读取文件内容到字符串列表，忽略空行
     */
    private static List<String> readFileToListWithoutEmptyLines(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        return lines.stream()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toList());
    }

    /*
     * 将字符串列表写入文件
     */
    private static void writeListToFile(List<String> lines, String filePath) throws IOException {
        Files.write(Paths.get(filePath), lines);
    }

    /**
     * 解析并修改设置文件，删除所有空行
     */
    public static void settingsParser(String filePath, String section, String subField, String newValue)
            throws IOException {
        List<String> lines = readFileToListWithoutEmptyLines(filePath);
        List<String> modifiedLines = new ArrayList<>();
        String currentSection = "";
        // boolean fieldFound = false;

        for (String line : lines) {
            if (line.startsWith("--")) {
                currentSection = line.substring(2).trim();
                modifiedLines.add(line);
                if (currentSection.equals(section)) {
                    // fieldFound = false;
                }
            } else {
                if (currentSection.equals(section)) {
                    String[] parts = line.split(": ", 2);
                    if (parts.length == 2 && parts[0].trim().equals(subField)) {
                        modifiedLines.add(subField + ": " + newValue);
                        // fieldFound = true;
                    } else {
                        modifiedLines.add(line);
                    }
                } else {
                    modifiedLines.add(line);
                }
            }
        }

        writeListToFile(modifiedLines, filePath);
    }

    /**
     * 从配置文件中读取指定主字段和次字段的值。
     *
     * @param filePath 文件路径
     * @param section  主字段
     * @param subField 次字段
     * @return 次字段的值，如果未找到则返回 null
     * @throws IOException 如果文件读取过程中发生错误
     */
    public static String readFieldValue(String filePath, String section, String subField) throws IOException {
        MyFileReader reader = new MyFileReader(filePath);
        String currentSection = "";
        String line;

        while ((line = reader.readNonBlankLine()) != null) {
            if (line.startsWith("--")) {
                currentSection = line.substring(2).trim();
            } else {
                String[] parts = line.split(": ", 2);
                if (parts.length == 2) {
                    String field = parts[0].trim();
                    String value = parts[1].trim();

                    if (currentSection.equals(section) && field.equals(subField)) {
                        reader.close();
                        return value;
                    }
                }
            }
        }

        reader.close();
        return null;
    }

    /*
     * 使用示例
     */
    // public static void main(String[] args) {
    // try {
    // String filePath =
    // "B:\\1_project\\5_自写代码\\_200自写java\\TSIv2工作区\\TSIv2\\res\\story1\\setting.txt";
    // // 请替换为实际的文件路径
    // settingsParser(filePath, "story", "章节数", "61");
    // System.out.println("修改成功！");

    // String section = "story";
    // String subField = "章节数";
    // String value = MyFileModifier.readFieldValue(filePath, section, subField);
    // System.out.println("章节数: " + value);

    // section = "c2.1";
    // subField = "名称";
    // value = MyFileModifier.readFieldValue(filePath, section, subField);
    // System.out.println("c2.1: " + value);
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
}