import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 存放数据库的数据
 */
public class MyDbDate {

    // 用户数据，登录成功后一次性存入

    static String UID;
    static String userName;
    static String pwd;
    static String role;
    static String registrationTime;
    static String signUpTime;
    static int isMusic;
    static int textSpeed;
    static int isAutoLoad;
    static int achieveNum;

    public static void inputUserDate(String userName, String pwd, String role, int isMusic, int textSpeed,
            int isAutoLoad, int achieveNum) {
        MyDbDate.userName = userName;
        MyDbDate.pwd = pwd;
        MyDbDate.role = role;
        MyDbDate.isMusic = isMusic;
        MyDbDate.textSpeed = textSpeed;
        MyDbDate.isAutoLoad = isAutoLoad;
        MyDbDate.achieveNum = achieveNum;
        System.out.println("已存入用户数据：");
        System.out.println("用户名: " + MyDbDate.userName);
        System.out.println("密码: " + MyDbDate.pwd);
        System.out.println("角色: " + MyDbDate.role);
        System.out.println("是否播放音乐: " + (MyDbDate.isMusic == 1 ? "是" : "否"));
        System.out.println("文本速度: " + MyDbDate.textSpeed);
        System.out.println("是否自动加载: " + (MyDbDate.isAutoLoad == 1 ? "是" : "否"));
        System.out.println("成就数量: " + MyDbDate.achieveNum);
        System.out.println("===========================");

    }

    public static String getUserName() {
        return userName;
    }

    public static String getPwd() {
        return pwd;
    }

    public static String getRole() {
        return role;
    }

    public static int getIsMusic() {
        return isMusic;
    }

    public static int getTextSpeed() {
        return textSpeed;
    }

    public static int getIsAutoLoad() {
        return isAutoLoad;
    }

    public static int getAchieveNum() {
        return achieveNum;
    }

    public static String iSPage;

    /**
     * 可以得到
     * "Launch", "Story", "Chapter", "Plot", "Achievement"
     */
    public static String getIsPage() {
        return iSPage;
    }

    /**
     * 请输入
     * "Launch", "Story", "Chapter", "Plot", "Achievement"
     */
    public static void setIsPage(String isPage) {
        Set<String> validStates = new HashSet<>(Arrays.asList("Launch", "Story", "Chapter", "Plot", "Achievement"));
        if (validStates.contains(isPage)) {
            MyDbDate.iSPage = isPage;
        } else {

            System.out.println("亲，当前页面不可以这么设计哦。");
            throw new IllegalArgumentException("Invalid state: " + isPage);
        }
    }

    public static int efectFolder;

    public static int getEfectFolder() {
        return efectFolder;
    }

    public static void setEfectFolder(int efectFolder) {
        MyDbDate.efectFolder = efectFolder;
    }

    // 文章数据，把后面需要获得的数据都放在这里了
    // 成就，音量开关（只有自动登录才可以直接弄，后面登录的默认开启）
    // 先读取文件夹，看存在的故事数量，从第一个开始，查找故事ID和故事名称是否存在
    // 存在就从 setting 里面读取配置到MyDBDate:故事ID、故事名称、故事简介、章节数量、创作者
    // 里面的每一个故事都会读取

    static List<StoryDate> stories = new ArrayList<>();
    static List<ChapterDate> chapters = new ArrayList<>();

    // MyDbDate.stories.get(0).storyName

    public static class StoryDate {
        String storyID;
        String storyName;
        String storyDescription;
        int chapterNum;
        int chapterTotalNum;
        String storyWriter;
        Boolean isComplete;
    }

    public static class ChapterDate {
        String chapterOrdinalNumber;
        String storyID;
        String chapterName;
        String chapterDescription;
        Boolean isBranch;
    }

    // 添加故事数据
    public void addStory(StoryDate story) {
        stories.add(story);
    }

    // 添加章节数据
    public void addChapter(ChapterDate chapter) {
        chapters.add(chapter);
    }

