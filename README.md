# MovieBluff-MVVM
An app to find movie lovers to go watch a movie together.


This app originally will use AWS for signup/login and data storage and will be available on playstore soon.

Currently the skelton of the project is uploaded on github.
This github project uses mysqli php for login.The registration part is not implemented in android.

MVVM PATTERN is used for:<br/>
1)Login<br/>
<img src="https://github.com/devsarahgeo/MovieBluff-MVVM/blob/master/loginpg.jpg" width=200 height=400/><br/>

INVALID LOGIN<br/>
<img src="https://github.com/devsarahgeo/MovieBluff-MVVM/blob/master/invalidlogin.jpg" width=200 height=400/><br/>

2)Getting data from movies api using retrofit<br/>
3)Used fragments for switching between bottom nav tabs<br/>
FINDFRIENDS FRAGMENT(needs to be implemented)<br/>
<img src="https://github.com/devsarahgeo/MovieBluff-MVVM/blob/master/findfriends%20fragment.jpg" width=200 height=400/><br/>
POPULAR MOVIES FRAGMENT(data got from api using retrofit)<br/>
<img src="https://github.com/devsarahgeo/MovieBluff-MVVM/blob/master/popularmovies%20fragment.jpg" width=200 height=400/><br/>




4)Used shared preferences to save user session(like username) after login and keep app logged in once user has logged in until user deletes app or logs out.<br/>


