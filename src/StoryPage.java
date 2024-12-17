import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
* 故事页面
*/

public class StoryPage extends JPanel {

    static int currentStory = 1;

    public static int getCurrentStory() {
        return currentStory;
    }

    public static void setCurrentStory(int currentStory) {
        StoryPage.currentStory = currentStory;
    }

    InterfaceExecution IE;
    JLayeredPane layeredPane;
    StarrySkyPanel starrySkyPanel;

    JLabel title;
    RoundedButton volumeButton, achievementButton;

    ImagePanel contentPanel;
    JLabel authorLabel = new JLabel("作者名");
    JLabel storySummaryLabel = new JLabel("故事简介");

    JButton leftButton = new JButton("左");
    JButton rightButton = new JButton("右");

    JButton enterButton = new JButton("进入");
    JButton helpButton = new JButton("帮助");
    JButton settingsButton = new JButton("设置");

    // Title.setBounds(396, 55, 800, 126);
    // 音量.setBounds(74, 63, 60, 54);
    // 成就.setBounds(1461, 64, 60, 54);

    // jtextField.setBounds(344, 253, 913, 426);
    // 作者名.setBounds(1139, 277, 88, 37);
    // 故事简介.setBounds(344, 569, 913, 112);
    // 左按钮.setBounds(255, 428, 50, 57);
    // 右按钮.setBounds(1308, 429, 50, 57);

    // 进入.setBounds(704, 722, 193, 82);
    // 帮助按钮.setBounds(580, 738, 52, 51);
    // 设置按钮.setBounds(966, 738, 52, 51);

    StoryPage(InterfaceExecution interfaceExecution) {

        IE = interfaceExecution;
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1600, 900);
        MyStyle.playStoryBgMusic();

        addStarrySkyPanel();
        addTitle();
        addTitleButton();
        addContentPanel();
        // addStoryButton();
        // addUnderBotton();
        layeredPane.repaint();
        layeredPane.revalidate();

        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);

    }

    void addStarrySkyPanel() {

        starrySkyPanel = new StarrySkyPanel();
        starrySkyPanel.setBounds(0, 0, 1600, 900);
        layeredPane.add(starrySkyPanel, new Integer(JLayeredPane.DEFAULT_LAYER));
    }

    void addTitle() {

        title = new JLabel(MyDbDate.stories.get(0).storyName);
        layeredPane.add(title, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        title.setFont(new Font("微软雅黑", Font.PLAIN, 60));
        title.setForeground(MyStyle.getTitleColor());
        title.setBounds(150, 35, 1300, 126);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);

    }

    void addTitleButton() {

        volumeButton = new RoundedButton("音量", 40);
        layeredPane.add(volumeButton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));

        LineBorder lineBorder = new LineBorder(MyStyle.getTitleColor(), 2);
        volumeButton.setBorder(lineBorder);
        volumeButton.setBorderPainted(true);
        Font buttonFont = new Font("微软雅黑", Font.PLAIN, 16);
        volumeButton.setFont(buttonFont);
        volumeButton.setBackground(MyStyle.getStarrySkyBackgroundColor());
        volumeButton.setForeground(MyStyle.getBottonFontColor());
        volumeButton.setBounds(74, 63, 50, 50);

        volumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();
                IE.musicSet();

            }
        });

        volumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();

            }
        });

        volumeButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                volumeButton.setForeground(MyStyle.getBottonFontActiveColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                volumeButton.setFont(buttonFont);
                volumeButton.setForeground(MyStyle.getBottonFontColor());

            }
        });

        achievementButton = new RoundedButton("成就", 40);
        layeredPane.add(achievementButton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));

        achievementButton.setBorder(lineBorder);
        achievementButton.setBorderPainted(true);
        achievementButton.setFont(buttonFont);
        achievementButton.setBackground(MyStyle.getStarrySkyBackgroundColor());
        achievementButton.setForeground(MyStyle.getBottonFontColor());
        achievementButton.setBounds(1461, 64, 50, 50);

        achievementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();
                IE.goToAchievementPage();

            }
        });

        achievementButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                achievementButton.setForeground(MyStyle.getBottonFontActiveColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                achievementButton.setFont(buttonFont);
                achievementButton.setForeground(MyStyle.getBottonFontColor());

            }
        });

    }

    void addContentPanel() {

        contentPanel = new ImagePanel(800, 450,
                MyStyle.getStoryImageFilePath(currentStory));
        contentPanel.setBounds(400, 225, contentPanel.getWidth(),
                contentPanel.getHeight());
        layeredPane.add(contentPanel, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        System.out.println("进来了吗？");
        layeredPane.repaint();
        layeredPane.revalidate();
    }

    void addStoryButton() {

    }

    void addUnderBotton() {

    }
}
