<?php

include_once 'DBInfo.php';
require "./vendor/autoload.php";
// use \Firebase\JWT\JWT;

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

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

if($email && $password){

	$ph=password_hash($password, PASSWORD_DEFAULT);

	$query = "select * from login where email='{$email}' and password='{$password}'";


	$result = mysqli_query($connect,$query);

	if(!$result){
		die('Error cannot run query');
	}
	$userInfo = array();
	while($row=mysqli_fetch_assoc($result)){
		$userInfo[] = $row;
		break;
	}
	// $hash=json_encode($userInfo[0]['password']);
	// $pw=password_verify($password,$ph);
	if($userInfo){
		// print("{'msg':'pass login','info':'". json_encode($userInfo) ."'}");
		 echo json_encode(
            array(
            	"isSuccessful"=>"true",
                "message" => "Successful login.",
                "user"=>$userInfo
               
            ));
	}else{
		print("{'msg':'cannot login'}");
	}
	mysqli_free_result($result);

}else{
		print("{'msg':'Either email or password is not entered'}");

}
mysqli_close($connect);

// if(isset($_POST['password'])){
// $password=$_POST['password'];

// }
// if(isset($_POST['email'])){

// $email = $_POST['email'];
// }





// $databaseService = new DatabaseConnection();
// $conn = $databaseService->getConnection();




// $table_name = 'login';

// $query = "select * from login where email=:email";


// $stmt = $conn->prepare( $query );

// $stmt->bindParam(':email', $email,PDO::PARAM_STR);




// $stmt->execute();

// $num = $stmt->rowCount();


// if($num>0){
//     $row = $stmt->fetch(PDO::FETCH_ASSOC);
//     $id = $row['user_id'];
//     $firstname = $row['first_name'];
//     $picturepath = $row['picture_path'];
//     $password2 = $row['password'];
//     $ph=password_hash($password, PASSWORD_BCRYPT);


//     if(password_verify($password,$ph))
//     {
//         $secret_key = "secret";
//         $issuer_claim = "moviebluff"; // this can be the servername
//         $audience_claim = "THE_AUDIENCE";
//         $issuedat_claim = time(); // issued at
//         $notbefore_claim = $issuedat_claim + 10; //not before in seconds
//         $expire_claim = $issuedat_claim + 60; // expire time in seconds
//         $token = array(
//             "iss" => $issuer_claim,
//             "aud" => $audience_claim,
//             "iat" => $issuedat_claim,
//             "nbf" => $notbefore_claim,
//             "exp" => $expire_claim,
//             "data" => array(
//                 "user_id" => $id,
//                 "firstname" => $firstname,
//                 "email" => $email,
//                 "picturepath"=>$picturepath
//         ));

//         http_response_code(200);

//         $jwt = JWT::encode($token, $secret_key);
//         echo json_encode(
//             array(
//             	"isSuccessful"=>"true",
//                 "message" => "Successful login.",
//                 "user"=>array([ 
//                 "id"=>$id,
//                 "firstname"=>$firstname,
//                 "jwt" => $jwt,
//                 "email" => $email,
//                 "expireAt" => $expire_claim])
               
//             ));

//     }
//     else{

//         http_response_code(401);
//         echo json_encode(array("message" => "Login failed.", "password" => $password));
//     }
// }

?>