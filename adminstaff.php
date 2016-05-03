<?php session_start(); 
	$pageInfoStr = "Group 10 Admin Functionality";
	$pulledName;
	$pulledPass;
	$infoArr; #unused
	$serverAddr = 'localhost';
	$dbloginname = 'handler';
	$dbloginpw = 'handler';
	$dbtouse = 'squaddb';
	$sqlreturn;
	$retrow;
	$loginpw;
?>
<body bgcolor=#95B9C7>
<p id=imgpara style="textalign:center"><center><img src="logotest.png" heigh=150 width = 600></img></center></p><br><br>
<?php
	
	if(isset($_REQUEST["logout"]) && $_REQUEST["logout"] == true)
	{
		#unset($_SESSION["isAdmin"]);
		session_unset();
		session_destroy();
		session_start();
		unset($_REQUEST["logout"]);
		echo "<br>Account has been logged out due to user's request or elevated action performed<br><br>";
	}

	#dirty work of pulling admin's pw from db to validate against entry
	function pullAdminPass()
	{
		global $dbloginname, $dbloginpw, $serverAddr, $sqlreturn, $retrow, $dbtouse, $pulledPass;
		
		$dbcon = mysqli_connect($serverAddr, $dbloginname, $dbloginpw, $dbtouse);
		if ( !$dbcon )
			die("Could not connect to database server");
		$sqlquery = "SELECT password FROM users WHERE username='admin'";
		$sqlreturn = mysqli_query($dbcon, $sqlquery);
		mysqli_close($dbcon);
		#var_dump($sqlreturn);
		#$checkrows = mysqli_num_rows($sqlreturn);
		#echo "<br>"; echo ($checkrows); echo "<br>";
		if (mysqli_num_rows($sqlreturn) > 0) 
		{
		$retrow = mysqli_fetch_assoc($sqlreturn);
		$pulledPass = $retrow["password"];
		#echo($pulledPass);
		}
	}

	#pulls all user info when requested and dumps into html table
	function pullInfoByName()
	{
		global $dbloginname, $dbloginpw, $serverAddr, $sqlreturn, $retrow, $dbtouse;
		
		$dbcon = mysqli_connect($serverAddr, $dbloginname, $dbloginpw, $dbtouse);
		if ( !$dbcon )
			die("Could not connect to database server");
		$sqlquery = "SELECT * FROM users WHERE username='" . $_SESSION["uNamePull"] . "'";
		$sqlreturn = mysqli_query($dbcon, $sqlquery);
		mysqli_close($dbcon);
		#var_dump($sqlreturn);
		#$checkrows = mysqli_num_rows($sqlreturn);
		#echo "<br>"; echo ($checkrows); echo "<br>";
		if (mysqli_num_rows($sqlreturn) > 0) 
		{
		#table headers drop out of php tags and back in after
		?> <table border="1" style="width:100%"><th>Username</th>
		   <th>Email</th><th>Wins</th><th>Total Games</th><th>Ranking</th><th>Online</th>
		<?php
    		#display table of information
    			while($retrow = mysqli_fetch_assoc($sqlreturn)) 
			{
        		echo ("<tr>
                             <td>" . $retrow["username"] . "</td>
			     <td>" . $retrow["email"] . "</td><td>" . $retrow["wins"] . "</td>
			     <td>" . $retrow["totalgames"] . "</td><td>" . $retrow["ranking"] . "</td>
			     <td>" . $retrow["online"] . "</td>" . 
                             "</tr>");
    			}
		echo("</table>");
		} else { echo "<br>NO RECORD FOUND<br><br>";}
	}

	#call to validate admin password, use in login form submit
	function validateAdmin()
	{
		#echo "<br> running validate admin <br>";
		global $pulledPass, $loginpw;
		pullAdminPass();
		#echo("comparing " . $pulledPass . " with " . $_REQUEST["pwLogin"] . "<br><br>");
		if ($pulledPass == $loginpw)
		{
			#echo($_REQUEST["pwLogin"]);
			$_SESSION["isAdmin"] = true;
			unset($_REQUEST["pwLogin"]);
			return true;
		}
		else
		{
			return false;
		}
	}

	#sets session var for pulling user
	function setPullName()
	{
		$_SESSION["uNamePull"] = $_REQUEST["userToPull"];
	}

	function updateUser()
	{
		global $dbloginname, $dbloginpw, $serverAddr, $sqlreturn, $dbtouse;
	
		$editName = $_REQUEST["editUser"];
		
		$sqlpw; $sqlemail; $sqlwins; $sqlgames; $sqlrank;

		#if they are set and non-blank, push them to queries
		if(isset($_REQUEST["editPW"]) && $_REQUEST["editPW"] != "")
		{
		$sqlpw = "UPDATE users SET password='" . $_REQUEST["editPW"] ."' WHERE username ='". $editName . "'";
		}
		if(isset($_REQUEST["editEmail"]) && $_REQUEST["editEmail"] != "")
		{
		$sqlemail = "UPDATE users SET email='". $_REQUEST["editEmail"] ."' WHERE username ='". $editName . "'";
		}
		if(isset($_REQUEST["editWins"]) && $_REQUEST["editWins"] != "")
		{
		$sqlwins = "UPDATE users SET wins='". $_REQUEST["editWins"] ."' WHERE username ='". $editName . "'"; 	
		}
		if(isset($_REQUEST["editTotalGames"]) && $_REQUEST["editTotalGames"] != "")
		{
		$sqlgames = "UPDATE users SET totalgames='". $_REQUEST["editTotalGames"] ."' WHERE username ='". $editName . "'";
		}
		if(isset($_REQUEST["editRanking"]) && $_REQUEST["editRanking"] != "")
		{
		$sqlrank = "UPDATE users SET ranking='". $_REQUEST["editRanking"] ."' WHERE username ='". $editName . "'";
		}
		
#var_dump($sqlpw); echo "<br>";
#var_dump($sqlemail); echo "<br>";
#var_dump($sqlwins); echo "<br>";
#var_dump($sqlgames); echo "<br>";
#var_dump($sqlrank); echo "<br>";
		
		$dbcon = mysqli_connect($serverAddr, $dbloginname, $dbloginpw, $dbtouse);

		#if the queries were built, update row
		#$sqlpw; $sqlemail; $sqlwins; $sqlgames; $sqlrank;
		if ( !$dbcon )
			die("Could not connect to database server");
		if(isset($sqlpw))
			{if(!mysqli_query($dbcon, $sqlpw))
				{echo "<br><br>Failed to execute " . $sqlpw . "<br><br>";}}
		if(isset($sqlemail))
			{if(!mysqli_query($dbcon, $sqlemail))
				{echo "<br><br>Failed to execute " . $sqlemail . "<br><br>";}}
		if(isset($sqlwins))
			{if(!mysqli_query($dbcon, $sqlwins))
				{echo "<br><br>Failed to execute " . $sqlwins . "<br><br>";}}
		if(isset($sqlgames))
			{if(!mysqli_query($dbcon, $sqlgames))
				{echo "<br><br>Failed to execute " . $sqlgames . "<br><br>";}}
		if(isset($sqlrank))
			{if(!mysqli_query($dbcon, $sqlrank))
				{echo "<br><br>Failed to execute " . $sqlrank . "<br><br>";}}
		
		mysqli_close($dbcon);
		#echo("Account has been logged out after performing elevated action.<br>");
	}
	
	
	if(isset($_REQUEST["login"]))
	{
		global $loginpw;
		$loginpw = $_REQUEST["pwLogin"];
		#echo("loginpw is " . $loginpw . "<br>");
		validateAdmin();
		unset($_REQUEST["login"]);
		unset($_REQUEST["pwLogin"]);
	}

	if(isset($_REQUEST["pullForm"]))
	{
		setPullName();
		unset($_REQUEST["pullForm"]);
	}

	#var_dump($_SESSION["isAdmin"]);

	if(isset($_REQUEST["updateForm"]))
	{
		#echo "update usr<br>";
		updateUser();
		unset($_REQUEST["updateForm"]);
	}

	#var_dump($_SESSION["isAdmin"]);

