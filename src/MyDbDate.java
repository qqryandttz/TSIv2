/*
 * 存放数据库数据
 */
public class MyDbDate {

    // 用户数据

    static String userName;
    static String pwd;
    static String role;
    static int isMusic;
    static int textSpeed;
    static int isAutoLoad;
    static int achieveNum;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        MyDbDate.userName = userName;
    }

    public static String getPwd() {
        return pwd;
    }

    public static void setPwd(String pwd) {
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

    public static int getIsAutoLoad() {
        return isAutoLoad;
    }

    public static void setIsAutoLoad(int isAutoLoad) {
        MyDbDate.isAutoLoad = isAutoLoad;
    }

    public static int getAchieveNum() {
        return achieveNum;
    }

    public static void setAchieveNum(int achieveNum) {
        MyDbDate.achieveNum = achieveNum;
    }

    // 文章数据

}
