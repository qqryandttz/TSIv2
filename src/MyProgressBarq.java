import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class MyProgressBarq extends JProgressBar {
    private static final int CORNER_RADIUS = 25; // 定义角半径
    private static final int TIMER_DELAY = 50; // Timer 的延迟时间（毫秒）
    private Timer progressTimer;

    // 假设的颜色定义
    private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    private static final Color FOREGROUND_COLOR = Color.BLUE;

    public MyProgressBarq(int minValue, int maxValue) {
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

                int width = c.getWidth();
                int height = c.getHeight();

                // 绘制背景
                g2d.setColor(BACKGROUND_COLOR);
                g2d.fillRect(0, 0, width, height);

                // 绘制圆角进度条
                int amountFull = (int) (width * (((double) getValue()) / getMaximum()));
                g2d.setColor(FOREGROUND_COLOR);
                RoundRectangle2D foreground = new RoundRectangle2D.Double(0, 0, amountFull, height, CORNER_RADIUS,
                        CORNER_RADIUS);
                g2d.fill(foreground);
            }
        };
    }

    // 逐步增加进度条值到目标值的方法
    public void smoothProgressTo(int targetValue) {
        if (progressTimer != null && progressTimer.isRunning()) {
            progressTimer.stop();
        }

        int currentValue = getValue();
        int steps = (targetValue - currentValue) / (TIMER_DELAY / 10); // 每10毫秒更新一次，计算总步数
        if (steps == 0)
            steps = 1; // 防止除以零

        progressTimer = new Timer(TIMER_DELAY / 10, new ActionListener() {
            int currentValue = getValue();
            int steps = (targetValue - currentValue) / (TIMER_DELAY / 10);
            int increment = (targetValue - currentValue) / steps;
            int currentStep = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                currentValue += increment;
                if (currentValue >= targetValue) {
                    currentValue = targetValue;
                    progressTimer.stop();
                }
                setValue(currentValue);
                repaint(); // 强制重绘进度条
            }
        });
        progressTimer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);

        MyProgressBarq progressBar = new MyProgressBarq(0, 100);
        progressBar.setPreferredSize(new Dimension(300, 30));
        frame.add(progressBar, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Progress");
        startButton.addActionListener(e -> progressBar.smoothProgressTo(100));
        frame.add(startButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}