-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20220501.46b7525c53
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Май 11 2022 г., 04:55
-- Версия сервера: 10.4.24-MariaDB
-- Версия PHP: 8.1.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `motivatordatabase`
--

-- --------------------------------------------------------

--
-- Структура таблицы `branch`
--

CREATE TABLE `branch` (
  `ID_Target` int(11) NOT NULL,
  `ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `branch`
--

INSERT INTO `branch` (`ID_Target`, `ID`) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(2, 5);

-- --------------------------------------------------------

--
-- Структура таблицы `reference`
--

CREATE TABLE `reference` (
  `ID_Step` int(11) NOT NULL,
  `Reference` varchar(128) NOT NULL,
  `ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `reference`
--

INSERT INTO `reference` (`ID_Step`, `Reference`, `ID`) VALUES
(1, 'http://blabla', 1),
(3, 'http://haha', 2),
(1, 'http://ooohh', 3),
(2, 'http://yeah', 4),
(6, 'https://d.com', 5),
(7, 'http://asdlkjfhdfvfbvfg.com', 6),
(7, 'http://asdfg.com', 7),
(8, 'http://pok.com', 8),
(9, 'http://bnutgf.com', 9),
(10, 'http://n.ru', 10),
(11, 'http://nuadvn.com', 11),
(12, 'http://rir987.com', 12),
(13, 'http://bvnsjdxf91273.com', 13),
(14, 'http://cvmbkshandeujhygfage763.com', 14),
(15, 'http://adflgnb, 9.com', 15),
(16, 'http://dfvcdfkjbvn3we90.com', 16),
(17, 'http://bfndase0.com', 17),
(18, 'http://e9roiflkf.com', 18),
(19, 'http://4983ri.com', 19),
(20, 'http://weriotk.com', 20),
(20, 'http://38eofnsdvb.com', 21);

-- --------------------------------------------------------

--
-- Структура таблицы `step`
--

CREATE TABLE `step` (
  `ID_Branch` int(11) NOT NULL,
  `Description` varchar(512) NOT NULL,
  `Number` int(11) NOT NULL,
  `ID` int(11) NOT NULL,
  `Name` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `step`
--

INSERT INTO `step` (`ID_Branch`, `Description`, `Number`, `ID`, `Name`) VALUES
(1, 'Учитесь на пятерки и все дела', 0, 1, 'учеба'),
(1, 'Сдайте ЕГЭ', 1, 2, 'егэ'),
(1, 'Пересдайте ЕГЭ', 2, 3, 'ааа'),
(1, 'Выберите ВУЗ', 3, 4, 'еще'),
(2, 'Ботайте всякие предметы', 0, 5, 'да'),
(2, 'Еще раз ботайте', 1, 6, 'нет'),
(2, 'Участвуйте в олимпиадах', 2, 7, 'не знаю'),
(2, 'хз еще че придумать', 3, 8, 'что еще'),
(2, 'победите', 4, 9, 'квантыф'),
(2, 'Помойте руки', 5, 10, 'dfas'),
(3, 'читайте книжки там всякие', 0, 11, 'книжки'),
(3, 'читайте еще книжки там всякие', 1, 12, 'книжки2'),
(3, 'хз, проходите курсы', 2, 13, 'курсы'),
(3, 'делайте проекты (ага, вот как я сейчас с этой бд)', 3, 14, 'проекты'),
(4, 'выучите язык с помощью интели сенс', 0, 15, 'боль'),
(4, 'можете поплакать, этот опыт никому не советую', 1, 16, 'плакать'),
(4, 'рано или поздно вы привыкните', 2, 17, 'смириться'),
(5, 'сделайте вид, что и так все знаете', 0, 18, 'сделать вид'),
(5, 'напишите резюме', 1, 19, 'резюме'),
(5, 'конечно же вас не возьмут на работу, поплачьте и идите на стековерфлов писать гневные комменты', 2, 20, 'плачьте');

-- --------------------------------------------------------

--
-- Структура таблицы `target`
--

CREATE TABLE `target` (
  `Name` varchar(64) NOT NULL,
  `ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `target`
--

INSERT INTO `target` (`Name`, `ID`) VALUES
('Как поступить в ВУЗ', 1),
('как выучить цешарп', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `Name` varchar(128) NOT NULL,
  `CryptedPassword` varchar(128) NOT NULL,
  `Email` varchar(64) NOT NULL,
  `Login` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`ID`, `Name`, `CryptedPassword`, `Email`, `Login`) VALUES
(1, '', '', '', 'login'),
(2, '', '', '', 'login0'),
(4, 'Вася Иванов', '$2y$10$CuBmuNciwD5a7WTMNifetO7KfmbVydauNlc5LadTHzql1HfR2WoQe', 'a@gmail.com', 'superkek22'),
(5, 'Павел Олегович', '$2y$10$KGySZUtJkUt0Zgr7Qv/H7eJmC5WPns3oaBMhe2gvGLbU8k4q36Fqq', 'b@ya.com', 'superkek220');

-- --------------------------------------------------------

--
-- Структура таблицы `usertarget`
--

CREATE TABLE `usertarget` (
  `ID` int(11) NOT NULL,
  `ID_Branch` int(11) NOT NULL,
  `ID_CurrentStep` int(11) NOT NULL,
  `ID_User` int(11) NOT NULL,
  `IsEnded` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `usertarget`
--

INSERT INTO `usertarget` (`ID`, `ID_Branch`, `ID_CurrentStep`, `ID_User`, `IsEnded`) VALUES
(1, 4, 17, 4, 1),
(3, 2, 10, 5, 1),
(4, 2, 10, 4, 1),
(5, 3, 11, 5, 0);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Target` (`ID_Target`);

--
-- Индексы таблицы `reference`
--
ALTER TABLE `reference`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Step` (`ID_Step`);

--
-- Индексы таблицы `step`
--
ALTER TABLE `step`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Branch` (`ID_Branch`);

--
-- Индексы таблицы `target`
--
ALTER TABLE `target`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `usertarget`
--
ALTER TABLE `usertarget`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Branch` (`ID_Branch`,`ID_CurrentStep`,`ID_User`),
  ADD KEY `ID_User` (`ID_User`),
  ADD KEY `ID_CurrentStep` (`ID_CurrentStep`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `branch`
--
ALTER TABLE `branch`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `reference`
--
ALTER TABLE `reference`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT для таблицы `step`
--
ALTER TABLE `step`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT для таблицы `target`
--
ALTER TABLE `target`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `usertarget`
--
ALTER TABLE `usertarget`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `branch`
--
ALTER TABLE `branch`
  ADD CONSTRAINT `branch_ibfk_1` FOREIGN KEY (`ID_Target`) REFERENCES `target` (`ID`);

--
-- Ограничения внешнего ключа таблицы `reference`
--
ALTER TABLE `reference`
  ADD CONSTRAINT `reference_ibfk_1` FOREIGN KEY (`ID_Step`) REFERENCES `step` (`ID`);

--
-- Ограничения внешнего ключа таблицы `step`
--
ALTER TABLE `step`
  ADD CONSTRAINT `step_ibfk_1` FOREIGN KEY (`ID_Branch`) REFERENCES `branch` (`ID`);

--
-- Ограничения внешнего ключа таблицы `usertarget`
--
ALTER TABLE `usertarget`
  ADD CONSTRAINT `usertarget_ibfk_1` FOREIGN KEY (`ID_User`) REFERENCES `user` (`ID`),
  ADD CONSTRAINT `usertarget_ibfk_2` FOREIGN KEY (`ID_Branch`) REFERENCES `branch` (`ID`),
  ADD CONSTRAINT `usertarget_ibfk_3` FOREIGN KEY (`ID_CurrentStep`) REFERENCES `step` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



