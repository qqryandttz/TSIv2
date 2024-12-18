import javax.imageio.ImageIO;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

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
    private BufferedImage backgroundImage;

    JLabel title;
    RoundedButton volumeButton, achievementButton;

    ImagePanel contentPanel;
    JLabel authorLabel;
    JLabel storyLabel, storySummaryLabel;

    TriangleButton leftButton;
    TriangleButton rightButton;

    RoundedButton enterButton;
    RoundedButton helpButton;
    RoundedButton settingsButton;

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
        AddLayeredPane();

        addTitle();
        addTitleButton();
        addContentPanel();
        addStoryButton();
        addUnderBotton();

        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);

    }

    void AddLayeredPane() {
        layeredPane = new JLayeredPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        layeredPane.setBounds(0, 0, 1600, 900);

        try {
            backgroundImage = ImageIO.read(new File(MyStyle.getBackgroundImageFilepath()));
            // repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addTitle() {

        title = new JLabel(MyDbDate.stories.get(currentStory - 1).storyName);
        layeredPane.add(title, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        title.setFont(new Font("宋体", Font.BOLD, 60));

        title.setForeground(new Color(190, 200, 230));
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

        volumeButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                volumeButton.setForeground(MyStyle.getBottonFontActiveColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
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
                JOptionPane.showMessageDialog(null, "正在努力开发中...", "开发者", JOptionPane.INFORMATION_MESSAGE);

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
                achievementButton.setForeground(MyStyle.getBottonFontColor());

            }
        });

    }

    void addContentPanel() {

        contentPanel = new ImagePanel(800, 450,
                MyStyle.getStoryImageFilePath(currentStory));
        contentPanel.setBounds(400, 200, 800, 450);
        layeredPane.add(contentPanel, new Integer(JLayeredPane.DEFAULT_LAYER + 10));

        storyLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 背景色为黑
                Color semiTransparentBlack = new Color(0, 0, 0, 190); // alpha，范围从0（透明）到255（不透明）
                g.setColor(semiTransparentBlack);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        layeredPane.add(storyLabel, new Integer(JLayeredPane.DEFAULT_LAYER + 15));
        storyLabel.setBounds(400, 550, 800, 100);

        storySummaryLabel = new JLabel(MyDbDate.stories.get(currentStory - 1).storyDescription);
        layeredPane.add(storySummaryLabel, new Integer(JLayeredPane.DEFAULT_LAYER + 20));
        storySummaryLabel.setFont(new Font("宋体", Font.BOLD, 23));
        storySummaryLabel.setForeground(MyStyle.getSubTitleColor());
        storySummaryLabel.setBounds(400, 550, 800, 100);
        storySummaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        storySummaryLabel.setVerticalAlignment(SwingConstants.CENTER);

        authorLabel = new JLabel("作者");
    }

    void addStoryButton() {

        leftButton = new TriangleButton(TriangleButton.Orientation.LEFT);
        leftButton.setBounds(280, 350, 90, 90);
        layeredPane.add(leftButton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        leftButton.setText("L  ");
        leftButton.setAlpha(0.6f);

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();
                turnLeft();

            }
        });

        leftButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                leftButton.setAlpha(1.0f);
            }

            @Override
            public void mouseExited(MouseEvent e) {

                leftButton.setAlpha(0.6f);

            }
        });

        rightButton = new TriangleButton(TriangleButton.Orientation.RIGHT);
        rightButton.setBounds(1230, 350, 90, 90);
        layeredPane.add(rightButton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        rightButton.setText("  R");
        rightButton.setAlpha(0.6f);

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();
                turnRight();

            }
        });

        rightButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                rightButton.setAlpha(1.0f);
            }

            @Override
            public void mouseExited(MouseEvent e) {

                rightButton.setAlpha(0.6f);

            }
        });

    }

    void addUnderBotton() {

        enterButton = new RoundedButton("START", 0);
        layeredPane.add(enterButton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));

        LineBorder lineBorder = new LineBorder(MyStyle.getTitleColor(), 2);
        enterButton.setBorder(lineBorder);
        enterButton.setBorderPainted(true);
        Font buttonFont = new Font("Consolas", Font.PLAIN, 40);
        enterButton.setFont(buttonFont);
        enterButton.setBackground(MyStyle.getTriangleButtonBackgroundColor());
        enterButton.setForeground(MyStyle.getBottonFontColor());
        enterButton.setBounds(700, 690, 193, 60);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();
                IE.goToPlotPage();
                IE.plotPage.startFileReader();
            }
        });

        enterButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                enterButton.setForeground(MyStyle.getBottonFontActiveColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                enterButton.setForeground(MyStyle.getBottonFontColor());

            }
        });

        helpButton = new RoundedButton("help", 0);
        layeredPane.add(helpButton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));

        helpButton.setBorder(lineBorder);
        helpButton.setBorderPainted(true);
        helpButton.setFont(new Font("Consolas", Font.ITALIC, 15));
        helpButton.setBackground(MyStyle.getStarrySkyBackgroundColor());
        helpButton.setForeground(MyStyle.getBottonFontColor());
        helpButton.setBounds(580, 690, 52, 51);

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();
                IE.openFile.inputFilePath(MyStyle.getReadmeFilePath());

            }
        });

        helpButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                helpButton.setForeground(MyStyle.getBottonFontActiveColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {

                helpButton.setForeground(MyStyle.getBottonFontColor());

            }
        });

        settingsButton = new RoundedButton("Opts", 0);
        layeredPane.add(settingsButton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));

        settingsButton.setBorder(lineBorder);
        settingsButton.setBorderPainted(true);
        settingsButton.setFont(new Font("Consolas", Font.ITALIC, 15));
        settingsButton.setBackground(MyStyle.getStarrySkyBackgroundColor());
        settingsButton.setForeground(MyStyle.getBottonFontColor());
        settingsButton.setBounds(960, 690, 52, 51);

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();
                JOptionPane.showMessageDialog(null, "正在努力开发中...", "开发者", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        settingsButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                settingsButton.setForeground(MyStyle.getBottonFontActiveColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {

                settingsButton.setForeground(MyStyle.getBottonFontColor());

            }
        });

    }

    /**
     * 回到上一个故事
     */
    void turnLeft() {

        currentStory--;
        if (currentStory == 0) {
            currentStory = MyDbDate.efectFolder;
        }
        // System.out.println("向左，当前故事为：" + currentStory);
        title.setText(MyDbDate.stories.get(currentStory - 1).storyName);
        storySummaryLabel.setText(MyDbDate.stories.get(currentStory - 1).storyDescription);
        contentPanel.loadImage(MyStyle.getStoryImageFilePath(currentStory));

    }

    /**
     * 回到下一个故事
     */
    void turnRight() {

        currentStory++;
        if (currentStory > MyDbDate.efectFolder) {
            currentStory = 1;
        }
        // System.out.println("向右，当前故事为：" + currentStory);
        title.setText(MyDbDate.stories.get(currentStory - 1).storyName);
        storySummaryLabel.setText(MyDbDate.stories.get(currentStory - 1).storyDescription);
        contentPanel.loadImage(MyStyle.getStoryImageFilePath(currentStory));

    }
}
