var URL = 'http://ehrc-dev.iiitb.ac.in/hitham';
//var URL = 'http://localhost:8080/hitham';

function doOnLoad()
{
        $("#AddSong").load("AddSong.html");
        $("#PlayList").load("PlayList.html");
        $("#Profile").load("Profile.html");
        loadPlayList();
}
function loadPlayList() {
	$.ajax({
		url : URL+'/webapi/playlist/display',
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			var no_of_object = data.length;	
			var query3 = '<select id="playListSelect3" style="width:150px" onchange="displaySongs()"> ';
			$('#selectPlayList3').empty();
			for (var i = 0; i < no_of_object; i++) {
				var playlist_id = data[i]['playlist_id'];
				var playlist_name = data[i]['playlist_name'];
				query3 += '<option value ="'+playlist_id+'">'+playlist_name+'</option>';
			}
			query3 += '</select>';
			$('#selectPlayList3').append(query3);
		}
	});
	
}


// for assigning playlist to student according to student id
function displayPlayList() {
	$.ajax({
		url : URL+'/webapi/playlist/displayplaylist/'+$('#studentId').val(),
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			$('#selectPlayList2').empty();
			var no_of_object = data.length;	
			var query2 = '<label class="control-label col-sm-3">PlayList</label>'+
			'<div class="col-sm-4" >'+
			'<select id="playListSelect2">';
			for (var i = 0; i < no_of_object; i++) {
				var playlist_id = data[i]['playlist_id'];
				var playlist_name = data[i]['playlist_name'];
				query2 += '<option value ="'+playlist_id+'">'+playlist_name+'</option>';
			}
			query2 += '</select></div>';
			$('#selectPlayList2').append(query2);
		}
	});
	
}

//for displaying song to playlist according to playlist
function displaySongs() {
	$.ajax({
		url : URL+'/webapi/playlist/displaysong/'+$('#playListSelect3').val(),
		type : 'GET',
		dataType : 'json',
		async: false,
		success : function(data) {
			var no_of_object = data.length;	
			
			var query = '<label class="control-label col-sm-3">SongList</label>'+
			'<div class="col-sm-4" >'+
			'<select id="songListSelect"> ';
			$('#selectSongList1').empty();
			for (var i = 0; i < no_of_object; i++) {
				var songlist_id = data[i]['songlist_id'];
				var songlist_name = data[i]['songlist_name'];
				query += '<option value ="'+songlist_id+'">'+songlist_name+'</option>';
			}
			
			if(no_of_object === 0)
				query += '<option value ="NULL">None</option>';
			query += '</select></div>';
			$('#selectSongList1').append(query);
		}
	});
	
}

// add song to playlist
function assignSongToPlayListFormSubmit() {
	$.ajax({
		url : URL+'/webapi/playlist/songassign/'+$('#songListSelect').val()+'/'+$('#playListSelect3').val(),
		type : 'POST',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
		data: {},
        success: function(data){
        }
	});
	displaySongs();
}

// add song
function songFormSubmit() {
	$.ajax({
		url : URL+'/webapi/playlist/add',
		type : 'POST',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
		data: JSON.stringify({
			songlist_name:$('#songName').val(),
			songlist_url: $('#songURL').val() ,
			songlist_pic_url: $('#songPicURL').val() ,
			songlist_song_color: $('#songColor').val() ,
			songlist_raaga: $('#songRaaga').val() ,
			songlist_taal: $('#songTaal').val() ,
			songlist_singer: $('#songSinger').val() ,
			songlist_composer: $('#songComposer').val() 
        }),
        success: function(data){
        }
	});
		
}


// playlist assigned to student
function assignSongFormSubmit() {
	$.ajax({
		url : URL+'/webapi/playlist/assign/'+$('#studentId').val()+'/'+$('#playListSelect2').val(),
		type : 'POST',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
		data: {},
        success: function(data){
        }
	});
	displayPlayList();	
}

// create new playlist
function createNewPlayList() {
	var selectedoption = document.getElementById("playlist_name").value;
		$.ajax({
			url : URL+'/webapi/playlist/create',
			type : 'POST',
			contentType: 'application/json',
			dataType : 'json',
			async: false,
			data: JSON.stringify({
				playlist_name:selectedoption
	        }),
	        complete : function(request, textStatus, errorThrown){
	        	$('#playlistmodal').modal('toggle');
	        }
		});
		$('#playlistmodal').modal('toggle');
		loadPlayList();
	}



// create student profile
function studentProfileSubmit() {
	$.ajax({
		url : URL+'/webapi/user/createstudent',
		type : 'POST',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
		data: JSON.stringify({
			student_name:$('#studentName').val(),
			student_id: $('#studentId1').val() ,
			student_password: $('#studentpassword').val() ,
			student_profile: $('#studentProfile').val() ,
			
        }),
        success: function(data){
        }
	});
}
