import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class PlotPage extends JPanel {

    InterfaceExecution IE;
    JLayeredPane layeredPane;
    private BufferedImage backgroundImage;

    StatementProcessor statementProcessor;

    TransparentButton A_Button, B_Button, C_Button;
    TransparentButton storyButton, nameButton;
    JLabel storyJLabel, nameJLabel;

    static char userOpt;

    public static char getUserOpt() {
        return userOpt;
    }

    public static void setUserOpt(char userOpt) {
        PlotPage.userOpt = userOpt;
    }

    PlotPage(InterfaceExecution interfaceExecution) {

        IE = interfaceExecution;
        addLayeredPane();

        addOptButton();
        addStoryContent();
        addStyleLister();

        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);

    }

    void addLayeredPane() {
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
            backgroundImage = ImageIO.read(new File(MyStyle.getBackgroundImageFilepath("1")));
            // repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置背景图片
     */
    public void setBackgroundImage(String newImagePath) {
        try {
            backgroundImage = ImageIO.read(new File(newImagePath));
            layeredPane.repaint(); // 切换图片后重绘JLayeredPane以显示新图片
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void startFileReader() {
        statementProcessor = new StatementProcessor(IE, this);
        statementProcessor.simpleAnalyze();
    }

    void addOptButton() {

        A_Button = new TransparentButton();
        A_Button.setBorderPainted(true);
        layeredPane.add(A_Button, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        A_Button.setBounds(487, 112, 637, 75);
        A_Button.setBackgroundColor(new Color(255, 255, 255, 100));
        A_Button.setBorderColor(new Color(0, 0, 0));
        A_Button.setCornerRadius(30);

        B_Button = new TransparentButton();
        B_Button.setBorderPainted(true);
        layeredPane.add(B_Button, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        B_Button.setBounds(487, 262, 637, 75);
        B_Button.setBackgroundColor(new Color(255, 255, 255, 100));
        B_Button.setBorderColor(new Color(0, 0, 0));
        B_Button.setCornerRadius(30);

        C_Button = new TransparentButton();
        C_Button.setBorderPainted(true);
        layeredPane.add(C_Button, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        C_Button.setBounds(487, 412, 637, 75);
        C_Button.setBackgroundColor(new Color(255, 255, 255, 100));
        C_Button.setBorderColor(new Color(0, 0, 0));
        C_Button.setCornerRadius(30);

        A_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();
                setUserOpt('A');
                removeOptButton();
                statementProcessor.simpleAnalyze();

            }
        });

        B_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();
                setUserOpt('B');
                removeOptButton();
                statementProcessor.simpleAnalyze();

            }
        });

        C_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();
                setUserOpt('C');
                removeOptButton();
                statementProcessor.simpleAnalyze();

            }
        });

    }

    void addStoryContent() {

        storyButton = new TransparentButton("愿你在沉眠的间隙中积蓄力量，每一次醒来都如晨曦般充满希望。");
        storyButton.setBorderPainted(true);
        layeredPane.add(storyButton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        storyButton.setBounds(0, 600, 1600, 200);
        storyButton.setBackgroundColor(new Color(255, 255, 255, 100));
        storyButton.setBorderColor(MyStyle.titleColor);
        storyButton.setFont(new Font("黑体", Font.BOLD, 27));

        nameButton = new TransparentButton("qqry");
        nameButton.setBorderPainted(true);
        layeredPane.add(nameButton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        nameButton.setBounds(0, 545, 245, 56);
        nameButton.setBackgroundColor(new Color(255, 255, 255, 100));
        storyButton.setBorderColor(MyStyle.titleColor);
        nameButton.setFont(new Font("黑体", Font.BOLD, 27));

        storyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();
                statementProcessor.simpleAnalyze();

            }
        });

        nameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();
                statementProcessor.simpleAnalyze();

            }
        });

    }

    void attachNameButton() {

        layeredPane.add(nameButton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));

    }

    void removeNameButton() {

        layeredPane.remove(nameButton);

    }

    void attachOptButton() {

        layeredPane.add(A_Button, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        layeredPane.add(B_Button, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        layeredPane.add(C_Button, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
    }

    void removeOptButton() {

        layeredPane.remove(A_Button);
        layeredPane.remove(B_Button);
        layeredPane.remove(C_Button);

    }

    void addStyleLister() {

        A_Button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                A_Button.setBorderColor(MyStyle.titleColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {

                A_Button.setBorderColor(new Color(0, 0, 0));
            }
        });

        B_Button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                B_Button.setBorderColor(MyStyle.titleColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {

                B_Button.setBorderColor(new Color(0, 0, 0));
            }
        });

        C_Button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                C_Button.setBorderColor(MyStyle.titleColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {

                C_Button.setBorderColor(new Color(0, 0, 0));
            }
        });

        storyButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                storyButton.setBorderColor(MyStyle.titleColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {

                storyButton.setBorderColor(new Color(0, 0, 0));
            }
        });

        // 创建一个抽象动作（Action），用于绑定到空格键按下操作
        AbstractAction spaceBarAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storyButton.doClick();
            }
        };

        // 使用KeyStroke获取表示空格键按下的键击对象
        KeyStroke spaceBarKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0);
        // 将空格键按下的键击与抽象动作绑定到按钮上
        storyButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(spaceBarKeyStroke, "spaceBarAction");
        storyButton.getActionMap().put("spaceBarAction", spaceBarAction);

    }

}