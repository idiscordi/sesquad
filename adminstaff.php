<?php #session_start(); 
	$loginpage = false;
	$pageInfoStr = "Group 10 Admin Functionality";
	$pulledName;
	$pulledPass;
	$infoArr;
	$serverAddr = 'localhost';
	$dbloginname = 'handler';
	$dbloginpw = 'handler';
	$dbtouse = 'squaddb';
	$sqlreturn;
	$retrow;

	function pullUserPass($reqName, $reqpass)
	{
		global $dbloginname, $dbloginpw, $serverAddr, $sqlreturn, $retrow, $dbtouse;
		
		$dbcon = mysqli_connect($serverAddr, $dbloginname, $dbloginpw, $dbtouse);
		if ( !$dbcon )
			die("Could not connect to database server");
		$sqlquery = "SELECT * FROM users WHERE username='admin'";
		$sqlreturn = mysqli_query($dbcon, $sqlquery);
		mysqli_close($dbcon);
		#var_dump($sqlreturn);
		#$checkrows = mysqli_num_rows($sqlreturn);
		#echo "<br>"; echo ($checkrows); echo "<br>";
		if (mysqli_num_rows($sqlreturn) > 0) 
		{
		#table headers drop out of php tags and back in after
		?> <table border="1" style="width:100%"><th>Username</th><th>Password</th>"<?php
    		#display table of information
    			while($retrow = mysqli_fetch_assoc($sqlreturn)) 
			{
        		echo ("<tr><td>" . $retrow["username"] . "</td><td>" . $retrow["password"]. "</td></tr>");
    			}
		echo("</table>");
		}
	}

	function pullUserName()
	{

	}

	function pullInfoByName($reqname)
	{
global $dbloginname, $dbloginpw, $serverAddr, $sqlreturn, $retrow, $dbtouse;
		
		$dbcon = mysqli_connect($serverAddr, $dbloginname, $dbloginpw, $dbtouse);
		if ( !$dbcon )
			die("Could not connect to database server");
		$sqlquery = "SELECT * FROM users WHERE username='" . $reqname . "'";
		$sqlreturn = mysqli_query($dbcon, $sqlquery);
		mysqli_close($dbcon);
		#var_dump($sqlreturn);
		#$checkrows = mysqli_num_rows($sqlreturn);
		#echo "<br>"; echo ($checkrows); echo "<br>";
		if (mysqli_num_rows($sqlreturn) > 0) 
		{
		#table headers drop out of php tags and back in after
		?> <table border="1" style="width:100%"><th>Username</th><th>Password</th>
		   <th>Email</th><th>Wins</th><th>Total Games</th><th>Ranking</th><th>Online</th>
		<?php
    		#display table of information
    			while($retrow = mysqli_fetch_assoc($sqlreturn)) 
			{
        		echo ("<tr>
                             <td>" . $retrow["username"] . "</td><td>" . $retrow["password"]. "</td>
			     <td>" . $retrow["email"] . "</td><td>" . $retrow["wins"] . "</td>
			     <td>" . $retrow["totalgames"] . "</td><td>" . $retrow["ranking"] . "</td>
			     <td>" . $retrow["online"] . "</td>" . 
                             "</tr>");
    			}
		echo("</table>");
		}
	}

	#if isset($_SESSION['uName'])
	#{
	#	$loginpage = true;
	#}
	pullInfoByName('admin');
?>
