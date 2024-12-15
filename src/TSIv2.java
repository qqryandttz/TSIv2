import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JPanel;

/**
 * 主程序执行
 */
public class TSIv2 {
    public static void main(String[] args) {

        InterfaceExecution interfaceExecution = new InterfaceExecution();
        // MyStyle.stopMusic();

    }
}

/**
 * 主执行逻辑，创建各个页面
 */
class InterfaceExecution {

    CardLayout cardLayout;
    MyJFrame myJFrame;
    JPanel glassPane;
    LaunchPage launchPage;
    StoryPage storyPage;
    ChapterPage chapterPage;
    PlotPage plotPage;
    AchievementPage achievementPage;
    Container myJFrameContentPane;

    OpenFile openFile = new OpenFile();

    Boolean isDBexist;
    Boolean isDBchanged;
    int isToggle;

    public static enum ToggleState {
        Launch,
        Story,
        Chapter,
        Plot,
        Achievement
    }
    // ToggleState currentState = ToggleState.LA;

    InterfaceExecution() {

        this.initLoading();

        this.loadAllPages();
    }

    void initLoading() {

        myJFrame = new MyJFrame("TSIv2", 1600, 900);
        myJFrame.setJMenuBar(myJFrame.setMenu(3));
        myJFrame.addJMenuListener();
        myJFrame.setIE(this);

        glassPane = (JPanel) myJFrame.getGlassPane();
        myJFrame.add(glassPane);

        cardLayout = new CardLayout();
        myJFrameContentPane = myJFrame.getContentPane();
        myJFrameContentPane.setLayout(cardLayout);

        launchPage = new LaunchPage(this);
        myJFrameContentPane.add("launch", launchPage);
        cardLayout.show(myJFrameContentPane, "launch");

        MyStyle.playBgMusic();
    }

    /**
     * 用户退出登录界面时，恢复初始状态
     */
    void revertLogin() {
        launchPage.revertLogin();

    }

    void DBconnect() {

        // 去获取连接，然后查询是否存在DB，给isDBexist赋值

        if (!isDBexist) {
            this.initDB();

        } else if (isDBexist) {

            // 先询问用户是否进行检测

            // Boolean isDBchanged = this.数据库内容检测();

            if (!isDBchanged) {
            } else {
                this.数据库改变();
            }
        }

    }

    void initDB() {

        // 查看是否有用户

    }

    Boolean checkDB() {

        return true;
    }

    void 数据库改变() {

        // 询问需要哪一种改变

        this.数据库清空();

        this.数据库追加();

    }

    void 数据库清空() {

    }

    void 数据库追加() {

    }

    void loadAllPages() {

        // 进行用户查询：userQuery
        storyPage = new StoryPage();
        chapterPage = new ChapterPage();
        plotPage = new PlotPage();

    }

}

class StoryPage extends JPanel {

    StarrySkyPanel starrySkyPanel;

}

class ChapterPage extends JPanel {

    StarrySkyPanel starrySkyPanel;

}

class PlotPage extends JPanel {

}

class AchievementPage extends JPanel {

}