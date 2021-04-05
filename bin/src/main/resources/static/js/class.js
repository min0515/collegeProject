window.onload = function() {
	$('.ui.dropdown').dropdown();
};

$(document).ready(function() {

	//class_no 중복검사
	$('#classNumConfirm').on('click', function() {
		var class_no = $('#class_no').val();
		if (class_no == "") {
			$('.classmodal.content').text("강의번호를 입력하세요!")
			$('.ui.mini.classNumconfirm.modal').modal('show');
		} else {
			$.ajax({
				type: 'POST',
				data: { class_no: class_no },
				datatype: 'json',
				url: 'classNumConfirmAjax',
				contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
				success: function(data) {
					var msg = "";
					if (data == "y") {
						msg = "사용중인 강의번호입니다"
						$('.classmodal.content').text(msg)
						$('.ui.mini.classNumconfirm.modal').modal('show');
						$('#class_no').val('');
						$('#class_no').focus();
					} else {
						msg = "사용 가능한 강의번호입니다"
						$('.classmodal.content').text(msg)
						$('.ui.mini.classNumconfirm.modal').modal('show');
					}

				},
				error: function(xhr, status, error) {
					alert('ajax error' + xhr.status);
				}
			});
		}
	})

	//학과검색버튼
	$('#departmentSearchInsert').on('click', function() {
		$('.ui.department.search.modal').modal('show');
	})

	//학과리스트
	$('#departmentExample').DataTable({
		lengthChange: false,
		deferRender: true,
		autoWidth: true,
		displayLength: 5,

		"dom": '<"top"f>rt<"bottom"lp><"clear">', // Positions table elements
		"language": {
			"search": "_INPUT_",            // Removes the 'Search' field label
			"searchPlaceholder": "Search"   // Placeholder for the search box
		},

		"fnDrawCallback": function() {
			$("input[type='search']").attr("id", "searchBox");
			$('#dialPlanListTable').css('cssText', "margin-top: 0px !important;");
			$("select[name='dialPlanListTable_length'], #searchBox").removeClass("input-sm");
			$('#searchBox').css("width", "500px").focus();
			$('#searchBox').css("height", "40px").focus();
			$('#dialPlanListTable_filter').removeClass('dataTables_filter');
		}
	});
	//강의 관리용 리스트
	$('#classAdminListexample').DataTable({
		lengthChange: false,
		deferRender: true,
		autoWidth: true,
		displayLength: 10,

		"dom": '<"top"f>rt<"bottom"lp><"clear">', // 옵션들 위치잡기
		"language": {
			"search": "_INPUT_",            // 검색박스 글자지우기
			"searchPlaceholder": "Search"   // placeholder 만들기
		},

		"fnDrawCallback": function() {
			$("input[type='search']").attr("id", "searchBox");
			$('#dialPlanListTable').css('cssText', "margin-top: 0px !important;");
			$("select[name='dialPlanListTable_length'], #searchBox").removeClass("input-sm");
			$('#searchBox').css("width", "700px").focus();
			$('#searchBox').css("margin", "20px").focus();
			$('#searchBox').css("margin-right", "240px").focus();
			$('#searchBox').css("height", "40px").focus();
			$('#dialPlanListTable_filter').removeClass('dataTables_filter');
		}
	});

	//강의 관리용 리스트에서 삭제버튼
	$("#classAdminListexample").on('click', '#classAdminDeleteBtn', 'td', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var class_no = td.eq(0).text();
		$('.ui.mini.classDelete.modal').modal('show');

		$('#classDeleteOkBtn').on('click', function() {
			$.ajax({
				type: 'POST',
				data: { class_no: class_no },
				datatype: 'json',
				url: 'classDeleteAjax',
				contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
				success: function(data) {
					if (data == "y") {
						row.remove();
						$('#resultmessage').text("삭제 되었습니다.");
						$('#successmessage').css('display', "block")
							.delay(1200).queue(function() {
								$('#successmessage').css('display', "none").dequeue();
							});
					} else {
						$('.ui.noncancel.modal').modal("show");
					}

					$('.ui.mini.classDelete.modal').modal('hide');
				},
				error: function(xhr, status, error) {
					alert('ajax error' + xhr.status);
				}
			});
		})
	});

	//학과 리스트에서 누르면
	$("#departmentExample").on('click', '#departmentSelect', 'td', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var departmentNum = td.eq(0).text();
		var departmentName = td.eq(1).text();
		$('#department_name').val(departmentName);
		$('#department_no').val(departmentNum);
		$('#departmentModalInput').val(departmentName);
	})

	//학과검색모달에서 취소버튼
	$('#departmentSearchCancelBtn').on('click', function() {
		$('#department_no').val("");
		$('#department_name').val("");
		$('#departmentModalInput').val("");
	})
	//업데이트 페이지에서 학과검색모달에서 취소버튼
	$('#departmentUpdateCancelBtn').on('click', function() {
		var beforeDepartment_name = $('#beforeDepartment_name').val();
		var beforeDepartment_no = $('#beforeDepartment_no').val();
		$('#department_no').val(beforeDepartment_no);
		$('#department_name').val(beforeDepartment_name);
		$('#departmentModalInput').val(" ");
	})

	//교수검색버튼
	$("#professorSearchInsert").on('click', function() {
		$('.ui.professor.search.modal').modal('show');
	})

	//교수 리스트에서 누르면
	$("#professorExample").on('click', '#professorSelect', 'td', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var professorNum = td.eq(0).text();
		var professorName = td.eq(1).text();
		$('#professor_name').val(professorName);
		$('#professor_no').val(professorNum);
		$('#professorModalInput').val(professorName);
	})

	//교수검색모달에서 취소버튼
	$('#professorSearchCancelBtn').on('click', function() {
		$('#professor_no').val("");
		$('#professor_name').val("");
		$('#professorModalInput').val("");
	})


	//교수검색모달에서 취소버튼
	$('#professorUpdateCancelBtn').on('click', function() {
		var beforeProfessor_no = $('#beforeProfessor_no').val();
		var beforeProfessor_name = $('#beforeProfessor_name').val();
		$('#professor_no').val(beforeProfessor_no);
		$('#professor_name').val(beforeProfessor_name);
		$('#professorModalInput').val(" ");
	})

	//교수 리스트
	$('#professorExample').DataTable({
		lengthChange: false,
		deferRender: true,
		autoWidth: true,
		displayLength: 5,

		"dom": '<"top"f>rt<"bottom"lp><"clear">',
		"language": {
			"search": "_INPUT_",
			"searchPlaceholder": "Search"
		},

		"fnDrawCallback2": function() {
			$("input[type='search']").attr("id", "searchBox");
			$('#dialPlanListTable').css('cssText', "margin-top: 0px !important;");
			$("select[name='dialPlanListTable_length'], #searchBox").removeClass("input-sm");
			$('#searchBox').css("width", "700px").focus();
			$('#searchBox').css("margin", "20px").focus();
			$('#searchBox').css("margin-right", "130px").focus();
			$('#searchBox').css("height", "40px").focus();
			$('#dialPlanListTable_filter').removeClass('dataTables_filter');
		}
	});

	/*학생용 강의 페이지*/

	//학생용 강의 검색 리스트
	$('#classListExample').DataTable({
		lengthChange: false,
		deferRender: true,
		displayLength: 8,
		searching: false,
		"dom": '<"top"f>rt<"bottom"lp><"clear">',
		destroy: true
	});


	$('#classFindBtn').on('click', function() {
		var credit = $("#credit").val();
		var classType = $("#class_type").val();
		var tb_class = $('#classFindForm').serialize();
		if (credit == "" || classType == "") {
			$('.ui.classFind.modal').modal('show');
			return;
		} else {
			$.ajax({
				type: 'POST',
				data: tb_class,
				datatype: 'json',
				url: 'classFindListView',
				/*cache : false*/
			}).done(function(data) {
				$("#ajaxReplace").replaceWith(data);
				$('#classListExample').DataTable({
					lengthChange: false,
					deferRender: true,
					displayLength: 9,
					searching: false,
					"dom": '<"top"f>rt<"bottom"lp><"clear">',
					destroy: true
				});
			});
		}
	});

	//수강신청버튼
	$("#classListExample").on('click', '#classRegistration', 'td', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var class_no = td.eq(0).text();
		var credit = td.eq(3).text();
		var registrationBtn = td.eq(6).children().eq(0);
		var registrationText = td.eq(6).children().eq(1);
		var capacity = td.eq(5);
		var capacityTextAfter = td.eq(5).text()
		if (capacityTextAfter == 0) {
			var capacityTextAfter = td.eq(5).text();
		} else {
			var capacityTextAfter = td.eq(5).text() - 1;
		}
		$.ajax({
			type: 'POST',
			data: { class_no: class_no, credit: credit },
			datatype: 'json',
			url: 'classRegistrationAjax',
			success: function(data) {
				if (data == "login") {
					$('.classRegistration.header').text('로그인 세션 만료');
					$('.classRegistration.content').text('로그인을 다시 해주세요.');
					$('.ui.classRegistration.modal').modal('show');
				} else if (data == "already") {
					$('.classRegistration.header').text('중복된 수강신청');
					$('.classRegistration.content').text('이미 수강신청 하였습니다.');
					$('.ui.classRegistration.modal').modal('show');
				} else if (data == "full") {
					$('.classRegistration.header').text('수강신청 실패');
					$('.classRegistration.content').text('이미 정원이 다 찬 강의입니다.');
					$('.ui.classRegistration.modal').modal('show');
				} else if (data == "creditfull") {
					$('.classRegistration.header').text('수강신청 실패');
					$('.classRegistration.content').text('최대학점을 초과했습니다.');
					$('.ui.classRegistration.modal').modal('show');
				} else {
					$(registrationBtn).css('display', 'none');
					$(registrationText).css('display', 'block');
					$(capacity).text(capacityTextAfter);
					$.post("myClassListRefreshAjax", function(data) {
					}).done(function(data) {
						$("#ajaxReplaceMyClass").replaceWith(data);
						$('#MyClassListExample').DataTable({
							lengthChange: false,
							deferRender: true,
							displayLength: 7,
							searching: false,
							"dom": '<"top"f>rt<"bottom"lp><"clear">',
							destroy: true
						});
					});
					$.post("myClassListRefreshCreditAjax", function(data) {
					}).done(function(data) {
						$("#creditRefresh").replaceWith(data);
					});
				}
			},
			error: function(xhr, status, error) {
				alert('ajax error' + xhr.status);
			}
		});
	});

	//학생용 수강신청 내역 리스트
	$('#MyClassListExample').DataTable({
		lengthChange: false,
		deferRender: true,
		displayLength: 7,
		searching: false,
		"dom": '<"top"f>rt<"bottom"lp><"clear">',
		destroy: true

	});

	//수강신청 취소버튼
	$("#MyClassListExample").on('click', '#MyClassRegistrationCancelBtn', 'td', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var class_no = td.eq(1).text();
		var credit = td.eq(3).text();
		var registrationCancelBtn = td.eq(5).children().eq(0);

		var registrationCancelText = td.eq(5).children().eq(1);
		$('.ui.MyClassRegistration.modal').modal('show');
		//수강신청 취소 확인 버튼
		$('#MyClassRegistrationCancelOkBtn').off().on('click', function() {
			$('.ui.MyClassRegistration.modal').modal('hide');
			$.ajax({
				type: 'POST',
				data: { class_no: class_no, credit: credit },
				datatype: 'json',
				url: 'classRegistrationCancelAjax',
				success: function(data) {
					if (data == "login") {
						$('.classRegistration.header').text('로그인 세션 만료');
						$('.classRegistration.content').text('로그인을 다시 해주세요.');
						$('.ui.classRegistration.modal').modal('show');
					} else {
						$("#MyClassRegistrationCancelBtn").css('display', "none");
						$('#MyClassCancelOk').css('display', "block")
							.delay(700).queue(function() {
								row.remove();
							});
					}

				},
				error: function(xhr, status, error) {
					alert('ajax error' + xhr.status);
				}
			});
		});
	});

	//수강신청내역 새로고침 버튼
	$('#MyClassListRefresh').on('click', function() {
		$.post("myClassListRefreshAjax", function(data) {
		}).done(function(data) {
			$("#ajaxReplaceMyClass").replaceWith(data);
			$('#MyClassListExample').DataTable({
				lengthChange: false,
				deferRender: true,
				displayLength: 7,
				searching: false,
				"dom": '<"top"f>rt<"bottom"lp><"clear">',
				destroy: true
			});
		});
		$.post("myClassListRefreshCreditAjax", function(data) {
		}).done(function(data) {
			$("#creditRefresh").replaceWith(data);
		});
	});

	//학생용 수강바구니신청 내역 리스트
	$('#classBasketListExample').DataTable({
		lengthChange: false,
		deferRender: true,
		displayLength: 8,
		searching: false,
		"dom": '<"top"f>rt<"bottom"lp><"clear">',
		destroy: true
	});

	//수강바구니 신청에서 강의 검색
	$('#classBasketFindBtn').on('click', function() {
		var credit = $("#credit").val();
		var classType = $("#class_type").val();
		var tb_class = $('#classBasketFindForm').serialize();
		var depart = $("#department_no").val();
		if (credit == "" || classType == "") {
			$('.ui.classBasketFind.modal').modal('show');
			return;
		} else {
			$.ajax({
				type: 'POST',
				data: tb_class,
				datatype: 'json',
				url: 'classBasketFindListView',
				/*cache : false*/
			}).done(function(data) {
				$("#ajaxBasketReplace").replaceWith(data);
				$('#classBasketListExample').DataTable({
					lengthChange: false,
					deferRender: true,
					displayLength: 9,
					searching: false,
					"dom": '<"top"f>rt<"bottom"lp><"clear">',
					destroy: true
				});
			});
		}
	});

	//학생용 수강바구니 내역 리스트
	$('#MyClassBasketListExample').DataTable({
		lengthChange: false,
		deferRender: true,
		displayLength: 7,
		searching: false,
		autowidth: true,
		"dom": '<"top"f>rt<"bottom"lp><"clear">',
		destroy: true
	});

	var makeAjaxCall = function(url, success_callback) {
		$.ajax({
			type: 'POST',
			url: url,
			datatype: 'json',
		});
	};

	//수강바구니내역 새로고침 버튼
	$('#MyClassBasketListRefresh').on('click', function() {
		$.post("myClassBasketListRefreshAjax", function(data) {
		}).done(function(data) {
			$("#ajaxReplaceMyClassBasket").replaceWith(data);
			$('#MyClassBasketListExample').DataTable({
				lengthChange: false,
				deferRender: true,
				displayLength: 7,
				searching: false,
				"dom": '<"top"f>rt<"bottom"lp><"clear">',
				destroy: true
			});
		});
		$.post("myClassBasketListRefreshCreditAjax", function(data) {
		}).done(function(data) {
			$("#preCreditRefresh").replaceWith(data);
		});
	});

	//수강바구니담기버튼
	$("#classBasketListExample").on('click', '#classPreRegistration', 'td', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var class_no = td.eq(0).text();
		var credit = td.eq(3).text();
		var preRegistrationBtn = td.eq(6).children().eq(0);
		var preRegistrationText = td.eq(6).children().eq(1);
		var capacity = td.eq(5);
		var capacityTextAfter = Number(td.eq(5).text()) + 1;
		var a = 0;
		$.ajax({
			type: 'POST',
			data: { class_no: class_no, credit: credit },
			datatype: 'json',
			url: 'classPreRegistrationAjax',
			success: function(data) {
				if (data == "login") {
					$('.classPreRegistration.header').text('로그인 세션 만료');
					$('.classPreRegistration.content').text('로그인을 다시 해주세요.');
					$('.ui.classPreRegistration.modal').modal('show');
				} else if (data == "already") {
					$('.classPreRegistration.header').text('수강바구니');
					$('.classPreRegistration.content').text('이미 수강바구니에 담은 강의입니다');
					$('.ui.classPreRegistration.modal').modal('show');
				} else if (data == "creditfull") {
					$('.classPreRegistration.header').text('담기 실패');
					$('.classPreRegistration.content').text('최대학점을 초과했습니다');
					$('.ui.classPreRegistration.modal').modal('show');
				} else {
					$(preRegistrationBtn).css('display', 'none');
					$(preRegistrationText).css('display', 'block');
					$(capacity).text(capacityTextAfter);

					$.post("myClassBasketListRefreshAjax", function(data) {
					}).done(function(data) {
						$("#ajaxReplaceMyClassBasket").replaceWith(data);
						$('#MyClassBasketListExample').DataTable({
							lengthChange: false,
							deferRender: true,
							displayLength: 7,
							searching: false,
							"dom": '<"top"f>rt<"bottom"lp><"clear">',
							destroy: true
						});
					});
					$.post("myClassBasketListRefreshCreditAjax", function(data) {
					}).done(function(data) {
						$("#preCreditRefresh").replaceWith(data);
					});
				}
			},
			error: function(xhr, status, error) {
				alert('ajax error' + xhr.status);
			}
		});



	});

	//수강바구니 취소버튼
	$("#MyClassBasketListExample").on('click', '#MyClassPreRegistrationCancelBtn', 'td', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var class_no = td.eq(1).text();
		var credit = td.eq(3).text();
		var preRegistrationCancelBtn = td.eq(5).children().eq(0);
		var preRegistrationCancelText = td.eq(5).children().eq(1);
		$('.ui.MyClassPreRegistration.modal').modal('show');
		//수강바구니 삭제 확인 버튼
		$('#MyClassPreRegistrationCancelOkBtn').off().on('click', function() {
			$('.ui.MyClassPreRegistration.modal').modal('hide');
			$.ajax({
				type: 'POST',
				data: { class_no: class_no, credit: credit },
				datatype: 'json',
				url: 'classPreRegistrationCancelAjax',
				success: function(data) {
					if (data == "login") {
						$('.classPreRegistration.header').text('로그인 세션 만료');
						$('.classPreRegistration.content').text('로그인을 다시 해주세요.');
						$('.ui.classPreRegistration.modal').modal('show');
					} else {
						$('#MyClassPreRegistrationCancelBtn').css('display', "none");
						$('#MyClassBasketCancelOk').css('display', "block")
							.delay(700).queue(function() {
								row.remove();
							});
					}

				},
				error: function(xhr, status, error) {
					alert('ajax error' + xhr.status);
				}
			});
		});
	});
	$('#preRegistrationStart').on('click', function() {
		$('.ui.registrationAdmin.button').attr("id", "preRegistrationStartBtn");
		$('.ui.registrationAdmin.button').text("시작");
		$('.registrationAdmin.header').text("수강바구니 기간 시작");
		$('.registrationAdmin.content').text("강의의 정원정보가 초기화되며 복구되지 않습니다. 수강바구니 기간을 시작하시겠습니까?");
		$('.ui.registrationAdmin.modal').modal('show');
		$('#preRegistrationStartBtn').on('click', function() {
			$.post("preRegistrationStartAjax", function(data) {
				$('.ui.registrationAdmin.modal').modal('hide');
			})
		});
	});

	$('#preRegistrationEnd').on('click', function() {
		$('.ui.registrationAdmin.button').attr("id", "preRegistrationEndBtn");
		$('.ui.registrationAdmin.button').text("마감");
		$('.registrationAdmin.header').text("수강바구니 기간 마감");
		$('.registrationAdmin.content').text("마감 클릭시 데이터는 복구되지 않습니다. 수강바구니 기간을 마감하시겠습니까?");
		$('.ui.registrationAdmin.modal').modal('show');
		$('#preRegistrationEndBtn').on('click', function() {
			$.post("preRegistrationEndAjax", function(data) {
				$('.ui.registrationAdmin.modal').modal('hide');
			})
		});
	});

	$('#registrationStart').on('click', function() {
		$('.ui.registrationAdmin.button').attr("id", "registrationStartBtn");
		$('.ui.registrationAdmin.button').text("시작");
		$('.registrationAdmin.header').text("수강신청 기간 시작");
		$('.registrationAdmin.content').text("강의의 정원정보가 수정되며 복구되지 않습니다. 수강신청 기간을 시작하시겠습니까?");
		$('.ui.registrationAdmin.modal').modal('show');
		$('#registrationStartBtn').on('click', function() {
			$.post("registrationStartAjax", function(data) {
				$('.ui.registrationAdmin.modal').modal('hide');
			})
		});
	});

	$('#registrationEnd').on('click', function() {
		$('.ui.registrationAdmin.button').attr("id", "registrationEndBtn");
		$('.ui.registrationAdmin.button').text("마감");
		$('.registrationAdmin.header').text("수강신청 기간 마감");
		$('.registrationAdmin.content').text("마감 클릭시 데이터는 복구되지 않습니다. 수강신청 기간을 마감하시겠습니까?");
		$('.ui.registrationAdmin.modal').modal('show');
		$('#registrationEndBtn').on('click', function() {
			$.post("registrationEndAjax", function(data) {
				$('.ui.registrationAdmin.modal').modal('hide');
			})
		});
	});

	//학생용 수강 검색 내역 리스트
	$('#MyclassSearchfinalListExample').DataTable({
		lengthChange: false,
		deferRender: true,
		displayLength: 7,
		searching: false,
		"dom": '<"top"f>rt<"bottom"lp><"clear">',
		destroy: true
	});

	//내강의 검색
	$('#myclassListSearchBtn').on('click', function() {
		var yyyy = $("#yyyy").val();
		var term = "0" + $("#term").val();
		var term_no = yyyy + term;
		$.ajax({
			type: 'POST',
			data: { term_no: term_no },
			datatype: 'json',
			url: 'myclassFindListViewFinalAjax',
		}).done(function(data) {
			$("#ajaxReplaceFinalList").replaceWith(data);
			$('#MyclassSearchfinalListExample').DataTable({
				lengthChange: false,
				deferRender: true,
				displayLength: 9,
				searching: false,
				"dom": '<"top"f>rt<"bottom"lp><"clear">',
				destroy: true
			});
		});
	});

	$('.ui.rating')
		.rating({
			initialRating: 3,
			maxRating: 5
		});

	$('#classReviewInsertBtn').on('click', function() {
		var tb_classreview = $('#classReviewInsert').serialize();
		$.ajax({
			type: 'POST',
			data: tb_classreview,
			datatype: 'json',
			url: 'classReviewAlreadyCheckAjax',
		}).done(function(data) {
			if (data == "login") {
				$('.ui.classReviewLogin.modal').modal('show');
			} else if (data == "already") {
				$('.ui.classReviewAlready.modal').modal('show');
			} else {
				$("#classReviewInsert").submit();
			}
		});

	});

	//학생용 강의 검색 리스트
	$('#classSearchListExample').DataTable({
		lengthChange: false,
		deferRender: true,
		displayLength: 8,
		searching: false,
		"dom": '<"top"f>rt<"bottom"lp><"clear">',
		destroy: true
	});


	$('#classSearchFindBtn').on('click', function() {
		var credit = $("#credit").val();
		var classType = $("#class_type").val();
		var tb_class = $('#classFindForm').serialize();
		if (credit == "" || classType == "") {
			$('.ui.classFind.modal').modal('show');
			return;
		} else {
			$.ajax({
				type: 'POST',
				data: tb_class,
				datatype: 'json',
				url: 'classSearchListView',
				/*cache : false*/
			}).done(function(data) {
				$("#ajaxSearchReplace").replaceWith(data);
				$('#classSearchListExample').DataTable({
					lengthChange: false,
					deferRender: true,
					displayLength: 9,
					searching: false,
					"dom": '<"top"f>rt<"bottom"lp><"clear">',
					destroy: true
				});
			});
		}
	});

	$("#classSearchListExample").on('click', '#classReviewList', 'td', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var class_no = td.eq(0).text();
		$('.ui.classReview.search.modal').modal('show');
		$.ajax({
			type: 'POST',
			data: { class_no: class_no },
			datatype: 'json',
			url: 'classReviewSearchAjax',
			/*cache : false*/
		}).done(function(data) {
			$("#ajaxReviewReplace").replaceWith(data);
		});
	});








});