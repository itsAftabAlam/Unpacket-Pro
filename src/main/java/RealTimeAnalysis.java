
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.pcap4j.core.PcapPacket;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RealTimeAnalysis {
    //IPs crossing threshold
    static HashSet<String> thresholdIP = new HashSet<>();
    static int totalPackets = 0;
    static int currentMaxValue = Integer.MIN_VALUE;
    static double currentMaxPercent = 0;
    static int thresholdValue = Integer.MAX_VALUE;
    static double thresholdPercentage = 100;
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
        for(int i = indexTalkers ; i< indexTalkers+100 && i<GUI.data.length;i++){
            totalPackets++;
            if(GUI.data[i][2]!=null){
                String key = GUI.data[i][2];
                int value = 1;
                if(talkersDataset.getIndex(key)!=-1) value = talkersDataset.getValue(key).intValue()+1;
                talkersDataset.setValue(key,value);
                setMax(value);
                alarm(key);
            }
            if(GUI.data[i][3]!=null){
                String key = GUI.data[i][3];
                int value = 0;
                if(talkersDataset.getIndex(key)!=-1) value = talkersDataset.getValue(key).intValue()+1;
                talkersDataset.setValue(key,value);
                setMax(value);
                alarm(key);
            }
        }
        indexTalkers += 100;
    }

    private static void setMax(int value) {
        if(currentMaxValue<value) currentMaxValue=value;
        double percentValue =  ((double)value/totalPackets)*100;
        if(currentMaxPercent<percentValue) currentMaxPercent=percentValue;
    }

    static class Beep extends TimerTask {
        @Override
        public void run() {
            Toolkit.getDefaultToolkit().beep();
        }
    }
    private static void alarm(String key){
        if(currentMaxValue>thresholdValue && currentMaxPercent>thresholdPercentage && !thresholdIP.contains(key)){
            thresholdIP.add(key);
            Timer timer = new Timer();
            // Scheduling a TimerTask to beep every 1 second
            timer.schedule(new Beep(), 0, 1000);
            // Show the dialog box with the alert message
            JOptionPane.showMessageDialog(null, "Alert! "+key+" Hit Threshold!","Alert",JOptionPane.WARNING_MESSAGE);
            // Cancel the beeping task when the button is pressed
            timer.cancel();
        }
    }

    private static void getTopTalkers() {
        setTalkersNumbers();
        JFreeChart talkerChart = ChartFactory.createPieChart("Top Talkers",talkersDataset,true,true,false);
        PiePlot pie= (PiePlot) talkerChart.getPlot();
        System.out.println("before frame");
        ChartFrame talkerPieChart = new ChartFrame("Top Talkers",talkerChart);
        talkerPieChart.setBounds(100,100,500,500);
        talkerPieChart.setVisible(true);
        System.out.println("true frame");
        javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTalkersNumbers();
                talkerPieChart.repaint();;
            }
        });
        timer.setRepeats(true);
        timer.start();
        talkerPieChart.addWindowListener(new WindowAdapter() {
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
        javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
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
