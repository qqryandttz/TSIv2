import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Color;

public class TSIv2 {
    public static void main(String[] args) {

        // DBConnection dbConnection = new DBConnection();
        // dbConnection.connectDB("teaching", "root", "61");

        // 这里进行读取数据库，看是以玩家还是以用户的身份登入
        // 数据库以什么身份登入也是一个要点
        InterfaceExecution InterfaceExecution = new InterfaceExecution();

    }

}

class InterfaceExecution {

    CardLayout cardLayout;
    MyJFrame myJFrame;

    void InterruptedException() {

        cardLayout = new CardLayout();
        myJFrame = new MyJFrame(this, "TSIv2");

    }

}

class MyJFrame extends JFrame {

    InterfaceExecution IE;
    JMenuBar myJMenuBar;
    JMenu myJMenu;

    MyJFrame(InterfaceExecution interfaceExecution, String frameName) {
        IE = interfaceExecution;

        setTitle(frameName);
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        this.getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        this.setResizable(false);

        setVisible(true);
    }

    MyJFrame(InterfaceExecution interfaceExecution, String frameName, int menuItemNumber) {
        IE = interfaceExecution;
        setTitle(frameName);
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        this.setResizable(false);

        setVisible(true);
    }

    void initMenu() {

    }

}
