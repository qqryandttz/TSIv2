import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MyProgressBar extends JProgressBar {
    private static final int CORNER_RADIUS = 25; // 定义角半径

    public MyProgressBar(int minValue, int maxValue) {
        super(minValue, maxValue);
        setBorderPainted(false);
        setFocusable(false);
    }

    {
        setUI(new BasicProgressBarUI() {
            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 获取进度条的大小
                int width = c.getWidth();
                int height = c.getHeight();

                // 绘制背景
                g2d.setColor(MyStyle.getBottonColor()); // 假设MyStyle.getBackgroundColor()返回背景颜色
                g2d.fillRect(0, 0, width, height);

                // 绘制圆角进度条
                int amountFull = (int) (width * (((double) getValue()) / getMaximum()));
                g2d.setColor(MyStyle.getBottonFontColor()); // 假设MyStyle.getBottonFontColor()返回前景颜色
                RoundRectangle2D foreground = new RoundRectangle2D.Double(0, 0, amountFull, height, CORNER_RADIUS,
                        CORNER_RADIUS);
                g2d.fill(foreground);

            }

        });
    }

}
