<?php
require_once __DIR__ . '/vendor/autoload.php';
use PhpAmqpLib\Connection\AMQPConnection;
use PhpAmqpLib\Message\AMQPMessage;

include("db.php");

        
extract($_POST);

if($urlField && $idField){
    $sql = mysql_query("INSERT INTO jobs VALUES('', '$urlField', '$idField', '')");
    if($sql){
        $jid = mysql_insert_id();
    }
    else{
        $jid = "-1";
    }
}

if($jid != "-1"){
    $message_body = array(
    "jid" => $jid,
    "url" => $urlField,
    "id" => $idField
    );
    
    $connection = new AMQPConnection('127.0.0.1', 5672, 'guest', 'guest');
    $channel = $connection->channel();
    
    $channel->queue_declare('peerialism', false, false, false, false);
    
    $msg = new AMQPMessage(json_encode($message_body));
    
    $channel->basic_publish($msg, '', 'peerialism');
    
    
    echo " [x] Sent Job<br />";
    
    $channel->close();
    $connection->close();
}


?>

<a href = "/index.php" >Go Home</a>