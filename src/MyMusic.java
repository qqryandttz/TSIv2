import java.io.FileInputStream;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class MyMusic {

    static Boolean musicState = true;
    MusicThread musicOnceThread;
    MusicThread musicLoopThread;

    public static void main(String[] args) {

        try {
            MyStyle myStyle = new MyStyle();
            myStyle.playBgMusic();
            Thread.sleep(3000);
            myStyle.stopMusic();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    Boolean getMusicStatic() {
        return musicState;
    }

    void stopMusic() {
        musicState = false;
    }

    void startMusic() {
        musicState = true;
    }

    void playMusicOnce(String path) {

        if (musicState) {
            musicOnceThread = new MusicThread(path, this);
            musicOnceThread.start();
        }
    }

    void playMusicLoop(String path) {

        if (musicState) {
            musicLoopThread = new MusicThread(path, this);
            musicLoopThread.start();
        }
        if (musicState) {
            musicLoopThread.musicPlayer.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent event) {
                    playMusicLoop(path);
                }
            });
        }
    }

}

class MusicThread extends Thread {

    String path;
    MyMusicPlayer musicPlayer;
    Boolean musicState;

    MusicThread(String path, MyMusic myMusic) {

        musicState = myMusic.getMusicStatic();

        try {
            musicPlayer = new MyMusicPlayer(new FileInputStream(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        if (musicState)
            try {
                musicPlayer.myPlay();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}

class MyMusicPlayer extends AdvancedPlayer {

    MyMusic myMusic = new MyMusic();

    public MyMusicPlayer(InputStream arg0) throws JavaLayerException {
        super(arg0);
    }

    public void myPlay() {

        while (myMusic.getMusicStatic()) {

            if (myMusic.getMusicStatic()) {
                try {
                    decodeFrame();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    skipFrame();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}