# SimpleNetworkMonitor

## Overview

A simple network monitor is an application with a GUI that includes three graphic elements:

1. The "Current Type of Traffic" is a pie chart showing the types of services / protocols collected in 5 seconds.

2. The "Brief network info"" is an abstract factory that collects information about the internal ip-address, mask, gateway, external ip-address, DNS server ip-address from the network interface. Also, the field displays the maximum incoming / outgoing speed on the interface.

3. The "Current speed rate" is a linear chart showing the current speed on the interface in 1 second in real time. The update interval is 60 seconds.

![GitHub Logo](/images/Selection_006.png)

At the core of the application are [jNetPcap](https://github.com/ruedigergad/clj-net-pcap/tree/master/jnetpcap), which include native library helps capture packet from physical/logical interface and [JFreeChart](https://github.com/jfree/jfreechart) that helps draw pie chart and linear-real-time chart.

## Download

[GNU/Linux 32 bit](https://github.com/timmyventura/SimpleNetworkMonitor/raw/master/dist/simple_network_monitor_linux32.tar.gz)

[GNU/Linux 64 bit](https://github.com/timmyventura/SimpleNetworkMonitor/raw/master/dist/simple_network_monitor_linux64.tar.gz)

[Windows 32 bit](https://github.com/timmyventura/SimpleNetworkMonitor/raw/master/dist/simple_network_monitor_win32.zip)

[Windows 64 bit](https://github.com/timmyventura/SimpleNetworkMonitor/raw/master/dist/simple_network_monitor_win64.zip)


### Note

**The project is designed for the practical apply of theoretical knowledges of the Java language. Main using technology is Java SE**

**The project does not pretend to be unique.**

**For all questions please contact timmyventura@yandex.ru**
