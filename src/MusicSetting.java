import javax.swing.JOptionPane;

public class MusicSetting {

    MusicSetting() {

        Object[] options = { "开启", "关闭" };
        int result = JOptionPane.showOptionDialog(null, "改变音乐状态：", "音乐切换",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (result == JOptionPane.YES_OPTION) {
            MyStyle.startMusic();
        } else if (result == JOptionPane.NO_OPTION) {
            MyStyle.stopMusic();
        }
    }

}
