$(document).ready(function() {
	
	$("#goodsmanagebtn").click(function(){
		$('#goodsmanagebtn').attr('class', 'item active');
		$('#goodsorderbtn').attr('class', 'item');
		$('#goodscancelbtn').attr('class', 'item');
		$('#goodsreviewbtn').attr('class', 'item');
		$('#goodsqnabtn').attr('class', 'item');
	})
	
	$("#goodsorderbtn").click(function(){
		$('#goodsmanagebtn').attr('class', 'item');
		$('#goodsorderbtn').attr('class', 'item active');
		$('#goodscancelbtn').attr('class', 'item');
		$('#goodsreviewbtn').attr('class', 'item');
		$('#goodsqnabtn').attr('class', 'item');
	})

	$("#goodscancelbtn").click(function(){
		$('#goodsmanagebtn').attr('class', 'item');
		$('#goodsorderbtn').attr('class', 'item');
		$('#goodscancelbtn').attr('class', 'item active');
		$('#goodsreviewbtn').attr('class', 'item');
		$('#goodsqnabtn').attr('class', 'item');
	})
	
	$("#goodsreviewbtn").click(function(){
		$('#goodsmanagebtn').attr('class', 'item');
		$('#goodsorderbtn').attr('class', 'item');
		$('#goodscancelbtn').attr('class', 'item');
		$('#goodsreviewbtn').attr('class', 'item active');
		$('#goodsqnabtn').attr('class', 'item');
	})
	
	$("#goodsqnabtn").click(function(){
		$('#goodsmanagebtn').attr('class', 'item');
		$('#goodsorderbtn').attr('class', 'item');
		$('#goodscancelbtn').attr('class', 'item');
		$('#goodsreviewbtn').attr('class', 'item');
		$('#goodsqnabtn').attr('class', 'item active');
	})
	
});