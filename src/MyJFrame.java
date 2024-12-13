import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Color;

/**
 * 创建窗口
 */
class MyJFrame extends JFrame {

    JMenuBar myJMenuBar;
    JMenu myJMenu[];
    JMenuItem myJMenuItem[][];
    MyStyle myStyle = new MyStyle();

    MyJFrame(String frameName, int width, int height) {

        setTitle(frameName);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        this.getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        this.setResizable(false);

        setVisible(true);
    }

    JMenuBar setMenu(int menuNumber) {

        myJMenuBar = new JMenuBar();
        myJMenu = new JMenu[menuNumber];

        // 菜单选项
        // 转到：转到主页面、转到故事页面、转到章节页面、转到剧情页面、转到成就页面
        // 设置：音量开启与关闭、文本呈现速度、用户数据（都是直接打开设置页面）
        // 帮助：用户手册、关于
        myJMenu[0] = new JMenu("设置");
        myJMenu[1] = new JMenu("转到");
        myJMenu[2] = new JMenu("帮助");

        JMenuItem[][] myJMenuItem = new JMenuItem[3][];

        myJMenuItem[0] = new JMenuItem[] {
                new JMenuItem("音量"),
                new JMenuItem("文本速度"),
                new JMenuItem("切换用户")
        };

        myJMenuItem[1] = new JMenuItem[] {
                new JMenuItem("转到主页面"),
                new JMenuItem("转到故事页面"),
                new JMenuItem("转到章节页面"),
                new JMenuItem("转到剧情页面"),
                new JMenuItem("转到成就页面")
        };

        myJMenuItem[2] = new JMenuItem[] {
                new JMenuItem("用户手册"),
                new JMenuItem("关于")
        };

        for (int i = 0; i < myJMenu.length; i++) {
            myJMenuBar.add(myJMenu[i]);
            myJMenu[i].setForeground(myStyle.getMenuColor());
            myJMenu[i].setFont(myStyle.getMenuFont());

            if (myJMenuItem[i] != null) {
                for (int j = 0; j < myJMenuItem[i].length; j++) {
                    if (myJMenuItem[i][j] != null) {
                        myJMenu[i].add(myJMenuItem[i][j]);
                        myJMenuItem[i][j].setFont(myStyle.getMenuFont());
                    }
                }
            }
        }

        return myJMenuBar;
    }

    void addJMenuListener() {

    }

}
