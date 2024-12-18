import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Graphics;

import java.awt.image.BufferedImage;

class PlotPage extends JPanel {

    InterfaceExecution IE;
    JLayeredPane layeredPane;
    private BufferedImage backgroundImage;

    StatementProcessor statementProcessor;

    TransparentButton A_Button, B_Button, C_Button;
    TransparentButton storyButton;
    JLabel storyJLabel, nameLabel;
    // 透明按钮，面板上的故事Jlabel

    PlotPage(InterfaceExecution interfaceExecution) {

        IE = interfaceExecution;
        addLayeredPane();

        addOptButton();
        addStoryContent();

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
        layeredPane.add(A_Button, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        A_Button.setBounds(487, 112, 637, 75);

        B_Button = new TransparentButton();
        layeredPane.add(B_Button, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        B_Button.setBounds(487, 262, 637, 75);

        C_Button = new TransparentButton();
        layeredPane.add(C_Button, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        C_Button.setBounds(487, 412, 637, 75);

    }

    void addStoryContent() {

        storyButton = new TransparentButton();
        layeredPane.add(storyButton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        storyButton.setBounds(0, 600, 1500, 200);

        storyJLabel = new JLabel("愿你在沉眠的间隙中积蓄力量，每一次醒来都如晨曦般充满希望。");
        layeredPane.add(storyJLabel, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        storyJLabel.setBounds(0, 600, 1500, 200);
        nameLabel = new JLabel();

    }

}