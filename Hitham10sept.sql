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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
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
  PRIMARY KEY (`songlist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songlist`
--

LOCK TABLES `songlist` WRITE;
/*!40000 ALTER TABLE `songlist` DISABLE KEYS */;
INSERT INTO `songlist` VALUES (1,'life_is_beautiful','https://drive.google.com/file/d/0BwtDpsO0CtJZZlFfRG1nRjRuY00/view?usp=sharing','gjgjh','green'),(2,'world_is_mine','https://raw.githubusercontent.com/zmxv/react-native-sound-demo/master/advertising.mp3','hgfhg','yellow'),(3,'raga kafi','https://drive.google.com/open?id=0BwtDpsO0CtJZeGlqTG1Obm5FSEE','ghfh','blue'),(8,'bhupali','https://drive.google.com/open?id=0BwtDpsO0CtJZeGlqTG1Obm5FSEE','jhkjdkaskmcsd','red'),(9,'bhairavi','https://drive.google.com/open?id=0BwtDpsO0CtJZeGlqTG1Obm5FSEE','jhkjdkaskmcsd','red');
/*!40000 ALTER TABLE `songlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `songlist_playlist_mapping`
--

DROP TABLE IF EXISTS `songlist_playlist_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `songlist_playlist_mapping` (
  `songlist_id` int(11) NOT NULL,
  `playlist_id` int(11) NOT NULL,
  PRIMARY KEY (`songlist_id`,`playlist_id`),
  KEY `fk_songlist_playlist_mapping_1_idx` (`playlist_id`),
  KEY `fk_songlist_playlist_mapping_2_idx` (`songlist_id`),
  CONSTRAINT `fk_songlist_playlist_mapping_1` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`playlist_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_songlist_playlist_mapping_2` FOREIGN KEY (`songlist_id`) REFERENCES `songlist` (`songlist_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songlist_playlist_mapping`
--

LOCK TABLES `songlist_playlist_mapping` WRITE;
/*!40000 ALTER TABLE `songlist_playlist_mapping` DISABLE KEYS */;
INSERT INTO `songlist_playlist_mapping` VALUES (1,1),(1,2),(8,3);
/*!40000 ALTER TABLE `songlist_playlist_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_name` varchar(45) NOT NULL,
  `student_id` varchar(45) NOT NULL,
  `student_profile` varchar(45) DEFAULT 'default',
  `student_password` varchar(45) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('Jack','MT2015078','default','default'),('john','MT2016004','default','default');
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
  `student_id` varchar(45) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `playlist_id` int(11) DEFAULT NULL,
  `student_song_assignment_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`student_song_assignment_id`),
  KEY `fk_student_song_assignment_1_idx` (`student_id`),
  KEY `fk_student_song_assignment_2_idx` (`teacher_id`),
  KEY `fk_student_song_assignment_3_idx` (`playlist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_song_assignment`
--

LOCK TABLES `student_song_assignment` WRITE;
/*!40000 ALTER TABLE `student_song_assignment` DISABLE KEYS */;
INSERT INTO `student_song_assignment` VALUES (1,'MT2016004',NULL,1,'2017-09-06 08:51:35'),(2,'MT2016004',NULL,2,'2017-09-06 08:52:17'),(3,'MT2015078',NULL,2,'2017-09-06 09:34:04');
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

-- Dump completed on 2017-09-10 12:24:28
