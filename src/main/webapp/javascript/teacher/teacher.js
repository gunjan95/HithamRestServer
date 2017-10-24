function doOnLoad() {
    $("#Playlist").load("Playlist.html");
    $("#StudentAssignment").load("StudentA.html");
    setTimeout(function(){
        //do what you need here
        $(document).ready(function(){
        	loadplayList();
        });
        
        $(document).ready(function(){
        	loadStudentForTeacherList();
        });
    }, 1000);

}

//function loadTeacherList() {
//	$.ajax({
//		url : URL+'/webapi/teacher/fetchall',
//		type : 'GET',
//		dataType : 'json',
//		contentType: 'application/json',
//		async: false,
//        success: function(data){
//        	//alert("in success");
//        	var no_of_object = data.length;	
//			var teacherlistdropdown = '';
//			$('#teacherIDList').empty();
//			if(no_of_object == 0) {
//				teacherlistdropdown += '<option>none</option>';
//			}
//			else {
//				for (var i = 0; i < no_of_object; i++) {
//					var teacher_id = data[i]['teacher_id'];
//					teacherlistdropdown += '<option>'+teacher_id+'</option>';
//				}
//			}
//			
//			$('#teacherIDList').append(teacherlistdropdown);
//        }
//	});
//}