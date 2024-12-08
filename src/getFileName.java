import java.io.File;

public class getFileName {

    /**
     * 简单读取该路径下的文件名
     */
    void get1() {

        // 获取文件路径文件夹下的全部文件列表
        System.out.println("文件有如下：");
        // 表示一个文件路径
        File file = new File("E:\\Java\\serverDel\\");
        // 用数组把文件夹下的文件存起来
        File[] files = file.listFiles();
        // foreach遍历数组
        for (File file2 : files) {
            // 打印文件列表：只读取名称使用getName();
            System.out.println("路径：" + file2.getPath());
            System.out.println("文件夹/文件名：" + file2.getName());
        }

    }

    /**
     * 读取文件夹下的所有文件名：文件夹，文件名
     */
    void get2() {

        System.out.println("文件有如下：");
        File file = new File("E:\\Java\\serverDel\\");
        // 1)如果这个路径是一个文件夹
        if (file.isDirectory()) {
            // 2)获取文件夹下的
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    System.out.println("文件夹:" + files[i].getPath());
                } else {
                    System.out.println("文件:" + files[i].getPath());
                }
            }
        } else {
            // 这是一个文件
            System.out.println("文件：" + file.getPath());
        }

    }

    /**
     * 读取文件夹下的所有文件名：文件名，文件夹，如果文件夹里的文件夹还有文件，继续读
     */
    public static void main(String[] args) {
        // 表示一个文件路径
        String path = "E:\\Java\\serverDel\\";
        getFiles(path);
    }

    /**
     * 递归获取某路径下的所有文件，文件夹，并输出
     */
    public static void getFiles(String clientBase) {
        File file = new File(clientBase);
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                if (files[i].isDirectory()) {
                    System.out.println("目录：" + files[i].getPath());
                    // 继续读取文件夹里面的所有文件
                    getFiles(files[i].getPath());
                } else {
                    System.out.println("文件：" + files[i].getPath());
                }
            }
        } else {
            System.out.println("文件：" + file.getPath());
        }
    }

}
