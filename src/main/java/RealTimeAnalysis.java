import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.pcap4j.core.PcapPacket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RealTimeAnalysis {
    static int indexProtocol = 0;
    static int indexTalkers = 0;
    static int tcp = 0 ,udp = 0,https = 0,icmp = 0,igmp = 0,arp = 0,dns = 0;
    static DefaultPieDataset<String> talkersDataset = new DefaultPieDataset<>();
    public static void chartPlot(String item){
        System.out.println("called");
        if(item.equals("Protocol Distribution")){
            getProtocolDistribution();
        }
        else if (item.equals("Top Talkers")){
            System.out.println("talk talkers called");
            getTopTalkers();
        }
    }

    private static void setTalkersNumbers() {
        System.out.println("creating talkers chart");
        for(int i = indexTalkers ; i< indexTalkers+100 && i<GUI.packetList.length;i++){
            if(GUI.packetList[i]!=null){
                String key = PacketDetails.getSource(GUI.packetList[i]);
                int value = 0;
                if(talkersDataset.getValue(key)!=null) value = talkersDataset.getValue(key).intValue()+1;
                talkersDataset.setValue(key,value);
            }
        }
        indexTalkers += 100;
    }

    private static void getTopTalkers() {
        setTalkersNumbers();
        JFreeChart chart = ChartFactory.createPieChart("Top Talkers",talkersDataset,true,true,false);
        PiePlot pie= (PiePlot) chart.getPlot();
        ChartFrame pieChart = new ChartFrame("Top Talkers",chart);
        pieChart.setBounds(100,100,500,500);
        pieChart.setVisible(true);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTalkersNumbers();
                pieChart.repaint();;
            }
        });
        timer.setRepeats(true);
        timer.start();
        pieChart.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                timer.stop();
            }
        });
    }

    private static void setProtocolNumbers() {
        System.out.println("creating chart");
        for(int i = indexProtocol ; i< indexProtocol+100 && i<GUI.data.length;i++){
            if(GUI.data[i][4]!=null){
                System.out.println(GUI.data[i][5]);
                if(GUI.data[i][4].equals("TCP")) tcp++;
                if(GUI.data[i][4].equals("UDP")) udp++;
                if(GUI.data[i][4].equals("HTTPS")) https++;
                if(GUI.data[i][4].equals("ICMP")) icmp++;
                if(GUI.data[i][4].equals("IGMP")) igmp++;
                if(GUI.data[i][4].equals("ARP")) arp++;
                if(GUI.data[i][4].equals("DNS")) dns++;
            }
        }
        indexProtocol += 100;
    }
    private static void getProtocolDistribution(){
        setProtocolNumbers();
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.setValue("TCP",tcp);
        dataset.setValue("UDP",udp);
        dataset.setValue("HTTPS",https);
        dataset.setValue("ICMP",icmp);
        dataset.setValue("IGMP",igmp);
        dataset.setValue("ARP",arp);
        dataset.setValue("DNS",dns);
        JFreeChart chart = ChartFactory.createPieChart("Protocol Distribution",dataset,true,true,false);
        PiePlot pie= (PiePlot) chart.getPlot();
        ChartFrame pieChart = new ChartFrame("Protocol Distribution",chart);
        pieChart.setBounds(100,100,500,500);
        pieChart.setVisible(true);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setProtocolNumbers();
                dataset.setValue("TCP", tcp);
                dataset.setValue("UDP", udp);
                dataset.setValue("HTTPS", https);
                dataset.setValue("ICMP", icmp);
                dataset.setValue("IGMP", igmp);
                dataset.setValue("ARP", arp);
                dataset.setValue("DNS", dns);
                pieChart.repaint();;
            }
        });
        timer.setRepeats(true);
        timer.start();
        pieChart.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                timer.stop();
            }
        });
    }

}
