import java.awt.CardLayout;
import java.awt.Container;

/**
 * 主程序执行
 */
public class TSIv2 {
    public static void main(String[] args) {

        // MyStyle.playBgMusic();
        MyStyle.stopMusic();
        InterfaceExecution interfaceExecution = new InterfaceExecution();
        interfaceExecution.initLoading();

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

    void initLoading() {

        myJFrame = new MyJFrame("TSIv2", 1600, 900);
        myJFrame.setJMenuBar(myJFrame.setMenu(3));
        myJFrame.addJMenuListener();
        myJFrame.setIE(this);

        cardLayout = new CardLayout();
        myJFrameContentPane = myJFrame.getContentPane();
        myJFrameContentPane.setLayout(cardLayout);

        launchPage = new LaunchPage(this);
        myJFrameContentPane.add("Launch", launchPage);
        cardLayout.show(myJFrameContentPane, "Launch");
        MyDbDate.setIsPage("Launch");

    }

    /**
     * 用户退出登录界面时，恢复初始状态
     */
    void revertLogin() {
        launchPage.revertLogin();

    }

    void addStoryPage() {

        storyPage = new StoryPage(this);
        myJFrameContentPane.add("Story", storyPage);

        plotPage = new PlotPage(this);
        myJFrameContentPane.add("Plot", plotPage);

        goToStoryPage();
        revertLogin();

        // chapterPage = new ChapterPage(this);
        // myJFrameContentPane.add("Chapter", chapterPage);
        // achievementPage = new AchievementPage(this);
        // myJFrameContentPane.add("AchievementPage", achievementPage);

    }

    void goToLaunchPage() {

        MyStyle.playBgMusic();
        cardLayout.show(myJFrameContentPane, "Launch");
        MyDbDate.setIsPage("Launch");

    }

    void goToStoryPage() {

        MyStyle.playStoryBgMusic();
        cardLayout.show(myJFrameContentPane, "Story");
        MyDbDate.setIsPage("Story");

    }

    void goToChapterPage() {

        cardLayout.show(myJFrameContentPane, "Chapter");
        MyDbDate.setIsPage("Chapter");

    }

    void goToPlotPage() {

        cardLayout.show(myJFrameContentPane, "Plot");
        MyDbDate.setIsPage("Plot");

    }

    void goToAchievementPage() {

        cardLayout.show(myJFrameContentPane, "Achievement");
        MyDbDate.setIsPage("Achievement");

    }

}
