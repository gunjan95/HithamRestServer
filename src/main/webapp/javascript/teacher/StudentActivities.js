
var teacher_id = "teacher01";
var last_fetched_data = null;

function student_select_change(selectObject)
{
	//alert(selectObject.value);
	getAllActivity(selectObject.value);
}

function getAllActivity(student_pk)
{
	if(student_pk == 0){
		var url = URL + '/webapi/studentActivity/allActivity';
	}
	else {
		var url = URL+'/webapi/studentActivity/allActivity/'+student_pk;
	}
		
	$.ajax({
		url : url,
		type : 'POST',
		dataType : 'json',
		success : function(data){
			if(data.length == 0){
				//No Activity
				$('#activity_Display_heading').empty();
				$('#activity_Display_body').empty();
				last_fetched_data = null;
				document.getElementById('no_activity_display').style.display = 'block';
				document.getElementById('activity_Display').style.display = 'none';
			}
			else{
				
				$('#activity_Display_heading').empty();
				$('#activity_Display_body').empty();
				last_fetched_data = data;
				document.getElementById('no_activity_display').style.display = 'none';
				document.getElementById('activity_Display').style.display = 'block';
				
				$('#activity_Display_heading').append('<tr>'+
        		        '<th>Student</th>'+
        		        '<th>Recording</th>'+
        		        '<th>Activity Type</th>'+
        		        '<th>Activity Time on song</th>'+
        		        '<th>Activity Time</th>'
        		      +'</tr>');
                for(var i=0 ; i < data.length ; i++){
                    $('#activity_Display_body').append('<tr>'+
                        '<td>'+data[i]['student_name']+'</td>'+
                        '<td>'+data[i]['recording_name']+'</td>'+
                        '<td>'+data[i]['student_activity_type']+'</td>'+
                        '<td>'+data[i]['student_activity_time']+'</td>'+
                        '<td>'+data[i]['student_activity_timestamp']+'</td>'+
                        '</tr>'
                        );
                }
			}
		}
	});
}

function downloadData()
{
	$("#dummy").excelexportjs({
	  containerid: "dummy",
	  datatype: 'json',
	  dataset: last_fetched_data, 
	  columns: getColumns(last_fetched_data)     
	});
}