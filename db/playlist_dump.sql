/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.6.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: playlist
-- ------------------------------------------------------
-- Server version	11.6.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Current Database: `playlist`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `playlist` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;

USE `playlist`;

--
-- Table structure for table `albums`
--

DROP TABLE IF EXISTS `albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `albums` (
  `album_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`album_id`)
) ENGINE=InnoDB AUTO_INCREMENT=236914423 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES
(123825,'Michael Flatley\'s Feet Of Flames','https://www.deezer.com/album/123825'),
(240794,'Michael Flatley\'s Lord Of The Dance','https://www.deezer.com/album/240794'),
(245955,'Survival','https://www.deezer.com/album/245955'),
(1239023,'City Of Vultures','https://www.deezer.com/album/1239023'),
(4467361,'Been There Done That','https://www.deezer.com/album/4467361'),
(6164412,'From These Wounds (Bonus Track Version)','https://www.deezer.com/album/6164412'),
(10232416,'Tubular Bells','https://www.deezer.com/album/10232416'),
(12977824,'Selected Ambient Works 85-92','https://www.deezer.com/album/12977824'),
(14381184,'The Huntress','https://www.deezer.com/album/14381184'),
(62384562,'Aigaio','https://www.deezer.com/album/62384562'),
(90597102,'In The End','https://www.deezer.com/album/90597102'),
(97619012,'World','https://www.deezer.com/album/97619012'),
(100450322,'Toy Story 4 (Original Motion Picture Soundtrack)','https://www.deezer.com/album/100450322'),
(185320622,'Interstellar (Original Motion Picture Soundtrack) (Expanded Edition)','https://www.deezer.com/album/185320622'),
(236914422,'Sad Night Dynamite (Remixes)','https://www.deezer.com/album/236914422');
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artists`
--

DROP TABLE IF EXISTS `artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artists` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
INSERT INTO `artists` VALUES
(373,'Staind'),
(580,'Aphex Twin'),
(719,'Bob Marley & The Wailers'),
(799,'Boyz II Men'),
(990,'Mike Oldfield'),
(1154,'Marvin Gaye'),
(1293,'Astrud Gilberto'),
(1320,'Supergrass'),
(1935,'Hans Zimmer'),
(2162,'The Supremes'),
(2421,'Ronan Hardiman'),
(2695,'Tamba Trio'),
(3458,'Stan Getz'),
(4085,'Antonio Carlos Jobim'),
(4683,'Joe Henderson'),
(4699,'Diana Ross'),
(5102,'Brian McKnight'),
(5615,'Sergio Mendes & Brasil  66'),
(5646,'Wet Wet Wet'),
(5679,'Randy Newman'),
(7964,'Luiz Bonfa'),
(9242,'Dizzy Gillespie'),
(9728,'Marcos Valle'),
(9999,'Gladys Knight & The Pips'),
(11275,'Cleptomaniacs'),
(11617,'The Miracles'),
(12563,'Dissidenten'),
(13024,'Lionel Hampton'),
(13683,'Os Cariocas'),
(14488,'Charalambides'),
(15724,'Mary Wells'),
(60617,'Susan Rafey'),
(60724,'Lync'),
(61266,'LawnChair Generals'),
(61662,'Lena Horne'),
(62373,'Knowing Looks'),
(73642,'Reead'),
(75047,'The Parlotones'),
(75104,'Wes Montgomery'),
(75332,'Jackson 5'),
(76714,'Mediaeval Baebes'),
(76930,'Moe Bandy'),
(79137,'Duke Ellington And His Orchestra'),
(93642,'Polecats'),
(97310,'Hellfueled'),
(99296,'Runa Laila'),
(99387,'Al Margolis/If, Bwana'),
(100609,'The Funk Lab'),
(103337,'Astaroth'),
(104796,'Ladder El'),
(109227,'Peter Hale'),
(134753,'Duo Leuchtfeuer'),
(144102,'Hasse Alfredson'),
(151464,'Inglese & Marini'),
(157896,'Ortie'),
(185903,'Jackie & Roy'),
(200652,'Alf'),
(211331,'After 7'),
(215506,'Wolf Maahn'),
(235549,'Taiguara'),
(235951,'Tasavallan Presidentti'),
(246917,'Edith Esther'),
(343290,'Chris Stapleton'),
(364225,'Rise To Remain'),
(396513,'Linsey Alexander'),
(476730,'Valeron'),
(488613,'Tommee Profitt'),
(1186608,'Tim Polecat'),
(7389572,'The Pop Group'),
(86801562,'Sad Night Dynamite'),
(233190561,'Funeral');
/*!40000 ALTER TABLE `artists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genres` (
  `genre_id` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES
(1,'Pop'),
(2,'Gospel'),
(3,'House'),
(4,'Rockabilly'),
(5,'Metal'),
(6,'Jazz'),
(7,'Spoken Word'),
(8,'Indie'),
(9,'Electronic'),
(10,'Soul'),
(11,'Indian'),
(16,'Pakistani'),
(85,'Алтернативен рок'),
(98,'Класическа музика'),
(106,'Електро'),
(113,'Club'),
(132,'Pop-Rock'),
(144,'Реге'),
(152,'Рок'),
(173,'Филми/Игри'),
(464,'Метъл'),
(466,'Фолк');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_genre`
--

