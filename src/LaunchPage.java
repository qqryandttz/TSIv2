import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class LaunchPage extends JPanel {

    InterfaceExecution IE;
    MyStyle myStyle = new MyStyle();
    JLayeredPane layeredPane;
    StarrySkyPanel starrySkyPanel;

    JLabel title, subTitle;
    roundedButton startBotton;

    LaunchPage(InterfaceExecution interfaceExecution) {

        IE = interfaceExecution;
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1600, 900);

        AddStarrySkyPanel();
        AddTitle();
        AddStartBotton();

        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);
    }

    void AddStarrySkyPanel() {

        starrySkyPanel = new StarrySkyPanel();
        starrySkyPanel.setBounds(0, 0, 1600, 900);
        layeredPane.add(starrySkyPanel, new Integer(JLayeredPane.DEFAULT_LAYER));
    }

    void AddTitle() {

        title = new JLabel("The Slumber Interval");
        layeredPane.add(title, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        title.setFont(myStyle.getTitleFont());
        title.setForeground(myStyle.getTitleColor());
        title.setBounds(155, 75, 1500, 225);

        subTitle = new JLabel(
                "<html> I hope your dreams are filled with candies and laughter. When you wake up, maybe we can go explore the wider world together.</html>");
        layeredPane.add(subTitle, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        subTitle.setFont(myStyle.getSubTitleFont());
        subTitle.setForeground(myStyle.getSubTitleColor());
        subTitle.setBounds(355, 322, 787, 187);

    }

    void AddStartBotton() {

        startBotton = new roundedButton("START", 30);
        startBotton.setBorderPainted(false);
        LineBorder lineBorder = new LineBorder(myStyle.getTitleColor(), 2);
        startBotton.setBorder(lineBorder);

        layeredPane.add(startBotton);
        startBotton.setFont(myStyle.getBottonFont());
        startBotton.setBackground(myStyle.getBottonColor());
        startBotton.setForeground(myStyle.getBottonFontColor());

        layeredPane.add(startBotton, new Integer(JLayeredPane.DEFAULT_LAYER + 1));
        startBotton.setBounds(638, 675, 300, 75);
    }

}
