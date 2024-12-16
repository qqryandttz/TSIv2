import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * by Imanoooo
 * 不太会弄，先放着
 */
public class AException {
    public static void badStory(String location) {
        JOptionPane.showMessageDialog(null, "错误：在读取故事时出错！故事文件可能已经损坏！错误语句：\n" +
                location, "错误",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void failedToLogin(JDialog parent) {
        JOptionPane.showMessageDialog(parent, "账号或密码不正确！", "错误",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void internalException(String message) {
        JOptionPane.showMessageDialog(null, "内部错误：" + message, "错误",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void existInvalidCharacter(JDialog parent) {
        JOptionPane.showMessageDialog(parent, "账号或密码存在非法字符！请重新输入正确的账号密码！", "错误",
                JOptionPane.ERROR_MESSAGE);

    }

    public static void lengthInvalid(JDialog parent, String name, int minLength, int maxLength) {
        JOptionPane.showMessageDialog(parent, name + "的长度需要在" + minLength + "-" + maxLength + "之间！", "错误",
                JOptionPane.ERROR_MESSAGE);
    }

}