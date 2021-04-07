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
	$('#buyReview').on('click', function() {
		$('.ui.mini.reviewmodal').modal('show');
	});
		$('#star_grade a').click(function(){
        $(this).parent().children("a").removeClass("on");  /* 별점의 on 클래스 전부 제거 */ 
        $(this).addClass("on").prevAll("a").addClass("on"); /* 클릭한 별과, 그 앞 까지 별점에 on 클래스 추가 */
        return false;
    });
	$('.ui.star.rating').rating('enable');
});