<?php
	header('Content-Type: application/json;charset=utf-8');  
	$con = mysqli_connect("motivator2.ru","root","");
	$database = mysqli_select_db($con, "motivatordatabase");
	//регистрация юзера в бд скрипт ворзвращает список ошибок связанных с некорректностью данных введенных пользователем (пароль слишком короткий, в логине есть русские буквы и тд)
	$login = $_GET['login'];
	$password = $_GET['password'];
	$email = $_GET['eMail'];
	$name = $_GET['name'];

	//следующая переменная нужна для проверок всяких, все таки не хухры мухры, пользователя регаем)
	$isUserDataCorrect = true;
	//массив ошибок
	$errors = Array();

	//проверка: есть ли юзер с таким же логином и нет ли в логине русских букав(понятно, что приложуха для россиян, но надо все таки соблюдать стандарты)
	$result = mysqli_query($con,"SELECT user.Login FROM user
		WHERE user.Login = '". $login ."'");

	//ну тут если чел с таким же логином есть, anyLogin будет состоять из одного элемента и в if выведется ошибка если в массиве есть какие-либо данные
	$anyLogin = Array();
	while ( $row = mysqli_fetch_array($result) ) {
        $anyLogin[] = $row;
    }

	if($anyLogin){
		for ($i = 0; $i > -1; $i++){
			//генерация другого логина для юзера (он просто предлагаетя в ошибке, а не отправляется в бд)
			$result = mysqli_query($con,"SELECT user.Login FROM user
				WHERE user.Login = '". $login .$i."'");

			$anyLoginGen = Array();
			while ( $row = mysqli_fetch_array($result) ) {
				$anyLoginGen[] = $row;
			}

			if(!$anyLoginGen){
				break;
			}
		}

        $errors[] = 'вы не можете использовать этот логин, тк его использовал кто-то другой. Можете использовать логин: '.$login.$i; //не знаю можно ли так сообщать об ошибке ошибка...
		$isUserDataCorrect = false;
    }

	//есть ли в логине русские буквы
	if(preg_match("/[а-яё]/iu", $login)){
		$errors[] = "логин не может содержать русские буквы!";//ошибка
		$isUserDataCorrect = false;
	}
	//проверка: возможен ли такой EMail
	if(preg_match("/[а-яё]/iu", $email)){
		$errors[] = "емэйл не может содержать русские буквы!";//ошибка
		$isUserDataCorrect = false;
	}
	else if(!filter_var($email, FILTER_VALIDATE_EMAIL)){
		$errors[] = "не очень то похож ваш \"емэйл\" на емэил!";//ошибка
		$isUserDataCorrect = false;
	}
	

	//проверка: все ли норм с паролем
	if(strlen($password) < 8){
		$errors[] = "ваш пароль слишком короткий - ".strlen($password)." символов, а надо минимум 8";//ошибка
		$isUserDataCorrect = false;
	}
	else if(strlen($password) > 22){
		$errors[] = "ваш пароль слишком длинный - ".strlen($password)." символов, а можно максимум 22";//ошибка
		$isUserDataCorrect = false;
	}

	//тут уже идет регистрация пользователя, если все норм
	if($isUserDataCorrect){
		$password = password_hash($password, PASSWORD_BCRYPT); // хеширование пароля, используется апи хеширования, встроенного в пхп, ничего устанавливать не надо (по крайней мере у меня и так взлетело)
		$result = mysqli_query($con,"INSERT INTO user 
			(Login, CryptedPassword, Email, Name)
			VALUES ('".$login."', '".$password."', '".$email."', '".$name."')");

		if($result){
			echo 'ok';
		}
	}
	else{
		echo html_entity_decode(json_encode($errors, JSON_UNESCAPED_UNICODE));
	}

	mysqli_close($con);
?>