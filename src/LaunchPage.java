import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * 启动页面，数据库逻辑执行
 */
public class LaunchPage extends JPanel {

    InterfaceExecution IE;
    JLayeredPane layeredPane;
    StarrySkyPanel starrySkyPanel;
    JLabel title, subTitle;
    roundedButton startBotton;

    MyProgressBar progressBar;
    MyLoginPage myLoginPage;

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

        startBotton = new roundedButton("START", 30);
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
                startBotton.setVisible(false);
                AddProcessBar();
                progressBar.smoothProgressTo(15);
                myLoginPage = new MyLoginPage("登入页面", IE);
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

}
