<?php
    header('Content-Type: application/json;charset=utf-8');  
    $con = mysqli_connect("motivator2.ru","root","");
    $database = mysqli_select_db($con, "motivatordatabase");

    //добавл€ет цель в список целей юзера возвращает true если удачно и false если у юзера уже есть эта цель (независимо от выбранной ветки) (»—ѕќЋ№«ќ¬ј“№ “ќЋ№ ќ ƒЋя ƒќЅј¬Ћ≈Ќ»я, ƒЋя ќЅЌќ¬Ћ≈Ќ»я —Ћ≈ƒ”ёў≈√ќ Ё“јѕј ƒ–”√ќ… — –»ѕ“)(в цели нет targetid но есть branchId, так как этого достаточно дл€ того, чтобы знать кака€ это цель, так как по branchId мы можем найти и target)
    $userId = $_GET['userId'];
    $branchId = $_GET['branchId'];

    //поиск: нет ли у юзера уже этой цели
    $result = mysqli_query($con,"SELECT branch.ID_Target FROM branch
        WHERE branch.ID = ". $branchId ."");

    $row = mysqli_fetch_array($result);
    $targetId = $row[0];

    $result = mysqli_query($con,"SELECT target.ID FROM usertarget
        LEFT JOIN branch ON usertarget.ID_Branch = branch.ID
        LEFT JOIN target ON branch.ID_Target = target.ID
        WHERE usertarget.ID_User = ". $userId ." AND target.ID = " . $targetId . "");

    $row = mysqli_fetch_array($result);

    //если такой цели у юзера нет, то мы ее ставим
    if($row == null){
        //поиск айди первого(нулевого) шага в этой ветке, чтоб сделать его текущим шагом юзера
        $result = mysqli_query($con,"SELECT step.ID FROM step
            WHERE step.ID_Branch = ". $branchId ." AND step.Number = 0");

        $row = mysqli_fetch_array($result);
        $stepId = $row[0];

        //вставка всех значений в таблицу
        $result = mysqli_query($con,"INSERT INTO usertarget (ID_User, ID_Branch, ID_CurrentStep, IsEnded) 
            VALUES (".$userId.", ".$branchId.", ". $stepId .", 0)");

        if($result){
            echo 'true';
        }
    }
    else{
        echo 'false';
    }

    mysqli_close($con);
    
?>