    public static void addStoryDate(int storyNum) {
        MyDbDate dbDate = new MyDbDate();
        String path = MyStyle.getStorySettingFilePath(storyNum);

        // 读取故事数据
        String storyID = MyFileModifier.readFieldValue(path, "story", "id");
        String storyName = MyFileModifier.readFieldValue(path, "story", "name");
        String storyWriter = MyFileModifier.readFieldValue(path, "story", "writer");
        String storyDescription = MyFileModifier.readFieldValue(path, "story", "description");
        String chapterTotalNumStr = MyFileModifier.readFieldValue(path, "story", "总章节数");
        int chapterTotalNum = Integer.parseInt(chapterTotalNumStr);
        String chapterNumStr = MyFileModifier.readFieldValue(path, "story", "章节数");
        int chapterNum = Integer.parseInt(chapterNumStr);
        boolean isComplete = true; // 默认为true

        StoryDate storyDate = new StoryDate();
        storyDate.storyID = storyID;
        storyDate.storyName = storyName;
        storyDate.storyDescription = storyDescription;
        storyDate.chapterTotalNum = chapterTotalNum;
        storyDate.chapterNum = chapterNum;
        storyDate.storyWriter = storyWriter;
        storyDate.isComplete = isComplete;

        dbDate.addStory(storyDate);

        // 读取章节数据
        for (int i = 1; i <= chapterTotalNum; i++) {
            String chapterPrefix = "c" + (i <= 9 ? "0" + i : i); // 章节前缀
            String chapterName = MyFileModifier.readFieldValue(path, chapterPrefix, "name");
            String chapterDescription = MyFileModifier.readFieldValue(path, chapterPrefix, "description");
            String chapterOrdinalNumber = MyFileModifier.readFieldValue(path, chapterPrefix, "序号");
            boolean isBranch = chapterOrdinalNumber.contains(".");

            ChapterDate chapterDate = new ChapterDate();
            chapterDate.storyID = storyID;
            chapterDate.chapterName = chapterName;
            chapterDate.chapterDescription = chapterDescription;
            chapterDate.isBranch = isBranch;

            dbDate.addChapter(chapterDate);
        }

    }

    public static void printAllStoriesAndChapters() {
        // 输出所有的故事数据
        for (StoryDate story : stories) {
            System.out.println("Story ID: " + story.storyID);
            System.out.println("Story Name: " + story.storyName);
            System.out.println("Story Description: " + story.storyDescription);
            System.out.println("Total Chapters: " + story.chapterTotalNum);
            System.out.println("Current Chapter: " + story.chapterNum);
            System.out.println("Story Writer: " + story.storyWriter);
            System.out.println("Is Complete: " + story.isComplete);
            System.out.println("---------------------------");

            // 遍历chapters列表,找到与这个故事相关的所有章节
            for (ChapterDate chapter : chapters) {
                if (chapter.storyID.equals(story.storyID)) {
                    System.out.println("\tChapter Ordinal Number: " + chapter.chapterOrdinalNumber);
                    System.out.println("\tChapter Name: " + chapter.chapterName);
                    System.out.println("\tChapter Description: " + chapter.chapterDescription);
                    System.out.println("\tIs Branch: " + chapter.isBranch);
                    System.out.println("\t----------");
                }
            }
            System.out.println("===========================");
        }
        System.out.println("输入完成！");
    }

    public static void printSpecificStoryInfo() {
        if (stories.size() >= 1) {
            // 获取第一个故事的ID
            String firstStoryID = stories.get(0).storyID;
            System.out.println("第一个故事的ID: " + firstStoryID);
        } else {
            System.out.println("没有故事数据可供获取！");
        }

        if (stories.size() >= 2) {
            // 获取第二个故事的名字
            String secondStoryName = stories.get(1).storyName;
            System.out.println("第二个故事的名字: " + secondStoryName);
        } else {
            System.out.println("没有足够的故事数据以供获取第二个故事的名字！");
        }
    }

    static void clearMyDbDate() {

        stories.clear();
        chapters.clear();
    }

    public static void main(String[] args) {
        MyDbDate.addStoryDate(1);
        MyDbDate.addStoryDate(2);
        MyDbDate.printSpecificStoryInfo();
    }

}

class AchievementDate {

    String achievementID;
    String storyID;
    String achieveName;
    String achievementDetails;

}