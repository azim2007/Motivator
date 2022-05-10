<?php
	header('Content-Type: application/json;charset=utf-8');  
	$con = mysqli_connect("motivator2.ru","root","");
	$database = mysqli_select_db($con, "motivatordatabase");

	//вход в аккаунт выводит все данные пользователя(кроме пароля) для дальнейшего использования в приложухе если юзер залогинился и 'false' если нет
	$login = $_GET['login'];
	$password = $_GET['password'];

	//проверка есть ли юзер с таким логином
	$result = mysqli_query($con,"SELECT user.Login FROM user
		WHERE user.Login = '". $login ."'");

	$anyLogin = Array();
	while ( $row = mysqli_fetch_array($result) ) {
        $anyLogin[] = $row;
    }
	//если все норм то идет проверка пароля
	if($anyLogin){
		$result = mysqli_query($con,"SELECT user.CryptedPassword FROM user
				WHERE user.Login = '".$login."'");

		$row = mysqli_fetch_array($result);
		if(password_verify($password, $row[0])){// проверка на равенство хешей, данные об алге хеширования передавать не надо, тк они содержатся в "соли" хэша
			$result = mysqli_query($con,"SELECT user.ID, user.Name, user.Login, user.Email FROM user
				WHERE user.Login = '".$login."'");

			$user = Array();
			while ( $row = mysqli_fetch_array($result) ) {
				for($i = 0; $i < 4; $i++){
					$user[$i] = $row[$i];
				}
			}
			
			echo html_entity_decode(json_encode($user, JSON_UNESCAPED_UNICODE));
		}
		else{
			echo 'false';
		}
	}
	else{
		echo 'false';
	}
	mysqli_close($con);
?>