#!/usr/bin/perl
#tcpserver.pl

use IO::Socket::INET;

# flush after every write
$| = 1;

my ($socket,$client_socket);
my ($peeraddress,$peerport);

# creating object interface of IO::Socket::INET modules which internally does 
# socket creation, binding and listening at the specified port address.
$socket = new IO::Socket::INET (
LocalHost => '127.0.0.1',
LocalPort => '5000',
Proto => 'tcp',
Listen => 5,
Reuse => 1
) or die "ERROR in Socket Creation : $!\n";

print "SERVER Waiting for client connection on port 5000\n";

while(1)
{
	# waiting for new client connection.
	$client_socket = $socket->accept();

	# get the host and port number of newly connected client.
	$peer_address = $client_socket->peerhost();
	$peer_port = $client_socket->peerport();


	$data = "What is your name?";
	print $client_socket "$data\n";
	$data = <$client_socket>;
	chomp($data);


	if($data eq "Randal")
	{
		$data = "Hello, Randal! How good of you to be here!\n";
		$client_socket ->send($data);
	}
	elsif($data eq "randal")
	{
		$data = "Hello, randal! How good of you to be here!\n";
		$client_socket ->send($data);
	}
	else
	{
		$data = "Hello, $data!\n";
		$client_socket ->send($data);
	}

}

$socket->close();
