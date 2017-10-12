#!/usr/bin/perl
#tcpclient.pl

use IO::Socket::INET;

# flush after every write
$| = 1;

my ($socket,$client_socket);

# creating object interface of IO::Socket::INET modules which internally creates 
# socket, binds and connects to the TCP server running on the specific port.
$socket = new IO::Socket::INET (
PeerHost => '127.0.0.1',
PeerPort => '5000',
Proto => 'tcp',
) or die "ERROR in Socket Creation : $!\n";

print "TCP Connection Success.\n";

# read the socket data sent by server and print.
$data = <$socket>;
print "$data\n";

#Reads in the name from the keyboard
$data = <STDIN>;


#sends the name to the server
$socket->send($data);

# read the socket data sent by server and print.
$data = <$socket>;
print "$data\n";

#closes the clients connection to the server
$socket->close();
