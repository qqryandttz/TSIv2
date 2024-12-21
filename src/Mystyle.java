import java.awt.Color;
import java.awt.Font;

/**
 * 存放着一些基础的样式，基础音乐、颜色、字体等等
 */
class MyStyle {

    static Color StarrySkyBackgroundColor = new Color(0x0b, 0x0c, 0x10);

    public static Color getStarrySkyBackgroundColor() {
        return StarrySkyBackgroundColor;
    }

    static Color titleColor = new Color(0x66, 0xfa, 0xf1);
    static Color subTitleColor = new Color(0xc5, 0xc6, 0xc7);
    static Font titleFont = new Font("Consolas", Font.ITALIC, 120);
    static Font subTitleFont = new Font("Consolas", Font.CENTER_BASELINE, 24);

    static Color bottonColor = new Color(0x01, 0x42, 0x54);
    static Color bottonFontColor = new Color(0x1a, 0xbc, 0xbd);
    static Font bottonFont = new Font("微软雅黑", Font.PLAIN, 30);

    static Color bottonFontActiveColor = new Color(0x66, 0xfa, 0xf1);
    static Font bottonActiveFont = new Font("微软雅黑", Font.BOLD, 27);

    static Color TriangleButtonBackgroundColor = new Color(20, 25, 50);

    static public Color getBottonFontActiveColor() {
        return bottonFontActiveColor;
    }

    static public Color getTriangleButtonBackgroundColor() {
        return TriangleButtonBackgroundColor;
    }

    static public Font getBottonActiveFont() {
        return bottonActiveFont;
    }

    static public Color getTitleColor() {
        return titleColor;
    }

    static public Color getSubTitleColor() {
        return subTitleColor;
    }

    static public Font getTitleFont() {
        return titleFont;
    }

    static public Font getSubTitleFont() {
        return subTitleFont;
    }

    static public Color getBottonColor() {
        return bottonColor;
    }

    static public Color getBottonFontColor() {
        return bottonFontColor;
    }

    static public Font getBottonFont() {
        return bottonFont;
    }

    static Color menuColor = new Color(0x00, 0x14, 0x2f);
    static Font menuFont = new Font("黑体", Font.BOLD, 22);

    static public Color getMenuColor() {
        return menuColor;
    }

    static public Font getMenuFont() {
        return menuFont;
    }

    // 这里可以播放启动背景音乐和故事背景音乐，
    // 还要有按钮等一些音效

    static public MyMusic myMusic = new MyMusic();;
    static public String bgMusic = ".\\res\\TSIv2\\TSIv2.mp3";
    static public String touchButtonSound = ".\\res\\TSIv2\\touchButtonSound.MP3";
    static public String pressButtonSound = ".\\res\\TSIv2\\pressButtonSound.MP3";
    static public String storyBgMusic = ".\\res\\" + "story" + ".mp3";

    static void playChapterOnceMp3(String path) {

        myMusic.playMusicOnce(path);
    }

    static void playChapterLoopMp3(String path) {

        myMusic.playMusicLoop(path);
    }

    static public void playBgMusic() {
        myMusic.playMusicLoop(bgMusic);
    }

    static public void playTouchButtonSound() {
        myMusic.playMusicOnce(touchButtonSound);
    }

    static public void playPressButtonSound() {
        myMusic.playMusicOnce(pressButtonSound);
    }

    static public void playStoryBgMusic() {

        int storyNumber = StoryPage.getCurrentStory();
        myMusic.playMusicLoop(".\\res\\story" + storyNumber + "\\story.mp3");
    }

    static public void stopMusic() {
        myMusic.stopMusic();
    }

    static public void startMusic() {
        myMusic.startMusic();
    }

    // 这里放一些文件路径，方便引用

    static public String readmeFilePath = ".\\README.md";
    static public String storySettingFilePath = ".\\res\\story";
    static public String TSIv2SettingFilePath = ".\\res\\TSIv2\\setting.txt";
    static public String resFilePath = ".\\res";

    public static String getReadmeFilePath() {
        return readmeFilePath;
    }

    public static String getStorySettingFilePath(int storyNumber) {
        return storySettingFilePath + storyNumber + "\\setting.txt";
    }

    public static String getStoryImageFilePath(int storyNumber) {
        System.out.println(storySettingFilePath + storyNumber + "\\story.png");
        return storySettingFilePath + storyNumber + "\\story.png";
    }

    public static String getTSIv2SettingFilePath() {
        return TSIv2SettingFilePath;
    }

    public static String getResFilePath() {
        return resFilePath;
    }

    public static String getTSIv2ImageFilepath() {
        return resFilePath + "\\TSIv2\\story.png";
    }

    public static String getBackgroundImageFilepath() {
        return resFilePath + "\\TSIv2\\image.png";
    }

    public static String getBackgroundImageFilepath(String x) {
        return resFilePath + "\\TSIv2\\image" + x + ".png";
    }

    public static String getNewChapterFilepath() {
        String path = storySettingFilePath + StoryPage.getCurrentStory() + "\\chapter\\c0"
                + MyDbDate.stories.get(StoryPage.getCurrentStory() - 1).currentChapter + ".txt";
        System.out.println(path);
        return path;
    }

    public static String getChapterImgFilepath() {

        String path = storySettingFilePath + StoryPage.getCurrentStory() + "\\img\\";
        System.out.println(path);
        return path;
    }

    public static String getChapterOnceMp3Filepath() {

        String path = storySettingFilePath + StoryPage.getCurrentStory() + "\\music\\LoopMusic\\";
        System.out.println(path);
        return path;

    }

    public static String getChapterLoopMp3Filepath() {

        String path = storySettingFilePath + StoryPage.getCurrentStory() + "\\music\\OnceMusic\\";
        System.out.println(path);
        return path;

    }

}
