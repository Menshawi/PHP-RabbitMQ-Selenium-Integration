<html>
    <head>
        <title>
            Peerialism Test - enqueue job!
        </title>
    </head>
    <body>
    <a href="/index.php?i=newjob">Add new job!</a>
    <br />
    <a href="/index.php?i=checkjobs">Check jobs</a>
    <?php
    include("db.php");
    $i = $_GET["i"];
    switch($i){
	case "newjob":
	    ?>
	    <form method="post" action="/send.php">
		<table>
		    <tr>
			<td>URL: </td>
			<td>
			    <input type="text" name="urlField" />
			</td>
		    </tr>
		    <tr>
			<td>
			    ID:
			</td>
			<td>
			    <input type="text" name="idField" />
			</td>
		    </tr>
		</table>
		<input type="submit" value="submit job" />
	    </form>
	    <?php
	    break;
	case "checkjobs":
	    checkCurrentJobQueue();
	    break;
	case "update":
	    updateJobItem();
	    break;
	default:
	    echo "<br /><br /><div id='testid'>This is a DIV for testing and its id is testid</div>";
	    break;
    }
    
    function updateJobItem(){
	extract($_POST);
	$sql = mysql_query("UPDATE jobs SET result = '$result' WHERE jid = '$jid'");
	if($sql){
	    echo "done";
	}
	else{
	    echo "error";
	}
    }
    
    function checkCurrentJobQueue(){
	?>
	<center>
	    <big><b>Pending Jobs</b></big>
	    <table border="border">
	    <tr>
		<td><b>Job ID</b></td>
		<td><b>URL</b></td>
		<td><b>Element ID</b></td>
		<td><b>Result</b></td>
	    </tr>
		<?php
		$sql = mysql_query("SELECT * FROM jobs WHERE result = '' ORDER BY jid");
		while($line = mysql_fetch_array($sql)){
		    ?>
		    <tr>
			<td><?php echo $line["jid"]; ?></td>
			<td><?php echo $line["url"]; ?></td>
			<td><?php echo $line["id"]; ?></td>
			<td><?php echo $line["result"]; ?></td>
		    </tr>
		    <?php
		}
		?>
	    </table>
	    <br />
	    <br />
	    
	    <big><b>Finished Jobs</b></big>
	    <table border="border">
	    <tr>
		<td><b>Job ID</b></td>
		<td><b>URL</b></td>
		<td><b>Element ID</b></td>
		<td><b>Result</b></td>
	    </tr>
		<?php
		$sql = mysql_query("SELECT * FROM jobs WHERE result != '' ORDER BY jid");
		while($line = mysql_fetch_array($sql)){
		    ?>
		    <tr>
			<td><?php echo $line["jid"]; ?></td>
			<td><?php echo $line["url"]; ?></td>
			<td><?php echo $line["id"]; ?></td>
			<td><?php echo $line["result"]; ?></td>
		    </tr>
		    <?php
		}
		?>
	    </table>
	    <br />
	    <br />
	</center>
	<?php
	
    }
    ?>
	
    </body>
        

</html>