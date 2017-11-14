//var URL = 'http://ehrc-dev.iiitb.ac.in/hitham';
//var URL = 'http://172.16.82.145:8080/hitham'
//var URL = 'http://172.16.82.103:8080/hitham';
var URL = 'http://localhost:8080/hitham';

function encryptme(pass)
{
	var hash = 0;
	for (i = 0; i < pass.length; i++) {
		char = pass.charCodeAt(i);
		hash = ((hash<<5)-hash)+char;
		hash = hash & hash; // Convert to 32bit integer
	}
	return hash;
}