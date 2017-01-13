form.barre-navigation {
    list-style-type: none;
    margin: 0;
    padding: 0;
    left: 0;
    overflow: hidden;
    background-color: #333;
    position: fixed;
    top: 0;
    width: 100%;
}

input.left {
	background-color: Transparent;
    background-repeat:no-repeat;
    border: none;
    cursor:pointer;
    overflow: hidden;
    outline:none;
    float: left;
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

input.right {
    background-color: Transparent;
    background-repeat:no-repeat;
    border: none;
    cursor:pointer;
    overflow: hidden;
    outline:none;
    float: right;
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

input.left:hover {
	background-color: #111;
    background-repeat:no-repeat;
    border: none;
    cursor:pointer;
    overflow: hidden;
    outline:none;
    float: left;
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

input.right:hover {
    background-color: #111;
    background-repeat:no-repeat;
    border: none;
    cursor:pointer;
    overflow: hidden;
    outline:none;
    float: right;
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

input.left-active {
	background-color: #c0392b;
    background-repeat:no-repeat;
    border: none;
    cursor:pointer;
    overflow: hidden;
    outline:none;
    float: left;
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

input.right-active {
    background-color: #c0392b;
    background-repeat:no-repeat;
    border: none;
    cursor:pointer;
    overflow: hidden;
    outline:none;
    float: right;
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

div#principal {
    padding:20px;
    margin-top:30px;
}

form.rechercher input {
	font-size: 15pt;
	width:400px;
    padding: 10px;
    border: none;
    border-bottom: solid 2px #c9c9c9;
    -webkit-transition: border 0.3s;
    -moz-transition: border 0.3s;
    -o-transition: border 0.3s;
    transition: border 0.3s;
}

form.rechercher {
	width:400px;
	margin: auto;
}

img#ajouterPlaylist { 
  position: absolute; 
  bottom: 50px; 
  left: 50px;
  border-width: 1px; 
}

img#logoYoulist {
  position: relative; 
  top: 0;
  left: 0;
  width: 18px;
  height: 18px;
}

div.playlist {
    margin: 5px;
    border: 1px solid #ccc;
    float: left;
    width: 700px;
}

div.playlist:hover {
    border: 1px solid #777;
}

div.titre {
	font-size: 130%;
    text-align: left;
}

div .motsClefs {
	font-size: 90%;
    text-align: right;
}
