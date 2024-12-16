import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;

/*
 * 故事页面
 */

public class StoryPage extends JPanel {

    InterfaceExecution IE;
    JLayeredPane layeredPane;
    StarrySkyPanel starrySkyPanel;

    JLabel TitleLabel = new JLabel("题目");
    JButton 音量 = new JButton("按钮");
    JButton 成就 = new JButton("按钮");

    // 这个应该是面板上加载图片
    JTextField jtextField = new JTextField("文本输入框");
    JLabel 作者名 = new JLabel("标签");
    JLabel 故事简介 = new JLabel("标签");
    JButton 左按钮 = new JButton("按钮");
    JButton 右按钮 = new JButton("按钮");

    JButton 进入 = new JButton("按钮");
    JButton 帮助按钮 = new JButton("按钮");
    JButton 设置按钮 = new JButton("按钮");

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

        addStarrySkyPanel();
        addTitle();
        addTitleButton();
        addStoryPanel();
        addStoryButton();
        addUnderBotton();

        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);
    }

    void addStarrySkyPanel() {

        starrySkyPanel = new StarrySkyPanel();
        starrySkyPanel.setBounds(0, 0, 1600, 900);
        layeredPane.add(starrySkyPanel, new Integer(JLayeredPane.DEFAULT_LAYER));
    }

    void addTitle() {

    }

    void addTitleButton() {

    }

    void addStoryPanel() {

    }

    void addStoryButton() {

    }

    void addUnderBotton() {

    }
}
