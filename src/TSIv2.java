import java.awt.CardLayout;
import javax.swing.JPanel;

public class TSIv2 {
    public static void main(String[] args) {

        // DBConnection dbConnection = new DBConnection();
        // dbConnection.connectDB("teaching", "root", "61");

        // 这里进行读取数据库，看是以玩家还是以用户的身份登入
        // 数据库以什么身份登入也是一个要点
        InterfaceExecution interfaceExecution = new InterfaceExecution();
        interfaceExecution.init();

    }

}

class InterfaceExecution {

    CardLayout cardLayout;
    MyJFrame myJFrame;
    LaunchPage launchPage;
    StoryPage storyPage;
    ChapterPage chapterPage;
    PlotPage plotPage;
    AchievementPage achievementPage;
    MyStyle myStyle = new MyStyle();

    public enum ToggleState {
        Launch,
        Story,
        Chapter,
        Plot,
        Achievement
    }
    // ToggleState currentState = ToggleState.LA;

    InterfaceExecution() {

        cardLayout = new CardLayout();
        myJFrame = new MyJFrame("TSIv2");
        myJFrame.setJMenuBar(myJFrame.setMenu(3));
        myJFrame.addJMenuListener();

        launchPage = new LaunchPage();

        storyPage = new StoryPage();
        chapterPage = new ChapterPage();
        plotPage = new PlotPage();

    }

    void init() {

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