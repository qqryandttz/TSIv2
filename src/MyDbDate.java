import java.util.Arrays;
import java.util.HashSet;
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

}

class StoryDate {
    static String storyID;
    static String storyName;
    static String storyDescription;
    static int chapterNum;
    static String storyWriter;
    static Boolean isComplete;
}

class ChapterDate {
    static String chapterID;
    static String storyID;
    static String chapterName;
    static String chapterDescription;
    static Boolean isBranch;
}

class AchievementDate {

    static String achievementID;
    static String storyID;
    static String achieveName;
    static String achievementDetails;

}