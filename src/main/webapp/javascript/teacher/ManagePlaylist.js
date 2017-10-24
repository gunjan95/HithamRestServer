var playlist_nameArray = [];
// create playlist profile
function playlistProfileSubmit() {
	if(playlist_nameArray.indexOf($('#playlistName').val()) != -1) {
		alert('Name already Exists');
	}
	else {
		$.ajax({
			url : URL+'/webapi/playlist/create',
			type : 'POST',
			dataType : 'json',
			contentType: 'application/json',
			async: false,
			data: JSON.stringify({
				playlist_name:$('#playlistName').val(),
		
	        }),
	        success: function(data){
	        }
		});
		$('#playlistProfileForm').modal('toggle');
		loadplayList();
	}
}


//load playlistList

function loadplayList() {
	//alert("in playlistList");
	$.ajax({
		url : URL+'/webapi/playlist/fetch/1',
		type : 'POST',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
        success: function(data){
       //alert("in success");
        	var no_of_object = data.length;	
			var tdata = '<thead>'+
					        '<tr>'+
					            '<th>Name</th>'+
					            '<th>Edit/Delete</th>'+
					            '<th>Current Recordings</th>'+
					            '<th>Assign New Recording</th>'+
					        '</tr>'+
					    '</thead>';
			$('#playlisttable').empty();
			playlist_nameArray = [];
			if(no_of_object == 0) {
				tdata += '<tfoot><tr><td> No data found </td></tr></tfoot>';
			}
			else {
				for (var i = 0; i < no_of_object; i++) {
					var playlist_id = data[i]['playlist_id'];
					var playlist_name = data[i]['playlist_name'];
					playlist_nameArray.push(playlist_name);
					
					tdata += '<tr><td>'+playlist_name+
								'</td><td><a  href="#playlistEditForm" data-toggle="modal" onclick="editplaylist(\''+playlist_id+'\',\''+playlist_name+'\')">Edit</a>/<a onclick="deleteplaylist(\''+playlist_name+'\',\''+playlist_id+'\')">delete</a></td>'+
								'</td><td><a  href="#currentRecordingsForm" data-toggle="modal" onclick="getCurrentRecordingList(\''+playlist_id+'\')">click here</a></td>'+
								'</td><td><a  href="#recordingAssignForm" data-toggle="modal" onclick="getRecordingList(\''+playlist_id+'\')">click here</a></td></tr>';
				}
			}
			//alert(playlist_nameArray);
			$('#playlisttable').append(tdata);
        }
	});
}


/*
 * function to delete song record by admin.
 */

function deleteplaylist(name,id) {
	//alert('in edit'+URL+'/webapi/playlist/delete/'+id);
	if (confirm("Do you want to delete "+name+" ?") == true) {
		$.ajax({
			url : URL+'/webapi/playlist/delete/'+id,
			type : 'POST',
			dataType : 'json',
			contentType: 'application/json',
			async: false,
	        success: function(data){
	        }
		});
    } 
	loadplayList();
      	
}

var playlistID;
function editplaylist(sid,name) {
	playlistID = sid;
	$('#playlistName2').val(name);
	
}

function saveEditedplaylist()  {
	alert('in edit'+URL+'/webapi/playlist/edit/'+playlistID);
	$.ajax({
		url : URL+'/webapi/playlist/edit/'+playlistID,
		type : 'POST',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
		data: JSON.stringify({
			playlist_name:$('#playlistName2').val() 
			
        }),
        success: function(data){
        }
	});
	$('#playlistEditForm').modal('toggle');
	loadplayList();
}


function getRecordingList(id) {
	//alert(id);
	playlistID = id;
	$.ajax({
		url : URL+'/webapi/playlist/fetchRecordings/'+id,
		type : 'POST',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
        success: function(data){
        	//alert(data);
        	var no_of_object = data.length;	
        	var appendData = '';
        	$('#recordingCheckbox').empty();
        	if(no_of_object == 0){
        		appendData += 'No data found';
        	}
        	else {
        		for(var i = 0; i < no_of_object; i++) {
            		appendData += '<input type="checkbox" name="recordingBox" value="'+data[i]['recording_id']+'"> &nbsp;'+data[i]['recording_name']+'<br>';
            	}
        	}
        	
        	
        	$('#recordingCheckbox').append(appendData);
        }
	});
	
	
}


