import org.pcap4j.core.PcapPacket;

public class PacketDetails {
    public static String getSource(PcapPacket packet) {
        String s = packet.toString();
        if(s.contains("IPv4 Header")){
            int start = s.indexOf("Source address",s.indexOf("IPv4 Header"))+17;
            int end = s.indexOf(" ",start);
            return s.substring(start,end);
        }
        else if(s.contains("IPv6 Header")){
            int start = s.indexOf("Source address",s.indexOf("IPv6 Header"))+17;
            int end = s.indexOf(" ",start);
            return s.substring(start,end);
        }
        else if(s.contains("Ethernet Header")){
            int start = s.indexOf("Source address",s.indexOf("Ethernet Header"))+16;
            int end = s.indexOf(" ",start);
            return s.substring(start,end);
        }
        else if(s.contains("IGMP")){
            int start = s.indexOf("Source address",s.indexOf("IGMP"))+16;
            int end = s.indexOf(" ",start);
            return s.substring(start,end);
        }
        else return " ";
    }

    public static String getDestination(PcapPacket packet) {
        String s = packet.toString();
        if(s.contains("IPv4 Header")){
            int start = s.indexOf("Destination address",s.indexOf("IPv4 Header"))+22;
            int end = Math.min(s.indexOf(" ",start),s.indexOf("[",start));
            return s.substring(start,end);
        }
        else if(s.contains("IPv6 Header")){
            int start = s.indexOf("Destination address",s.indexOf("IPv6 Header"))+22;
            int end = Math.min(s.indexOf(" ",start),s.indexOf("[",start));
            return s.substring(start,end);
        }
        else if(s.contains("Ethernet Header")){
            int start = s.indexOf("Destination address",s.indexOf("Ethernet Header"))+21;
            int end = Math.min(s.indexOf(" ",start),s.indexOf(" ",start));
            return s.substring(start,end);
        }
        else if(s.contains("IGMP")){
            int start = s.indexOf("Destination address",s.indexOf("IGMP"))+21;
            int end = Math.min(s.indexOf(" ",start),s.indexOf(" ",start));
            return s.substring(start,end);
        }
        else return " ";
    }

    public static String getProtocol(PcapPacket packet) {
        String s = packet.toString();
        if(s.contains("TCP")) return "TCP";
        else if(s.contains("UDP")) return "UDP";
        else if(s.contains("HTTPS")) return "HTTPS";
        else if(s.contains("ICMP")) return "ICMP";
        else if(s.contains("TLS")) return "TLS";
        else if(s.contains("ARP")) return "ARP";
        else if(s.contains("DNS")) return "DNS";
        else if(s.contains("IGMP")) return "IGMP";
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

    public static void setDataField(PcapPacket packet) {
        int startIndex = packet.toString().indexOf("Hex stream");
        int endIndex = packet.toString().length();
        String data = packet.toString().substring(startIndex,endIndex);
        GUI.dataField.setText(data);
        GUI.dataField.updateUI();
    }

    public static void setDetails(PcapPacket packet) {
        int endIndex = packet.toString().indexOf("Hex stream");
        String details = packet.toString().substring(0,endIndex);
        GUI.details.setText(details);
        GUI.details.updateUI();
    }
}
