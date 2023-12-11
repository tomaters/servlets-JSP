function idCheck(id) {
	if (id == "") {
		alert("Enter ID.");
		document.regForm.id.focus();
	} else {
		url = "member.mdo?cmd=idCheck&id=" + id;
		window.open(url, "post", "width=300,height=150");
	}
}

function zipCheck() {
	url = "member.mdo?cmd=zipCheck&check=y";
	window.open(url, "post", "toolbar=no, width=500, height=300, directories = no, status = yes, scrollbars = yes, menubar = no");
}

function inputCheck() {
	var theForm = document.regForm;
	if (!theForm.id.value) {
		alert("Enter ID");
		theForm.id.focus();
		return;
	}
	if (!theForm.pass.value) {
		alert("Enter password");
		theForm.pass.focus();
		return;
	}
	if (!theForm.repass.value) {
		alert("Check password");
		theForm.repass.focus();
		return;
	}
	if (theForm.repass.value != theForm.pass.value) {
		alert("Passwords do not match");
		theForm.repass.focus();
		return;
	}
	if (!theForm.name.value) {
		alert("Enter name");
		theForm.name.focus();
		return;
	}
	if (!theForm.phone1.value) {
		alert("Enter first digits of phone");
		theForm.phone1.focus();
		return;
	}
	if (!theForm.phone2.value) {
		alert("Enter middle digits of phone");
		theForm.phone2.focus();
		return;
	}
	if (!theForm.phone3.value) {
		alert("Enter last digits of phone");
		theForm.phone3.focus();
		return;
	}
	if (!theForm.email.value) {
		alert("Enter email address");
		theForm.email.focus();
		return;
	}
	var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	if (!regExp.test(theForm.email.value)) {
		alert("Check email address");
		theForm.email.focus();
		return;
	}
	if (!theForm.zipcode.value) {
		alert("Enter zipcode");
		theForm.zipcode.focus();
		return;
	}
	if (!theForm.address1.value) {
		alert("Enter address 1");
		theForm.address1.focus();
		return;
	}
	if (!theForm.address2.value) {
		alert("Enter address 2");
		theForm.address2.focus();
		return;
	}
	theForm.submit();
}