function createPlaylistRecordingMapping() {
	var selected = [];
	$('#recordingCheckbox input:checked').each(function() {
	    selected.push($(this).attr('value'));
	});
	//alert( selected.join(',') +' '+ teacherID);
	if(selected.length != 0) {
		$.ajax({
			url : URL+'/webapi/playlist/recordingPlaylistMapping/'+playlistID,
			type : 'POST',
			dataType : 'text',
			contentType: 'text/plain',
			async: false,
			data: ''+selected.join(','),
	        success: function(data){
	        }
		});
	}
	$('#recordingAssignForm').modal('toggle');
}



function getCurrentRecordingList(id) {
	$.ajax({
		url : URL+'/webapi/playlist/fetchAssignedRecordings/'+id,
		type : 'POST',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
        success: function(data){
        	//alert(data);
        	var no_of_object = data.length;	
        	var appendData = '<ul>';
        	$('#currentRecordingList').empty();
        	if(no_of_object == 0){
        		appendData += 'No data found';
        	}
        	else {
        		for(var i = 0; i < no_of_object; i++) {
            		appendData += '&nbsp;&nbsp;&nbsp;&nbsp;<li>'+data[i]['recording_name']+'</li>';
            	}
        	}
        	
        	appendData += '</ul>';
        	$('#currentRecordingList').append(appendData);
        }
	});
	
}













//// create new playlist--------------------------------------------------start
//function createNewPlayList() {
//	var selectedoption = document.getElementById("playlist_name").value;
//		$.ajax({
//			url : URL+'/webapi/playlist/create',
//			type : 'POST',
//			contentType: 'application/json',
//			dataType : 'json',
//			async: false,
//			data: JSON.stringify({
//				playlist_name:selectedoption
//	        }),
//	        complete : function(request, textStatus, errorThrown){
//	        	$('#playlistmodal').modal('toggle');
//	        }
//		});
//		$('#playlistmodal').modal('toggle');
//		loadPlayList();
//}
//
//// create new playlist-------------------------------------------------------end
//
//
////add song to playlist------------------------------------------------------------------ start
////
////
//
//function assignSongToPlayListFormSubmit() {
//	$.ajax({
//		url : URL+'/webapi/playlist/songassign/'+$('#songListSelect').val()+'/'+$('#playListSelect3').val(),
//		type : 'POST',
//		dataType : 'json',
//		contentType: 'application/json',
//		async: false,
//		data: {},
//        success: function(data){
//        }
//	});
//	displaySongs();
//}
//
////load playlists
//function loadPlayList() {
//	$.ajax({
//		url : URL+'/webapi/playlist/display',
//		type : 'GET',
//		dataType : 'json',
//		success : function(data) {
//			var no_of_object = data.length;	
//			var query3 = '<select id="playListSelect3" style="width:150px" onchange="displaySongs()"> ';
//			$('#selectPlayList3').empty();
//			for (var i = 0; i < no_of_object; i++) {
//				var playlist_id = data[i]['playlist_id'];
//				var playlist_name = data[i]['playlist_name'];
//				query3 += '<option value ="'+playlist_id+'">'+playlist_name+'</option>';
//			}
//			query3 += '</select>';
//			$('#selectPlayList3').append(query3);
//		}
//	});
//	
//}
//
//
////for displaying song to playlist according to playlist
//function displaySongs() {
//	$.ajax({
//		url : URL+'/webapi/playlist/displaysong/'+$('#playListSelect3').val(),
//		type : 'GET',
//		dataType : 'json',
//		async: false,
//		success : function(data) {
//			var no_of_object = data.length;	
//			
//			var query = '<label class="control-label col-sm-3">SongList</label>'+
//			'<div class="col-sm-4" >'+
//			'<select id="songListSelect"> ';
//			$('#selectSongList1').empty();
//			for (var i = 0; i < no_of_object; i++) {
//				var songlist_id = data[i]['songlist_id'];
//				var songlist_name = data[i]['songlist_name'];
//				query += '<option value ="'+songlist_id+'">'+songlist_name+'</option>';
//			}
//			
//			if(no_of_object === 0)
//				query += '<option value ="NULL">None</option>';
//			query += '</select></div>';
//			$('#selectSongList1').append(query);
//		}
//	});
//	
//}
//
//
////
////
////add song to playlist ------------------------------------ end
//
