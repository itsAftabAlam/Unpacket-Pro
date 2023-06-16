import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        System.out.println("In main");
        GUI packetSniffer = new GUI();
        packetSniffer.gui();
    }
}
