/**
 * 
 */
function load(id,path) {
	console.log('div id : ${id} , filename : ${path}', id,path);
	var element = document.getElementById(id);
	var ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			console.log(ajax.responseText);
			element.innerHTML = ajax.responseText;
			element.style.display='inline';
		}
	}
	ajax.open('GET', path,true);
	ajax.send();
}
