
# Generate Playlist project 💻
Dear colleagues and guests,

We are pleased to introduce our group project - SpotifyWannaBe Web Application. SpotifyWannaBe is an innovative application that allows users to generate playlists specifically tailored to the duration of their travels based on their preferred music genres.




# What is SpotifyWannaBe?


SpotifyWannaBe is a web application that empowers you to create personalized playlists for your journeys. The playlists consist of individual songs, each with an artist, title, album, duration, and ranking. An important feature is the ability to preview the songs with a special audio stream URL.



## Playlist Structure:


Playlists have a title, tags (music genres), and a list of songs with details for each song. The total duration of the playlist is the sum of the durations of all songs. The playlist's rank is the arithmetic mean of the ranks of all songs in it.


## Why is SpotifyWannaBe unique?

SpotifyWannaBe allows you to personalize your musical experience during travel. We provide you with a tool that combines your preferences with the duration of your journey.


## How will SpotifyWannaBe work?

Users choose their preferred music genres and the duration of their travel. SpotifyWannaBe generates a unique playlist personalized for them. Each song is carefully selected to be suitable for the given travel time.


## The Future of SpotifyWannaBe:


We plan to expand functionality with additional customization options. Integration of social elements, such as playlist sharing and recommendations from friends.\
\
Thank you for listening. SpotifyWannaBe - your musical adventure during every journey!









# 🚀 Project Description


## 🔗 Technologies
- SpringBoot, Mockito Unit Testing, MariaDB, Hibernate, REST API, Thymeleaf, HTML, CSS

## 🔗 Rest Api
- Link to the documentation in Swagger -> http://localhost:8080/api-docs (access after you have successfully configured the project)


## Functionalities
- *Guest part* - available without authentication
- *User part* - available for registered users
- *Administrative part* - available for administrators only


## Unauthenticated Part
- **Home View**
  >Anonymous users can see 3 highest rank playlists
  and all playlists.

<img width="1121" alt="HomeView" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/HomeView.png">
<img width="1121" alt="HomeView2" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/HomeView2.png">
<img width="1121" alt="HomeView3" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/HomeView3.png">
<img width="1121" alt="HomeView4" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/HomeView4.png">


- **About**
  >Anonymous users can access the About page

<img width="1121" alt="About1" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/About1.png">
<img width="1121" alt="Aout2" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/Aout2.png">
<img width="1121" alt="About3" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/About3.png">

- **Register**
  >Anonymous users can Register

  <img width="1121" alt="Register1" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/Register1.png">
  <img width="1121" alt="Register2" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/Register2.png">

- **Login**
  >Anonymous users can Login

  <img width="1121" alt="Login" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/Login.png">








## Authenticated Part
- **Browse Playlist**
  >Users can browse and filter all playlists.

<img width="1121" alt="BrowsePlaylist" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/BrowsePlaylist.png">

- **Generate Playlist**
  >Users can enter starting and destination address, select musical genres and clicks “Generate”.

<img width="1121" alt="Generate" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/Generate.png">
<img width="1121" alt="generate2" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/generate2.png">
<img width="1121" alt="generate3" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/generate3.png">
<img width="1121" alt="generate4" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/generate4.png">

- **My Playlist**
  >The user can view all their generated playlists and also see the songs included in each playlist.

<img width="1121" alt="myplaylist" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/myplaylist.png">
<img width="1121" alt="myplaylist2" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/myplaylist2.png">

  >A Playlist without repeating artists.

<img width="1121" alt="myplaylist4" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/myplaylist4.png">

  >A Playlist were repeating artists is allowed.

<img width="1121" alt="myplaylist5" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/myplaylist5.png">


- **Settings**
  >User can edit their profile information

<img width="1121" alt="settings" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/settings.png">



## Administrative Part
- **Admin Portal**
  >Admins can view all users, view other admins; make other users admins, and make admins users;

<img width="1121" alt="admin1" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/admin1.png">
<img width="1121" alt="admin2" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/admin2.png">
<img width="1121" alt="admin3" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/admin3.png">
<img width="1121" alt="admin4" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/admin4.png">
## 📊Database Structure

<img width="1121" alt="sql" src="https://github.com/Spotify-wanna-be/RidePal/blob/main/src/main/resources/static/img/readme/sql.png">

## 🚀 About Our Team

We are all students in the Java Alpha 53 program, and this our final project. Throughout this project, we've not only deepened our technical knowledge but also developed strong teamwork skills. This project brought us together, and we're excited to showcase our collaborative efforts.




## 🔗 Contact us
Feel free to reach out to us for any questions, feedback, or collaboration opportunities. We're excited to connect with the community and share our journey!
- **Marian Maximov** - GitHub [https://github.com/marianmgm] 
- **Martin Zlatkov** - GitHub [https://github.com/popito2]
- **Dora Dimitrov** - GitHub [https://github.com/doraad] 