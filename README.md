# SNMP-bandwidth-monitoring

School project - subject 13S113RM2 - Computer networks course, december 2020. 

## Description
SNMP Bandwidth Monitor application is used to provide data about every router and every of its interfaces. \
Data that is provided:
- Number of packages 
- Bandwidth – bit/s \

Depending on the time, the data is shown in the form of a chart.

For more details - there is a pdf file in Serbian language (project is version 2).

## Instalation
Using GNS3 open SNMP_setup project and start all routers. \
At your computer in terminal insert following commands: \
sudo ip route add 192.168.10.0/24 via 192.168.122.100 dev virbr0 \
sudo ip route add 192.168.20.0/24 via 192.168.122.100 dev virbr0 \
sudo ip route add 192.168.30.0/24 via 192.168.122.100 dev virbr0 \
sudo ip route add 192.168.12.0/24 via 192.168.122.100 dev virbr0 \
sudo ip route add 192.168.13.0/24 via 192.168.122.100 dev virbr0 \
sudo ip route add 192.168.23.0/24 via 192.168.122.100 dev virbr0 

When the connection between routers is established, launch java application. 

## Usage
From select menu with label "Select router" select IP address of router that you would like to monitor. \
From select menu with label "Select interface" select interface of router that you would like to monitor. \
Charts will be shown after that. 

Default refresh time of charts is 10 seconds.

### Built with
- Java

### Libraries
Libraries are provided in project/Referenced Libraries. \
Used libraries:
- iReasoning SNMP API
- xchart-3.6.6

#### Author
Aleksandra Bogicevic - @alebogi