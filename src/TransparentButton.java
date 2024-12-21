import javax.swing.*;
import java.awt.*;

public class TransparentButton extends JButton {

    private float alpha = 0.5f; // 透明度，默认值，可通过方法修改
    private Color borderColor = Color.BLACK; // 边框默认黑色
    private Color backgroundColor = new Color(0, 0, 0, 0); // 默认完全透明
    private Color textColor = Color.BLACK; // 字体默认黑色
    private int cornerRadius = 10; // 圆角半径，默认值，可修改
    private float glowAlpha = 0.3f; // 发光的透明度，可调整

    public TransparentButton() {
        super();
        setContentAreaFilled(false); // 使按钮内容区域透明
        setBorderPainted(false); // 先不绘制默认边框
        setOpaque(false); // 按钮整体设为非不透明，配合透明度设置生效
        setForeground(textColor); // 设置字体颜色
        setFocusPainted(false); // 禁用焦点边框
        setFocusable(false);

        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    public TransparentButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setForeground(textColor);
        setFocusPainted(false);
        setFocusable(false);
    }

    // 设置透明度的方法
    public void setAlpha(float alpha) {
        this.alpha = alpha;
        repaint(); // 重绘按钮以显示透明度变化
    }

    // 设置边框颜色的方法
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    // 设置背景颜色的方法，(可带透明度
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    // 设置字体颜色的方法
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        setForeground(textColor);
        repaint();
    }

    // 设置圆角半径的方法
    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 绘制带透明度的背景
        if (backgroundColor.getAlpha() > 0) {
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }

        // 绘制边框
        if (borderColor != null) {
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
            Color glowColor = new Color(
                    Math.min((int) (borderColor.getRed() * 1.2), 255),
                    Math.min((int) (borderColor.getGreen() * 1.2), 255),
                    Math.min((int) (borderColor.getBlue() * 1.2), 255),
                    (int) (255 * glowAlpha));
            g2.setColor(glowColor);
            g2.drawRoundRect(2, 2, getWidth() - 5, getHeight() - 5, cornerRadius, cornerRadius);
        }

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {

        // 圆角边框
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Transparent Button Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        TransparentButton button = new TransparentButton("Click Me");
        button.setAlpha(0.8f); // 设置透明度
        button.setBackgroundColor(new Color(255, 0, 0, 100)); // 设置带透明度的背景色
        button.setBorderColor(Color.BLUE); // 设置边框颜色
        button.setTextColor(Color.WHITE); // 设置字体颜色
        button.setCornerRadius(20); // 设置圆角半径

        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);
    }
}
