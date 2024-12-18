import java.net.InetAddress;

public class NetworkUtils {

    public static boolean isNetworkAvailable() {
        try {
            InetAddress address = InetAddress.getByName("www.taobao.com");
            return address.isReachable(5000); // 0.5秒超时
        } catch (Exception e) {
            return false;
        }
    }
}