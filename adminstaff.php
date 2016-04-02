<?php session_start(); 
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
	$loginpw;

	if(isset($_REQUEST["logout"]))
	{
		unset($_SESSION["isAdmin"]);
		session_unset();
		session_destroy();
		session_start();
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
	
	#var_dump($_REQUEST["login"]);
	#var_dump($_REQUEST["pwLogin"]);
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
	<form onsubmit=true action="adminstaff.php" method="get"
	name="f2" id="f2">
	Username to Pull: <input type="text" name="userToPull" id="userToPull" value=""><br>
	<input type="hidden" name="pullForm" id="pullForm" value="true">
	<input type="submit" value="Pull User"><br>
	</form>

	<br><br>
	<form onsubmit=true action="adminstaff.php" method="get"
	name="f2" id="f2">
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
