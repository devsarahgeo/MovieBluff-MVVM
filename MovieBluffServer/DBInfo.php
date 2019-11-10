<?php

$host = "127.0.0.1";
$user = "root";
$password = "";
$database = "moviebluff";

$connect = mysqli_connect($host,$user,$password,$database);
if(mysqli_connect_errno()){
	die("cannot connect to database".mysqli_connect_error());
}


// class DatabaseConnection{
// 	private $host = "127.0.0.1";
// 	private $user = "root";
// 	private $password = "";
// 	private $database = "moviebluff";
// 	private $connection;
// 	 public function getConnection(){

// 	    $this->connection = null;

// 	    try{
// 	        $this->connection = new PDO("mysql:host=" . $this->host . ";dbname=" . $this->database, $this->user, $this->password);
// 	    }catch(PDOException $exception){
// 	        echo "Connection failed: " . $exception->getMessage();
// 	    }

// 	    return $this->connection;
// 	}
// }


?>