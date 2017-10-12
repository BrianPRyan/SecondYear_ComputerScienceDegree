#!/usr/bin/perl
#tcpserver.pl

use IO::Socket::INET;

# flush after every write
$| = 1;
@words=qw(camel llama alpaca);
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

#sends the first question to the client and recives the response
$data = "What is your name?";
print $client_socket "$data\n";
$data = <$client_socket>;
chomp($data);

#Checks the name to determine what the client/server realationship should be
if($data eq "Randal")	#Checks for Randal with a capital R
{
	$data = "Hello, Randal! How good of you to be here!\n";
	$client_socket ->send($data);
}

elsif($data eq "randal")#Checks for Randal with a lower case r
{
	$data = "Hello, randal! How good of you to be here!\n";
	$client_socket ->send($data);
}

else#Checks for any other name and asks for the secret word
{
	$data = "Hello, $data!\n";
	$client_socket ->send($data);

	$data = "What is the Secret Word?\n";
	$client_socket ->send($data);
	$data = <$client_socket>;
	chomp($data);

	#sets up the variables for the loop
	$i=0;
	$correct = "maybe";
	chomp($correct);

	#Loops around asking the secret word and exits loop when it is guessed
	while($correct eq "maybe")
	{
	#this part of the while loop is for when the right word is submitted
		if($words[$i] eq $data) 
		{
			print "The secret word has been entered correctly";
			$data = "yes";
			$client_socket ->send($data);
			$correct = "yes";
			chomp($correct);
			$client_socket->close();
			
		}
	#this part of the while loop is for changing the secret word to the next one 		when guessed wrong
		elsif($i<2)
		{
			$i = $i+1;
		}
	#this part of the while loop is for when to wrong word is submitted.
	#and will ask the user to submit another guess.
		else
		{
			$data= "Wrong, try again. What is the secret word?\n";
			$client_socket ->send($data);
			$data = <$client_socket>;
			chomp($data);
			$i=0;
		}
	}
}
}

