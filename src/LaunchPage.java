import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 启动页面
 */
public class LaunchPage extends JPanel {

    InterfaceExecution IE;
    JLayeredPane layeredPane;
    StarrySkyPanel starrySkyPanel;
    JLabel title, subTitle;
    RoundedButton startBotton;

    MyProgressBar progressBar;
    MyLoginPage myLoginPage;
    PopUpLabel loadPopUpLabel;
    String latestLoggedInUsername;

    LaunchPage(InterfaceExecution interfaceExecution) {

        IE = interfaceExecution;
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1600, 900);

        AddStarrySkyPanel();
        AddTitle();
        AddPopUpLable();
        AddStartBotton();
        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);
    }

    void AddStarrySkyPanel() {

        starrySkyPanel = new StarrySkyPanel();
        starrySkyPanel.setBounds(0, 0, 1600, 900);
        starrySkyPanel.start();
        layeredPane.add(starrySkyPanel, new Integer(JLayeredPane.DEFAULT_LAYER));
    }

    void AddPopUpLable() {

        loadPopUpLabel = new PopUpLabel();
        layeredPane.add(loadPopUpLabel, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
    }

    void AddTitle() {

        title = new JLabel("The Slumber Interval");
        layeredPane.add(title, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        title.setFont(MyStyle.getTitleFont());
        title.setForeground(MyStyle.getTitleColor());
        title.setBounds(155, 75, 1500, 225);

        subTitle = new JLabel(
                "<html> I hope your dreams are filled with candies and laughter. When you wake up, maybe we can go explore the wider world together.</html>");
        layeredPane.add(subTitle, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        subTitle.setFont(MyStyle.getSubTitleFont());
        subTitle.setForeground(MyStyle.getSubTitleColor());
        subTitle.setBounds(355, 322, 787, 187);

    }

    void AddStartBotton() {

        startBotton = new RoundedButton("Load", 30);
        layeredPane.add(startBotton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        startBotton.setBorderPainted(false);

        LineBorder lineBorder = new LineBorder(MyStyle.getTitleColor(), 2);
        startBotton.setBorder(lineBorder);
        startBotton.setFont(MyStyle.getBottonFont());
        startBotton.setBackground(MyStyle.getBottonColor());
        startBotton.setForeground(MyStyle.getBottonFontColor());
        startBotton.setBounds(630, 610, 300, 75);

        startBotton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();

                if (NetworkUtils.isNetworkAvailable()) {
                    startBotton.setVisible(false);
                    AddProcessBar();
                    progressBar.smoothProgressTo(15);

                    if (!isAutoLoading()) {
                        myLoginPage = new MyLoginPage("登入页面", IE);
                    } else {

                        System.out.println("自动登录成功！");

                        IE.launchPage.loadPopUpLabel.showMessageWithAnimation(latestLoggedInUsername + "，欢迎进入游戏！");
                        progressBar.smoothProgressTo(15, 30);
                        // 开始执行其他数据库获取逻辑，当时不能直接在这里执行，我在LaunchPage 再写一个方法
                        getDbDate(latestLoggedInUsername);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "网络连接超时，请检查网络设置。", "警告",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        startBotton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                startBotton.setForeground(MyStyle.getBottonFontActiveColor());
                startBotton.setFont(MyStyle.getBottonActiveFont());
                startBotton.setBounds(650, 612, 270, 70);
                startBotton.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startBotton.setFont(MyStyle.getBottonFont());
                startBotton.setForeground(MyStyle.getBottonFontColor());
                startBotton.setBounds(630, 610, 300, 75);
                startBotton.setBorderPainted(false);
            }
        });

    }

    void AddProcessBar() {

        progressBar = new MyProgressBar(0, 100);
        progressBar.setStringPainted(true);

        layeredPane.add(progressBar, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        progressBar.setBounds(330, 662, 900, 30);

    }

    void revertLogin() {
        progressBar.setVisible(false);
        startBotton.setVisible(true);
    }

    /**
     * 实现自动登入逻辑，需要在配置文件中存储字段，最近的登录用户
     * 查找到用户名，再去看是否开启自动登录，如果开启，就获取mac地址，获取成功就自动登入
     */
    Boolean isAutoLoading() {

        if (MyDbDate.isAutoLoad == 1) {
            return false;
        }

        try {

            latestLoggedInUsername = MyFileModifier.readFieldValue(MyStyle.getTSIv2SettingFilePath(),
                    "TSIv2", "最近登录用户");
            List<String> macs = MacTools.getActiveMacList();
            MyDB myDB = new MyDB();
            return myDB.checkUserMacAddresses(latestLoggedInUsername, macs);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "无法自动登录！请手动登录！", "警告", JOptionPane.ERROR_MESSAGE);

        }
        return false;

    }

    void getDbDate(String userName) {
        MyDbDate.clearMyDbDate();
        MyDB.dbGetUserDate(userName);
        progressBar.smoothProgressTo(30, 80);
        int storyFolders = StoryFolderCounter.countStoryFolders();
        if (storyFolders == -1) {
            IE.revertLogin();
            JOptionPane.showMessageDialog(null, "没有找到任何文件夹！", "提示",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (storyFolders == 0) {
            IE.revertLogin();
            JOptionPane.showMessageDialog(null, "不存在故事文件夹。", "提示",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            int efectFolder = 0;
            for (int i = 0; i < storyFolders; i++) {
                if (getGameDate(i + 1)) {
                    efectFolder++;
                } else {
                    break;
                }
            }

            if (efectFolder == 0) {

                IE.revertLogin();
                JOptionPane.showMessageDialog(null,
                        String.format("找到的故事文件夹数量: %d\n故事文件夹全部无效！", storyFolders), "提示",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(null,
                        String.format("找到的故事文件夹数量: %d\n有效的故事文件夹数量: %d", storyFolders, efectFolder), "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                progressBar.smoothProgressTo(80, 100);

                MyDbDate.setEfectFolder(efectFolder); // 存放数据

                for (int i = 0; i < efectFolder; i++) {
                    // 对故事、章节进行存储
                    MyDbDate.addStoryDate(i + 1);
                    MyDbDate.printAllStoriesAndChapters();
                    // 开始创建页面
                    IE.addAllPages();
                }
            }
        }

    }

    /**
     * 对每一个故事文件夹的setting进行检测，查看里面的故事是否有效
     */
    Boolean getGameDate(int storyNum) {

        String settingpath = MyStyle.getStorySettingFilePath(storyNum);
        String[] listName = new String[3];
        listName[0] = "故事ID";
        listName[1] = "故事名称";
        listName[2] = "是否完整";

        String[] storyName = new String[3];

        System.out.println("launch开始查找" + settingpath + "的ID和名称");
        storyName[0] = MyFileModifier.readFieldValue(settingpath, "story", "id");
        storyName[1] = MyFileModifier.readFieldValue(settingpath, "story", "name");
        storyName[2] = "1";

        if (listName != null && storyName != null) {
            if (MyDB.hasOrNot("故事表", listName, storyName) == 1) {
                return true;
            }
        } else {
            System.out.println(listName + " " + storyName);
            JOptionPane.showMessageDialog(null, "配置文件出错！", "警告",
                    JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

}
