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

    public MyMusic getBackgroundMyMusic(String musicState, String path) {
        backgroundMyMusic = new MyMusic(musicState, path);
        return backgroundMyMusic;
    }

    void startBackgroundMyMusic() {

    }

}

class MyMusic {

    static Boolean musicStatic = true;
    String musicState;
    String path;
    MusicOnceThread musicOnceThread;
    MusicLoopThread musicLoopThread;

    MyMusic(String musicState, String path) {
        this.musicState = musicState;
        this.path = path;
    }

    public static Boolean getMusicStatic() {
        return musicStatic;
    }

    public static void setMusicStatic(Boolean musicStatic) {
        MyMusic.musicStatic = musicStatic;
    }

    void playMusicOnc() {
        musicOnceThread = new MusicOnceThread(path, this);
    }

    void playMusicLoop() {

    }

}

class MusicOnceThread extends Thread {

    String path;
    AdvancedPlayer musicPlayer;
    int musicStatic = 100;

    MusicOnceThread(String path, MyMusic music) {
        try {
            musicPlayer = new AdvancedPlayer(new FileInputStream(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if (musicStatic == 0)
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
