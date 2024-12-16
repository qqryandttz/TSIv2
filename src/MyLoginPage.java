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
import java.io.IOException;

/**
 * 登入页面
 */
public class MyLoginPage {

    InterfaceExecution IE;
    MyJFrame loginFrame;
    Container loginContentPane;

    JLabel titleLabel, nameLabel, pwdLabel;
    JTextField loginField;
    JPasswordField pwdField;
    JRadioButton autoLoginRadioButton;
    roundedButton signButton, registerButton;

    Boolean isLoad = false;
    MyDB myDB = new MyDB();

    MyLoginPage(String frameName, InterfaceExecution interfaceExecution) {

        IE = interfaceExecution;
        loginFrame = new MyJFrame(frameName, 800, 450);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setLocationRelativeTo(IE.myJFrame);
        loginFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (!isLoad) {
                    IE.revertLogin();
                } else {
                    isLoad = true;
                    IE.launchPage.progressBar.smoothProgressTo(15, 30);
                    // 开始执行其他数据库数据获取逻辑，但是不能在这里执行
                }
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
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 70));
        titleLabel.setBounds(290, 38, 465, 70);
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

        // loginField = new JTextField("input your user name");
        loginField = new JTextField("qqry");
        loginField.setFont(new Font("Consolas", Font.BOLD, 24));
        loginField.setForeground(MyStyle.getSubTitleColor());
        loginField.setBackground(new Color(36, 39, 43));
        loginField.setBorder(BorderFactory.createEmptyBorder());
        loginField.setBounds(230, 135, 412, 37);
        loginContentPane.add(loginField);

        pwdField = new JPasswordField("qqry");
        pwdField.setFont(new Font("Consolas", Font.BOLD, 24));
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
    }

    void addBotton() {

        signButton = new roundedButton("登录", 30);
        loginContentPane.add(signButton, new Integer(JLayeredPane.DEFAULT_LAYER + 10));
        signButton.setBorderPainted(false);

        LineBorder lineBorder = new LineBorder(MyStyle.getTitleColor(), 2);
        signButton.setBorder(lineBorder);
        signButton.setFont(MyStyle.getBottonFont());
        signButton.setBackground(MyStyle.getBottonColor());
        signButton.setForeground(MyStyle.getBottonFontColor());
        signButton.setBounds(153, 320, 165, 55);
        signButton.setBorderPainted(true);

        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MyStyle.playPressButtonSound();

                String username = loginField.getText();
                String pwd = pwdField.getText();

                if (!isValidString(username)) {
                    String message = "<html>请重新输入用户名！<br>用户名格式为：4-12位，<br>由数字、字母、部分特殊字符组成。</html>";
                    JOptionPane.showMessageDialog(null, message, "登录失败", JOptionPane.INFORMATION_MESSAGE);

                } else if (!isValidString(pwd)) {
                    String message = "<html>请重新输入密码！<br>密码格式为：4-12位，<br>由数字、字母、部分特殊字符组成。</html>";
                    JOptionPane.showMessageDialog(null, message, "登录失败", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    if (MyDB.con != null && !myDB.findUser(username)) {
                        JOptionPane.showMessageDialog(null, "未找到该用户，请重新输入。", "登录失败",
                                JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        Boolean result = myDB.load(username, pwd);
                        if (result == false) {
                            JOptionPane.showMessageDialog(null, "用户名与密码不匹配，请重新输入。", "登录失败",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            result = myDB.updateAutoLogin(username);
                            if (result == false) {
                                JOptionPane.showMessageDialog(null, "程序出错！数据库无法交互！", "警告", JOptionPane.ERROR_MESSAGE);

                            } else {
                                System.out.println("成功登录");
                                IE.launchPage.loadPopUpLabel.showMessageWithAnimation(username + "，欢迎进入游戏！");

                                // 在文件记录下来登录名，引用一个函数
                                try {
                                    MyFileModifier.settingsParser(MyStyle.getTSIv2SettingFilePath(),
                                            "TSIv2", "最近登录用户", username);

                                } catch (IOException e1) {
                                    JOptionPane.showMessageDialog(null, "配置文件出错！无法写入用户信息！", "警告",
                                            JOptionPane.ERROR_MESSAGE);

                                }

                            }
                            if (autoLoginRadioButton.isSelected()) {
                                try {
                                    List<String> macs = MacTools.getActiveMacList();
                                    myDB.updateUserMacAddresses(username, macs);
                                    System.out.println("自动登录设置成功！ 本机的活动网卡的MAC地址有:" + macs);
                                } catch (SocketException ex) {
                                    JOptionPane.showMessageDialog(null, "程序出错！无法获取自动登录信息！", "警告",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            isLoad = true;
                            loginFrame.dispose();

                        }
                    }
                }
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
                signButton.setForeground(MyStyle.getBottonFontColor());
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

                String username = loginField.getText();
                String pwd = pwdField.getText();
                if (!isValidString(username)) {
                    String message = "<html>请重新输入用户名！<br>用户名格式为：4-12位，<br>由数字、字母、部分特殊字符组成。</html>";
                    JOptionPane.showMessageDialog(null, message, "注册失败", JOptionPane.INFORMATION_MESSAGE);

                } else if (!isValidString(pwd)) {
                    String message = "<html>请重新输入密码！<br>密码格式为：4-12位，<br>由数字、字母、部分特殊字符组成。</html>";
                    JOptionPane.showMessageDialog(null, message, "注册失败", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    String invitationCode = JOptionPane.showInputDialog(null, "请输入邀请码：\n", "用户注册",
                            JOptionPane.PLAIN_MESSAGE);
                    if (Integer.parseInt(invitationCode) != 61) {
                        JOptionPane.showMessageDialog(null, "邀请码错误，无法注册。", "用户注册", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (myDB.findUser(username)) {
                            JOptionPane.showMessageDialog(null, "该用户名已经存在，请重新输入用户名", "注册失败",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            if (!myDB.registerUser(username, pwd)) {
                                JOptionPane.showMessageDialog(null, "程序出错！无法进行注册！", "警告", JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "注册成功，请重新登录。", "用户注册",
                                        JOptionPane.INFORMATION_MESSAGE);

                            }
                        }

                    }
                }
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
