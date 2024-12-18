import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

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
    public static void settingsParser(String filePath, String section, String subField, String newValue) {

        try {
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

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "无法写入配置文件。", "警告",
                    JOptionPane.ERROR_MESSAGE);
        }

        System.out.println("===========================");

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
    public static String readFieldValue(String filePath, String section, String subField) {

        try {
            MyFileReader reader = new MyFileReader(filePath);
            String currentSection = "";
            String line;

            // wc，如果主字段在第一行，会直接跳过不读取，会这样
            while ((line = reader.readNonBlankLine()) != null) {
                if (line.startsWith("--")) {
                    currentSection = line.substring(2).trim();
                    // System.out.println("当前为主字段" + currentSection);

                    if (currentSection.equalsIgnoreCase(section)) {

                        System.out.println("找到主字段" + currentSection);

                        while ((line = reader.readNonBlankLine()) != null && !line.startsWith("--")) {
                            String[] parts = line.split(": ", 2);
                            if (parts.length == 2) {
                                System.out.println(Arrays.toString(parts) + "成功分成两部分");
                                String field = parts[0].trim();
                                String value = parts[1].trim();

                                if (field.equals(subField)) {
                                    System.out.println(Arrays.toString(parts) + "找到" + field + "下的副字段" + subField);
                                    reader.close();
                                    return value;
                                } else {
                                    System.out.println(Arrays.toString(parts) + "没有找到" + field + "下的副字段" + subField);
                                }
                            } else {
                                System.out.println(currentSection + "没有成功分成两部分，格式出错，请使用英文冒号+空格");
                            }
                        }

                    }

                } else if (currentSection.equalsIgnoreCase(section)) {

                } else {
                    // System.out.println("没有找到主字段");
                }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "未读取到配置文件。" + filePath + "中" + section + "的有效值" + subField, "警告",
                JOptionPane.ERROR_MESSAGE);
        return null;

    }

    /*
     * 使用示例
     */
    public static void main(String[] args) {

        // String filePath =
        // "B:\\1_project\\5_自写代码\\_200自写java\\TSIv2工作区\\TSIv2\\res\\story1\\setting.txt";
        // settingsParser(filePath, "story", "章节数", "61");
        // System.out.println("修改成功！");

        String path = MyStyle.getStorySettingFilePath(2);
        System.out.println(path);

        String value = MyFileModifier.readFieldValue(path, "story", "writer");
        System.out.println("章节数: " + value);
    }
}