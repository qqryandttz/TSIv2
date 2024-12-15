import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FileModifier {

    /**
     * 读取文件内容到列表的方法
     * 
     * @param filePath
     * @return
     * @throws IOException
     */
    public static List<String> readFileToList(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static void writeListToFile(List<String> lines, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    /**
     * 查找以"--"开头并紧跟指定字符串的行
     * 
     * @param lines
     * @param prefixes
     * @return
     */
    public static List<Integer> findLinesWithPrefix(List<String> lines, String... prefixes) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (String prefix : prefixes) {
                if (line.startsWith("--" + prefix)) {
                    indices.add(i);
                    break;
                }
            }
        }
        return indices;
    }

    // 对文件内容进行处理的方法（示例：删除找到的行并在其后添加新行）
    public static void processFile(String filePath, String[] prefixesToDelete, String[] prefixesToAddAfter,
            String newLine) throws IOException {
        List<String> lines = readFileToList(filePath);
        List<Integer> indicesToDelete = findLinesWithPrefix(lines, prefixesToDelete);
        List<Integer> indicesToAdd = findLinesWithPrefix(lines, prefixesToAddAfter);

        // 注意：我们先处理删除操作，再处理添加操作，以避免索引冲突
        // 逆序删除以避免影响未处理的索引
        for (int i = indicesToDelete.size() - 1; i >= 0; i--) {
            int index = indicesToDelete.get(i);
            lines.remove(index);
        }

        // 在找到的索引后添加新行（注意：删除操作后索引可能已经改变，但这里假设添加操作不依赖于已删除的行）
        // 如果需要在每个找到的索引后都添加新行，则使用indicesToAdd的原始列表进行循环
        for (int index : indicesToAdd) {
            // 由于我们之前可能删除了行，这里需要检查索引是否仍然有效
            // 如果index大于当前列表大小，则跳过（这意味着我们尝试在一个已删除的行之后添加新行）
            if (index < lines.size()) {
                lines.add(index + 1, newLine); // 在找到的索引后添加新行
            }
        }

        writeListToFile(lines, filePath);
    }

    public static void main(String[] args) {
        String filePath = "path/to/your/file.txt";
        String newLineToAdd = "This is the new line to add.";
        try {
            List<String> lines = readFileToList(filePath);
            boolean inserted = false; // 用于标记是否已经插入新行

            // 遍历文件内容列表
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                // 检查行是否以 "--TSIv2" 或 "--story" 开头
                if (line.startsWith("--TSIv2") || line.startsWith("--story")) {
                    // 在这一行后面插入新行
                    lines.add(i + 1, newLineToAdd);
                    inserted = true; // 标记为新行已插入
                    // 如果只需要插入一次，则退出循环（如果需要多次插入，则移除这个break）
                    // break;
                }
            }

            // 如果没有找到符合条件的行，可以选择在文件末尾添加新行（可选）
            if (!inserted) {
                lines.add(newLineToAdd);
            }

            writeListToFile(lines, filePath);
            System.out.println("File has been updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}