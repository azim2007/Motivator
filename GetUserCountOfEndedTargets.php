<?php
    header('Content-Type: application/json;charset=utf-8');  
    $con = mysqli_connect("motivator2.ru","root","");
    $database = mysqli_select_db($con, "motivatordatabase");

    //���������� ���������� ����������� ����� �����
    $userId = $_GET['userId'];

    //����� ����������� ����� �����
    $result = mysqli_query($con,"SELECT target.ID FROM usertarget
        LEFT JOIN branch ON usertarget.ID_Branch = branch.ID
        LEFT JOIN target ON branch.ID_Target = target.ID
        WHERE usertarget.ID_User = ". $userId ." AND usertarget.IsEnded = 1");

    $columnValues = Array();

    while ( $row = mysqli_fetch_array($result) ) {
        for($i = 0; $i < 1; $i++){
            $columnValues[] = $row[$i];
        }
    }
    echo count($columnValues);
    
    mysqli_close($con);
    
?>