?> <title> <?php echo($pageInfoStr . " </title>"); ?>

<?php
	if(!isset($_SESSION["isAdmin"]))
	{ ?>
	<h>Enter Administrator Password</h>
	<form onsubmit=true action="adminstaff.php" method="post"
	name="f1" id="f1">
	Password: <input type="password" name="pwLogin" id="pwLogin" value=""><br>
	<input type="hidden" name="login" id="login" value="true">
	<input type="submit" value="Login"><br>
	</form>
<?php   } 
	else if(isset($_SESSION["uNamePull"]))
        { pullInfoByName(); unset($_SESSION["uNamePull"]); echo "<br><br>"; }
	

	echo "<br><br>"; 
	if(isset($_SESSION["isAdmin"]))
	{ ?>

	<h>User Account Administration Interface</h>
	<br>
	
	<br>
	<h>Pull User Data</h><br>
	<form onsubmit=true action="adminstaff.php" method="post"
	name="f2" id="f2">
	Username to Pull: <input type="text" name="userToPull" id="userToPull" value=""><br>
	<input type="hidden" name="pullForm" id="pullForm" value="true">
	<input type="submit" value="Pull User"><br>
	</form>

	
	
	<br>
	<h>Change User Data</h><br>
	<form onsubmit=true action="adminstaff.php" method="post"
	name="f3" id="f3">
	Username to Edit: <input type="text" name="editUser" id="editUser" value=""><br>
	Change password to: <input type="text" name="editPW" id="editPW" value=""><br>
	Change email to: <input type="text" name="editEmail" id="editEmail" value=""><br>
	Change wins to: <input type="text" name="editWins" id="editWins" value=""><br>
	Change total games to: <input type="text" name="editTotalGames" id="editTotalGames" value=""><br>
	Change ranking to: <input type="text" name="editRanking" id="editRanking" value=""><br>
	<input type="hidden" name="updateForm" id="updateForm" value="true">
	<input type="submit" value="Update User"><br>

	<br>
	<h>Logout When Finished</h><br>
	<form onsubmit=true action="adminstaff.php" method="get"
	name="f4" id="f4">
	<input type="hidden" name="logout" id="logout" value="true">
	<input type="submit" value="Logout"><br>
	</form>

	<?php } ?> 


<?php
	#testing stuff down here
	#pullInfoByName('admin');
	#echo "<br><br><br>";
	#$testvar = validateAdmin('admin');
	#echo(var_dump($testvar));

	#temporary cleanup stuff while working
	
?>
