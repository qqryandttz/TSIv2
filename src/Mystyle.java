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

    MyMusic backgroundMyMusic;

    void playBackgroundMyMusic(String path) {

        backgroundMyMusic = new MyMusic(path);

    }

}

class MyMusic {

    static Boolean musicState = true;
    String path;
    MusicOnceThread musicOnceThread;
    MusicLoopThread musicLoopThread;

    MyMusic(String path) {

        this.path = path;
    }

    public static Boolean getMusicStatic() {
        return musicState;
    }

    public static void setMusicStatic(Boolean musicState) {
        MyMusic.musicState = musicState;
    }

    void playMusicOnc() {
        musicOnceThread = new MusicOnceThread(path, this);
    }

    void playMusicLoop() {

    }

    void stopMusic() {
        musicState = false;
    }

}

class MusicOnceThread extends Thread {

    String path;
    AdvancedPlayer musicPlayer;
    Boolean musicState;

    MusicOnceThread(String path, MyMusic music) {
        try {
            musicPlayer = new AdvancedPlayer(new FileInputStream(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if (musicState)
            // break;
            synchronized (MusicOnceThread.class) {

                try {
                    musicPlayer.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    }
}

class MusicLoopThread {

    String path;
    AdvancedPlayer musicPlayer;

    MusicLoopThread(String path, MyMusic myMusic) {
        this.path = path;

    }

}