DROP TABLE IF EXISTS `playlist_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist_genre` (
  `playlist_id` int(11) NOT NULL,
  `genre_id` int(11) DEFAULT NULL,
  KEY `playlist_genre_genres_genre_id_fk` (`genre_id`),
  KEY `playlist_genre_playlists_playlist_id_fk` (`playlist_id`),
  CONSTRAINT `playlist_genre_genres_genre_id_fk` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`genre_id`),
  CONSTRAINT `playlist_genre_playlists_playlist_id_fk` FOREIGN KEY (`playlist_id`) REFERENCES `playlists` (`playlist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_genre`
--

LOCK TABLES `playlist_genre` WRITE;
/*!40000 ALTER TABLE `playlist_genre` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlist_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_track`
--

DROP TABLE IF EXISTS `playlist_track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist_track` (
  `playlist_id` int(11) NOT NULL,
  `track_id` int(11) NOT NULL,
  PRIMARY KEY (`playlist_id`,`track_id`),
  KEY `track_id` (`track_id`),
  CONSTRAINT `playlist_track_ibfk_1` FOREIGN KEY (`playlist_id`) REFERENCES `playlists` (`playlist_id`),
  CONSTRAINT `playlist_track_ibfk_2` FOREIGN KEY (`track_id`) REFERENCES `tracks` (`track_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_track`
--

LOCK TABLES `playlist_track` WRITE;
/*!40000 ALTER TABLE `playlist_track` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlist_track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlists`
--

DROP TABLE IF EXISTS `playlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlists` (
  `playlist_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `duration` time NOT NULL,
  `user_id` int(11) NOT NULL,
  `rank` int(11) NOT NULL,
  PRIMARY KEY (`playlist_id`),
  KEY `playlists_users_user_id_fk` (`user_id`),
  CONSTRAINT `playlists_users_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlists`
--

LOCK TABLES `playlists` WRITE;
/*!40000 ALTER TABLE `playlists` DISABLE KEYS */;
INSERT INTO `playlists` VALUES
(1,'Late night drive','00:17:51',1,66252),
(2,'I dont know','02:14:25',2,44405);
/*!40000 ALTER TABLE `playlists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tracks`
--

DROP TABLE IF EXISTS `tracks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tracks` (
  `track_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `artist` int(11) DEFAULT NULL,
  `duration` time DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `genre` int(11) DEFAULT NULL,
  `preview` text DEFAULT NULL,
  `link` text DEFAULT NULL,
  `albums` int(11) DEFAULT NULL,
  PRIMARY KEY (`track_id`),
  KEY `tracks_albums_fk` (`albums`),
  KEY `tracks_artists_id_fk` (`artist`),
  KEY `tracks_genres_genre_id_fk` (`genre`),
  CONSTRAINT `tracks_albums_fk` FOREIGN KEY (`albums`) REFERENCES `albums` (`album_id`),
  CONSTRAINT `tracks_artists_id_fk` FOREIGN KEY (`artist`) REFERENCES `artists` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tracks_genres_genre_id_fk` FOREIGN KEY (`genre`) REFERENCES `genres` (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracks`
--

LOCK TABLES `tracks` WRITE;
/*!40000 ALTER TABLE `tracks` DISABLE KEYS */;
INSERT INTO `tracks` VALUES
(1,'You\'ve Got a Friend in Me',5679,'00:02:04',608888,173,'https://cdnt-preview.dzcdn.net/api/1/1/3/a/0/0/3a0c0ab0f03cefc9f23e89a283b1d3da.mp3?hdnea=exp=1782579823~acl=/api/1/1/3/a/0/0/3a0c0ab0f03cefc9f23e89a283b1d3da.mp3*~data=user_id=0,application_id=42~hmac=eeae2962edc76d0d741148af799ac5d4c4e3bc1f1f80ae5a1082a8fddf59bb22','https://www.deezer.com/track/696962792',100450322),
(2,'I Can\'t Let You Throw Yourself Away',5679,'00:02:06',317228,173,'https://cdnt-preview.dzcdn.net/api/1/1/4/9/7/0/497bd65972ca00c27fe86a5124e54db9.mp3?hdnea=exp=1782579823~acl=/api/1/1/4/9/7/0/497bd65972ca00c27fe86a5124e54db9.mp3*~data=user_id=0,application_id=42~hmac=8e6242fba6d389b747c9a81304293ebbf6c7d57442df6c493fcfb6f98992336c','https://www.deezer.com/track/696962802',100450322),
(3,'The Ballad of the Lonesome Cowboy',343290,'00:01:45',271415,173,'https://cdnt-preview.dzcdn.net/api/1/1/9/2/7/0/9273b8d614e462afecac7f05c7688f4a.mp3?hdnea=exp=1782579823~acl=/api/1/1/9/2/7/0/9273b8d614e462afecac7f05c7688f4a.mp3*~data=user_id=0,application_id=42~hmac=331dfc4bb53635a98a005bb5e739228a149535e86cf8ba90194dd32d1fe6cb3f','https://www.deezer.com/track/696962812',100450322),
(4,'Operation Pull Toy',5679,'00:05:19',181390,173,'https://cdnt-preview.dzcdn.net/api/1/1/7/2/8/0/728e05b0046b57cec2b9b9e1689e75cf.mp3?hdnea=exp=1782579823~acl=/api/1/1/7/2/8/0/728e05b0046b57cec2b9b9e1689e75cf.mp3*~data=user_id=0,application_id=42~hmac=1c3d7530092406833b0ba91fe0b54764e8a9944c8ecfb09970509cd414ab735f','https://www.deezer.com/track/696962822',100450322),
(5,'Woody\'s Closet of Neglect',5679,'00:03:56',150089,173,'https://cdnt-preview.dzcdn.net/api/1/1/4/f/6/0/4f61df98303572acd701e61f6da49695.mp3?hdnea=exp=1782579823~acl=/api/1/1/4/f/6/0/4f61df98303572acd701e61f6da49695.mp3*~data=user_id=0,application_id=42~hmac=340dc8f6cea861b58c066012e61730209d19f0afbf397a3d7a1febf90f93a3a0','https://www.deezer.com/track/696962832',100450322),
(6,'School Daze',5679,'00:04:22',142149,173,'https://cdnt-preview.dzcdn.net/api/1/1/5/3/b/0/53bf6f3d884bd7c40b989aa543287759.mp3?hdnea=exp=1782579823~acl=/api/1/1/5/3/b/0/53bf6f3d884bd7c40b989aa543287759.mp3*~data=user_id=0,application_id=42~hmac=87bafbe6f92e1acc3cccad528efe16876656831d6b28e348f2decafe60f9d397','https://www.deezer.com/track/696962842',100450322),
(7,'Trash Can Chronicles',5679,'00:03:28',108534,173,'https://cdnt-preview.dzcdn.net/api/1/1/f/c/d/0/fcd3ea6b948fe9c94eb8a8178f914bac.mp3?hdnea=exp=1782579823~acl=/api/1/1/f/c/d/0/fcd3ea6b948fe9c94eb8a8178f914bac.mp3*~data=user_id=0,application_id=42~hmac=a32778a6983e8408e65814645fa53b49ec781cac45683de960eea1a424534ed5','https://www.deezer.com/track/696962852',100450322),
(8,'The Road to Antiques',5679,'00:02:42',125689,173,'https://cdnt-preview.dzcdn.net/api/1/1/e/d/1/0/ed1a3451854ad57ca4a08c89dd971375.mp3?hdnea=exp=1782579823~acl=/api/1/1/e/d/1/0/ed1a3451854ad57ca4a08c89dd971375.mp3*~data=user_id=0,application_id=42~hmac=437aa095299dfbda6951413992458b7ccd11d1305ec1cf721c11ea7e2b8673d0','https://www.deezer.com/track/696962862',100450322),
(9,'A Spork in the Road',5679,'00:01:56',109164,173,'https://cdnt-preview.dzcdn.net/api/1/1/6/0/3/0/603b74f4841a73a5d888541e0a9f441f.mp3?hdnea=exp=1782579823~acl=/api/1/1/6/0/3/0/603b74f4841a73a5d888541e0a9f441f.mp3*~data=user_id=0,application_id=42~hmac=c5a8441aeca6ba2b28bf4a179466968ae1f6b53b32bf9289306732566504db69','https://www.deezer.com/track/696962872',100450322),
(10,'Rubber Baby Buggy Butlers',5679,'00:01:53',117533,173,'https://cdnt-preview.dzcdn.net/api/1/1/5/9/1/0/591c9960b178d933a89691217b050110.mp3?hdnea=exp=1782579823~acl=/api/1/1/5/9/1/0/591c9960b178d933a89691217b050110.mp3*~data=user_id=0,application_id=42~hmac=aeb95e14977660c0dd4c79eeae5f6520be7e859a0818dcdea871d1c64cfec848','https://www.deezer.com/track/696962882',100450322),
(11,'Buzz\'s Flight & a Maiden',5679,'00:04:07',185203,173,'https://cdnt-preview.dzcdn.net/api/1/1/f/4/a/0/f4a30ab4964cc4d6ee35583099d9e647.mp3?hdnea=exp=1782579823~acl=/api/1/1/f/4/a/0/f4a30ab4964cc4d6ee35583099d9e647.mp3*~data=user_id=0,application_id=42~hmac=539393de23cd86d6f4896f23c71965475306060650537aa9daf1d8569f7ef55c','https://www.deezer.com/track/696962892',100450322),
(12,'Ducky, Bunny & Tea',5679,'00:02:17',107801,173,'https://cdnt-preview.dzcdn.net/api/1/1/b/4/2/0/b421a8a69b5ff3f0b615cdbf22832f7c.mp3?hdnea=exp=1782579823~acl=/api/1/1/b/4/2/0/b421a8a69b5ff3f0b615cdbf22832f7c.mp3*~data=user_id=0,application_id=42~hmac=2bbd06fb650ccaa86ddabfcd33e72f522d422c5bbfe97125b36faac55546c9c0','https://www.deezer.com/track/696962902',100450322),
(13,'Moving at the Speed of Skunk',5679,'00:01:35',115543,173,'https://cdnt-preview.dzcdn.net/api/1/1/4/e/a/0/4ea16710cb234bd2104da9f41a2df07e.mp3?hdnea=exp=1782579823~acl=/api/1/1/4/e/a/0/4ea16710cb234bd2104da9f41a2df07e.mp3*~data=user_id=0,application_id=42~hmac=8f456ae34a52b7138dde9cfd60659a7c91527bd81d090967d7324be92d472c9f','https://www.deezer.com/track/696962912',100450322),
(14,'Bo Peep\'s Panorama for Two',5679,'00:02:36',146915,173,'https://cdnt-preview.dzcdn.net/api/1/1/2/2/8/0/228ac75173b313f5b1abf07ff1276a99.mp3?hdnea=exp=1782579823~acl=/api/1/1/2/2/8/0/228ac75173b313f5b1abf07ff1276a99.mp3*~data=user_id=0,application_id=42~hmac=1b65a54c813d703128f57b2e159b978d5be11e0f562333d850691b6b42d0f6ea','https://www.deezer.com/track/696962922',100450322),
(15,'Three Sheeps to the Wind',5679,'00:02:55',102630,173,'https://cdnt-preview.dzcdn.net/api/1/1/b/9/f/0/b9f472636e1c436c87a048cd786257ec.mp3?hdnea=exp=1782579823~acl=/api/1/1/b/9/f/0/b9f472636e1c436c87a048cd786257ec.mp3*~data=user_id=0,application_id=42~hmac=8bba87b42869fb8662918eecdcb6f11881a9b3849e258b4ca0482bb72c3e0c9e','https://www.deezer.com/track/696962932',100450322),
(16,'Sneaking and Antiquing',5679,'00:01:43',112211,173,'https://cdnt-preview.dzcdn.net/api/1/1/2/3/4/0/234ec9cf530d5949616ccef9d7037381.mp3?hdnea=exp=1782579823~acl=/api/1/1/2/3/4/0/234ec9cf530d5949616ccef9d7037381.mp3*~data=user_id=0,application_id=42~hmac=9c5d04c381a7c540ced95ddea084d9c2acdad8e709faf28df8d3837036399b4a','https://www.deezer.com/track/696962942',100450322),
(17,'Recruiting Duke Caboom',5679,'00:01:17',144250,173,'https://cdnt-preview.dzcdn.net/api/1/1/e/5/8/0/e582c14d9a8fe7bc46498a27b2ab8e22.mp3?hdnea=exp=1782579823~acl=/api/1/1/e/5/8/0/e582c14d9a8fe7bc46498a27b2ab8e22.mp3*~data=user_id=0,application_id=42~hmac=34d544055986204999775f93a5d8f64f6d04bf424ae63882193760f7d568716b','https://www.deezer.com/track/696962952',100450322),
(18,'Prepping the Jump',5679,'00:02:20',118472,173,'https://cdnt-preview.dzcdn.net/api/1/1/3/f/e/0/3fedf462fae98f97026bff95899a88d9.mp3?hdnea=exp=1782579823~acl=/api/1/1/3/f/e/0/3fedf462fae98f97026bff95899a88d9.mp3*~data=user_id=0,application_id=42~hmac=6f697d1803f6c000c0ce757751296186189e2757757c1e4dcf79c15fde7d9e08','https://www.deezer.com/track/696962962',100450322),
(19,'Let\'s Caboom!',5679,'00:04:08',99360,173,'https://cdnt-preview.dzcdn.net/api/1/1/4/c/b/0/4cbe787dbba8e2a9fab3eba75df09f33.mp3?hdnea=exp=1782579823~acl=/api/1/1/4/c/b/0/4cbe787dbba8e2a9fab3eba75df09f33.mp3*~data=user_id=0,application_id=42~hmac=0511b3400bdb196c7f1f631ed998f7de5be754ad4457b0b13e3d42a2f1e1de2d','https://www.deezer.com/track/696962972',100450322),
(20,'Cowboy Sacrifice',5679,'00:02:07',101036,173,'https://cdnt-preview.dzcdn.net/api/1/1/6/5/6/0/6562a1fa65764767123e6df0d3d5c176.mp3?hdnea=exp=1782579823~acl=/api/1/1/6/5/6/0/6562a1fa65764767123e6df0d3d5c176.mp3*~data=user_id=0,application_id=42~hmac=4dd5c98c4582729e354227cd514420b879c351583040643c5441bf3f86000ab9','https://www.deezer.com/track/696962982',100450322),
(21,'Operation Harmony',5679,'00:04:24',110227,173,'https://cdnt-preview.dzcdn.net/api/1/1/b/d/b/0/bdbd168662cbedc18e92a6a282ff7cc9.mp3?hdnea=exp=1782579823~acl=/api/1/1/b/d/b/0/bdbd168662cbedc18e92a6a282ff7cc9.mp3*~data=user_id=0,application_id=42~hmac=63f07748cc18bb2a33ad4f7a82c1e0443919d7ac2959c4ea403377e0d3c40f05','https://www.deezer.com/track/696962992',100450322),
(22,'Duke\'s Best Crash Ever',5679,'00:02:43',107992,173,'https://cdnt-preview.dzcdn.net/api/1/1/b/0/5/0/b055606871e057901c964e67795a2cad.mp3?hdnea=exp=1782579823~acl=/api/1/1/b/0/5/0/b055606871e057901c964e67795a2cad.mp3*~data=user_id=0,application_id=42~hmac=57f0227dc5ef03570f0fc7aa90056d14571419d45dcb6cacf87c41ece7d3569b','https://www.deezer.com/track/696963002',100450322),
(23,'Gabby Gabby\'s Most Noble Thing',5679,'00:03:03',151150,173,'https://cdnt-preview.dzcdn.net/api/1/1/a/d/6/0/ad63eae0d11914381c07508672407a11.mp3?hdnea=exp=1782579824~acl=/api/1/1/a/d/6/0/ad63eae0d11914381c07508672407a11.mp3*~data=user_id=0,application_id=42~hmac=666630bf007764f40ba31ce3b23944a321ce4f34e7257497f0a08a83fcb051e9','https://www.deezer.com/track/696963012',100450322),
(24,'Parting Gifts & New Horizons',5679,'00:05:05',246855,173,'https://cdnt-preview.dzcdn.net/api/1/1/f/0/a/0/f0a49834b5f044f92ce3a9d8915a53c6.mp3?hdnea=exp=1782579824~acl=/api/1/1/f/0/a/0/f0a49834b5f044f92ce3a9d8915a53c6.mp3*~data=user_id=0,application_id=42~hmac=ec13e2dfb77927c53c7474fa8726b48f9a8d889d0c05a155c9acb55885e5d691','https://www.deezer.com/track/696963022',100450322),
(25,'The Ballad of the Lonesome Cowboy (Soundtrack Version)',5679,'00:01:46',154373,173,'https://cdnt-preview.dzcdn.net/api/1/1/8/a/c/0/8acfbc059479b258a6c477ac6b690cd2.mp3?hdnea=exp=1782579824~acl=/api/1/1/8/a/c/0/8acfbc059479b258a6c477ac6b690cd2.mp3*~data=user_id=0,application_id=42~hmac=1ee39df0977d1c9a92825033bcfd32c59d914ad0c907b71e2e0ed78cadc86396','https://www.deezer.com/track/696963032',100450322),
(26,'Dreaming of the Crash',1935,'00:03:55',528156,173,'https://cdnt-preview.dzcdn.net/api/1/1/1/d/a/0/1da37902998a9fb27f9dcbabf6e74093.mp3?hdnea=exp=1782579825~acl=/api/1/1/1/d/a/0/1da37902998a9fb27f9dcbabf6e74093.mp3*~data=user_id=0,application_id=42~hmac=1122a86542bc45024b37cbdd6fc1be833b7c97ff270563764a142e280050c61d','https://www.deezer.com/track/1137370832',185320622),
(27,'Cornfield Chase',1935,'00:02:06',850478,173,'https://cdnt-preview.dzcdn.net/api/1/1/9/e/4/0/9e4e3f7f152deaec518e502b9e5cb023.mp3?hdnea=exp=1782579825~acl=/api/1/1/9/e/4/0/9e4e3f7f152deaec518e502b9e5cb023.mp3*~data=user_id=0,application_id=42~hmac=ca6947b73ad16fb4a948c2ae4bf668bd4bce068f69af5dcbe2676f84a6e42169','https://www.deezer.com/track/1137370842',185320622),
(28,'Dust',1935,'00:05:41',556478,173,'https://cdnt-preview.dzcdn.net/api/1/1/1/e/a/0/1ea032a37286ce1094d33f6ebc45c747.mp3?hdnea=exp=1782579825~acl=/api/1/1/1/e/a/0/1ea032a37286ce1094d33f6ebc45c747.mp3*~data=user_id=0,application_id=42~hmac=1cba1cd905a6c649dd0ac5110a7dbef6573efce16b746d1be9c580f327eb98c2','https://www.deezer.com/track/1137370852',185320622),
(29,'Day One (Interstellar Theme)',1935,'00:03:19',664276,173,'https://cdnt-preview.dzcdn.net/api/1/1/e/9/b/0/e9bff816fe1b9a45833ee373e5e12a10.mp3?hdnea=exp=1782579825~acl=/api/1/1/e/9/b/0/e9bff816fe1b9a45833ee373e5e12a10.mp3*~data=user_id=0,application_id=42~hmac=eec477dca0bce54c2c5a8e8a8709b76c9734ec775b4528f6bfff6a59308b417e','https://www.deezer.com/track/1137370862',185320622),
(30,'Stay',1935,'00:06:52',523923,173,'https://cdnt-preview.dzcdn.net/api/1/1/e/9/4/0/e9440989c24637274c231da3be5ab3ab.mp3?hdnea=exp=1782579825~acl=/api/1/1/e/9/4/0/e9440989c24637274c231da3be5ab3ab.mp3*~data=user_id=0,application_id=42~hmac=265a69759b16e9fb64b06f76401cec1a8283d78f0b2b61171000b0bf1071611f','https://www.deezer.com/track/1137370872',185320622),
(31,'Message from Home',1935,'00:01:40',469666,173,'https://cdnt-preview.dzcdn.net/api/1/1/9/e/5/0/9e523bdb6713f2671c52690e5a916fed.mp3?hdnea=exp=1782579825~acl=/api/1/1/9/e/5/0/9e523bdb6713f2671c52690e5a916fed.mp3*~data=user_id=0,application_id=42~hmac=d14fb94ed9ae5ae057d731588a10b0410ff4fab236543396224b260dace979c6','https://www.deezer.com/track/1137370882',185320622),
(32,'The Wormhole',1935,'00:01:30',484909,173,'https://cdnt-preview.dzcdn.net/api/1/1/7/a/3/0/7a36556df0459966810abaea51d0b707.mp3?hdnea=exp=1782579825~acl=/api/1/1/7/a/3/0/7a36556df0459966810abaea51d0b707.mp3*~data=user_id=0,application_id=42~hmac=e72ff94ccaaf6bbce8d30f54cafc82833f009b9a1a02c4d2e1a9c043e1663d20','https://www.deezer.com/track/1137370892',185320622),
(33,'Mountains',1935,'00:03:39',632283,173,'https://cdnt-preview.dzcdn.net/api/1/1/7/c/9/0/7c914eb2375c94448eed54b29ee76c99.mp3?hdnea=exp=1782579825~acl=/api/1/1/7/c/9/0/7c914eb2375c94448eed54b29ee76c99.mp3*~data=user_id=0,application_id=42~hmac=7d691ac5664ff3f8a7de4997cc48cbdc1647b74fc86b89b3f0e95689fd4482b2','https://www.deezer.com/track/1137370902',185320622),
(34,'Afraid of Time',1935,'00:02:32',556626,173,'https://cdnt-preview.dzcdn.net/api/1/1/b/4/0/0/b4035fac90448eb47bb3537d3ef571ea.mp3?hdnea=exp=1782579825~acl=/api/1/1/b/4/0/0/b4035fac90448eb47bb3537d3ef571ea.mp3*~data=user_id=0,application_id=42~hmac=495742067f74a84257a86b66832094cb9ef82e1e0844136a22e2d0deb90b7e3b','https://www.deezer.com/track/1137370912',185320622),
(35,'A Place Among the Stars',1935,'00:03:27',475050,173,'https://cdnt-preview.dzcdn.net/api/1/1/7/9/5/0/795676f02312277e0ae71547db913faf.mp3?hdnea=exp=1782579825~acl=/api/1/1/7/9/5/0/795676f02312277e0ae71547db913faf.mp3*~data=user_id=0,application_id=42~hmac=8d4d3b5dd3ab0c87f1613074e17db5e93c8f69a698a4a79ba499894efd9b07d8','https://www.deezer.com/track/1137370922',185320622),
(36,'Running Out',1935,'00:01:57',446310,173,'https://cdnt-preview.dzcdn.net/api/1/1/9/6/c/0/96cb28f5d9c1a619e3213c6155f2d821.mp3?hdnea=exp=1782579825~acl=/api/1/1/9/6/c/0/96cb28f5d9c1a619e3213c6155f2d821.mp3*~data=user_id=0,application_id=42~hmac=5398a9bb618f64bc3a296d97c306860f624b7d1440753433366457732a82f76b','https://www.deezer.com/track/1137370932',185320622),
(37,'I\'m Going Home',1935,'00:05:48',471461,173,'https://cdnt-preview.dzcdn.net/api/1/1/4/9/4/0/4942a494e802308fee206a8f8c4699d9.mp3?hdnea=exp=1782579825~acl=/api/1/1/4/9/4/0/4942a494e802308fee206a8f8c4699d9.mp3*~data=user_id=0,application_id=42~hmac=892325d90cadd35271e7d3e4e2e30c1db5e1aeaab110f5e88984d116c3285140','https://www.deezer.com/track/1137370942',185320622),
(38,'Coward',1935,'00:08:26',485636,173,'https://cdnt-preview.dzcdn.net/api/1/1/0/4/e/0/04e0dfd3a65716d505fa236cbd157f48.mp3?hdnea=exp=1782579825~acl=/api/1/1/0/4/e/0/04e0dfd3a65716d505fa236cbd157f48.mp3*~data=user_id=0,application_id=42~hmac=a1428daa04d2ad69a49e723d80978328c2bc4ec2f37f595b62ca3967e05e2816','https://www.deezer.com/track/1137370952',185320622),
(39,'Detach',1935,'00:06:42',446888,173,'https://cdnt-preview.dzcdn.net/api/1/1/3/d/5/0/3d53df5791aa7358b16455e18ac79a7a.mp3?hdnea=exp=1782579825~acl=/api/1/1/3/d/5/0/3d53df5791aa7358b16455e18ac79a7a.mp3*~data=user_id=0,application_id=42~hmac=a0db9bcc749055f9695dcf4b6ee080d44fd8825b79f607605aa5aaa6a2a828cf','https://www.deezer.com/track/1137370962',185320622),
(40,'S.T.A.Y.',1935,'00:06:23',564134,173,'https://cdnt-preview.dzcdn.net/api/1/1/0/9/4/0/09486190f30a2028a9bbaca59d59b5e2.mp3?hdnea=exp=1782579825~acl=/api/1/1/0/9/4/0/09486190f30a2028a9bbaca59d59b5e2.mp3*~data=user_id=0,application_id=42~hmac=f93faebef98abe8bdbe2b2b2e7cf76ea0ee5e53dac1b7d8c6c124a9ae4f2d2b6','https://www.deezer.com/track/1137370972',185320622),
(41,'Where We\'re Going',1935,'00:07:41',444785,173,'https://cdnt-preview.dzcdn.net/api/1/1/2/f/1/0/2f1717da5f2755d35a456ef6072fe2cb.mp3?hdnea=exp=1782579825~acl=/api/1/1/2/f/1/0/2f1717da5f2755d35a456ef6072fe2cb.mp3*~data=user_id=0,application_id=42~hmac=cefe6861a6d9ccb6e34a0db5f91a5296623b673d91f141b61443a957fbb20f59','https://www.deezer.com/track/1137370982',185320622),
(42,'First Step',1935,'00:01:47',497873,173,'https://cdnt-preview.dzcdn.net/api/1/1/6/2/b/0/62b4162f012a36c8e2fb3a3311298b35.mp3?hdnea=exp=1782579825~acl=/api/1/1/6/2/b/0/62b4162f012a36c8e2fb3a3311298b35.mp3*~data=user_id=0,application_id=42~hmac=c3ee926cbd10e622beaa059775f147de1317a0612c548ac0aa3c70fd119bad9a','https://www.deezer.com/track/1137370992',185320622),
(43,'Flying Drone',1935,'00:01:53',482725,173,'https://cdnt-preview.dzcdn.net/api/1/1/f/b/b/0/fbb0084743d1764d446732268b60fa30.mp3?hdnea=exp=1782579825~acl=/api/1/1/f/b/b/0/fbb0084743d1764d446732268b60fa30.mp3*~data=user_id=0,application_id=42~hmac=06e23aea8e5e460cc2747a04da0583a316a4ee60733f5fdfb2b614f83aac027d','https://www.deezer.com/track/1137371002',185320622),
(44,'Atmospheric Entry',1935,'00:01:40',409076,173,'https://cdnt-preview.dzcdn.net/api/1/1/4/2/7/0/4271815360b3a1212aedad38422a39f1.mp3?hdnea=exp=1782579825~acl=/api/1/1/4/2/7/0/4271815360b3a1212aedad38422a39f1.mp3*~data=user_id=0,application_id=42~hmac=716721a865c222122d9900126eb0543e61eec47cf6588a0d8a226d1571a0e929','https://www.deezer.com/track/1137371012',185320622),
(45,'No Need to Come Back',1935,'00:04:32',395505,173,'https://cdnt-preview.dzcdn.net/api/1/1/9/1/5/0/9150508134f3ad609c2b0861a9694123.mp3?hdnea=exp=1782579825~acl=/api/1/1/9/1/5/0/9150508134f3ad609c2b0861a9694123.mp3*~data=user_id=0,application_id=42~hmac=c153780ef7683b6b137cb0931ac9b81c8389d7f9f26a81a4fe6cf82c272a02e0','https://www.deezer.com/track/1137371022',185320622),
(46,'Imperfect Lock',1935,'00:06:54',413135,173,'https://cdnt-preview.dzcdn.net/api/1/1/4/8/4/0/4844ec0f77890697e964ba37147b171b.mp3?hdnea=exp=1782579825~acl=/api/1/1/4/8/4/0/4844ec0f77890697e964ba37147b171b.mp3*~data=user_id=0,application_id=42~hmac=e0715c849052be5ce780573e28c8427d7a41629064b25789b2cf7e84784cbf7f','https://www.deezer.com/track/1137371032',185320622),
(47,'No Time for Caution',1935,'00:04:06',584341,173,'https://cdnt-preview.dzcdn.net/api/1/1/b/e/7/0/be74c5eae77ac3a92b6b4350308050a9.mp3?hdnea=exp=1782579825~acl=/api/1/1/b/e/7/0/be74c5eae77ac3a92b6b4350308050a9.mp3*~data=user_id=0,application_id=42~hmac=503de9555da8b30b563a00573883b0c13a5e0bca76ec922f2f60d0f4621acaf8','https://www.deezer.com/track/1137371042',185320622),
(48,'What Happens Now?',1935,'00:02:26',516860,173,'https://cdnt-preview.dzcdn.net/api/1/1/a/5/b/0/a5b70930452bf7918300e5c2fd6ed4ec.mp3?hdnea=exp=1782579825~acl=/api/1/1/a/5/b/0/a5b70930452bf7918300e5c2fd6ed4ec.mp3*~data=user_id=0,application_id=42~hmac=9589624ee34bff1f245752cbcb8e8f5873180459c22f9b3bfc76d7dacfb6b76e','https://www.deezer.com/track/1137371052',185320622),
(49,'Who\'s They?',1935,'00:07:17',509102,173,'https://cdnt-preview.dzcdn.net/api/1/1/c/2/b/0/c2b07793c97f000db3f6815ad36847ea.mp3?hdnea=exp=1782579825~acl=/api/1/1/c/2/b/0/c2b07793c97f000db3f6815ad36847ea.mp3*~data=user_id=0,application_id=42~hmac=18f3ef00ffa5566cab4dd714e817a8e0552b8421f8bf1c763375b57f3a7d9bf3','https://www.deezer.com/track/1137371062',185320622),
(50,'Murph',1935,'00:11:20',392108,173,'https://cdnt-preview.dzcdn.net/api/1/1/4/7/8/0/47868d0455cabc97cb3a6b43b094b6b9.mp3?hdnea=exp=1782579825~acl=/api/1/1/4/7/8/0/47868d0455cabc97cb3a6b43b094b6b9.mp3*~data=user_id=0,application_id=42~hmac=0042a468fb7d890cfec5cce506a33299d147d96621e2bab67e15d2193499e8f8','https://www.deezer.com/track/1137371072',185320622),
(51,'Lenten Is Come',76714,'00:02:14',25465,466,'https://cdnt-preview.dzcdn.net/api/1/1/a/e/4/0/ae4dbc76856a907c541860e706acd84f.mp3?hdnea=exp=1782579827~acl=/api/1/1/a/e/4/0/ae4dbc76856a907c541860e706acd84f.mp3*~data=user_id=0,application_id=42~hmac=41a5dd3d694408251ff89bc9a103436bc4e3cdc2396b6437ba9df1101901f403','https://www.deezer.com/track/134814742',14381184),
(52,'Winter Wakeneth',76714,'00:03:38',8952,466,'https://cdnt-preview.dzcdn.net/api/1/1/d/c/0/0/dc08efba39bc1a35159f6f3d0420e28f.mp3?hdnea=exp=1782579827~acl=/api/1/1/d/c/0/0/dc08efba39bc1a35159f6f3d0420e28f.mp3*~data=user_id=0,application_id=42~hmac=7fa1379cbbea70b41a6eeff9a16f7f57a40087390b5ad476ff758a113567de6b','https://www.deezer.com/track/134814744',14381184),
(53,'She Walks in Beauty',76714,'00:03:28',2026,466,'https://cdnt-preview.dzcdn.net/api/1/1/8/1/9/0/819d581f349dd4b59ac89c2b8cd25d6e.mp3?hdnea=exp=1782579827~acl=/api/1/1/8/1/9/0/819d581f349dd4b59ac89c2b8cd25d6e.mp3*~data=user_id=0,application_id=42~hmac=d2d765b0ebb24316e28a5c964f9c6afc497bedaf55217f97a688e822b8b6aba3','https://www.deezer.com/track/134814746',14381184),
(54,'She Moved Through the Fayre',76714,'00:04:02',427,466,'https://cdnt-preview.dzcdn.net/api/1/1/f/1/f/0/f1f1901f83b226e462f33a9305888f5b.mp3?hdnea=exp=1782579827~acl=/api/1/1/f/1/f/0/f1f1901f83b226e462f33a9305888f5b.mp3*~data=user_id=0,application_id=42~hmac=e9a8ebbd6da64ecc967eff45f86015f266fb02acf3a0e056b8ac91fc9dbb74d7','https://www.deezer.com/track/134814748',14381184),
(55,'Under the Wilow Tree',76714,'00:03:16',232,466,'https://cdnt-preview.dzcdn.net/api/1/1/0/1/b/0/01b00892dfc304852de821b970c7a24c.mp3?hdnea=exp=1782579827~acl=/api/1/1/0/1/b/0/01b00892dfc304852de821b970c7a24c.mp3*~data=user_id=0,application_id=42~hmac=ae5fde2a6e59ceb2ebeb18ae7c4d615d1a9b0a2f1cfe59654fc0762febb2687b','https://www.deezer.com/track/134814750',14381184),
(56,'Cry of the Garb',76714,'00:04:30',1297,466,'https://cdnt-preview.dzcdn.net/api/1/1/f/d/a/0/fda697b31346bc63613c98cd77d0eb16.mp3?hdnea=exp=1782579827~acl=/api/1/1/f/d/a/0/fda697b31346bc63613c98cd77d0eb16.mp3*~data=user_id=0,application_id=42~hmac=08f0fdc87830af09da5b5302ccabfce0e476c5a60674a7de22eff5dc1ce1ce67','https://www.deezer.com/track/134814752',14381184),
(57,'Cathedral Song',76714,'00:02:16',26433,466,'https://cdnt-preview.dzcdn.net/api/1/1/6/9/1/0/691d3387a1bb7e4a4e3ae757a07ce8f1.mp3?hdnea=exp=1782579827~acl=/api/1/1/6/9/1/0/691d3387a1bb7e4a4e3ae757a07ce8f1.mp3*~data=user_id=0,application_id=42~hmac=abf92545f7c7c759b6bd399aa7b88c81899ab7e80ad4c6c6dc4f016005ce797e','https://www.deezer.com/track/134814754',14381184),
(58,'Care Away',76714,'00:03:22',26376,466,'https://cdnt-preview.dzcdn.net/api/1/1/1/e/c/0/1ec40299a30bec2aef737318358085ea.mp3?hdnea=exp=1782579827~acl=/api/1/1/1/e/c/0/1ec40299a30bec2aef737318358085ea.mp3*~data=user_id=0,application_id=42~hmac=0adaabb2b75c003af42d3edd8ac1f97eac0824a9e7c214cd18648abbc0c36cf1','https://www.deezer.com/track/134814756',14381184),
(59,'Cruel Sister',76714,'00:06:04',25012,466,'https://cdnt-preview.dzcdn.net/api/1/1/2/0/e/0/20ee5ec80c252bcb28d2744fed5398f6.mp3?hdnea=exp=1782579827~acl=/api/1/1/2/0/e/0/20ee5ec80c252bcb28d2744fed5398f6.mp3*~data=user_id=0,application_id=42~hmac=8c915b514f60fb21228b5882cdd47b020d448474461bdf79281ec0b16f73ce43','https://www.deezer.com/track/134814758',14381184),
(60,'Dianae',76714,'00:04:18',2542,466,'https://cdnt-preview.dzcdn.net/api/1/1/3/9/4/0/394a81d0e2d0c57d41fc1d5010a1334f.mp3?hdnea=exp=1782579827~acl=/api/1/1/3/9/4/0/394a81d0e2d0c57d41fc1d5010a1334f.mp3*~data=user_id=0,application_id=42~hmac=b5f5a36421b2fd9f452892edd7b69841c2e320063cab64b73975a1e0b25ca7b3','https://www.deezer.com/track/134814760',14381184),
(61,'Queen & Huntress',76714,'00:04:34',31481,466,'https://cdnt-preview.dzcdn.net/api/1/1/2/5/f/0/25f412d0e80d3664f650da7daa3b244b.mp3?hdnea=exp=1782579827~acl=/api/1/1/2/5/f/0/25f412d0e80d3664f650da7daa3b244b.mp3*~data=user_id=0,application_id=42~hmac=e9a44fd1853c6d2aa3b76e6d5cdedc85ed0f01152da9ffdbfd26153dd890aa27','https://www.deezer.com/track/134814762',14381184),
(62,'Veni Veni Bella',76714,'00:03:06',5461,466,'https://cdnt-preview.dzcdn.net/api/1/1/d/e/1/0/de190c10bfe4da853653e119cf339cda.mp3?hdnea=exp=1782579827~acl=/api/1/1/d/e/1/0/de190c10bfe4da853653e119cf339cda.mp3*~data=user_id=0,application_id=42~hmac=7f7ff50afb875f3c115957c07a4cb0930f59e1a2e9aec67ad0c45fd5d4dfc2ed','https://www.deezer.com/track/134814764',14381184),
(63,'The Rose Bud',76714,'00:04:03',17858,466,'https://cdnt-preview.dzcdn.net/api/1/1/5/8/7/0/58798117b59911f27515fa212e36bf91.mp3?hdnea=exp=1782579827~acl=/api/1/1/5/8/7/0/58798117b59911f27515fa212e36bf91.mp3*~data=user_id=0,application_id=42~hmac=7c0c06f692852134d04cae07041532704bc042665c01a461ffb58d8ed2bf77b6','https://www.deezer.com/track/134814766',14381184),
(64,'Clasp of a Lion',76714,'00:02:46',11591,466,'https://cdnt-preview.dzcdn.net/api/1/1/c/4/b/0/c4b7763f59d75a87071ee3dfdbe9d148.mp3?hdnea=exp=1782579827~acl=/api/1/1/c/4/b/0/c4b7763f59d75a87071ee3dfdbe9d148.mp3*~data=user_id=0,application_id=42~hmac=dffd41723a3709902f09cdd6e0e4415fe16b7111ee245cde2b5b5449644578cd','https://www.deezer.com/track/134814768',14381184),
(65,'Phantom',76714,'00:04:02',3111,466,'https://cdnt-preview.dzcdn.net/api/1/1/e/6/5/0/e65db1c66774fe28dcdaa938ca726cdb.mp3?hdnea=exp=1782579827~acl=/api/1/1/e/6/5/0/e65db1c66774fe28dcdaa938ca726cdb.mp3*~data=user_id=0,application_id=42~hmac=957e29b5069b0acc83ca0606a549addf1cf3883a522001e5c7761f517a566757','https://www.deezer.com/track/134814770',14381184),
(66,'Jennet\'s Song',76714,'00:03:20',26172,466,'https://cdnt-preview.dzcdn.net/api/1/1/7/1/d/0/71df7387d989446e214142980d9a6278.mp3?hdnea=exp=1782579827~acl=/api/1/1/7/1/d/0/71df7387d989446e214142980d9a6278.mp3*~data=user_id=0,application_id=42~hmac=b125efcf11e1a3612e3057b2c8b6ca0e5a1fab4051cfced75c9ece9d5b488eba','https://www.deezer.com/track/134814772',14381184),
(67,'Dies Irae',76714,'00:05:46',2993,466,'https://cdnt-preview.dzcdn.net/api/1/1/4/4/9/0/449af1a22e2dc8ed59e83b08f6c72587.mp3?hdnea=exp=1782579827~acl=/api/1/1/4/4/9/0/449af1a22e2dc8ed59e83b08f6c72587.mp3*~data=user_id=0,application_id=42~hmac=4e0ca657ff9c36ae72a0856273fcd0f473a45cdf8704e1e5fd6765eef11ca14f','https://www.deezer.com/track/134814774',14381184),
(68,'Midas',476730,'00:07:28',81479,106,'https://cdnt-preview.dzcdn.net/api/1/1/0/5/7/0/0572f0639b74bce2c572a76ebd68884c.mp3?hdnea=exp=1782579829~acl=/api/1/1/0/5/7/0/0572f0639b74bce2c572a76ebd68884c.mp3*~data=user_id=0,application_id=42~hmac=6b018ba007fec37d9ba730c48f8ff1677b4f9938381b814ff60e102ad53f9e57','https://www.deezer.com/track/492784792',62384562),
(69,'Delos',476730,'00:06:44',169371,106,'https://cdnt-preview.dzcdn.net/api/1/1/1/0/3/0/10356f923f235c8d6e507f3ea49c0cc1.mp3?hdnea=exp=1782579829~acl=/api/1/1/1/0/3/0/10356f923f235c8d6e507f3ea49c0cc1.mp3*~data=user_id=0,application_id=42~hmac=5c7a20d948b3fc541b17d19594022a2beda384b608d120885ab7674266361438','https://www.deezer.com/track/492784802',62384562),
(70,'Toren',476730,'00:06:51',110829,106,'https://cdnt-preview.dzcdn.net/api/1/1/f/7/4/0/f74bf89acd5e233d2600f4a893b0bb16.mp3?hdnea=exp=1782579829~acl=/api/1/1/f/7/4/0/f74bf89acd5e233d2600f4a893b0bb16.mp3*~data=user_id=0,application_id=42~hmac=119d462e77fe81905a82154df2b734452512ccfb49a6b90cdc8a71f54d609c90','https://www.deezer.com/track/492784812',62384562),
(71,'Almajara',476730,'00:07:30',83094,106,'https://cdnt-preview.dzcdn.net/api/1/1/e/0/9/0/e09b1736a2c9348b3fc0ac06949587af.mp3?hdnea=exp=1782579829~acl=/api/1/1/e/0/9/0/e09b1736a2c9348b3fc0ac06949587af.mp3*~data=user_id=0,application_id=42~hmac=c55d6fc1787ba7c6be57f2f29cd496d3a03b0c31769db28cb25204df150302e4','https://www.deezer.com/track/492784822',62384562),
(72,'Jamal (Bercana Mix)',476730,'00:06:48',47363,106,'https://cdnt-preview.dzcdn.net/api/1/1/c/8/c/0/c8ce58180f75f3605ba783ad51e58023.mp3?hdnea=exp=1782579829~acl=/api/1/1/c/8/c/0/c8ce58180f75f3605ba783ad51e58023.mp3*~data=user_id=0,application_id=42~hmac=010c887c3954f5dc72f34f0505bb75f8a3afab36b2700c460cbb99c556d4b931','https://www.deezer.com/track/492784832',62384562),
(73,'Aigaio',476730,'00:06:52',77982,106,'https://cdnt-preview.dzcdn.net/api/1/1/2/2/7/0/227d34951526fd464e22cb8d727ee275.mp3?hdnea=exp=1782579829~acl=/api/1/1/2/2/7/0/227d34951526fd464e22cb8d727ee275.mp3*~data=user_id=0,application_id=42~hmac=5b425f059685d1e4ce624666f6eb08b0203a22f35b937575080ba8ab381f9675','https://www.deezer.com/track/492784842',62384562),
(74,'Vertigo',476730,'00:06:49',41313,106,'https://cdnt-preview.dzcdn.net/api/1/1/d/a/9/0/da9f06bed4fb28aff70d4cf240f12a1d.mp3?hdnea=exp=1782579829~acl=/api/1/1/d/a/9/0/da9f06bed4fb28aff70d4cf240f12a1d.mp3*~data=user_id=0,application_id=42~hmac=9a2f6a8b4162dd7f8cebad6112116d9edda6f75af377dd083ffd0409462f6d25','https://www.deezer.com/track/492784852',62384562),
(75,'Yesil',476730,'00:06:03',70035,106,'https://cdnt-preview.dzcdn.net/api/1/1/5/c/7/0/5c79fa697dc2aec1b204ab80b7e81fad.mp3?hdnea=exp=1782579829~acl=/api/1/1/5/c/7/0/5c79fa697dc2aec1b204ab80b7e81fad.mp3*~data=user_id=0,application_id=42~hmac=3d6b7998b4db953ee2d1a9cc3d24ca7c4cf10206310dd88ee91625b6a1bb3a87','https://www.deezer.com/track/492784862',62384562),
(76,'Hesperides',476730,'00:07:45',25388,106,'https://cdnt-preview.dzcdn.net/api/1/1/4/f/f/0/4ff9435daff9c2c6ea022f9d07f17671.mp3?hdnea=exp=1782579829~acl=/api/1/1/4/f/f/0/4ff9435daff9c2c6ea022f9d07f17671.mp3*~data=user_id=0,application_id=42~hmac=db51843bcf7815e7524f4f4bafbc6df2af4a226b426a869788929af23769b83f','https://www.deezer.com/track/492784872',62384562),
(77,'Killshot (DJ Seinfeld Remix)',86801562,'00:03:28',52710,85,'https://cdnt-preview.dzcdn.net/api/1/1/a/b/e/0/abe26d0115d36e6e27d6a68b614e451a.mp3?hdnea=exp=1782579830~acl=/api/1/1/a/b/e/0/abe26d0115d36e6e27d6a68b614e451a.mp3*~data=user_id=0,application_id=42~hmac=abe11dd8ab31b696c562258307684e0d24cf5b8ec803b7d8e6ca63028caa137e','https://www.deezer.com/track/1402943032',236914422),
(78,'Krunk (LARRY PINK THE HUMAN Remix)',86801562,'00:04:10',74868,85,'https://cdnt-preview.dzcdn.net/api/1/1/7/b/e/0/7beb1e914d4db275affd89eb864c285e.mp3?hdnea=exp=1782579830~acl=/api/1/1/7/b/e/0/7beb1e914d4db275affd89eb864c285e.mp3*~data=user_id=0,application_id=42~hmac=be89b5a03d9181b6093eb786d9b768ba26da4e1dd958a7d16a207777be0ed0a7','https://www.deezer.com/track/1402943042',236914422),
(79,'Icy Violence (Champion Remix)',86801562,'00:03:37',70465,85,'https://cdnt-preview.dzcdn.net/api/1/1/6/c/5/0/6c5da8358be662c18e011b9c77adede1.mp3?hdnea=exp=1782579830~acl=/api/1/1/6/c/5/0/6c5da8358be662c18e011b9c77adede1.mp3*~data=user_id=0,application_id=42~hmac=549c6dcab52dc37225922563d9d0a6ba4eb3e69b12e9f354f16178f050a770c3','https://www.deezer.com/track/1402943052',236914422),
(80,'Mountain Jack (ChopNotSlop Remix)',86801562,'00:05:54',105785,85,'https://cdnt-preview.dzcdn.net/api/1/1/f/7/e/0/f7ee03dc0d02b19664f3b968e03d80dc.mp3?hdnea=exp=1782579830~acl=/api/1/1/f/7/e/0/f7ee03dc0d02b19664f3b968e03d80dc.mp3*~data=user_id=0,application_id=42~hmac=9d729ba6846739828225ea48a76f1f5e0aa79e40c37e88af3a76e261634f44ef','https://www.deezer.com/track/1402943062',236914422),
(81,'Intro',364225,'00:00:51',27489,152,'https://cdnt-preview.dzcdn.net/api/1/1/8/0/b/0/80b974ec01ffe7c17359ff7f982864ad.mp3?hdnea=exp=1782579830~acl=/api/1/1/8/0/b/0/80b974ec01ffe7c17359ff7f982864ad.mp3*~data=user_id=0,application_id=42~hmac=be86ca7a2fb1ae05e264ebbda34a3ed462c7c4eeb9e5ab47956a44dc97bf6925','https://www.deezer.com/track/13530288',1239023),
(82,'The Serpent',364225,'00:03:36',172184,152,'https://cdnt-preview.dzcdn.net/api/1/1/e/7/6/0/e76d3cc5d5f0913a56993c970c181581.mp3?hdnea=exp=1782579830~acl=/api/1/1/e/7/6/0/e76d3cc5d5f0913a56993c970c181581.mp3*~data=user_id=0,application_id=42~hmac=f4e9a395d6188fe71c161bb680d7bab985c1b99f8f614a079a82e25df4ace0db','https://www.deezer.com/track/13530289',1239023),
(83,'This Day Is Mine',364225,'00:03:19',166666,152,'https://cdnt-preview.dzcdn.net/api/1/1/d/0/4/0/d046d81b3654700d19a356ec271396d4.mp3?hdnea=exp=1782579830~acl=/api/1/1/d/0/4/0/d046d81b3654700d19a356ec271396d4.mp3*~data=user_id=0,application_id=42~hmac=e18baa6b6829fd5b20f256070410661a4700bff38e5ad25a8cdd2e5832935bfa','https://www.deezer.com/track/13530290',1239023),
(84,'City of Vultures',364225,'00:04:59',97450,152,'https://cdnt-preview.dzcdn.net/api/1/1/a/f/8/0/af874932af7250b976ac7acf4794eeff.mp3?hdnea=exp=1782579830~acl=/api/1/1/a/f/8/0/af874932af7250b976ac7acf4794eeff.mp3*~data=user_id=0,application_id=42~hmac=d3985b34db54837ab596ae13f8a9d784c66dcf74982f38451bb46883b93993d6','https://www.deezer.com/track/13530291',1239023),
(85,'Talking in Whispers',364225,'00:04:07',233374,152,'https://cdnt-preview.dzcdn.net/api/1/1/f/0/6/0/f06afd3049078b66e4b10d1ecb290057.mp3?hdnea=exp=1782579830~acl=/api/1/1/f/0/6/0/f06afd3049078b66e4b10d1ecb290057.mp3*~data=user_id=0,application_id=42~hmac=5950910715d843787915e643720dce72a2aeada82ed492bb596ab31cce971a14','https://www.deezer.com/track/13530292',1239023),
(86,'God Can Bleed',364225,'00:03:46',53146,152,'https://cdnt-preview.dzcdn.net/api/1/1/3/5/8/0/3588f5df2fdf0ac867c71ad2202f09cd.mp3?hdnea=exp=1782579830~acl=/api/1/1/3/5/8/0/3588f5df2fdf0ac867c71ad2202f09cd.mp3*~data=user_id=0,application_id=42~hmac=9a4c62db550a1be8146522d39a385a02c25f5be2bb89e17746e4c52c22605796','https://www.deezer.com/track/13530293',1239023),
(87,'Power Through Fear',364225,'00:03:51',53001,152,'https://cdnt-preview.dzcdn.net/api/1/1/9/f/c/0/9fc5c0c9584cea400b0cebbbce678b7d.mp3?hdnea=exp=1782579830~acl=/api/1/1/9/f/c/0/9fc5c0c9584cea400b0cebbbce678b7d.mp3*~data=user_id=0,application_id=42~hmac=f38de0805a34ed547b04772f24fdb95a8558c3f6c6b08804223c6a5b1b275055','https://www.deezer.com/track/13530294',1239023),
(88,'Nothing Left',364225,'00:03:05',103848,152,'https://cdnt-preview.dzcdn.net/api/1/1/3/9/a/0/39a7187264d46e658aceeb404a044b5e.mp3?hdnea=exp=1782579830~acl=/api/1/1/3/9/a/0/39a7187264d46e658aceeb404a044b5e.mp3*~data=user_id=0,application_id=42~hmac=4ef4c30de02b7fcbd85a8de8adde83b92d889f7ceca6676cf5b26fc042b4adfb','https://www.deezer.com/track/13530295',1239023),
(89,'We Will Last Forever',364225,'00:04:14',53445,152,'https://cdnt-preview.dzcdn.net/api/1/1/3/a/5/0/3a55a63c227c7f883140e469c23de789.mp3?hdnea=exp=1782579830~acl=/api/1/1/3/a/5/0/3a55a63c227c7f883140e469c23de789.mp3*~data=user_id=0,application_id=42~hmac=439eac7d143455129d93367629df6a0a9aebc1f328ea3aa7bb9ba5c93486aa78','https://www.deezer.com/track/13530296',1239023),
(90,'Illusions',364225,'00:04:07',141874,152,'https://cdnt-preview.dzcdn.net/api/1/1/6/c/b/0/6cb051d36f8c4f04a43ce758127b7442.mp3?hdnea=exp=1782579830~acl=/api/1/1/6/c/b/0/6cb051d36f8c4f04a43ce758127b7442.mp3*~data=user_id=0,application_id=42~hmac=3b24033ff7f30bbac2508c5bd5ca0312206afbc09bc4b74bf282ecbac692d888','https://www.deezer.com/track/13530297',1239023),
(91,'Roads',364225,'00:04:18',74491,152,'https://cdnt-preview.dzcdn.net/api/1/1/b/7/0/0/b70043df3335eafcf52ff7fb467b071e.mp3?hdnea=exp=1782579830~acl=/api/1/1/b/7/0/0/b70043df3335eafcf52ff7fb467b071e.mp3*~data=user_id=0,application_id=42~hmac=6397d42d920aeb30e9eecf954f3a9fd8b012d6e8a29bcbc7d19da67a8b9ec344','https://www.deezer.com/track/13530298',1239023),
(92,'Bridges Will Burn',364225,'00:05:08',103946,152,'https://cdnt-preview.dzcdn.net/api/1/1/1/5/6/0/1564788754aca3b2cdcc3f704153931a.mp3?hdnea=exp=1782579830~acl=/api/1/1/1/5/6/0/1564788754aca3b2cdcc3f704153931a.mp3*~data=user_id=0,application_id=42~hmac=4d4d09cb31edb255e60f922e2119ee4718f8a1e1c391dc8593eb36ae0b3be05b','https://www.deezer.com/track/13530299',1239023),
(93,'Tubular Bells (Pt. I)',990,'00:25:58',422630,152,'https://cdnt-preview.dzcdn.net/api/1/1/0/4/4/0/044b19404d8e0849faa9330b5c36161e.mp3?hdnea=exp=1782579831~acl=/api/1/1/0/4/4/0/044b19404d8e0849faa9330b5c36161e.mp3*~data=user_id=0,application_id=42~hmac=3b2dd90f4abeb3a747055a90a7658faf1d6caabbdbeee0561ecd0bb97e840495','https://www.deezer.com/track/99930576',10232416),
(94,'Tubular Bells (Pt. II)',990,'00:23:17',332666,152,'https://cdnt-preview.dzcdn.net/api/1/1/4/2/5/0/425808faf13ceb0bf792b0397de1e11c.mp3?hdnea=exp=1782579831~acl=/api/1/1/4/2/5/0/425808faf13ceb0bf792b0397de1e11c.mp3*~data=user_id=0,application_id=42~hmac=0075e0c11a348588ff83baf1de06aed61eb041253fc48ff485eebefb59d87cb9','https://www.deezer.com/track/99930578',10232416),
(95,'Mike Oldfield\'s Single (Theme From Tubular Bells)',990,'00:03:53',335819,152,'https://cdnt-preview.dzcdn.net/api/1/1/0/1/3/0/01388ae3c82442da00469c0d66b980e5.mp3?hdnea=exp=1782579831~acl=/api/1/1/0/1/3/0/01388ae3c82442da00469c0d66b980e5.mp3*~data=user_id=0,application_id=42~hmac=6b271483d8e92668b1fcaba7c62e2ff8b66567c8368c69d60f5baae7b34d86bf','https://www.deezer.com/track/99930580',10232416),
(96,'Sailor\'s Hornpipe (Viv Stanshall Version)',990,'00:02:48',237946,152,'https://cdnt-preview.dzcdn.net/api/1/1/d/9/e/0/d9edf4ef6eeda80875ad837d6cbcf1cc.mp3?hdnea=exp=1782579831~acl=/api/1/1/d/9/e/0/d9edf4ef6eeda80875ad837d6cbcf1cc.mp3*~data=user_id=0,application_id=42~hmac=ec0259753ee2822147e6ef3b3d330ab1b05e2c64ad52384e313583a88dfc9a2a','https://www.deezer.com/track/99930582',10232416),
(97,'In The End',488613,'00:03:54',635768,85,'https://cdnt-preview.dzcdn.net/api/1/1/0/4/9/0/0492bdc2d83fd425ccd20b450df18b6b.mp3?hdnea=exp=1782579832~acl=/api/1/1/0/4/9/0/0492bdc2d83fd425ccd20b450df18b6b.mp3*~data=user_id=0,application_id=42~hmac=60b388c9c7301188b59f70cf5dcc2b51b6f4c3d70fbd0e86690d699d85c85168','https://www.deezer.com/track/649768322',90597102),
(98,'In The End (Mellen Gi Remix)',488613,'00:03:38',731270,85,'https://cdnt-preview.dzcdn.net/api/1/1/a/6/7/0/a6769cff4e0229fee662352636836b5f.mp3?hdnea=exp=1782579832~acl=/api/1/1/a/6/7/0/a6769cff4e0229fee662352636836b5f.mp3*~data=user_id=0,application_id=42~hmac=2037e5a0fdfbef8846d7cf77637b5a659f77bd8c0134c405f7845e93fa90f380','https://www.deezer.com/track/649768332',90597102),
(99,'In The End (Instrumental)',488613,'00:03:54',252818,85,'https://cdnt-preview.dzcdn.net/api/1/1/b/2/c/0/b2cdb455cd7ee7b0a1132fae502ab36e.mp3?hdnea=exp=1782579832~acl=/api/1/1/b/2/c/0/b2cdb455cd7ee7b0a1132fae502ab36e.mp3*~data=user_id=0,application_id=42~hmac=a988c36e227255faa5fdf798e1a9959d82cc7be6ef0281a36eb52e5a9c2759b4','https://www.deezer.com/track/649768342',90597102),
(100,'World Intro',476730,'00:02:56',40109,106,'https://cdnt-preview.dzcdn.net/api/1/1/9/1/8/0/918b2b5b84be91b3613b2073e328c5d3.mp3?hdnea=exp=1782579832~acl=/api/1/1/9/1/8/0/918b2b5b84be91b3613b2073e328c5d3.mp3*~data=user_id=0,application_id=42~hmac=d245bc42032e034d290875e076276b248492ad0865f56593e578658b0d96b3a5','https://www.deezer.com/track/683052122',97619012),
(101,'Skylark',476730,'00:08:30',101153,106,'https://cdnt-preview.dzcdn.net/api/1/1/e/5/1/0/e51d7190a40288d0cc22b822d93702fc.mp3?hdnea=exp=1782579832~acl=/api/1/1/e/5/1/0/e51d7190a40288d0cc22b822d93702fc.mp3*~data=user_id=0,application_id=42~hmac=5db3f1c045632193bcd9c9078d114912b581bd296bf5460471adaa9a4b745510','https://www.deezer.com/track/683052132',97619012),
(102,'Cretan Heart',476730,'00:06:31',153358,106,'https://cdnt-preview.dzcdn.net/api/1/1/5/d/5/0/5d545585bba2d7c274b7a1b80e073e9f.mp3?hdnea=exp=1782579832~acl=/api/1/1/5/d/5/0/5d545585bba2d7c274b7a1b80e073e9f.mp3*~data=user_id=0,application_id=42~hmac=ad0e9bc890896ec2b8b21d4a32d1a3be178c117d91ef875506e8ed624219092e','https://www.deezer.com/track/683052142',97619012),
(103,'Scorpios',476730,'00:06:32',194649,106,'https://cdnt-preview.dzcdn.net/api/1/1/f/9/9/0/f9974d6e65737124b10e3c2d0332c87c.mp3?hdnea=exp=1782579832~acl=/api/1/1/f/9/9/0/f9974d6e65737124b10e3c2d0332c87c.mp3*~data=user_id=0,application_id=42~hmac=29f6ffacd874873d7892e8980adfb37543531582c30dfbbe11ce8b2fd7356986','https://www.deezer.com/track/683052152',97619012),
(104,'Sol',476730,'00:07:01',149843,106,'https://cdnt-preview.dzcdn.net/api/1/1/f/f/f/0/fff00c3d19d5b8a82e1ed882c6011df5.mp3?hdnea=exp=1782579832~acl=/api/1/1/f/f/f/0/fff00c3d19d5b8a82e1ed882c6011df5.mp3*~data=user_id=0,application_id=42~hmac=f778a0cc931416b31d8d522e3cfd81d0e55b72ea0af4eebc616d8f37f0d8c567','https://www.deezer.com/track/683052162',97619012),
(105,'Misirlou',476730,'00:07:59',190485,106,'https://cdnt-preview.dzcdn.net/api/1/1/3/7/f/0/37fe611716a545a9db798533a2843ae6.mp3?hdnea=exp=1782579832~acl=/api/1/1/3/7/f/0/37fe611716a545a9db798533a2843ae6.mp3*~data=user_id=0,application_id=42~hmac=dc5adfa4743e06a6eed11721e4aff6d5eb44e9111597ce4295655944750609e9','https://www.deezer.com/track/683052172',97619012),
(106,'Cadence',476730,'00:06:44',40226,106,'https://cdnt-preview.dzcdn.net/api/1/1/8/8/c/0/88c156def14a7ca04548f7699314b7d9.mp3?hdnea=exp=1782579832~acl=/api/1/1/8/8/c/0/88c156def14a7ca04548f7699314b7d9.mp3*~data=user_id=0,application_id=42~hmac=de26df76674c7bafed6d3c31978370d4d70524edc2a99aeba36356b051c8549b','https://www.deezer.com/track/683052182',97619012),
(107,'Summer Waltz',476730,'00:06:45',71609,106,'https://cdnt-preview.dzcdn.net/api/1/1/d/f/2/0/df25cd97f25d94911132dfd9bdaf2e2f.mp3?hdnea=exp=1782579832~acl=/api/1/1/d/f/2/0/df25cd97f25d94911132dfd9bdaf2e2f.mp3*~data=user_id=0,application_id=42~hmac=afecfec2f487302c55f1af3f2827b1136ab301ec145312a4fff30698f6babb78','https://www.deezer.com/track/683052192',97619012),
(108,'Dismas',476730,'00:07:19',63172,106,'https://cdnt-preview.dzcdn.net/api/1/1/9/5/6/0/9564193c1b4825aa1391d30e6dae64dd.mp3?hdnea=exp=1782579832~acl=/api/1/1/9/5/6/0/9564193c1b4825aa1391d30e6dae64dd.mp3*~data=user_id=0,application_id=42~hmac=163186ac6211a5b334635ab8566b4ee64c507784cf25d727e2c344d3f04125c6','https://www.deezer.com/track/683052202',97619012),
(109,'Anlar (Orchestra Outro)',476730,'00:03:42',65523,106,'https://cdnt-preview.dzcdn.net/api/1/1/8/d/b/0/8dba161bd2931f3bf0799fef499e60ff.mp3?hdnea=exp=1782579832~acl=/api/1/1/8/d/b/0/8dba161bd2931f3bf0799fef499e60ff.mp3*~data=user_id=0,application_id=42~hmac=bb91ab9154d7383e1170b984884ae860c6274bb90772418a5d689ec8a16b2185','https://www.deezer.com/track/683052212',97619012),
(110,'Xtal',580,'00:04:54',592724,106,'https://cdnt-preview.dzcdn.net/api/1/1/c/3/9/0/c398bde119c130008657c90479476a5b.mp3?hdnea=exp=1782579833~acl=/api/1/1/c/3/9/0/c398bde119c130008657c90479476a5b.mp3*~data=user_id=0,application_id=42~hmac=5ed12e1c9cf3254a135958a2bf4ac1b6134b41ef47dfe945d8c8cae196748db9','https://www.deezer.com/track/123789736',12977824),
(111,'Tha',580,'00:09:07',452619,106,'https://cdnt-preview.dzcdn.net/api/1/1/c/2/6/0/c26038d965e6c9193c304895d46431b2.mp3?hdnea=exp=1782579833~acl=/api/1/1/c/2/6/0/c26038d965e6c9193c304895d46431b2.mp3*~data=user_id=0,application_id=42~hmac=acd0f278df90dde156967f65155a3361e4e23a779dc4e9961a80e2081aa5ece7','https://www.deezer.com/track/123789738',12977824),
(112,'Pulsewidth',580,'00:03:48',579491,106,'https://cdnt-preview.dzcdn.net/api/1/1/9/d/6/0/9d6b9b31392feebab41bb6a9cf8914f1.mp3?hdnea=exp=1782579833~acl=/api/1/1/9/d/6/0/9d6b9b31392feebab41bb6a9cf8914f1.mp3*~data=user_id=0,application_id=42~hmac=d7b82aa3645183d730f9a1c81b6ab8dedc387178920899db81f113397fa6b57f','https://www.deezer.com/track/123789740',12977824),
(113,'Ageispolis',580,'00:05:23',479619,106,'https://cdnt-preview.dzcdn.net/api/1/1/e/5/e/0/e5e415e7cff3c5f729db7257e57d5a96.mp3?hdnea=exp=1782579833~acl=/api/1/1/e/5/e/0/e5e415e7cff3c5f729db7257e57d5a96.mp3*~data=user_id=0,application_id=42~hmac=23a1d94a496d01ed43a6f459847b76fecc2bb4f9adbfc61cbc80cac10a454b78','https://www.deezer.com/track/123789742',12977824),
(114,'I',580,'00:01:17',400397,106,'https://cdnt-preview.dzcdn.net/api/1/1/7/a/f/0/7afd7f9078f627ce768fd72fd4139627.mp3?hdnea=exp=1782579833~acl=/api/1/1/7/a/f/0/7afd7f9078f627ce768fd72fd4139627.mp3*~data=user_id=0,application_id=42~hmac=080c1e693bedd6a16f16b37b4066408e43ef13169ae184222b48fe938785029a','https://www.deezer.com/track/123789744',12977824),
(115,'Green Calx',580,'00:06:05',374901,106,'https://cdnt-preview.dzcdn.net/api/1/1/c/1/5/0/c156b2640d92791753a3413f5128c496.mp3?hdnea=exp=1782579833~acl=/api/1/1/c/1/5/0/c156b2640d92791753a3413f5128c496.mp3*~data=user_id=0,application_id=42~hmac=6fc46beff845fe5c75de83dac07326e35db490f659ab688a44eaf258751fdf76','https://www.deezer.com/track/123789746',12977824),
(116,'Heliosphan',580,'00:04:54',427706,106,'https://cdnt-preview.dzcdn.net/api/1/1/9/9/1/0/9916025292197e856830751fd9349603.mp3?hdnea=exp=1782579833~acl=/api/1/1/9/9/1/0/9916025292197e856830751fd9349603.mp3*~data=user_id=0,application_id=42~hmac=6c451368353e5dc635218a3e6b5ec8f5fb8ff5d6217afde0a2fec8ebaace5789','https://www.deezer.com/track/123789748',12977824),
(117,'We Are the Music Makers',580,'00:07:44',377900,106,'https://cdnt-preview.dzcdn.net/api/1/1/c/2/a/0/c2a0da5464346fb1069eb07fa09e7bcf.mp3?hdnea=exp=1782579833~acl=/api/1/1/c/2/a/0/c2a0da5464346fb1069eb07fa09e7bcf.mp3*~data=user_id=0,application_id=42~hmac=3cf152be2940153221019be2870e2b8e0e4563fdac54c32ad548c5a24cb31a83','https://www.deezer.com/track/123789750',12977824),
(118,'Schottkey 7th Path',580,'00:05:09',361524,106,'https://cdnt-preview.dzcdn.net/api/1/1/4/e/1/0/4e14ee0e94dc7b5a60f4af5629e36cb7.mp3?hdnea=exp=1782579833~acl=/api/1/1/4/e/1/0/4e14ee0e94dc7b5a60f4af5629e36cb7.mp3*~data=user_id=0,application_id=42~hmac=6a5d781d7c7c70aab4113a33e1c27db0b42e7307e10331986a4064bbfcc950d2','https://www.deezer.com/track/123789752',12977824),
(119,'Ptolemy',580,'00:07:14',360945,106,'https://cdnt-preview.dzcdn.net/api/1/1/e/8/5/0/e85b06cc79a53d7856efe457df1bbb78.mp3?hdnea=exp=1782579833~acl=/api/1/1/e/8/5/0/e85b06cc79a53d7856efe457df1bbb78.mp3*~data=user_id=0,application_id=42~hmac=71f017eeb37a1d0f6c18e1cb09bbf55d3d0f6f15202b2cb6ee3bf46944043c24','https://www.deezer.com/track/123789754',12977824),
(120,'Hedphelym',580,'00:06:04',311941,106,'https://cdnt-preview.dzcdn.net/api/1/1/3/0/9/0/3097b90086b9754a7f0db1edf3d2838c.mp3?hdnea=exp=1782579833~acl=/api/1/1/3/0/9/0/3097b90086b9754a7f0db1edf3d2838c.mp3*~data=user_id=0,application_id=42~hmac=c8a30ed9701da1bcf3ffadde9b5eb4b5af5dc582cc277f974155e4480f9bdbb3','https://www.deezer.com/track/123789756',12977824),
(121,'Delphium',580,'00:05:29',315960,106,'https://cdnt-preview.dzcdn.net/api/1/1/c/f/5/0/cf508a81585f9a8792091205c7120ad3.mp3?hdnea=exp=1782579833~acl=/api/1/1/c/f/5/0/cf508a81585f9a8792091205c7120ad3.mp3*~data=user_id=0,application_id=42~hmac=35f61c5f83f69d225e3b7a2dab748634af6d9f36d90806b0356573cdbb1b73b6','https://www.deezer.com/track/123789758',12977824),
(122,'Actium',580,'00:07:35',341626,106,'https://cdnt-preview.dzcdn.net/api/1/1/4/8/a/0/48a0663cdff1ceefa9e8e5823febcdc9.mp3?hdnea=exp=1782579833~acl=/api/1/1/4/8/a/0/48a0663cdff1ceefa9e8e5823febcdc9.mp3*~data=user_id=0,application_id=42~hmac=d56ca8293ac19577a945415947e196d6b6534826f9f1e2d9cf0e2a2dce270530','https://www.deezer.com/track/123789760',12977824),
(123,'So Much Trouble In The World',719,'00:04:00',691151,144,'https://cdnt-preview.dzcdn.net/api/1/1/4/c/e/0/4cecfe229b67fac3729fa5ff692d6ff9.mp3?hdnea=exp=1782579835~acl=/api/1/1/4/c/e/0/4cecfe229b67fac3729fa5ff692d6ff9.mp3*~data=user_id=0,application_id=42~hmac=4ff3090b4d58f875dd5eba1dcd8b994a92ae52efc82f583f7d51364da2e24a98','https://www.deezer.com/track/2502149',245955),
(124,'Zimbabwe',719,'00:03:50',776230,144,'https://cdnt-preview.dzcdn.net/api/1/1/c/3/f/0/c3fa38e85f6c9b5395e862022d6f0740.mp3?hdnea=exp=1782579835~acl=/api/1/1/c/3/f/0/c3fa38e85f6c9b5395e862022d6f0740.mp3*~data=user_id=0,application_id=42~hmac=7f596043ba1e88f5ae1ec3cc95fcc6767e24d232eddf3211a6237e8bcfb66259','https://www.deezer.com/track/2502150',245955),
(125,'Top Rankin\'',719,'00:03:10',507948,144,'https://cdnt-preview.dzcdn.net/api/1/1/b/a/d/0/badf69d0d4c3091863912863856856d3.mp3?hdnea=exp=1782579835~acl=/api/1/1/b/a/d/0/badf69d0d4c3091863912863856856d3.mp3*~data=user_id=0,application_id=42~hmac=497dada23ea9051d0d8d9a5841114269528815f61c8c2fd39d623343580147a0','https://www.deezer.com/track/2502151',245955),
(126,'Babylon System',719,'00:04:21',517072,144,'https://cdnt-preview.dzcdn.net/api/1/1/1/8/5/0/185ad6585d86094407577d373e6af629.mp3?hdnea=exp=1782579835~acl=/api/1/1/1/8/5/0/185ad6585d86094407577d373e6af629.mp3*~data=user_id=0,application_id=42~hmac=366a3d5f9cf130e2f5d740c9a64d014077bef35ee43fd9b5bbadb8575a79c702','https://www.deezer.com/track/2502152',245955),
(127,'Survival',719,'00:03:54',558983,144,'https://cdnt-preview.dzcdn.net/api/1/1/0/2/9/0/0296dec835c1383499899ffe6c7dc7b2.mp3?hdnea=exp=1782579835~acl=/api/1/1/0/2/9/0/0296dec835c1383499899ffe6c7dc7b2.mp3*~data=user_id=0,application_id=42~hmac=d65ff69c4bf2e7074315a55f1138156fb77e7ef88ae99ebba0ad083c93c361bc','https://www.deezer.com/track/2502153',245955),
(128,'Africa Unite',719,'00:02:55',681836,144,'https://cdnt-preview.dzcdn.net/api/1/1/c/6/3/0/c6350fd0cbbe2982612c0180b613e88c.mp3?hdnea=exp=1782579835~acl=/api/1/1/c/6/3/0/c6350fd0cbbe2982612c0180b613e88c.mp3*~data=user_id=0,application_id=42~hmac=7776c258fd781bc2cbd519da7eb7f1193d41bbe16d03788c8d0b5d928f3be93c','https://www.deezer.com/track/2502154',245955),
(129,'One Drop',719,'00:03:50',729109,144,'https://cdnt-preview.dzcdn.net/api/1/1/d/f/d/0/dfd4bac342dfaae41a66e1e5099d8e6e.mp3?hdnea=exp=1782579835~acl=/api/1/1/d/f/d/0/dfd4bac342dfaae41a66e1e5099d8e6e.mp3*~data=user_id=0,application_id=42~hmac=6ed657da17cbcdd675c53635fe5011636d5a527f1601c2e022cce9637cc64ebc','https://www.deezer.com/track/2502155',245955),
(130,'Ride Natty Ride',719,'00:03:51',526860,144,'https://cdnt-preview.dzcdn.net/api/1/1/f/5/c/0/f5ccf82676f3927483b318ba4d145fe6.mp3?hdnea=exp=1782579835~acl=/api/1/1/f/5/c/0/f5ccf82676f3927483b318ba4d145fe6.mp3*~data=user_id=0,application_id=42~hmac=861267ce878eb9007ebe13a553e7ea970d08502d1ed3e698b4eb1a70c806a5d0','https://www.deezer.com/track/2502156',245955),
(131,'Ambush In The Night',719,'00:03:12',522424,144,'https://cdnt-preview.dzcdn.net/api/1/1/b/1/f/0/b1f5e16eaf310a36a9da29d939bb10e0.mp3?hdnea=exp=1782579835~acl=/api/1/1/b/1/f/0/b1f5e16eaf310a36a9da29d939bb10e0.mp3*~data=user_id=0,application_id=42~hmac=452cdcce9b6461a241a79dde61e699360eeb6d6f603c71eb7c912130aea87c00','https://www.deezer.com/track/2502157',245955),
(132,'Wake Up And Live',719,'00:05:00',475161,144,'https://cdnt-preview.dzcdn.net/api/1/1/c/a/6/0/ca6cc8a8c266aa727183a009bdad12df.mp3?hdnea=exp=1782579835~acl=/api/1/1/c/a/6/0/ca6cc8a8c266aa727183a009bdad12df.mp3*~data=user_id=0,application_id=42~hmac=8152d1f4e03c3c0a8aa15b91c83799212cf963bc4cd09ce2f4bb1899a309d399','https://www.deezer.com/track/2502158',245955),
(133,'Ride Natty Ride (12” Mix / Bonus Track)',719,'00:06:22',448694,144,'https://cdnt-preview.dzcdn.net/api/1/1/3/f/3/0/3f3961869817b43ac526f2cb07cd57f1.mp3?hdnea=exp=1782579835~acl=/api/1/1/3/f/3/0/3f3961869817b43ac526f2cb07cd57f1.mp3*~data=user_id=0,application_id=42~hmac=6137699c34af5b93c39dde7087af6fb22adb0b78e37ac19bf21ea7fef86511e2','https://www.deezer.com/track/2502159',245955),
(134,'High Priests',2421,'00:02:41',162228,98,'https://cdnt-preview.dzcdn.net/api/1/1/a/3/5/0/a354556cd79cb623210397ab873cdf87.mp3?hdnea=exp=1782579835~acl=/api/1/1/a/3/5/0/a354556cd79cb623210397ab873cdf87.mp3*~data=user_id=0,application_id=42~hmac=1346e911ead95c6250c4774631960d2597d1cb018f5458260b99dcedb0ed955b','https://www.deezer.com/track/1156198',123825),
(135,'Dance Of Love',2421,'00:02:39',145646,98,'https://cdnt-preview.dzcdn.net/api/1/1/e/9/2/0/e92e632808e98e1ec774014e858f7a93.mp3?hdnea=exp=1782579835~acl=/api/1/1/e/9/2/0/e92e632808e98e1ec774014e858f7a93.mp3*~data=user_id=0,application_id=42~hmac=fc643600a0fb2d75c1467026fd37d9ad4d16502726525b061a4905b6de6dae50','https://www.deezer.com/track/1156199',123825),
(136,'Carrickfergus',2421,'00:03:50',92019,98,'https://cdnt-preview.dzcdn.net/api/1/1/3/2/3/0/323383247f287623a67adcaae72cc531.mp3?hdnea=exp=1782579835~acl=/api/1/1/3/2/3/0/323383247f287623a67adcaae72cc531.mp3*~data=user_id=0,application_id=42~hmac=5bf329a6f65a06d6352f6109d347c60a06ebdd79b758856de5683a667427b17a','https://www.deezer.com/track/1156200',123825),
(137,'Duelling Violins',2421,'00:03:42',262031,98,'https://cdnt-preview.dzcdn.net/api/1/1/a/0/5/0/a050b549227289938cbd1cb86597b321.mp3?hdnea=exp=1782579835~acl=/api/1/1/a/0/5/0/a050b549227289938cbd1cb86597b321.mp3*~data=user_id=0,application_id=42~hmac=55fa368c423782972da783b7cf4d4b0b8847bcfd24bcaa6ffef49a7d305cd6b8','https://www.deezer.com/track/1156201',123825),
(138,'Whispering Wind',2421,'00:02:47',143947,98,'https://cdnt-preview.dzcdn.net/api/1/1/9/1/4/0/9146943fe35b43ecdee30f796bb32a54.mp3?hdnea=exp=1782579835~acl=/api/1/1/9/1/4/0/9146943fe35b43ecdee30f796bb32a54.mp3*~data=user_id=0,application_id=42~hmac=e3a29983225f3549bd491ddcc7ee1f406949006ed2d3372492d13ebfeed61075','https://www.deezer.com/track/1156202',123825),
(139,'Dance Above The Rainbow',2421,'00:02:59',249293,98,'https://cdnt-preview.dzcdn.net/api/1/1/b/f/d/0/bfdf9ae91d2def14f5230b28623b1e0a.mp3?hdnea=exp=1782579835~acl=/api/1/1/b/f/d/0/bfdf9ae91d2def14f5230b28623b1e0a.mp3*~data=user_id=0,application_id=42~hmac=239bb8b4e042a7722f1439e258143eabcfa0b6dd0cb378839321b35ab8d8b957','https://www.deezer.com/track/1156203',123825),
(140,'The Dawning',2421,'00:08:03',153612,98,'https://cdnt-preview.dzcdn.net/api/1/1/a/f/1/0/af1eb7661ca0d9267438c6324b8bfe20.mp3?hdnea=exp=1782579835~acl=/api/1/1/a/f/1/0/af1eb7661ca0d9267438c6324b8bfe20.mp3*~data=user_id=0,application_id=42~hmac=e4e9697d0005ecd2e7c212175a2be738fbd98641f3c46daf2bc9dc886e2b9e13','https://www.deezer.com/track/1156204',123825),
(141,'Spirits Lament (1998 Version)',2421,'00:02:00',137809,98,'https://cdnt-preview.dzcdn.net/api/1/1/d/c/0/0/dc0753468c426e0a10b68e726ac74b95.mp3?hdnea=exp=1782579835~acl=/api/1/1/d/c/0/0/dc0753468c426e0a10b68e726ac74b95.mp3*~data=user_id=0,application_id=42~hmac=052d264b61f663ab560cb7fa92d49ec299c25aa35533c5bb2380a31727985d3d','https://www.deezer.com/track/1156205',123825),
(142,'I Dreamt I Dwelt',2421,'00:03:23',109785,98,'https://cdnt-preview.dzcdn.net/api/1/1/b/3/9/0/b39efe6d19735731932bb7aab559eda9.mp3?hdnea=exp=1782579835~acl=/api/1/1/b/3/9/0/b39efe6d19735731932bb7aab559eda9.mp3*~data=user_id=0,application_id=42~hmac=70fffbd055639dcc7b9169ff9546d8c7d9bb8950ca48d0b863f9363ca7e5be93','https://www.deezer.com/track/1156206',123825),
(143,'Strings Of Fire',2421,'00:03:56',191269,98,'https://cdnt-preview.dzcdn.net/api/1/1/e/3/3/0/e3355b8937d6d3bfb3e505c933b8c6bd.mp3?hdnea=exp=1782579835~acl=/api/1/1/e/3/3/0/e3355b8937d6d3bfb3e505c933b8c6bd.mp3*~data=user_id=0,application_id=42~hmac=88c94869519fbe9ae8fd0516e553c9838cba058808ee2a90382b19cc83839d38','https://www.deezer.com/track/1156207',123825),
(144,'Hell\'s Kitchen',2421,'00:02:53',103999,98,'https://cdnt-preview.dzcdn.net/api/1/1/b/3/9/0/b390c12fb0f2a37785cc608a3b88f9b6.mp3?hdnea=exp=1782579835~acl=/api/1/1/b/3/9/0/b390c12fb0f2a37785cc608a3b88f9b6.mp3*~data=user_id=0,application_id=42~hmac=abf115b41fdb95af97fa0f6c27b1ee891551de0acfba636201f99c8378386ad1','https://www.deezer.com/track/1156208',123825),
(145,'Celtic Fire (Live Version)',2421,'00:02:58',154106,98,'https://cdnt-preview.dzcdn.net/api/1/1/f/3/2/0/f32ec7ee9aa71f2f1574def34fe9fa40.mp3?hdnea=exp=1782579835~acl=/api/1/1/f/3/2/0/f32ec7ee9aa71f2f1574def34fe9fa40.mp3*~data=user_id=0,application_id=42~hmac=9a106e7c2e6495f81e34b7b5d350cbe071edf8adc342bbbf024f3a3bf7a9d35c','https://www.deezer.com/track/1156209',123825),
(146,'Cry Of The Celts',2421,'00:04:28',337565,98,'https://cdnt-preview.dzcdn.net/api/1/1/5/b/1/0/5b1a31a764e7840085c6e6392f276d19.mp3?hdnea=exp=1782579836~acl=/api/1/1/5/b/1/0/5b1a31a764e7840085c6e6392f276d19.mp3*~data=user_id=0,application_id=42~hmac=7f8a371e545b92f5798d89a4206fa1983687374da0615f5e7a28a8ca15dc1cc2','https://www.deezer.com/track/2446091',240794),
(147,'Suil A Ruin',2421,'00:03:18',241431,98,'https://cdnt-preview.dzcdn.net/api/1/1/e/8/7/0/e873083813b5857378eebfb5fc531506.mp3?hdnea=exp=1782579836~acl=/api/1/1/e/8/7/0/e873083813b5857378eebfb5fc531506.mp3*~data=user_id=0,application_id=42~hmac=d7d859bca75d5dc55ec005ca6a80f54d0a61cc8a1f08372541db9509b989df2b','https://www.deezer.com/track/2446092',240794),
(148,'Celtic Dream',2421,'00:05:39',262244,98,'https://cdnt-preview.dzcdn.net/api/1/1/c/a/2/0/ca20810f862517a7f8df8ee891e16203.mp3?hdnea=exp=1782579836~acl=/api/1/1/c/a/2/0/ca20810f862517a7f8df8ee891e16203.mp3*~data=user_id=0,application_id=42~hmac=0781d1547d61632ab7f90791172a8539df2bbb7067569d9e5102c5096e17fb9e','https://www.deezer.com/track/2446093',240794),
(149,'Warriors',2421,'00:03:09',305259,98,'https://cdnt-preview.dzcdn.net/api/1/1/9/4/8/0/9480aa521c7e13d23cab9ab1c80c1158.mp3?hdnea=exp=1782579836~acl=/api/1/1/9/4/8/0/9480aa521c7e13d23cab9ab1c80c1158.mp3*~data=user_id=0,application_id=42~hmac=ed71c45003174467f6ddc1379a3803c06ea71126eb31e86741a02d2729de85f0','https://www.deezer.com/track/2446094',240794),
(150,'Gypsy',2421,'00:02:10',363736,98,'https://cdnt-preview.dzcdn.net/api/1/1/0/f/2/0/0f2eae99f42b0156780aed9059e13426.mp3?hdnea=exp=1782579836~acl=/api/1/1/0/f/2/0/0f2eae99f42b0156780aed9059e13426.mp3*~data=user_id=0,application_id=42~hmac=de5b3848a7ebeb558e10915efecf901ae9342cf47af82ea950166e622fc9e7e8','https://www.deezer.com/track/2446095',240794),
(151,'Breakout',2421,'00:03:49',255236,98,'https://cdnt-preview.dzcdn.net/api/1/1/3/9/e/0/39eb927c5565cb52904882b4b2c16957.mp3?hdnea=exp=1782579836~acl=/api/1/1/3/9/e/0/39eb927c5565cb52904882b4b2c16957.mp3*~data=user_id=0,application_id=42~hmac=f5db6a903931c03e232770542cfef173ff241e6cdce137a7a069e769ae66f2c9','https://www.deezer.com/track/2446096',240794),
(152,'Lord Of The Dance',2421,'00:04:47',383118,98,'https://cdnt-preview.dzcdn.net/api/1/1/8/a/f/0/8af5c05f31137bd7722d9b585d984f57.mp3?hdnea=exp=1782579836~acl=/api/1/1/8/a/f/0/8af5c05f31137bd7722d9b585d984f57.mp3*~data=user_id=0,application_id=42~hmac=1cdc2111ca3e33d75ebc7a21acf8d7b05814da01ad6c1da208d338d69af122e7','https://www.deezer.com/track/2446097',240794),
(153,'Spirit Of The New World',2421,'00:01:33',169004,98,'https://cdnt-preview.dzcdn.net/api/1/1/c/5/b/0/c5b0386bdb4b296a01aecb15295b4ee9.mp3?hdnea=exp=1782579836~acl=/api/1/1/c/5/b/0/c5b0386bdb4b296a01aecb15295b4ee9.mp3*~data=user_id=0,application_id=42~hmac=185fa0ad492bfb1ace0108423ed65c195fd92d76b31dcbca2735f85cac4e304f','https://www.deezer.com/track/2446098',240794),
(154,'Fiery Nights',2421,'00:03:10',241691,98,'https://cdnt-preview.dzcdn.net/api/1/1/b/a/0/0/ba01b61a41405cf4488c6d8df0c5aee4.mp3?hdnea=exp=1782579836~acl=/api/1/1/b/a/0/0/ba01b61a41405cf4488c6d8df0c5aee4.mp3*~data=user_id=0,application_id=42~hmac=aedcf64f0e1f6e544d0cf4078da0a0b074e4105ca051537769b6d539f893e04c','https://www.deezer.com/track/2446099',240794),
(155,'Lament',2421,'00:03:23',208907,98,'https://cdnt-preview.dzcdn.net/api/1/1/0/e/1/0/0e12aad1b4cd03a6de78386bdf38ac75.mp3?hdnea=exp=1782579836~acl=/api/1/1/0/e/1/0/0e12aad1b4cd03a6de78386bdf38ac75.mp3*~data=user_id=0,application_id=42~hmac=084450c0844c484c8d1eba98fe9a9596275b3a052ed5c8283cd43c38eb9120cd','https://www.deezer.com/track/2446100',240794),
(156,'Siamsa',2421,'00:04:29',279159,98,'https://cdnt-preview.dzcdn.net/api/1/1/3/5/2/0/352031301ebfa1d42148d9ae277a01f2.mp3?hdnea=exp=1782579836~acl=/api/1/1/3/5/2/0/352031301ebfa1d42148d9ae277a01f2.mp3*~data=user_id=0,application_id=42~hmac=5469a32b4d878508eacb7dd5e6cdfadf63d70a861924c1d0d7bdd0959cd8a38c','https://www.deezer.com/track/2446101',240794),
(157,'Our Wedding Day',2421,'00:03:26',180680,98,'https://cdnt-preview.dzcdn.net/api/1/1/c/d/d/0/cddc82d8d4de35629f9427b23c7b331e.mp3?hdnea=exp=1782579836~acl=/api/1/1/c/d/d/0/cddc82d8d4de35629f9427b23c7b331e.mp3*~data=user_id=0,application_id=42~hmac=15cd80d7c43712ec1b24f23138bef512c8bd7ab7c9e5dbc4129e1bad65c16030','https://www.deezer.com/track/2446102',240794),
(158,'Stolen Kiss',2421,'00:03:27',189102,98,'https://cdnt-preview.dzcdn.net/api/1/1/4/4/8/0/4480935ae07e896eee18b513edd71764.mp3?hdnea=exp=1782579836~acl=/api/1/1/4/4/8/0/4480935ae07e896eee18b513edd71764.mp3*~data=user_id=0,application_id=42~hmac=3033ae031225b2b9a5ee404dafb438a5f9b471d7f8ddab4556b39bf5b2286aa0','https://www.deezer.com/track/2446103',240794),
(159,'Nightmare',2421,'00:03:37',169073,98,'https://cdnt-preview.dzcdn.net/api/1/1/6/9/4/0/6941e2d4cd73d35f714722f8d1fab8df.mp3?hdnea=exp=1782579836~acl=/api/1/1/6/9/4/0/6941e2d4cd73d35f714722f8d1fab8df.mp3*~data=user_id=0,application_id=42~hmac=a796c05d33164e3b7c0edef43e8a3241b285c44725e608e8612904d6b9b7bba4','https://www.deezer.com/track/2446104',240794),
(160,'Victory',2421,'00:02:51',190295,98,'https://cdnt-preview.dzcdn.net/api/1/1/b/9/7/0/b97d911545fa4a8e95622bcca1886782.mp3?hdnea=exp=1782579836~acl=/api/1/1/b/9/7/0/b97d911545fa4a8e95622bcca1886782.mp3*~data=user_id=0,application_id=42~hmac=3cb3ac39d2f5800d65438f43841ec17c3dbf273a62cab869e773d35cccdfa3e5','https://www.deezer.com/track/2446105',240794),
(161,'Cry Of The Celts (Single Edit With Taps)',2421,'00:02:23',246619,98,'https://cdnt-preview.dzcdn.net/api/1/1/b/5/8/0/b58f6690684a5d0cbeed41e6eeab8fef.mp3?hdnea=exp=1782579836~acl=/api/1/1/b/5/8/0/b58f6690684a5d0cbeed41e6eeab8fef.mp3*~data=user_id=0,application_id=42~hmac=c429eab2baabaa9fe144b75db4504a08711201537748612ccf5a5e008038364d','https://www.deezer.com/track/2446106',240794),
(162,'Lord Of The Dance (With Taps)',2421,'00:04:48',279361,98,'https://cdnt-preview.dzcdn.net/api/1/1/d/3/3/0/d330b3995a2c19c5374f3ef13a848cad.mp3?hdnea=exp=1782579836~acl=/api/1/1/d/3/3/0/d330b3995a2c19c5374f3ef13a848cad.mp3*~data=user_id=0,application_id=42~hmac=4110e67e9840effe9005eae8b9073a05f0a1e2dd2646f8035649a8a818d7f992','https://www.deezer.com/track/2446107',240794),
(163,'This Barren Skin',233190561,'00:08:10',38329,464,'https://cdnt-preview.dzcdn.net/api/1/1/0/e/6/0/0e6e21cc2a0e88c28121248a5b8440b0.mp3?hdnea=exp=1782579837~acl=/api/1/1/0/e/6/0/0e6e21cc2a0e88c28121248a5b8440b0.mp3*~data=user_id=0,application_id=42~hmac=2abc3553181522f302e6f169e99cf77c1ba8b6f22d4574c7b91047a36a9270c6','https://www.deezer.com/track/62807659',6164412),
(164,'From These Wounds',233190561,'00:07:44',33087,464,'https://cdnt-preview.dzcdn.net/api/1/1/a/c/4/0/ac438d6177292e97baf29a4dc6484032.mp3?hdnea=exp=1782579837~acl=/api/1/1/a/c/4/0/ac438d6177292e97baf29a4dc6484032.mp3*~data=user_id=0,application_id=42~hmac=c7077db20cbd00b47e5fab7b4091205a1a4d1d563c95a1cd83af2087e4735bc6','https://www.deezer.com/track/62807660',6164412),
(165,'The Architecture Of Loss',233190561,'00:09:04',73935,464,'https://cdnt-preview.dzcdn.net/api/1/1/9/2/2/0/92267485f86c55dff8a538a816fd3bc1.mp3?hdnea=exp=1782579837~acl=/api/1/1/9/2/2/0/92267485f86c55dff8a538a816fd3bc1.mp3*~data=user_id=0,application_id=42~hmac=d90cad27f5f2a249278cede674e6a43ef85d78756f979286da8bbef5d68f18a7','https://www.deezer.com/track/62807661',6164412),
(166,'Red Moon',233190561,'00:08:35',47286,464,'https://cdnt-preview.dzcdn.net/api/1/1/3/e/0/0/3e0255cbf8573abe289199ef6b306c9a.mp3?hdnea=exp=1782579837~acl=/api/1/1/3/e/0/0/3e0255cbf8573abe289199ef6b306c9a.mp3*~data=user_id=0,application_id=42~hmac=2493c8abf1f54e1a8f6420c46a88f4a00dafdeeff7d8030a3a586c16842bef8b','https://www.deezer.com/track/62807662',6164412),
(167,'Vagrant God',233190561,'00:06:17',42190,464,'https://cdnt-preview.dzcdn.net/api/1/1/d/e/4/0/de42d684ab8438e885297749ac116990.mp3?hdnea=exp=1782579837~acl=/api/1/1/d/e/4/0/de42d684ab8438e885297749ac116990.mp3*~data=user_id=0,application_id=42~hmac=73cbbcc0ae323bb82ef3125ce10cc23612b70f5894c052b4df551aad7a7d32b1','https://www.deezer.com/track/62807663',6164412),
(168,'Pendulum',233190561,'00:09:15',46492,464,'https://cdnt-preview.dzcdn.net/api/1/1/8/8/3/0/883ed8325133c2ae80514589878c511e.mp3?hdnea=exp=1782579837~acl=/api/1/1/8/8/3/0/883ed8325133c2ae80514589878c511e.mp3*~data=user_id=0,application_id=42~hmac=131719800b4af5bac519a44ba4b336807d38731193d28a017cbfc5b65899b351','https://www.deezer.com/track/62807664',6164412),
(169,'Saturn',233190561,'00:08:24',54816,464,'https://cdnt-preview.dzcdn.net/api/1/1/1/1/0/0/110eb03cf98e9f6af6da94f06cb9f92c.mp3?hdnea=exp=1782579837~acl=/api/1/1/1/1/0/0/110eb03cf98e9f6af6da94f06cb9f92c.mp3*~data=user_id=0,application_id=42~hmac=73d7f0d0b12e6cebf25313afbafe4bbee4c2ab2b8cf3f330466b52f15254cf31','https://www.deezer.com/track/62807665',6164412),
(170,'Breathing Through You (Bonus Track)',233190561,'00:07:22',47509,464,'https://cdnt-preview.dzcdn.net/api/1/1/9/5/6/0/956135268cafc5dd5422d17399088da2.mp3?hdnea=exp=1782579837~acl=/api/1/1/9/5/6/0/956135268cafc5dd5422d17399088da2.mp3*~data=user_id=0,application_id=42~hmac=2a7df815e1d5b4fce0781ec4969cc4cf4ad6332f28103fa962a5bcb26cb13e06','https://www.deezer.com/track/62807666',6164412),
(171,'Mother\'s Purgatory (Bonus Track)',233190561,'00:10:18',30405,464,'https://cdnt-preview.dzcdn.net/api/1/1/e/3/7/0/e37c8167a3185cdc2abe378493ee186c.mp3?hdnea=exp=1782579837~acl=/api/1/1/e/3/7/0/e37c8167a3185cdc2abe378493ee186c.mp3*~data=user_id=0,application_id=42~hmac=caaf2aa334db8a1c1e192a7a9c6715f3e88df2e059aa124fd4c38054dfcaf772','https://www.deezer.com/track/62807667',6164412),
(172,'Raffle Ticket',396513,'00:05:52',37111,152,'https://cdnt-preview.dzcdn.net/api/1/1/f/e/1/0/fe13a3c98543265232dc0352ac508fc5.mp3?hdnea=exp=1782579838~acl=/api/1/1/f/e/1/0/fe13a3c98543265232dc0352ac508fc5.mp3*~data=user_id=0,application_id=42~hmac=1f584574a32a4ba0f1ce42b262d4bc3423c8a3c1544deef5584815742f9aa4d9','https://www.deezer.com/track/45270091',4467361),
(173,'Bad Man',396513,'00:03:47',58449,152,'https://cdnt-preview.dzcdn.net/api/1/1/6/1/2/0/6126afb567b50ecac7174b40a89b8ca1.mp3?hdnea=exp=1782579838~acl=/api/1/1/6/1/2/0/6126afb567b50ecac7174b40a89b8ca1.mp3*~data=user_id=0,application_id=42~hmac=881080a3a6be7dd5762aa2d784f40dfcb36547cf9017bd771da7cec3eb656474','https://www.deezer.com/track/45270101',4467361),
(174,'Been There Done That',396513,'00:04:36',22186,152,'https://cdnt-preview.dzcdn.net/api/1/1/5/4/6/0/546586cf3fd7828959ad3a56be985795.mp3?hdnea=exp=1782579838~acl=/api/1/1/5/4/6/0/546586cf3fd7828959ad3a56be985795.mp3*~data=user_id=0,application_id=42~hmac=d8bad6239353e6e15c38821df06a943ae37b83cc5006d77747014ba900eb945b','https://www.deezer.com/track/45270111',4467361),
(175,'I Had a Dream',396513,'00:04:34',2232,152,'https://cdnt-preview.dzcdn.net/api/1/1/d/d/8/0/dd84fe4c68011af627be4faee7c45bf3.mp3?hdnea=exp=1782579838~acl=/api/1/1/d/d/8/0/dd84fe4c68011af627be4faee7c45bf3.mp3*~data=user_id=0,application_id=42~hmac=6e98ce7cb9e1ecbf676db2238e3284cde9041d52bd58a9b39110045e36b1f4ce','https://www.deezer.com/track/45270121',4467361),
(176,'Looks Like It\'s Going to Rain',396513,'00:05:45',95533,152,'https://cdnt-preview.dzcdn.net/api/1/1/c/b/0/0/cb040dc8735c5daf92d20a69c3c92e04.mp3?hdnea=exp=1782579838~acl=/api/1/1/c/b/0/0/cb040dc8735c5daf92d20a69c3c92e04.mp3*~data=user_id=0,application_id=42~hmac=6de9248cdc1cf47818d5bd51c82d7fa5468df9c33b35d833bf14509301a84541','https://www.deezer.com/track/45270131',4467361),
(177,'My Mama Gave Me the Blues',396513,'00:06:49',22183,152,'https://cdnt-preview.dzcdn.net/api/1/1/d/8/a/0/d8abd2c44dca91d753384d482621f345.mp3?hdnea=exp=1782579838~acl=/api/1/1/d/8/a/0/d8abd2c44dca91d753384d482621f345.mp3*~data=user_id=0,application_id=42~hmac=ee7dcec7f0a13ca2bd5b896207e5a2d7096c44fd424cfc328dcf5f080ffff471','https://www.deezer.com/track/45270141',4467361),
(178,'Going Back to My Old Time Used to Be',396513,'00:05:12',20169,152,'https://cdnt-preview.dzcdn.net/api/1/1/e/8/c/0/e8cec821206d47c126bddd6317482a2c.mp3?hdnea=exp=1782579838~acl=/api/1/1/e/8/c/0/e8cec821206d47c126bddd6317482a2c.mp3*~data=user_id=0,application_id=42~hmac=be9b1ddbafe68d1623fc0453ef60602e7e4d1f4c962c89025d7f81744bf15f8c','https://www.deezer.com/track/45270151',4467361),
(179,'The Same Thing I Could Tell Myself',396513,'00:06:36',30335,152,'https://cdnt-preview.dzcdn.net/api/1/1/1/e/5/0/1e5c644bcc48587ca0ef66ac86a8ed1a.mp3?hdnea=exp=1782579838~acl=/api/1/1/1/e/5/0/1e5c644bcc48587ca0ef66ac86a8ed1a.mp3*~data=user_id=0,application_id=42~hmac=aca77dff330d840207215b0993e40d6d5b76b97d2323525cc01b41d257e34638','https://www.deezer.com/track/45270161',4467361),
(180,'Big Woman',396513,'00:04:32',25557,152,'https://cdnt-preview.dzcdn.net/api/1/1/a/9/a/0/a9ad6828ba4f49139539045388e4340e.mp3?hdnea=exp=1782579838~acl=/api/1/1/a/9/a/0/a9ad6828ba4f49139539045388e4340e.mp3*~data=user_id=0,application_id=42~hmac=0bca56fa622b9073656baa465278a96ae141f6b1e9638c35ba0d3d256a276d87','https://www.deezer.com/track/45270171',4467361),
(181,'Going Up on the Roof',396513,'00:06:09',9820,152,'https://cdnt-preview.dzcdn.net/api/1/1/b/6/2/0/b626180e749b89296edf75b85e93303b.mp3?hdnea=exp=1782579838~acl=/api/1/1/b/6/2/0/b626180e749b89296edf75b85e93303b.mp3*~data=user_id=0,application_id=42~hmac=ec57698e927dafc9cbd4c972f48550bf0340c267804f7284b761e6b35dacb6bb','https://www.deezer.com/track/45270181',4467361),
(182,'I\'m Moving',396513,'00:04:54',1823,152,'https://cdnt-preview.dzcdn.net/api/1/1/d/7/b/0/d7be0bf7378d84157ee976407db48aee.mp3?hdnea=exp=1782579838~acl=/api/1/1/d/7/b/0/d7be0bf7378d84157ee976407db48aee.mp3*~data=user_id=0,application_id=42~hmac=3420ada064b3078ef049576b5a17d2bd683521c0d59cb3693cf976f9a2ca12e9','https://www.deezer.com/track/45270191',4467361),
(183,'Saving Robert Johnson',396513,'00:03:42',1546,152,'https://cdnt-preview.dzcdn.net/api/1/1/4/9/b/0/49b64395c441d44374868644adf47c98.mp3?hdnea=exp=1782579838~acl=/api/1/1/4/9/b/0/49b64395c441d44374868644adf47c98.mp3*~data=user_id=0,application_id=42~hmac=c3abaca693fe3787687cf79290dcdb7e2794401e6d2d7aaf12c42db650bf06fa','https://www.deezer.com/track/45270201',4467361);
/*!40000 ALTER TABLE `tracks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `is_admin` tinyint(1) NOT NULL,
  `email` varchar(300) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1,'Мартин','Златков','popito2','2931g6f69mg',1,'martizlato@abv.bg'),
(2,'Martin','Zlatkov','Hakuna matata','2931g6f69mg',0,'popito2@abv.bg');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'playlist'
--

--
-- Dumping routines for database 'playlist'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2026-06-27 19:56:07
