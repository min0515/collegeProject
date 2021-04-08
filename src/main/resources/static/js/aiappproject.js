$(document).ready(function() {

	// fix main menu to page on passing
	$('.main.menu').visibility({
		type: 'fixed'
	});
	$('.overlay').visibility({
		type: 'fixed',
		offset: 80
	});

	// lazy load images
	$('.image').visibility({
		type: 'image',
		transition: 'vertical flip in',
		duration: 500
	});

	// show dropdown on hover
	$('.main.menu  .ui.dropdown').dropdown({
		on: 'hover'
	});

	$('#studentLoginBtn').on('click', function() {
		$('#loginform').attr('action','studentLoginUp');
		$("#loginform").submit();
	});

	$('#professorLoginBtn').on('click', function() {
		$('#id').attr('name','professor_no');
		$('#pw').attr('name','professor_pw');
		$('#loginform').attr('action','professorLoginUp');
		$("#loginform").submit();
	});
	
	$('#studentLoginBtn2').on('click', function() {
		$('#loginform').attr('action','studentLoginUp2');
		$("#loginform").submit();
	});

	$('#professorLoginBtn2').on('click', function() {
		$('#id').attr('name','professor_no');
		$('#pw').attr('name','professor_pw');
		$('#loginform').attr('action','professorLoginUp2');
		$("#loginform").submit();
	});
	
	$('#studentLoginBtn3').on('click', function() {
		$('#loginform').attr('action','studentLoginUp3');
		$("#loginform").submit();
	});

	$('#professorLoginBtn3').on('click', function() {
		$('#id').attr('name','professor_no');
		$('#pw').attr('name','professor_pw');
		$('#loginform').attr('action','professorLoginUp3');
		$("#loginform").submit();
	});
	
});