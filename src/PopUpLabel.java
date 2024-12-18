import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

class PopUpLabel extends JLabel {
    private int startY = -30;
    private int endY = 30; // 修改目标位置为30
    private int moveDuration = 500; // 移动持续时间0.5秒
    private int stayDuration = 2000; // 停留持续时间2秒

    public PopUpLabel() {
        setFont(new Font("黑体", Font.BOLD, 30));
        setForeground(new Color(35, 35, 35));
        setBackground(new Color(254, 254, 254));
        setOpaque(true);

        Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE, 2);
        setBorder(whiteBorder);

        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    // 封装显示消息和动画的方法
    public void showMessageWithAnimation(String message) {
        setText(message);
        setVisible(true);

        animateLabelUpAndStayThenDown();
    }

    // 标签向上移动、停留然后向下
    private void animateLabelUpAndStayThenDown() {
        Timer moveUpTimer = new Timer(moveDuration / 50, new ActionListener() {
            private int step = (endY - startY) / 50;
            private int currentY = startY;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentY < endY) {
                    currentY += step;
                    setBounds(500, currentY, 600, 50);
                } else {
                    ((Timer) e.getSource()).stop();
                    Timer stayTimer = new Timer(stayDuration, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // 等待停留时间结束
                        }
                    });
                    stayTimer.addActionListener(e1 -> {
                        stayTimer.stop();
                        // 停留结束后启动向下移动
                        animateLabelDown();
                    });
                    stayTimer.setRepeats(false); // 只触发一次
                    stayTimer.start();
                }
            }
        });
        moveUpTimer.start();
    }

    // 标签向下移动并隐藏
    private void animateLabelDown() {
        Timer timer = new Timer(moveDuration / 50, new ActionListener() {
            private int step = (startY - endY) / 50;
            private int currentY = endY;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentY > startY) {
                    currentY += step;
                    setBounds(500, currentY, 600, 50);
                } else {
                    ((Timer) e.getSource()).stop();
                    setVisible(false);
                }
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape roundedRectangle = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        g2d.setColor(getBackground());
        g2d.fill(roundedRectangle);

        super.paintComponent(g2d);
    }
}
