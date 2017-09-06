CREATE DATABASE  IF NOT EXISTS `HITHAM` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `HITHAM`;
-- MySQL dump 10.13  Distrib 5.5.54, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: HITHAM
-- ------------------------------------------------------
-- Server version	5.5.54-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist` (
  `playlist_id` int(11) NOT NULL AUTO_INCREMENT,
  `playlist_name` varchar(45) NOT NULL,
  PRIMARY KEY (`playlist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (1,'Ragaa of autumn '),(2,'Ragaa of Vasant'),(3,'Ragaa of kafi'),(23,'my playlist');
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `songlist`
--

DROP TABLE IF EXISTS `songlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `songlist` (
  `songlist_id` int(11) NOT NULL AUTO_INCREMENT,
  `songlist_name` varchar(45) NOT NULL,
  `songlist_url` varchar(500) NOT NULL,
  `songlist_pic_url` varchar(500) DEFAULT NULL,
  `songlist_song_color` varchar(45) DEFAULT NULL,
  `songlist_playlist` int(11) DEFAULT NULL,
  PRIMARY KEY (`songlist_id`),
  KEY `fk_songlist_1_idx` (`songlist_playlist`),
  CONSTRAINT `fk_songlist_1` FOREIGN KEY (`songlist_playlist`) REFERENCES `playlist` (`playlist_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songlist`
--

LOCK TABLES `songlist` WRITE;
/*!40000 ALTER TABLE `songlist` DISABLE KEYS */;
INSERT INTO `songlist` VALUES (1,'life_is_beautiful','https://drive.google.com/file/d/0BwtDpsO0CtJZZlFfRG1nRjRuY00/view?usp=sharing',NULL,NULL,3),(2,'world_is_mine','https://raw.githubusercontent.com/zmxv/react-native-sound-demo/master/advertising.mp3',NULL,NULL,1),(3,'raga kafi','https://drive.google.com/open?id=0BwtDpsO0CtJZeGlqTG1Obm5FSEE',NULL,NULL,2),(8,'bhupali','https://drive.google.com/open?id=0BwtDpsO0CtJZeGlqTG1Obm5FSEE','jhkjdkaskmcsd','red',1),(9,'bhupali','https://drive.google.com/open?id=0BwtDpsO0CtJZeGlqTG1Obm5FSEE','jhkjdkaskmcsd','red',1);
/*!40000 ALTER TABLE `songlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(45) NOT NULL,
  `student_enrollment_no` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'john','MT20160045'),(2,'Jack','MT2015078');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_song_assignment`
--

DROP TABLE IF EXISTS `student_song_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_song_assignment` (
  `student_song_assignment_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `playlist_id` int(11) DEFAULT NULL,
  `student_song_assignment_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`student_song_assignment_id`),
  KEY `fk_student_song_assignment_1_idx` (`student_id`),
  KEY `fk_student_song_assignment_2_idx` (`teacher_id`),
  KEY `fk_student_song_assignment_3_idx` (`playlist_id`),
  CONSTRAINT `fk_student_song_assignment_3` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`playlist_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_student_song_assignment_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_student_song_assignment_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_song_assignment`
--

LOCK TABLES `student_song_assignment` WRITE;
/*!40000 ALTER TABLE `student_song_assignment` DISABLE KEYS */;
INSERT INTO `student_song_assignment` VALUES (1,1,1,1,'2017-08-26 18:02:32'),(2,1,1,2,'2017-08-26 18:03:39'),(3,1,1,3,'2017-08-30 08:53:37');
/*!40000 ALTER TABLE `student_song_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(45) NOT NULL,
  `teacher_enrollment_no` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'martha','TA_123');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-05 21:48:00
