import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.SocketException;
import java.util.List;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 登入页面
 */
public class MyLoginPage {

    MyJFrame loginFrame;
    Container loginContentPane;

    JLabel titleLabel, nameLabel, pwdLabel;
    JTextField loginField;
    JPasswordField pwdField;
    JRadioButton autoLoginRadioButton;
    roundedButton signButton, registerButton;

    MyDB myDB = new MyDB();

    MyLoginPage(String frameName, InterfaceExecution IE) {

        loginFrame = new MyJFrame(frameName, 800, 450);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setLocationRelativeTo(IE.myJFrame);
        loginFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                IE.revertLogin();
            }
        });

        loginContentPane = loginFrame.getContentPane();
        loginContentPane.setBackground(MyStyle.getBottonColor());
        loginContentPane.setLayout(null);

        addLabel();
        addField();
        addAutoLogin();
        addBotton();

        loginContentPane.repaint();
    }

    void addLabel() {

        titleLabel = new JLabel("TSIv2");
        titleLabel.setFont(new Font("黑体", Font.BOLD, 70));
        titleLabel.setBounds(290, 38, 465, 67);
        titleLabel.setForeground(MyStyle.getTitleColor());
        loginContentPane.add(titleLabel);

        nameLabel = new JLabel("用户名:");
        nameLabel.setFont(new Font("黑体", Font.BOLD, 24));
        nameLabel.setBounds(140, 135, 413, 37);
        nameLabel.setForeground(MyStyle.getTitleColor());
        loginContentPane.add(nameLabel);

        pwdLabel = new JLabel("密码:");
        pwdLabel.setFont(new Font("黑体", Font.BOLD, 24));
        pwdLabel.setBounds(140, 200, 413, 37);
        pwdLabel.setForeground(MyStyle.getTitleColor());
        loginContentPane.add(pwdLabel);
    }

    void addField() {

        loginField = new JTextField("input your user name");
        loginField.setFont(new Font("黑体", Font.BOLD, 24));
        loginField.setForeground(MyStyle.getSubTitleColor());
        loginField.setBackground(new Color(36, 39, 43));
        loginField.setBorder(BorderFactory.createEmptyBorder());
        loginField.setBounds(230, 135, 412, 37);
        loginContentPane.add(loginField);

        pwdField = new JPasswordField();
        pwdField.setFont(new Font("黑体", Font.BOLD, 24));
        pwdField.setForeground(MyStyle.getSubTitleColor());
        pwdField.setBackground(new Color(36, 39, 43));
        pwdField.setBorder(BorderFactory.createEmptyBorder());
        pwdField.setBounds(230, 200, 412, 37);
        pwdField.setEchoChar('*');
        loginContentPane.add(pwdField);

    }

    void addAutoLogin() {
        autoLoginRadioButton = new JRadioButton("自动登录");
        autoLoginRadioButton.setContentAreaFilled(false);
        autoLoginRadioButton.setFont(new Font("黑体", Font.BOLD, 17));
        autoLoginRadioButton.setBorderPainted(false);
        autoLoginRadioButton.setFocusable(false);
        autoLoginRadioButton.setBounds(140, 260, 165, 30);
        autoLoginRadioButton.setBackground(MyStyle.getBottonColor());
        autoLoginRadioButton.setForeground(MyStyle.getBottonFontColor());
        loginContentPane.add(autoLoginRadioButton);

        autoLoginRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (autoLoginRadioButton.isSelected()) {
                    // 如果选中
                    try {
                        List<String> macs = MacTools.getActiveMacList();
                        System.out.println("本机的活动网卡的MAC地址有: " + macs);
                    } catch (SocketException ex) {
                        System.out.println("MAC获取出现错误!");
                    }

                } else {
                    // 如果没选中

                }
            }
        });
    }

    void addBotton() {

        signButton = new roundedButton("登录", 30);
        loginContentPane.add(signButton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        signButton.setBorderPainted(false);

        LineBorder lineBorder = new LineBorder(MyStyle.getTitleColor(), 2);
        signButton.setBorder(lineBorder);
        signButton.setFont(MyStyle.getBottonFont());
        signButton.setBackground(MyStyle.getBottonColor());
        signButton.setForeground(MyStyle.getTitleColor());
        signButton.setBounds(153, 320, 165, 55);
        signButton.setBorderPainted(true);

        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();

                if (!isValidString(loginField.getText())) {
                    JOptionPane.showMessageDialog(null, "请重新输入用户名！", "警告", JOptionPane.INFORMATION_MESSAGE);

                } else if (!isValidString(pwdField.getText())) {
                    JOptionPane.showMessageDialog(null, "请重新输入密码！", "警告", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    Boolean result = myDB.load(loginField.getText(), pwdField.getText());
                    if (result == false) {
                        System.out.println("登录验证结果: " + result);
                        JOptionPane.showMessageDialog(null, "登录失败！", "警告", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        System.out.println("登录成功！");
                    }
                }

                // if (!autoLoginRadioButton.isSelected()) {

                // }

            }
        });

        signButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                signButton.setForeground(MyStyle.getBottonFontActiveColor());
                signButton.setFont(MyStyle.getBottonActiveFont());
                signButton.setBounds(156, 323, 159, 48);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signButton.setFont(MyStyle.getBottonFont());
                signButton.setForeground(MyStyle.getTitleColor());
                signButton.setBounds(153, 320, 165, 55);
            }
        });

        registerButton = new roundedButton("注册", 30);
        loginContentPane.add(registerButton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        registerButton.setBorderPainted(false);

        registerButton.setBorder(lineBorder);
        registerButton.setFont(MyStyle.getBottonFont());
        registerButton.setBackground(MyStyle.getBottonColor());
        registerButton.setForeground(MyStyle.getBottonFontColor());
        registerButton.setBounds(453, 320, 165, 55);
        registerButton.setBorderPainted(true);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();
                // registerButton.setVisible(false);
            }
        });

        registerButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                MyStyle.playTouchButtonSound();
                registerButton.setForeground(MyStyle.getBottonFontActiveColor());
                registerButton.setFont(MyStyle.getBottonActiveFont());
                registerButton.setBounds(456, 323, 159, 48);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerButton.setFont(MyStyle.getBottonFont());
                registerButton.setForeground(MyStyle.getBottonFontColor());
                registerButton.setBounds(453, 320, 165, 55);
            }
        });

    }

    private static final String VALID_CHARS_REGEX = "^[\\u4E00-\\u9FA5A-Za-z0-9_%&',;=?$\\x22]+$";

    /**
     * 字符串检测
     */
    public boolean isValidString(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        int length = input.length();
        if (length < 4 || length > 12) {
            return false;
        }

        if (!input.matches(VALID_CHARS_REGEX)) {
            return false;
        }

        return true;
    }

}
