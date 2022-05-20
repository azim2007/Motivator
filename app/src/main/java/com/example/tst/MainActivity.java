package com.example.tst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Pusher pusher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pusher = new Pusher();

        /*PushTargetsInDatabase();
        PushTargetsInDatabase1();
        PushTargetsInDatabase2();
        PushTargetsInDatabase3();*/
    }

    public void SignInAct(View v){
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    public void SignUpAct(View v){
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        SignUpActivity.countOfUsers = pusher.getCountOfUsers();
        startActivity(intent);
    }

    private void PushTargetsInDatabase(){
        List<List<Step>> stepsInTarget = new ArrayList<>();
        List<Step> stepsInBranch = new ArrayList<>();
        stepsInBranch.add(new Step("учите информатику и математику",
                "учите информатику и математику. Вы можете делать это самостоятельно, или с репетитором",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));
        stepsInBranch.add(new Step("готовьтесь к егэ",
                "Вы можете готовиться к егэ самостоятельно, или с репетитором. Так же стоит прорешать варианты прошлых лет и пообщаться с людьми, которые уже сдавали егэ",
                "http://sdamgia.ru", "https://www.youtube.com/watch?v=WMyW6LMkVt4&ab_channel=TutorOnline-%D1%83%D1%80%D0%BE%D0%BA%D0%B8%D0%B4%D0%BB%D1%8F%D1%88%D0%BA%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA%D0%BE%D0%B2", "https://foxford.ru/levels/ege"));
        stepsInBranch.add(new Step("сдайте егэ",
                "Что ж... хорошо, соберитесь, придите на егэ в день экзамена и СДАЙТЕ ЕГЭ",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));
        stepsInBranch.add(new Step("выберите вуз",
                "на основе результатов экзамена выберите себе подходящий вуз",
                "http://www.green-willow.ru/item/669-android-select-activity", "https://www.w3schools.com/java/ref_string_contains.asp"));
        stepsInTarget.add(stepsInBranch);
        List<Step> stepsInBranch1 = new ArrayList<>();
        stepsInBranch1.add(new Step("готовьтесь к олимпиадам",
                "Вы можете готовиться к олимпиадам по информатике и математике самостоятельно, или с репетитором, олимпиады - это очень важно ведь благодаря ним можно поступить в вуз.",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));
        stepsInBranch1.add(new Step("напишите школьный этап",
                "Школьный этап - это первый этап олимпиады. Написав его вы сможете пройти на муниципал и т. д. Вам стоит прорешать варианты прошлых лет.",
                "http://sdamgia.ru", "https://olympiads.ru", "https://foxford.ru/levels/ege"));
        stepsInBranch1.add(new Step("напишите муниципал",
                "муниципал - это второй этап. На подготовку к нему дается неделя, вы можете ее потратить для более глубокого изучения темы, например по ссылкам.",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));
        stepsInBranch1.add(new Step("напишите регион",
                "регион - это третий этап. На подготовку к нему дается две недели, вы можете ее потратить для более глубокого изучения темы, например по ссылкам. Так же стоит пообщаться с людьми уже писавшими регион.",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));
        stepsInBranch1.add(new Step("выиграйте всерос",
                "Задача сложная, но ничего простого в этом мире не бывает, все таки не зря вы готовились.",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));
        stepsInBranch1.add(new Step("напишите егэ",
                "Если вы выиграли всерос, то егэ достаточно написать на 75 баллов для подтверждения ваших способностей.",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));
        stepsInBranch1.add(new Step("выберите вуз",
                "выберите себе подходящий вуз",
                "http://www.green-willow.ru/item/669-android-select-activity", "https://www.w3schools.com/java/ref_string_contains.asp"));
        stepsInTarget.add(stepsInBranch1);
        List<Step> stepsInBranch2 = new ArrayList<>();
        stepsInBranch2.add(new Step("работайте целыми днями",
                "Вы можете работать на различных подработках для школьников. Это надо для накопления стартового капитала",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));
        stepsInBranch2.add(new Step("поступите на платной основе",
                "Надеюсь, вы накопили достаточное количество денег.",
                ""));
        stepsInTarget.add(stepsInBranch2);
        Target target = new Target("Как поступить в IT вуз", stepsInTarget);
        pusher.PushTarget(target);
    }
    private void PushTargetsInDatabase1(){
        List<List<Step>> stepsInTarget = new ArrayList<>();
        List<Step> stepsInBranch = new ArrayList<>();
        stepsInBranch.add(new Step("найдите себе тренера",
                "Вы можете найти себе тренера на авито",
                "http://avito.ru"));
        stepsInBranch.add(new Step("ежедневно играйте",
                "Помимо тренировок с тренером, вам необходимо самостоятельно набивать скилл в игре",
                "http://sdamgia.ru", "https://www.youtube.com/watch?v=WMyW6LMkVt4&ab_channel=TutorOnline-%D1%83%D1%80%D0%BE%D0%BA%D0%B8%D0%B4%D0%BB%D1%8F%D1%88%D0%BA%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA%D0%BE%D0%B2", "https://foxford.ru/levels/ege"));
        stepsInBranch.add(new Step("Продолжайте",
                "Так держать, скоро ваша цель будет достигнута",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));

        stepsInTarget.add(stepsInBranch);
        List<Step> stepsInBranch1 = new ArrayList<>();
        stepsInBranch1.add(new Step("пройдите обучение",
                "Для начала пройдите обучение, оно запускается сразу, при первом запуске доты.",
                ""));
        stepsInBranch1.add(new Step("посмотреть гайды на ютубе",
                "Вам стоит посмотреть гайды на ютубе. Достаточно много блогеров снимают видео по доте",
                "", "https://www.youtube.com/watch?v=dpE5rhimlLE&ab_channel=GoodWINLive"));
        stepsInBranch1.add(new Step("пообщайтесь с более опытными игроками",
                "Дружелюбных собеседников вы всегда можете найти в голосовом чате в катке.",
                ""));
        stepsInBranch1.add(new Step("играйте в алл пике",
                "алл пик - это тренировочный режим, там вы можете набивать свой скилл.",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));
        stepsInBranch1.add(new Step("играйте в рейтинг",
                "Играть в рейтинг - это очень важно, так как играя в рейтинг вы можете получить звание в игре. Так же игроки в этом режиме, как правило, более опытные.",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));

        stepsInTarget.add(stepsInBranch1);
        Target target = new Target("Как научиься играть в доту", stepsInTarget);
        pusher.PushTarget(target);
    }
    private void PushTargetsInDatabase2(){
        List<List<Step>> stepsInTarget = new ArrayList<>();
        List<Step> stepsInBranch = new ArrayList<>();
        stepsInBranch.add(new Step("купите гитару",
                "Вы можете купить гитару в музыкальном магазине или на авито, при покупке на авито рекомендуем вам покупать ее со знакомым, разбирающимся в гитарах.",
                "http://avito.ru"));
        stepsInBranch.add(new Step("изучите аккорды и играйте боем",
                "Изучите легкие аккорды для новичков и играйте любимые песни так называемым боем, техникой игры, при которой вы высекаете звук из всех струн, проводя по ним пальцем. Аккорды к песням вы можете найти в интернете.",
                "http://guitars.ru"));
        stepsInBranch.add(new Step("научитесь ставить баррэ",
                "баррэ - это аккорды, которые ставятся за счет зажимания указательным пальцем всех струн на каком либо ладу и постановкой аккорда оставшимися пальцами. Уметь ставить эти аккорды необходимо, ведь почти все песни играются на них.",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));
        stepsInBranch.add(new Step("играйте на улице",
                "Игра на гитаре на публике помогает вам развить свои сценические способности.",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));
        stepsInBranch.add(new Step("научитесь играть перебором",
                "Перебор - это техника игры, при которой вы должны по очереди щипать струны в определенной циклической последовательности. Перебором играются многие мелодичные песни.",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));
        stepsInBranch.add(new Step("практикуйтесь",
                "Практика в любом деле - это очень важно, для практики на гитаре достаточно часа в день.",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));
        stepsInBranch.add(new Step("подбирайте аккорды к песням",
                "Умение подбирать аккорды к песням - это очень важно, так как это развивает ваш музыкальный слух.",
                "http://math.ru", "https://www.youtube.com/watch?v=__OMnFR-wZU&ab_channel=BenO%27Brien"));

        stepsInTarget.add(stepsInBranch);
        List<Step> stepsInBranch1 = new ArrayList<>();
        stepsInBranch1.add(new Step("поступите в музыкалку",
                "Поступите в музыкальную школу и ходите туда каждый день",
                ""));
        stepsInBranch1.add(new Step("участвуйте в концертах",
                "В музыкалке у вас по любому будут выступления, не отлынивайте, участвуйте в них",
                "https://www.youtube.com/watch?v=dpE5rhimlLE&ab_channel=GoodWINLive"));
        stepsInBranch1.add(new Step("закончите музыкалку",
                "Ну вот и все музыкалка окончена",
                ""));

        stepsInTarget.add(stepsInBranch1);
        Target target = new Target("Как научиься играть на гитаре", stepsInTarget);
        pusher.PushTarget(target);
    }
    private void PushTargetsInDatabase3(){
        List<List<Step>> stepsInTarget = new ArrayList<>();
        Target target = new Target("Как стать backend разработчиком", stepsInTarget);
        pusher.PushTarget(target);
        target = new Target("Как приготовить яичницу", stepsInTarget);
        pusher.PushTarget(target);
        target = new Target("Как вырастить морскую свинку", stepsInTarget);
        pusher.PushTarget(target);
        target = new Target("Как накачаться", stepsInTarget);
        pusher.PushTarget(target);
        target = new Target("Как выучить c++", stepsInTarget);
        pusher.PushTarget(target);
        target = new Target("Как жить", stepsInTarget);
        pusher.PushTarget(target);
    }
}