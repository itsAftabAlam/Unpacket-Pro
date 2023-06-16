import org.pcap4j.core.PcapPacket;

public class PacketDetails {
    public static String getSource(PcapPacket packet) {
        String s = packet.toString();
        int start = s.indexOf("Source address:")+16;
        int end = s.indexOf(" ",start);
        return s.substring(start,end);
    }

    public static String getDestination(PcapPacket packet) {
        String s = packet.toString();
        int start = s.indexOf("Destination address:")+21;
        int end = s.indexOf(" ",start);
        return s.substring(start,end);
    }

    public static String getProtocol(PcapPacket packet) {
        String s = packet.toString();
        if(s.contains("TCP")) return "TCP";
        else if(s.contains("UDP")) return "UDP";
        else if(s.contains("HTTPS")) return "HTTPS";
        else if(s.contains("ICMP")) return "ICMP";
        else if(s.contains("TLS")) return "TLS";
        else if(s.contains("ARP")) return "ARP";
        else return " ";
    }

    public static String getLength(PcapPacket packet) {
        String s = packet.toString();
        int start = s.indexOf("Original length:")+17;
        int end = s.indexOf(" ",start);
        return s.substring(start,end);
    }

    public static boolean isFilterTrue(PcapPacket packet){
        int index = GUI.filterSelect.getSelectedIndex();
        String filterItem = GUI.filterText.getText();
        if(index==0){
            return filterItem.equals(Integer.toString(GUI.sno));
        }
        else if(index==1){
            return filterItem.equals(packet.getTimestamp().toString());
        }
        else if(index==2){
            return filterItem.equals(getSource(packet));
        }
        else if(index==3){
            return filterItem.equals(getDestination(packet));
        }
        else if(index==4){
            return filterItem.equals(getProtocol(packet));
        }
        else if(index==5){
            return filterItem.equals(getLength(packet));
        }
        return false;
    }
}
