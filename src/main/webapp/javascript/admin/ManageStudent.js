// create student profile
function studentProfileSubmit() {
	$.ajax({
		url : URL+'/webapi/student/createstudent',
		type : 'POST',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
		data: JSON.stringify({
			student_name:$('#studentName').val(),
			student_id: $('#studentId1').val() ,
			student_password: $('#studentpassword').val() ,
			student_profile: $('#studentProfile').val() 
			
        }),
        success: function(data){
        }
	});
	$('#StudentProfileForm').modal('hide');
	loadStudentList();
}

//load StudentList

function loadStudentList() {
	//alert("in studentList");
	$.ajax({
		url : URL+'/webapi/student/fetchall',
		type : 'GET',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
        success: function(data){
        	//alert("in success");
        	var no_of_object = data.length;	
        	var student_ids;
			var tdata = '<thead>'+
					        '<tr>'+
					            '<th>Name</th>'+
					            '<th>Username</th>'+
					            '<th>Password</th>'+
					            '<th>Profile</th>'+
					            '<th>Edit/Delete</th>'+
					        '</tr>'+
					    '</thead>';
			$('#studentList').empty();
			if(no_of_object == 0) {
				tdata += '<tfoot><tr><td> No data found </td></tr></tfoot>';
			}
			else {
				for (var i = 0; i < no_of_object; i++) {
					var student_id = data[i]['student_id'];
					var student_name = data[i]['student_name'];
					var student_password = data[i]['student_password'];
					var student_profile = data[i]['student_profile'];
					student_ids += '<option>'+student_id+'</option>';
					tdata += '<tr><td>'+student_name+
								'</td><td>'+student_id+
								'</td><td>'+student_password+
								'</td><td>'+student_profile+
								'</td><td><a  href="#studentEditForm" data-toggle="modal" onclick="editStudent(\''+student_name+'\',\''+student_id+'\',\''+student_password+'\',\''+student_profile+'\')">Edit</a>/<a onclick="deleteStudent(\''+student_id+'\')">delete</a></td></tr>'
				}
			}
			
			$('#studentList').append(tdata);
			$('#studentDropDown').empty();
			$('#studentDropDown').append(student_ids);
        }
	});
}


/*
 * function to delete student record by admin.
 */

function deleteStudent(id) {
	
	if (confirm("Do you want to delete "+id+" ?") == true) {
		$.ajax({
			url : URL+'/webapi/student/delete/'+id,
			type : 'POST',
			dataType : 'json',
			contentType: 'application/json',
			async: false,
	        success: function(data){
	        }
		});
    } 
	loadStudentList();
      	
}

var studentID;
function editStudent(name,sid,pass,profile) {
	studentID = sid;
	$('#studentName2').val(name);
	$('#studentId2').val(sid);
	$('#studentPassword2').val(pass);
	$('#studentProfile2').val(profile);
}

function saveEditedStudent()  {
	alert('in edit'+URL+'/webapi/student/edit/'+studentID);
	$.ajax({
		url : URL+'/webapi/student/edit/'+studentID,
		type : 'POST',
		dataType : 'json',
		contentType: 'application/json',
		async: false,
		data: JSON.stringify({
			student_name:$('#studentName2').val(),
			student_id: $('#studentId2').val() ,
			student_password: $('#studentPassword2').val() ,
			student_profile: $('#studentProfile2').val() ,
			
        }),
        success: function(data){
        }
	});
	$('#studentEditForm').modal('toggle');
	loadStudentList();
}