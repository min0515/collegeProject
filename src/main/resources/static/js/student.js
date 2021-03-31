//우편번호
function zipcodeFind() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var fullAddr = ''; // 최종 주소 변수
			var extraAddr = ''; // 조합형 주소 변수
			// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				fullAddr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				fullAddr = data.jibunAddress;
			}
			// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
			if (data.userSelectedType === 'R') {
				//법정동명이 있을 경우 추가한다.
				if (data.bname !== '') {
					extraAddr += data.bname;
				}
				// 건물명이 있을 경우 추가한다.
				if (data.buildingName !== '') {
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
				fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')' : '');
			}
			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
			document.getElementById('addr1').value = fullAddr;
			// 커서를 상세주소 필드로 이동한다.
			document.getElementById('addr2').focus();
		}
	}).open();
}


//기본 틀
$(document)
    .ready(function() {

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

//학생 리스트 테이블
	$('#studenttable').DataTable({
		deferRender: true,
		autoWidth: false,
		scrollY: 500,
		scrollCollapse: true,
		scroller: true,
		language: { search: "" }
	});
	
	$('#gradetable').DataTable({
		deferRender: true,
		autoWidth: false,
		scrollY: 500,
		scrollCollapse: true,
		scroller: true,
		language: { search: "" }
	});
	
	$('#departmentttable').DataTable({
		deferRender: true,
		autoWidth: true,
		scrollY: 500,
		scrollCollapse: true,
		scroller: true,
		language: { search: "" }
	});
	
	$('#professortable').DataTable({
		deferRender: true,
		autoWidth: true,
		scrollY: 500,
		scrollCollapse: true,
		scroller: true,
		language: { search: "" }
	});
	
	
//학생 리스트 삭제
	$(document).on('click', '#studenttable td #studentdeletebtn', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var student_no = td.eq(0).text();
		$('.ui.mini.modal.studentdel').modal('show');

		$('#studentdeleteok').on('click', function() {
			$.ajax({
				type: 'POST',
				data: { student_no: student_no },
				datatype: 'json',
				url: 'studentDeleteAjax',
				contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
				success: function(data) {
					if (result == "y") {
						row.remove();
						$('#resultmessage').text("삭제 되었습니다.");
					} else {
						$('#resultmessage').text("삭제 되지않았습니다.");
					}
					$('#successmessage').css('display', "block")
						.delay(1000).queue(function() {
							$('#successmessage').css('display', "none").dequeue();
						});
					$('.ui.mini.modal.studentdel').modal('hide');
				},
				error: function(xhr, status, error) {
					alert('ajax error' + xhr.status);
				}
			});
		});

		$('#studentdeletecancel').on('click', function() {
			$('.ui.mini.modal.productdel').modal('hide');
		});

	});
	
//학생 리스트 수정		
	$(document).on('click', '#studenttable td #studenteditbtn', function() {
		var row = $(this).closest('tr');
		var td = row.children();

		$.ajax({
			type: 'POST',
			data: {},
			datatype: 'json',
			url: 'StudentUpdateAjax',
			contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
			success: function(data) {
				if (data == "y") {
					$('#resultmessage').text("수정 되었습니다.");
				} else {
					$('#resultmessage').text("수정 되지 않았습니다.");
				}

				$('#successmessage').css('display', "block")
					.delay(1200).queue(function() {
						$('#successmessage').css('display', "none").dequeue();
					});
			},
			error: function(xhr, status, error) {
				alert('ajax error' + xhr.status);
			}
		});
	});
	
//성적 수정
	$(document).on('click', '#gradetable td #gradeeditbtn', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var term_no = td.eq(0).text();
		var student_no = td.eq(1).text();
		var class_no = td.eq(2).text();
		var point = td.eq(3).children().val();
		alert(point)
		$.ajax({
			type: 'POST',
			data: {term_no: term_no, student_no: student_no ,class_no:class_no, point: point},
			datatype: 'json',
			url: 'gradeUpdateAjax',
			contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
			success: function(data) {
				if (data == "y") {
					$('#resultmessage').text("수정 되었습니다.");
				} else {
					$('#resultmessage').text("수정 되지 않았습니다.");
				}

				$('#successmessage').css('display', "block")
					.delay(1200).queue(function() {
						$('#successmessage').css('display', "none").dequeue();
					});
			},
			error: function(xhr, status, error) {
				alert('ajax error' + xhr.status);
			}
		});
	});


	$('#studentdeletecancel').on('click', function() {
		$('.ui.mini.modal.studentdel').modal('show');
	});
	
	$('#studentbtn').on('click', function() {
		$('.ui.modal.stuinsertselect').modal('show');
	});
	
	$('#coachbtn').on('click', function() {
		$('.ui.modal.coachinsert').modal('show');
	});
	
	//학생 중복확인 모달
	$('.stuconfirm').on('click', function() {
		var student_no = $('#student_no').val();
		if (student_no == "") {
			$('.studentNoConfirm.description').text("학번을 입력하세요!");
			$('.ui.stuconfirm.modal').modal('show');
			return;
		} else {
			var student_no = student_no;
			$.ajax({
				type: 'POST',
				data: { student_no: student_no },
				datatype: 'json',
				url: 'studentConfirmAjax',
				contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
				success: function(data) {
					var msg = "";
					if (data == "y") {
						msg = "사용중인 학번입니다!"
						$('.confirmyn').val('n');
						$('.description').text(msg);
						$('.ui.stuconfirm.modal').modal('show');
						$('#student_no').val('');
						$('#student_no').focus();
					} else {
						$('.confirmyn').val('y');
						msg = "사용 가능한 학번입니다!"
						$('.description').text(msg);
						$('.ui.stuconfirm.modal').modal('show');
					}

				},
				error: function(xhr, status, error) {
					alert('ajax error' + xhr.status);
				}
			});
		}
		$('.ui.black.deny.button').modal('hide');
	});
	
	$("#departmentttable").on('click','td', function(){
		var row = $(this).closest('tr');
		var td = row.children();
		var department_no = td.eq(0).text();
		$('#department_no').val(department_no);
	})
	
	$("#professortable").on('click', 'td', function(){
		var row = $(this).closest('tr');
		var td = row.children();
		var coach_professor_no = td.eq(0).text();
		$('#coach_professor_no').val(coach_professor_no);
	})
	
	$("#gradetable").on('click', 'td', function(){
		var row = $(this).closest('tr');
		var td = row.children();
		var student_no = td.eq(1).text();
		$('#student_no').val(student_no);
	})
	
});