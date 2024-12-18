import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * 圆角按钮
 */
class RoundedButton extends JButton {

    private int rounded直径;
    private float alpha = 1.0f; // 1.0f 完全不透明，0.0f 完全透明

    public RoundedButton(String 文本, int rounded直径) {
        super(文本);
        this.rounded直径 = rounded直径;
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusable(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    // 设置按钮透明度
    public void setAlpha(float alpha) {
        this.alpha = alpha;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Composite originalComposite = g2d.getComposite();
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alphaComposite);

        Shape rounded矩形 = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, rounded直径, rounded直径);
        g2d.setColor(getBackground());
        g2d.fill(rounded矩形);

        super.paintComponent(g2d);
        g2d.setComposite(originalComposite);
    }
}
