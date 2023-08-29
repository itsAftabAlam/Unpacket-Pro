# Unpacket Pro
A Powerful Packet Sniffing and Network Analysis Tool

## Features & Usage
- **Packet Capture** : Using Unpacket Pro you can capture packets roaming in the network. Choose the appropriate NIC and click on start to start capturing the packets. To stop the packet capture, click on stop. You can click on a particular row of the packet capture table to get detailed information about the corresponding packet.
- **Save Packet Capture Information** : You can save the packet capture information. To do so, first stop the packet capture. Now, click on save. Make sure to end your file name with .pcap extension. The saved file can be opened by any tool or software that is capable of understanding .pcap file.
- **Capture Filter** : A capture filter is applied before we start capturing packets and is used to define which packets are to be captured and which are to be discarded. This is useful when we want to capture only specific traffic to reduce the size of the capture file or to avoid capturing sensitive information. To perform capture filter, select the appropriate filter and input the expected value of the applied filter before starting packet capture.
- **Real Time Network Analysis** : This is a powerful feature to analyse the network in real time.
  -  Network Throughput : This generates real time network throughput plot.
  -  Top Talkers : This generates a real time pie plot that demonstrates activeness of each of the party involved in the network.
  -  Protocol Distribution : This generates a real time pie plot that demonstrates the protocol distribution of the network.
- **Set Threshold** - This feature sets a threshold value entered by the user. An alert is given to the user when a party involved in communication within the network hits the threshold.

## Installation
### User
To use the project, follow these steps:
1. Download the jar file or the appropriate executable for your machine from the releases page.
2. Run the downloaded jar or executable file.

If you have downloaded jar, make sure your machine have the latest JDK (JDK 8 and onwards) installed.
That's it! After downloading the executable or the jar file, you can directly run it to use the application without any additional installation steps.

Note: As of now only jar file is available. Executable file for the application will be coming soon.

### Developer
Please refer contributing. Follow step 1 to step 7 mentioned in setup.

## Contributing
If you want to contribute to the project, follow these steps:
### Setup 
1. Fork the repository on GitHub by clicking the "Fork" button at the top-right corner of the repository page.
2. Clone the forked repository to your local machine using the following command:
  ```bash
  git clone https://github.com/your-username/Unpacket-Pro.git
  ```
3. Install JDK (Java Development Kit) version 8 and onwards if you haven't already. You can download it from the official Oracle website or use a package manager for your operating system.
4. Install the latest Apache Maven if you haven't already. You can download it from the official Apache Maven website or use a package manager for your operating system.

5. Navigate to the project directory:
  ```bash
  cd Unpacket-Pro
  ```
6. Build the project using Maven:
  ```bash
  mvn clean install
  ```
7. Run the application using the following command:
  ```bash
  java -jar target/executable-jar-file.jar
  ```

### Development
1. Create a new branch for your feature or bug fix.
  ```bash
   git checkout -b branch-name
  ```
2. Make your changes or additions to the codebase.
3. Write clear, concise, and meaningful commit messages.
4. Push your changes to your forked repository and submit a pull request from your branch to the main branch of the original repository.

## Feedback
If you encounter any issues with the project or find a bug, or have suggestions for new features and improvements in existing project then go to [Issues](https://github.com/itsAftabAlam/Unpacket-Pro/issues).

## License
[GPL-3.0 License](LICENSE)

## Authors
[@itsAftabAlam](https://github.com/itsAftabAlam)




