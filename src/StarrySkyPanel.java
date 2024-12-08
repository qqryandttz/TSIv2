import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class StarrySkyPanel extends JPanel {

    private static final int panel_w = 1600;
    private static final int panel_H = 900;
    private static final int starMaxNum = 1000;
    private static final double moveSpeed = 1.04d;

    private int[][] starPost = new int[starMaxNum][2];
    private Color[] starColor = new Color[starMaxNum];
    private Random randomer = new Random();

    public StarrySkyPanel() {
        setPreferredSize(new Dimension(panel_w, panel_H));
        setBackground(new Color(0x0b, 0x0c, 0x10));

        InitStar();
        new Timer(10, e -> {
            moveStar();
            repaint();
        }).start();
    }

    private void InitStar() {
        for (int i = 0; i < starMaxNum; i++) {
            starPost[i][0] = randomer.nextInt(panel_w);
            starPost[i][1] = randomer.nextInt(panel_H);

            int light = randomer.nextInt(256);
            starColor[i] = new Color(light, light, light);
        }
    }

    private void moveStar() {
        for (int i = 0; i < starMaxNum; i++) {
            starPost[i][0] += (int) (moveSpeed * randomer.nextDouble());
            if (starPost[i][0] > panel_w) {
                starPost[i][0] = 0;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < starMaxNum; i++) {
            g2d.setColor(starColor[i]);
            g2d.fillOval(starPost[i][0], starPost[i][1], 2, 2);
        }
    }
}