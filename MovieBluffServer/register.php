<?php
require 'DBInfo.php';
header("Access-Control-Allow-Origin: * ");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

 if(isset($_POST['first_name'])){
	$firstName = $_POST['first_name'];

}else{
	$firstName='';
}
if(isset($_POST['email'])){
$email = $_POST['email'];
}else{
	$email='';
}
if(isset($_POST['password'])){
	$password = $_POST['password'];

}else{
	$password='';
}
if(isset($_POST['picture_path'])){
	$picturePath = $_POST['picture_path'];
}else{
	$picturePath='';
}


// $password_hash = password_hash($password, PASSWORD_DEFAULT,array('cost' => 10 ));
$CheckSQLe = "SELECT * FROM login WHERE email='$email'";
$CheckSQLn = "SELECT * FROM login WHERE first_name='$firstName'";
 
 $checkEmail = mysqli_fetch_array(mysqli_query($connect,$CheckSQLe));
 $checkUserName=mysqli_fetch_array(mysqli_query($connect,$CheckSQLn));
 
 if(isset($checkEmail)){

 echo 'Email Already Exist';

 }else if($checkUserName){
 	 echo 'Username Already Exist';

 }else if($email && $password && $firstName && $picturePath){

	$query = " insert into login (first_name,email,password,picture_path) values ('{$firstName}', '{$email}', '{$password}', '{$picturePath}')";

	$result = mysqli_query($connect,$query);

	if(!$result){
		$output = "{'msg' : 'fail'}";
	}else{
			$output = "{'msg' : 'user is added'}";

	}
	print($output);
}else{
	 echo 'One of the fields is empty';

}

mysqli_close($connect);

// if(isset($_GET['first_name'])){
// 	$firstName = $_GET['first_name'];

// }
// if(isset($_GET['email'])){
// $email = $_GET['email'];
// }
// if(isset($_GET['password'])){
// 	$password = $_GET['password'];

// }
// if(isset($_GET['picture_path'])){
// 	$picturePath = $_GET['picture_path'];
// }
// $databaseService = new DatabaseConnection();
// $conn = $databaseService->getConnection();

// // $data = json_decode(file_get_contents("php://input"));



// $table_name = 'login';

// $query = 
// // "INSERT INTO " . $table_name . "
// //                 SET first_name = '" .$_GET['first_name'] . "',
// //                     email = '".$_GET['email']."',
// //                     password = '".$_GET['password']."',
// //                     picture_path = '".$_GET['picture_path']."'";


//                     "INSERT INTO " . $table_name . "
//                 SET first_name = :firstname,
//                     email = :email,
//                     password = :password,
//                     picture_path = :picturepath";

// $stmt = $conn->prepare($query);


// $stmt->bindParam(':firstname', $firstName);
// $stmt->bindParam(':picturepath', $picturePath);
// $stmt->bindParam(':email', $email);

// $password_hash = password_hash($password, PASSWORD_BCRYPT);
// $stmt->bindParam(':password', $password_hash);



// if($stmt->execute()){

//     http_response_code(200);
//     echo json_encode(array("message" => "User was successfully registered.",password_hash($password, PASSWORD_BCRYPT)));
// }
// else{
//     http_response_code(400);

//     echo json_encode(array("message" => "Unable to register the user."));
// }
?>

