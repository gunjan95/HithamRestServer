function doOnLoad()
{
        $("#Song").load("Song.html");
        $("#Teacher").load("Teacher.html");
        $("#Student").load("Student.html");
        setTimeout(function(){
            //do what you need here
        	$(document).ready(function(){
            	loadStudentList();
            });
            $(document).ready(function(){
                loadSongList();
            });
            $(document).ready(function(){
            	loadTeacherList();
            });
        }, 200);
        
        
}