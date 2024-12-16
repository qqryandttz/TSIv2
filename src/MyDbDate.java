/*
* 存放数据库数据
*/

// MyDbDate.setUserName(userName);

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
    static Boolean isAutoLoad;
    static int achieveNum;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        System.out.println("已存入用户名" + userName);
        MyDbDate.userName = userName;
    }

    public static String getPwd() {
        return pwd;
    }

    public static void setPwd(String pwd) {
        System.out.println("已存入密码" + pwd);
        MyDbDate.pwd = pwd;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        MyDbDate.role = role;
    }

    public static int getIsMusic() {
        return isMusic;
    }

    public static void setIsMusic(int isMusic) {
        MyDbDate.isMusic = isMusic;
    }

    public static int getTextSpeed() {
        return textSpeed;
    }

    public static void setTextSpeed(int textSpeed) {
        MyDbDate.textSpeed = textSpeed;
    }

    public static Boolean getIsAutoLoad() {
        return isAutoLoad;
    }

    public static void setIsAutoLoad(Boolean isAutoLoad) {
        MyDbDate.isAutoLoad = isAutoLoad;
        System.out.println("已存入自动登录" + isAutoLoad);
    }

    public static int getAchieveNum() {
        return achieveNum;
    }

    public static void setAchieveNum(int achieveNum) {
        MyDbDate.achieveNum = achieveNum;
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