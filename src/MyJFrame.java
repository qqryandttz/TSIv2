import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.JPanel;
import java.awt.event.*;

/**
 * 创建窗口
 */
class MyJFrame extends JFrame {

    InterfaceExecution IE;
    JMenuBar myJMenuBar;
    JMenu myJMenu[];
    JMenuItem myJMenuItem[][];

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

    void setIE(InterfaceExecution interfaceExecution) {
        IE = interfaceExecution;
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

        myJMenuItem = new JMenuItem[3][];

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
            myJMenu[i].setForeground(MyStyle.getMenuColor());
            myJMenu[i].setFont(MyStyle.getMenuFont());

            if (myJMenuItem[i] != null) {
                for (int j = 0; j < myJMenuItem[i].length; j++) {
                    if (myJMenuItem[i][j] != null) {
                        myJMenu[i].add(myJMenuItem[i][j]);
                        myJMenuItem[i][j].setFont(MyStyle.getMenuFont());
                    }
                }
            }
        }

        return myJMenuBar;
    }

    void addJMenuListener() {

        myJMenuItem[0][0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IE.musicSet();
                String isPage = MyDbDate.getIsPage();
                if (isPage == "Launch") {
                    MyStyle.playBgMusic();
                }
            }
        });

        myJMenuItem[2][0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IE.openFile.inputFilePath(MyStyle.getReadmeFilePath());
            }
        });

        myJMenuItem[2][0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IE.openFile.inputFilePath(MyStyle.getReadmeFilePath());
            }
        });

        myJMenuItem[2][1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "<html>" +
                        "<body>" +
                        "<p>《The Slumber Interval》版本 2.0（测试版本） - qqry<br>" +
                        "是一款基于Java开发的开源视觉小说游戏框架。</p>" +
                        "<p>本软件遵循MIT开源协议，在遵守协议条款的前提下，<br>" +
                        "用户可自由使用本软件及其衍生作品。</p>" +
                        "<p>若您希望深入了解软件的源代码或参与项目开发，<br>" +
                        "请复制以下GitHub链接并在浏览器中访问：</p>" +
                        "<p><font color='blue'><u>https://github.com/qqryandttz/TSIv2</u></font></p>" +
                        "</body>" +
                        "</html>";

                JOptionPane.showMessageDialog(null, message, "The Slumber Interval", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

}
