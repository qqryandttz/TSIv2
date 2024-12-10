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

    Color menuColor;
    Font menuFont;
    MyMusic backgroundMyMusic;

    public Color getMenuColor() {
        menuColor = new Color(0x00, 0x14, 0x2f);
        return menuColor;
    }

    public Font getMenuFont() {
        menuFont = new Font("黑体", Font.BOLD, 22);
        return menuFont;
    }

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
