function loginFunction()
{
	var hash = 0;
	var pass = $('#Password').val();
	if (pass.length == 0) alert("Enter password");
	for (i = 0; i < pass.length; i++) {
		char = pass.charCodeAt(i);
		hash = ((hash<<5)-hash)+char;
		hash = hash & hash; // Convert to 32bit integer
	}
	//alert(hash);
	$.ajax({
		url : URL+'/webapi/validate/admin',
		type : 'POST',
		dataType : 'text',
		contentType: 'application/json',
		async: false,
		data: JSON.stringify({
			username: $('#Username').val(),
			password: pass
        }),
        success: function(data){
        	alert("successfully login");
        	alert(data);
        	//window.location.href = "admin.html";
        }
	});
}