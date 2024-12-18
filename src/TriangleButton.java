import javax.swing.*;
import java.awt.*;

public class TriangleButton extends JButton {

    public enum Orientation {
        LEFT,
        RIGHT
    }

    private Orientation orientation; // 当前按钮朝向
    private String text = ""; // 默认文本
    private float alpha = 1.0f; // 1.0f 完全不透明，0.0f 完全透明

    // leftButton.setPreferredSize(new Dimension(100, 100));//重新设置大小
    public TriangleButton(Orientation orientation) {
        this.orientation = orientation;
        this.setContentAreaFilled(false); // 禁用默认按钮背景
        this.setBorderPainted(false); // 禁用默认按钮边框
        this.setFocusable(false);
        this.setFocusPainted(false); // 禁用焦点边框

    }

    // 设置按钮文本
    public void setText(String text) {
        this.text = text;
        this.repaint();
    }

    // 设置按钮透明度
    public void setAlpha(float alpha) {
        this.alpha = alpha;
        this.repaint();
    }

    @Override
    public Dimension getPreferredSize() {

        int sideLength = 70;
        return new Dimension(sideLength, sideLength);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // 应用透明度
        Composite originalComposite = g2d.getComposite();
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alphaComposite);

        int width = getWidth();
        int height = getHeight();

        int sideLength = Math.min(width, height); // 三角形的边长

        Polygon triangle = new Polygon();

        if (orientation == Orientation.LEFT) {
            // 左三角形
            triangle.addPoint(0, height / 2);
            triangle.addPoint(sideLength / 2, 0);
            triangle.addPoint(sideLength / 2, height);
        } else {
            // 右三角形
            triangle.addPoint(width, height / 2);
            triangle.addPoint(width - sideLength / 2, 0);
            triangle.addPoint(width - sideLength / 2, height);
        }

        // 三角形填充色
        g2d.setColor(MyStyle.getStarrySkyBackgroundColor());
        g2d.fillPolygon(triangle);

        // 三角形边框色
        g2d.setColor(MyStyle.getTitleColor());
        g2d.drawPolygon(triangle);

        // 绘制文本
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        int textX, textY;
        if (orientation == Orientation.LEFT) {
            textX = (sideLength / 2) - (textWidth / 2);
            textY = height / 2 + (textHeight / 2);
        } else {
            textX = width - (sideLength / 2) - (textWidth / 2);
            textY = height / 2 + (textHeight / 2);
        }

        this.setFont(MyStyle.getSubTitleFont());
        g2d.setColor(MyStyle.getBottonFontColor()); // 文本颜色
        g2d.drawString(text, textX, textY);

        g2d.setComposite(originalComposite);
    }

    // // 获取按钮当前朝向
    // public Orientation getOrientation() {
    // return orientation;
    // }

    // // 设置按钮朝向
    // public void setOrientation(Orientation orientation) {
    // this.orientation = orientation;
    // this.repaint(); // 触发重绘
    // }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Triangle Button with Text");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        TriangleButton leftButton = new TriangleButton(TriangleButton.Orientation.LEFT);
        leftButton.setText("L  ");
        leftButton.setAlpha(0.5f); // 设置50%透明度

        TriangleButton rightButton = new TriangleButton(TriangleButton.Orientation.RIGHT);
        rightButton.setText("  R");
        rightButton.setAlpha(1.0f); // 设置完全不透明

        frame.add(leftButton);
        frame.add(rightButton);

        frame.setVisible(true);

    }
}