import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PopUpLabel extends JLabel {
    private int startY = -30;
    private int endY = 30; // 修改目标位置为70
    private int moveDuration = 1000; // 移动持续时间1秒
    private int stayDuration = 2000; // 停留持续时间2秒

    public PopUpLabel() {
        setFont(new Font("Serif", Font.BOLD, 24));
        setForeground(Color.BLACK);
        setBackground(Color.WHITE);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // 初始位置设置为不可见
        setBounds(500, startY, 600, 50);
        setVisible(false);
    }

    // 封装显示消息和动画的方法
    public void showMessageWithAnimation(String message) {
        setText(message);
        setVisible(true);

        animateLabelUpAndStayThenDown();
    }

    // 动画标签向上移动、停留然后向下的方法
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
                    // 启动停留Timer
                    Timer stayTimer = new Timer(stayDuration, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // 不需要在这里做任何事情，只是等待停留时间结束
                        }
                    });
                    stayTimer.addActionListener(e1 -> {
                        stayTimer.stop();
                        // 停留结束后启动向下移动的动画
                        animateLabelDown();
                    });
                    stayTimer.setRepeats(false); // 只触发一次
                    stayTimer.start();
                }
            }
        });
        moveUpTimer.start();
    }

    // 动画标签向下移动并隐藏的方法（保持不变）
    private void animateLabelDown() {
        Timer timer = new Timer(moveDuration / 50, new ActionListener() { // 同样使用更精细的时间间隔
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
}

class MainFrame extends JFrame {
    private JPanel contentPane;
    private PopUpLabel popUpLabel;

    public MainFrame() {
        setTitle("Pop Up Label Example");
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 设置布局为 null，因为我们将手动控制组件的位置和大小
        setLayout(null);

        contentPane = new JPanel();
        contentPane.setLayout(null); // JPanel 也使用 null 布局
        contentPane.setBounds(0, 0, getWidth(), getHeight());
        add(contentPane);

        popUpLabel = new PopUpLabel();
        contentPane.add(popUpLabel);

        setVisible(true);
    }

    public void showMessage(String message) {
        popUpLabel.showMessageWithAnimation(message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.showMessage("欢迎进入游戏！");
        });
    }
}