<?php
    header('Content-Type: application/json;charset=utf-8');  
    $con = mysqli_connect("motivator2.ru","root","");
    $database = mysqli_select_db($con, "motivatordatabase");

    //��������� ���� � ������ ����� ����� ���������� true ���� ������ � false ���� � ����� ��� ���� ��� ���� (���������� �� ��������� �����) (������������ ������ ��� ����������, ��� ���������� ���������� ����� ������ ������)(� ���� ��� targetid �� ���� branchId, ��� ��� ����� ���������� ��� ����, ����� ����� ����� ��� ����, ��� ��� �� branchId �� ����� ����� � target)
    $userId = $_GET['userId'];
    $branchId = $_GET['branchId'];

    //�����: ��� �� � ����� ��� ���� ����
    $result = mysqli_query($con,"SELECT branch.ID_Target FROM branch
        WHERE branch.ID = ". $branchId ."");

    $row = mysqli_fetch_array($result);
    $targetId = $row[0];

    $result = mysqli_query($con,"SELECT target.ID FROM usertarget
        LEFT JOIN branch ON usertarget.ID_Branch = branch.ID
        LEFT JOIN target ON branch.ID_Target = target.ID
        WHERE usertarget.ID_User = ". $userId ." AND target.ID = " . $targetId . "");

    $row = mysqli_fetch_array($result);

    //���� ����� ���� � ����� ���, �� �� �� ������
    if($row == null){
        //����� ���� �������(��������) ���� � ���� �����, ���� ������� ��� ������� ����� �����
        $result = mysqli_query($con,"SELECT step.ID FROM step
            WHERE step.ID_Branch = ". $branchId ." AND step.Number = 0");

        $row = mysqli_fetch_array($result);
        $stepId = $row[0];

        //������� ���� �������� � �������
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