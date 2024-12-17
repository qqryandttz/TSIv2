import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {
    private Image image;

    public ImagePanel(int width, int height, String imagePath) {
        setPreferredSize(new Dimension(width, height));
        System.out.println("创建了吧");

        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.out.println("加载默认故事图片");

            try {
                image = ImageIO.read(new File(MyStyle.getTSIv2ImageFilepath()));
            } catch (Exception e1) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {

            System.out.println("有被调用吗");
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int imageWidth = image.getWidth(null);
            int imageHeight = image.getHeight(null);

            double scale = Math.min((double) panelWidth / imageWidth, (double) panelHeight / imageHeight);

            int scaledWidth = (int) (imageWidth * scale);
            int scaledHeight = (int) (imageHeight * scale);

            int x = (panelWidth - scaledWidth) / 2;
            int y = (panelHeight - scaledHeight) / 2;

            g.drawImage(image, x, y, scaledWidth, scaledHeight, null);
        } else {
            System.out.println("图片为空！");
        }
    }

    // 测试ImagePanel
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setTitle("Image Panel Example");
        frame.setSize(800, 470);

        ImagePanel imagePanel = new ImagePanel(800, 450,
                MyStyle.getStoryImageFilePath(1));

        frame.add(imagePanel);

        frame.setVisible(true);
    }
}