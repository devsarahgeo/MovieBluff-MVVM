<?php
class DatabaseConnection{
	private $host = "127.0.0.1";
	private $user = "root";
	private $password = "";
	private $database = "moviebluff";
	private $connection;
	 public function getConnection(){

	    $this->connection = null;

	    try{
	        $this->connection = new PDO("mysql:host=" . $this->host . ";dbname=" . $this->database, $this->user, $this->password);
	    }catch(PDOException $exception){
	        echo "Connection failed: " . $exception->getMessage();
	    }

	    return $this->connection;
	}

	
}


?>