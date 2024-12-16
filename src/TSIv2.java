import java.awt.CardLayout;
import java.awt.Container;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;

/**
 * 主程序执行
 */
public class TSIv2 {
    public static void main(String[] args) {

        InterfaceExecution interfaceExecution = new InterfaceExecution();
        interfaceExecution.start();
        MyStyle.stopMusic();

    }
}

/**
 * 主执行逻辑，创建各个页面
 */
class InterfaceExecution {

    CardLayout cardLayout;
    MyJFrame myJFrame;
    LaunchPage launchPage;
    StoryPage storyPage;
    ChapterPage chapterPage;
    PlotPage plotPage;
    AchievementPage achievementPage;
    Container myJFrameContentPane;

    OpenFile openFile = new OpenFile();
    MusicSetting musicSetting;

    void musicSet() {
        musicSetting = new MusicSetting();
    }

    Boolean isDBexist;
    Boolean isDBchanged;
    private String isPage;

    /**
     * 可以得到
     * "Launch", "Story", "Chapter", "Plot", "Achievement"
     */
    public String getIsPage() {
        return isPage;
    }

    /**
     * 请输入
     * "Launch", "Story", "Chapter", "Plot", "Achievement"
     */
    public void setIsPage(String isPage) {
        Set<String> validStates = new HashSet<>(Arrays.asList("Launch", "Story", "Chapter", "Plot", "Achievement"));
        if (validStates.contains(isPage)) {
            this.isPage = isPage;
        } else {
            throw new IllegalArgumentException("Invalid state: " + isPage);
        }
    }

    void start() {

        this.initLoading();
        this.loadAllPages();
    }

    void initLoading() {

        myJFrame = new MyJFrame("TSIv2", 1600, 900);
        myJFrame.setJMenuBar(myJFrame.setMenu(3));
        myJFrame.addJMenuListener();
        myJFrame.setIE(this);

        cardLayout = new CardLayout();
        myJFrameContentPane = myJFrame.getContentPane();
        myJFrameContentPane.setLayout(cardLayout);

        launchPage = new LaunchPage(this);
        myJFrameContentPane.add("launch", launchPage);
        cardLayout.show(myJFrameContentPane, "launch");
        setIsPage("Launch");

        MyStyle.playBgMusic();
    }

    /**
     * 用户退出登录界面时，恢复初始状态
     */
    void revertLogin() {
        launchPage.revertLogin();

    }

    // void 数据库改变() {
    // // 询问需要哪一种改变
    // this.数据库清空();
    // this.数据库追加();
    // }

    void loadAllPages() {

        // storyPage = new StoryPage();
        chapterPage = new ChapterPage();
        plotPage = new PlotPage();

    }

}

class ChapterPage extends JPanel {

    StarrySkyPanel starrySkyPanel;

}

class PlotPage extends JPanel {

}

class AchievementPage extends JPanel {

}