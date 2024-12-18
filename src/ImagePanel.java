import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {
    private Image image;
    // private String displayedImagePath; // 存储当前显示的图像的文件路径
    // private String newImagePath; // 存储要设置的新图像的文件路径

    public ImagePanel(int width, int height, String imagePath) {

        setPreferredSize(new Dimension(width, height));
        loadImage(imagePath);
    }

    /**
     * 加载图像和显示新的图像
     */
    public void loadImage(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
            // newImagePath = imagePath;
            repaint();
        } catch (IOException e) {
            System.out.println("加载图像失败: " + imagePath);
            try {
                image = ImageIO.read(new File(MyStyle.getTSIv2ImageFilepath()));
                repaint();
            } catch (Exception e1) {
                e.printStackTrace();
                image = null;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        // if (!newImagePath.equals(displayedImagePath)) {

        super.paintComponent(g);
        if (image != null) {

            // displayedImagePath = newImagePath;
            System.out.println("加载");

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
            // displayedImagePath = newImagePath;
        } else {
            System.out.println("图片为空！");
        }
        // }
    }

}