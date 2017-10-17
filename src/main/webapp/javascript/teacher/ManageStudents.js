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

//playlist assigned to student
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