import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
        layeredPane.add(startBotton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        startBotton.setBorderPainted(false);

        LineBorder lineBorder = new LineBorder(myStyle.getTitleColor(), 2);
        startBotton.setBorder(lineBorder);
        startBotton.setFont(myStyle.getBottonFont());
        startBotton.setBackground(myStyle.getBottonColor());
        startBotton.setForeground(myStyle.getBottonFontColor());
        startBotton.setBounds(630, 600, 300, 75);

        startBotton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                myStyle.playPressButtonSound();
                // 点击后出现进度条
                JOptionPane.showMessageDialog(new Frame(), "<html><font face='黑体' size='5'>已为您打开文件!</font></html>",
                        "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        startBotton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                myStyle.playTouchButtonSound();
                startBotton.setForeground(myStyle.getBottonFontActiveColor());
                startBotton.setFont(myStyle.getBottonActiveFont());
                startBotton.setBounds(650, 612, 270, 70);
                startBotton.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startBotton.setFont(myStyle.getBottonFont());
                startBotton.setForeground(myStyle.getBottonFontColor());
                startBotton.setBounds(630, 610, 300, 75);
                startBotton.setBorderPainted(false);
            }
        });

    }

}
