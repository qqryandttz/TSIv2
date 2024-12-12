import java.io.FileInputStream;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import java.awt.Color;
import java.awt.Font;

/**
 * 在这里可以设置一些样式的参数，比如背景音乐、图片、菜单颜色、文字的字体等等
 */
class MyStyle {

    static Color titleColor = new Color(0x66, 0xfa, 0xf1);
    static Color subTitleColor = new Color(0xc5, 0xc6, 0xc7);
    static Font titleFont = new Font("黑体", Font.ITALIC, 120);
    static Font subTitleFont = new Font("黑体", Font.CENTER_BASELINE, 24);

    static Color bottonColor = new Color(0x01, 0x42, 0x54);
    static Color bottonFontColor = new Color(0x1a, 0xbc, 0xbd);
    static Font bottonFont = new Font("黑体", Font.BOLD, 30);

    static Color bottonFontActiveColor = new Color(0x66, 0xfa, 0xf1);
    static Font bottonActiveFont = new Font("黑体", Font.PLAIN, 27);

    public Color getBottonFontActiveColor() {
        return bottonFontActiveColor;
    }

    public Font getBottonActiveFont() {
        return bottonActiveFont;
    }

    public Color getTitleColor() {
        return titleColor;
    }

    public Color getSubTitleColor() {
        return subTitleColor;
    }

    public Font getTitleFont() {
        return titleFont;
    }

    public Font getSubTitleFont() {
        return subTitleFont;
    }

    public Color getBottonColor() {
        return bottonColor;
    }

    public Color getBottonFontColor() {
        return bottonFontColor;
    }

    public Font getBottonFont() {
        return bottonFont;
    }

    static Color menuColor = new Color(0x00, 0x14, 0x2f);
    static Font menuFont = new Font("黑体", Font.BOLD, 22);

    public Color getMenuColor() {
        return menuColor;
    }

    public Font getMenuFont() {
        return menuFont;
    }

    // 这里可以播放启动背景音乐和故事背景音乐，
    // 还要有按钮等一些音效

    MyMusic myMusic = new MyMusic();;
    String bgMusic = ".\\res\\TSIv2\\TSIv2.mp3";
    String touchButtonSound = ".\\res\\TSIv2\\touchButtonSound.MP3";
    String pressButtonSound = ".\\res\\TSIv2\\pressButtonSound.MP3";
    String storyBgMusic = ".\\res\\" + "story" + ".mp3";

    void playBgMusic() {
        myMusic.playMusicLoop(bgMusic);
    }

    void playTouchButtonSound() {
        myMusic.playMusicOnce(touchButtonSound);
    }

    void playPressButtonSound() {
        myMusic.playMusicOnce(pressButtonSound);
    }

    void playStoryBgMusic(int storyNumber) {
        myMusic.playMusicLoop(".\\res\\story" + storyNumber + ".mp3");
    }

    void stopMusic() {
        myMusic.stopMusic();
    }

}
