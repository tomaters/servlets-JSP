function writeSave(){
	if(document.writeform.writer.value=="") {
		alert("Please enter an author");
		document.writeform.writer.focus();
		return false;
	}
	if(document.writeform.subject.value=""){
		alert("Please enter a subject");
		document.writeform.subject.focus();
		return false;
	}
	if(document.writeform.content.value=""){
		alert("Please enter the content");
		document.writeform.content.focus();
		return false;
	}
	if(document.writeform.pass.value=""){
		alert("Please enter your password");
		document.writeform.pass.focus();
		return false;
	}
}