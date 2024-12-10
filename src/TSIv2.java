import java.awt.CardLayout;
import java.text.ListFormat.Style;

import javax.swing.JPanel;

public class TSIv2 {
    public static void main(String[] args) {

        // DBConnection dbConnection = new DBConnection();
        // dbConnection.connectDB("teaching", "root", "61");

        // 这里进行读取数据库，看是以玩家还是以用户的身份登入
        // 数据库以什么身份登入也是一个要点
        InterfaceExecution interfaceExecution = new InterfaceExecution();

    }

}

/**
 * 总体执行逻辑，and 数据库连接
 */
class InterfaceExecution {

    CardLayout cardLayout;
    MyJFrame myJFrame;
    LaunchPage launchPage;
    StoryPage storyPage;
    ChapterPage chapterPage;
    PlotPage plotPage;
    AchievementPage achievementPage;
    MyStyle myStyle = new MyStyle();

    Boolean isDBexist;
    Boolean isDBchanged;

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
        this.DBconnect();

        this.loadAllPages();
    }

    void initLoading() {

        cardLayout = new CardLayout();
        myJFrame = new MyJFrame("TSIv2");
        myJFrame.setJMenuBar(myJFrame.setMenu(3));
        myJFrame.addJMenuListener();

        launchPage = new LaunchPage();
        myStyle.playBackgroundMyMusic("背景音乐路径");
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

class LaunchPage extends JPanel {

    StarrySkyPanel starrySkyPanel;

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