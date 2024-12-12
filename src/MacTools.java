import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

public class MacTools {
    public static List<String> getActiveMacList() throws SocketException {
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        ArrayList<String> macList = new ArrayList<>();
        while (en.hasMoreElements()) {
            NetworkInterface iface = en.nextElement();
            byte[] mac = iface.getHardwareAddress();
            if (mac != null && mac.length == 6) { // 确保MAC地址不为空且长度为6
                // 检查接口是否有IP地址绑定
                boolean hasIpAddress = false;
                Enumeration<InetAddress> inetAddresses = iface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) { // 排除回环地址和IPv6地址
                        hasIpAddress = true;
                        break;
                    }
                }
                if (hasIpAddress) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    macList.add(sb.toString());
                }
            }
        }
        // 去重
        List<String> uniqueMacList = macList.stream().distinct().collect(Collectors.toList());
        return uniqueMacList;
    }

    public static void main(String[] args) throws SocketException {
        System.out.println("进行 multi net address 测试===》");
        List<String> macs = getActiveMacList();
        System.out.println("本机的活动网卡的MAC地址有: " + macs);
    }
}