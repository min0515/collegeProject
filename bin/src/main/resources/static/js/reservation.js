$(document).ready(function() {

	//도서관 페이지에서 자리예약 버튼
	$("#libraryReservationGoBtn").on("click", function() {
		var room_no = 1;
		$.ajax({
			type: 'POST',
			data: { room_no: room_no },
			datatype: 'json',
			url: 'reservationView',
		}).done(function(data) {
			$("#reservationColumn").replaceWith(data);
			$("#hiddenRoom_no").val(room_no);
			$('.ui.libraryReservation.modal').modal('show');
		});
	});

	//제 1 열람실 페이지에서 자리예약 버튼
	$("#studyroomReservationGoBtn").on("click", function() {
		var room_no = 2;
		$.ajax({
			type: 'POST',
			data: { room_no: room_no },
			datatype: 'json',
			url: 'reservationView2',
		}).done(function(data) {
			$("#reservationColumn").replaceWith(data);
			$("#hiddenRoom_no").val(room_no);
			$('.ui.libraryReservation.modal').modal('show');
		});
	});

	//제 2 열람실 페이지에서 자리예약 버튼
	$("#studyroom2ReservationGoBtn").on("click", function() {
		var room_no = 3;
		$.ajax({
			type: 'POST',
			data: { room_no: room_no },
			datatype: 'json',
			url: 'reservationView3',
		}).done(function(data) {
			$("#reservationColumn").replaceWith(data);
			$("#hiddenRoom_no").val(room_no);
			$('.ui.libraryReservation.modal').modal('show');
		});
	});



	//예약페이지 변경
	$("#libraryBtn").on("click", function() {
		$('#libraryBtn').attr('class', 'item active');
		$('#studyroomBtn').attr('class', 'item');
		$('#studyroomBtn2').attr('class', 'item');
		$('#library').css('display', 'block');
		$('#studyroom').css('display', 'none');
		$('#studyroom2').css('display', 'none');
	});


	$("#studyroomBtn").on("click", function() {
		$('#studyroomBtn').attr('class', 'item active');
		$('#libraryBtn').attr('class', 'item');
		$('#studyroomBtn2').attr('class', 'item');
		$('#library').css('display', 'none');
		$('#studyroom').css('display', 'block');
		$('#studyroom2').css('display', 'none');
	});

	$("#studyroomBtn2").on("click", function() {
		$('#studyroomBtn').attr('class', 'item');
		$('#libraryBtn').attr('class', 'item');
		$('#studyroomBtn2').attr('class', 'item active');
		$('#library').css('display', 'none');
		$('#studyroom').css('display', 'none');
		$('#studyroom2').css('display', 'block');
	});

	//모달에서 자리선택 후 예약
	$(document).on("click", '#reservationPickBtn', 'this', function() {
		var seq = $(this).val();
		var room_no = $("#hiddenRoom_no").val();
		$(".blue.on").attr('class', 'ui blue basic button');
		$(this).attr('class', 'ui blue on button');
		$("#reservationFinalBtn").off().on("click", function() {
			$.ajax({
				type: 'POST',
				data: { seq: seq, room_no: room_no },
				datatype: 'json',
				url: 'reservationAjax',
				contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
				success: function(data) {
					if (data == "login") {
						location.href = "login";
					} else if (data == "already") {
						$('.ui.alreadyReservation.modal').modal('show');
						$('.ui.libraryReservation.modal').modal('hide');
					} else {
						$('.ui.libraryReservation.modal').modal('hide');
					}
				},
				error: function(xhr, status, error) {
					alert('ajax error' + xhr.status);
				}
			})
		})
	});

	$("#myReservationTable").on('click', '#myReservationCancelBtn', 'td', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var room_no = td.eq(0).text();
		var seq = td.eq(2).text();
		$('.ui.myReservationCancel.modal').modal('show');
		$('#myReservationCancelOkBtn').on('click', function() {
			$.ajax({
				type: 'POST',
				data: { room_no: room_no, seq: seq },
				datatype: 'json',
				url: 'reservationDeleteAjax',
				contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
				success: function(data) {
					row.remove();
					$('.ui.myReservationCancel.modal').modal('hide');
				},
				error: function(xhr, status, error) {
					alert('ajax error' + xhr.status);
				}
			});
		})
	});

	$('.special.cards .image').dimmer({
		on: 'hover'
	});

	$("#studyroomReservationColumn").on('click', '#studyroomReservationBtn', function() {
		var input = $(this).prev('input');
		var room_no = input.val(); 
		$('.ui.spaceReservation.modal').modal('show');
		$('#spaceReservationBtn').off().on('click', function() {
			$.ajax({
				type: 'POST',
				data: { room_no: room_no },
				datatype: 'json',
				url: 'spaceReservationAjax',
				success: function(data) {
					if (data == "login") {
						location.href = "login";
					} else if (data == "already") {
						$('.ui.spaceReservationAlready.modal').modal('show');
						$('.ui.spaceReservation.modal').modal('hide');
					} else {
						$('.ui.spaceReservation.modal').modal('hide');
						location.href = "spaceReservationPageGo";
					}
				},
				error: function(xhr, status, error) {
					alert('ajax error' + xhr.status);
				}
			});
		});
	});
	
	
});