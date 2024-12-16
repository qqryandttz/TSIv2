import java.io.File;

/**
 * 读取文件夹，记录当前故事文件夹的个数
 */
public class StoryFolderCounter {

    public static int countStoryFolders() {
        File resFolder = new File(MyStyle.getResFilePath());
        if (!resFolder.exists() || !resFolder.isDirectory()) {
            return -1;
        }

        File[] folders = resFolder.listFiles((dir, name) -> new File(dir, name).isDirectory());
        if (folders == null || folders.length == 0) {
            return -1;
        }

        int storyCount = 0;
        int currentStoryNumber = 1;

        for (File folder : folders) {
            if (folder.getName().startsWith("story") && folder.getName().matches("story\\d+")) {
                try {
                    int folderNumber = Integer.parseInt(folder.getName().substring(5));
                    if (folderNumber == currentStoryNumber) {
                        storyCount++;
                        currentStoryNumber++;
                    } else if (folderNumber > currentStoryNumber) {
                        break;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }

        return storyCount;
    }
}