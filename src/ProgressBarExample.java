import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProgressBarExample {

    private static void createAndShowGUI() {
        // 创建一个新的JFrame
        JFrame frame = new JFrame("进度条示例");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // 创建一个JProgressBar
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true); // 显示进度字符串

        // 将进度条添加到框架的内容面板中
        frame.add(progressBar, BorderLayout.CENTER);

        // 创建一个按钮来启动进度更新
        JButton startButton = new JButton("开始");
        startButton.addActionListener(e -> startProgress(progressBar));

        // 将按钮添加到框架的北部面板中
        frame.add(startButton, BorderLayout.NORTH);

        // 显示框架
        frame.setVisible(true);
    }

    private static void startProgress(JProgressBar progressBar) {
        // 使用一个ExecutorService来在后台线程中执行进度更新
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            int progress = 0;
            while (progress < 100) {
                try {
                    // 模拟工作，睡眠一段时间
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    return;
                }
                // 更新进度条的值
                progress += 10; // 假设每次增加10的进度
                progressBar.setValue(progress);
            }
        });

        // 注意：这里没有显式关闭ExecutorService，因为它将在程序结束时由JVM自动关闭。
        // 在实际的应用程序中，应该管理ExecutorService的生命周期，并适时关闭它。
    }

    public static void main(String[] args) {
        // 在事件调度线程中创建和显示GUI
        SwingUtilities.invokeLater(ProgressBarExample::createAndShowGUI);
    }
}