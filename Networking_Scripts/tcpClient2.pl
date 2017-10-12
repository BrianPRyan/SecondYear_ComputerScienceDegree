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
my $name;
# reads the question from the server.
$data = <$socket>;
# prints the data recived from the server
print "$data\n";

# Sends back the name.
$data = <STDIN>;
$name=$data;
chomp($name);
$socket->send($data);

#Checks the name to determine what the client/server realationship should be
if($name eq "Randal")
{
	#recieves the message back from the server and prints it
	$data = <$socket>;
	print "$data\n";
	$socket->close();
}

elsif($name eq "randal")
{
	#recieves the message back from the server and prints it
	$data = <$socket>;
	print "$data\n";
	$socket->close();
}

else
{
	#This is here for the secret word part questioning
	$data = <$socket>;
	print "$data\n";
		
	$data = <$socket>;
	chomp($data);
	print "$data\n";

	#sends back the secret word guess
	$data = <STDIN>;
	$socket->send($data);	

	$data = <$socket>;
	chomp($data);
	print "$data\n";
	$i=0;
	
	#this keeps the secret questioning looping until the correct answer is found
	while($i == 0)
	{
		if($data ne "yes")#this part is the loop for the secret question
		{
			$data = <STDIN>;
			$socket->send($data);
			$data = <$socket>;
			chomp($data);
			print "$data\n";
		}
		else		#this part exits the loop when the answer is correct
		{
			$i = $i+1;
		}
	}
	#prints out that you got the correct answer
	print "Correct word!";

}
$socket->close(); #closes the connection to the server
