window.onload = loadPlayList();

function loadPlayList() {
	$.ajax({
		url : 'http://localhost:8080/HITHAM/webapi/playlist/display',
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			var no_of_object = data.length;	
			var query = '<select> ';
			$('#selectPlayList').empty();
			for (var i = 0; i < no_of_object; i++) {
				var playlist_id = data[i]['playlist_id'];
				var playlist_name = data[i]['playlist_name'];
				query += '<option value ="'+playlist_id+'">'+playlist_name+'</option>';
			}
			query += '</select>';
			$('#selectPlayList').append(query);
		}
	});
}

function songFormSubmit() {
	$.ajax({
		url : 'http://localhost:8080/HITHAM/webapi/playlist/add',
		type : 'POST',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
		data: JSON.stringify({
			songlist_name:$('songName').val(),
			songlist_url: $('songURL').val() ,
			songlist_pic_url: $('songPicURL').val() ,
			songlist_color: $('songColor').val() ,
			songlist_playlist: $('selectPlayList').val()
        }),
        success: function(data){
        }
	});
		
}


function createNewPlayList() {
	var selectedoption = document.getElementById("playlist_name").value;
		$.ajax({
			url : 'http://localhost:8080/HITHAM/webapi/playlist/create',
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
	}
