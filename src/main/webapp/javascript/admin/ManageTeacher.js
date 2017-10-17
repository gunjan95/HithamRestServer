// create teacher profile
function teacherProfileSubmit() {
	$.ajax({
		url : URL+'/webapi/teacher/createteacher',
		type : 'POST',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
		data: JSON.stringify({
			teacher_name:$('#teacherName').val(),
			teacher_id: $('#teacherId1').val() ,
			teacher_password: $('#teacherpassword').val() 
			
        }),
        success: function(data){
        }
	});
	$('#teacherProfileForm').modal('hide');
	loadTeacherList();
}

//load teacherList

function loadTeacherList() {
	//alert("in teacherList");
	$.ajax({
		url : URL+'/webapi/teacher/fetchall',
		type : 'GET',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
        success: function(data){
        	//alert("in success");
        	var no_of_object = data.length;	
			var tdata = '<thead>'+
					        '<tr>'+
					            '<th>Name</th>'+
					            '<th>ID</th>'+
					            '<th>Password</th>'+
					            '<th>Edit/Delete</th>'+
					        '</tr>'+
					    '</thead>';
			$('#teacherList').empty();
			if(no_of_object == 0) {
				tdata += '<tfoot><tr><td> No data found </td></tr></tfoot>';
			}
			else {
				for (var i = 0; i < no_of_object; i++) {
					var teacher_id = data[i]['teacher_id'];
					var teacher_name = data[i]['teacher_name'];
					var teacher_password = data[i]['teacher_password'];
					tdata += '<tr><td>'+teacher_name+
								'</td><td>'+teacher_id+
								'</td><td>'+teacher_password+
								'</td><td><a  href="#teacherEditForm" data-toggle="modal" onclick="editteacher(\''+teacher_name+'\',\''+teacher_id+'\',\''+teacher_password+'\')">Edit</a>/<a onclick="deleteteacher(\''+teacher_id+'\')">delete</a></td></tr>'
				}
			}
			
			$('#teacherList').append(tdata);
        }
	});
}


/*
 * function to delete teacher record by admin.
 */

function deleteteacher(id) {
	
	if (confirm("Do you want to delete "+id+" ?") == true) {
		$.ajax({
			url : URL+'/webapi/teacher/delete/'+id,
			type : 'POST',
			dataType : 'json',
			contentType: 'application/json',
			async: false,
	        success: function(data){
	        }
		});
    } 
	loadTeacherList();
      	
}

var teacherID;
function editteacher(name,sid,pass) {
	teacherID = sid;
	$('#teacherName2').val(name);
	$('#teacherId2').val(sid);
	$('#teacherPassword2').val(pass);
}

function saveEditedTeacher()  {
	alert('in edit'+URL+'/webapi/teacher/edit/'+teacherID);
	$.ajax({
		url : URL+'/webapi/teacher/edit/'+teacherID,
		type : 'POST',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
		data: JSON.stringify({
			teacher_name:$('#teacherName2').val(),
			teacher_id: $('#teacherId2').val() ,
			teacher_password: $('#teacherPassword2').val() ,
			
        }),
        success: function(data){
        }
	});
	$('#teacherEditForm').modal('toggle');
	loadTeacherList();
}
