-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: helloDB
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Friends`
--

DROP TABLE IF EXISTS `Friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Friends` (
  `userID` int NOT NULL,
  `friendID` int NOT NULL,
  KEY `Friends_Users_userID_fk` (`userID`),
  KEY `Friends_Users_userID_fk_2` (`friendID`),
  CONSTRAINT `Friends_Users_userID_fk` FOREIGN KEY (`userID`) REFERENCES `Users` (`userID`),
  CONSTRAINT `Friends_Users_userID_fk_2` FOREIGN KEY (`friendID`) REFERENCES `Users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Friends`
--

LOCK TABLES `Friends` WRITE;
/*!40000 ALTER TABLE `Friends` DISABLE KEYS */;
INSERT INTO `Friends` (`userID`, `friendID`) VALUES (1,2),(1,3),(1,4),(2,1),(2,3),(2,4),(1,5),(1,6),(2,6),(2,5);
/*!40000 ALTER TABLE `Friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Status`
--

DROP TABLE IF EXISTS `Status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Status` (
  `statusID` int NOT NULL,
  `message` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `likes` int DEFAULT '0',
  PRIMARY KEY (`statusID`),
  UNIQUE KEY `Status_statusID_uindex` (`statusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Status`
--

LOCK TABLES `Status` WRITE;
/*!40000 ALTER TABLE `Status` DISABLE KEYS */;
INSERT INTO `Status` (`statusID`, `message`, `likes`) VALUES (1,'The best thing about a Boolean is that even if you are wrong, you are only off by a bit.',0),(2,'Why do Java developers often wear glasses? They can’t C#.',0),(3,'Why was the developer bankrupt? He’d used all his cache.',0),(4,'What is an algorithm? A word programmers use when they don’t want to explain what they did.',0),(5,'How many programmers does it take to screw in a light bulb? None. It\'s a hardware problem.',0);
/*!40000 ALTER TABLE `Status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users` (
  `userID` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(9999) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `emailaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `profileImgURL` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `Users_userID_uindex` (`userID`),
  CONSTRAINT `Users_UsersLogin_userID_fk` FOREIGN KEY (`userID`) REFERENCES `UsersLogin` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` (`userID`, `name`, `description`, `emailaddress`, `role`, `profileImgURL`) VALUES (1,'CyberxNuke','Too busy to update my bio.','cyberx@nuke.com','Student','random_pp_m.png'),(2,'Meenalochini Thiyagu','Java Expert','meena@tech.com','Trainer','random_pp_f.gif'),(3,'Soujanya','Recruiting Students','soujanya@tech.com','HR','random_pp_f.gif'),(4,'Dhvani','Student','dhvani@tech.com','Student','random_pp_f.gif'),(5,'Manikanta Nadella','Trainer','manikanta@tech.com','Trainer','random_pp_m.png'),(6,'Manasvi','Student','manasvi@tech.com','Student','random_pp_f.gif');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UsersLogin`
--

DROP TABLE IF EXISTS `UsersLogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UsersLogin` (
  `userID` int NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `UsersLogin_name_uindex` (`username`),
  UNIQUE KEY `UsersLogin_userID_uindex` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UsersLogin`
--

LOCK TABLES `UsersLogin` WRITE;
/*!40000 ALTER TABLE `UsersLogin` DISABLE KEYS */;
INSERT INTO `UsersLogin` (`userID`, `username`, `password`) VALUES (1,'cyberxnuke','cyberxnuke###123'),(2,'meena','meena###12345'),(3,'soujanya','soujanya###12'),(4,'dhvani','dhvani@12345'),(5,'manikanta','manikanta#1'),(6,'manasvi','manasvi#123456789');
/*!40000 ALTER TABLE `UsersLogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserStatus`
--

DROP TABLE IF EXISTS `UserStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserStatus` (
  `statusID` int NOT NULL,
  `userID` int NOT NULL,
  KEY `UserStatus_Status_statusID_fk_2` (`statusID`),
  KEY `UserStatus_Users_userID_fk` (`userID`),
  CONSTRAINT `UserStatus_Status_statusID_fk_2` FOREIGN KEY (`statusID`) REFERENCES `Status` (`statusID`),
  CONSTRAINT `UserStatus_Users_userID_fk` FOREIGN KEY (`userID`) REFERENCES `Users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserStatus`
--

LOCK TABLES `UserStatus` WRITE;
/*!40000 ALTER TABLE `UserStatus` DISABLE KEYS */;
INSERT INTO `UserStatus` (`statusID`, `userID`) VALUES (1,1),(2,1),(3,3),(4,2),(5,4);
/*!40000 ALTER TABLE `UserStatus` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-19  0:04:36
