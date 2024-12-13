import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

/**
 * 进度条,实现平滑过渡
 */
public class MyProgressBar extends JProgressBar {
    private static final int CORNER_RADIUS = 25; // 定义角半径
    private static final int TIMER_DELAY = 50;
    private Timer progressTimer;

    public MyProgressBar(int minValue, int maxValue) {
        super(minValue, maxValue);
        setBorderPainted(false);
        setFocusable(false);
        setUI(createCustomUI());
    }

    private BasicProgressBarUI createCustomUI() {
        return new BasicProgressBarUI() {
            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 获取进度条的大小
                int width = c.getWidth();
                int height = c.getHeight();

                // 绘制背景
                g2d.setColor(MyStyle.getBottonColor());
                g2d.fillRect(0, 0, width, height);

                // 绘制圆角进度条
                int amountFull = (int) (width * (((double) getValue()) / getMaximum()));
                g2d.setColor(MyStyle.getBottonFontColor());
                RoundRectangle2D foreground = new RoundRectangle2D.Double(0, 0, amountFull, height, CORNER_RADIUS,
                        CORNER_RADIUS);
                g2d.fill(foreground);

            }
        };
    }

    public void smoothProgressTo(int targetValue) {
        smoothProgressTo(getValue(), targetValue);
    }

    public void smoothProgressTo(int startValue, int targetValue) {
        if (progressTimer != null && progressTimer.isRunning()) {
            progressTimer.stop();
        }

        int steps = Math.max(1, (int) ((targetValue - startValue) / (TIMER_DELAY / 10))); // 计算步数，至少为1
        double increment = (targetValue - startValue) / (double) steps;

        progressTimer = new Timer(TIMER_DELAY, new ActionListener() {
            int currentValue = startValue;

            @Override
            public void actionPerformed(ActionEvent e) {
                currentValue = (int) Math.round(currentValue + increment); // 四舍五入
                if (currentValue >= targetValue) {
                    currentValue = targetValue;
                    progressTimer.stop();
                }
                setValue(currentValue);
                repaint();
            }
        });
        progressTimer.start();
    }

}
