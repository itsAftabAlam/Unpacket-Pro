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
    static int index = 0;
    static int tcp = 0 ,udp = 0,https = 0,icmp = 0,igmp = 0,arp = 0,dns = 0;
    public static void chartPlot(String item){
        System.out.println("called");
        if(item.equals("Protocol Distribution")){
            getProtocolDistribution();
        }
    }
    private static void setProtocolNumbers() {
        System.out.println("creating chart");
        for(int i = index ; i< index+100 && i<GUI.packetList.length;i++){
            if(GUI.packetList[i]!=null){
                if(GUI.packetList[i].toString().contains("TCP")) tcp++;
                if(GUI.packetList[i].toString().contains("UDP")) udp++;
                if(GUI.packetList[i].toString().contains("HTTPS")) https++;
                if(GUI.packetList[i].toString().contains("ICMP")) icmp++;
                if(GUI.packetList[i].toString().contains("IGMP")) igmp++;
                if(GUI.packetList[i].toString().contains("ARP")) arp++;
                if(GUI.packetList[i].toString().contains("DNS")) dns++;
            }
        }
        index += 100;
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
