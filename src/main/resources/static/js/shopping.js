
function test01() {
	document.getElementById("name2").value = $("#name1").val();
	document.getElementById("phone2").value = $("#phone1").val();
};

window.onpageshow = function(event) {
	if (event.persisted || (window.performance && window.performance.navigation.type == 2)) {
		// Back Forward Cache로 브라우저가 로딩될 경우 혹은 브라우저 뒤로가기 했을 경우
		response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
	}
}

// 검색시 주소 뛰우기
function zipcodeFindB() {

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

};


$(document).ready(function() {


	$('#qtyInputProdetail').on('change', function() {
		var qty = $('#qtyInputProdetail').val();
		var price = $('#priceHiddenDetail').val();
		var result = String(price * qty);
		var finalresult = result.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		$('#goodsDetailTotalPrice').text(finalresult + '원');
		$('#detailTotalQty').text("상품금액(" + qty + "개)");
	});

	$('#detailCartGo').off().on('click', function() {
		var tb_cart = $('#goodsDetailform').serialize();
		$.ajax({
			type: 'POST',
			data: tb_cart,
			datatype: 'json',
			url: 'goodsCartIn',
			success: function(data) {
				if (data == "login") {
					location.href = "login";
				} else {
					$('.ui.goodsdetailCart.modal').modal('show');
				}
			},
			error: function(xhr, status, error) {
				alert('ajax error' + xhr.status);
			}

		});
	});

	$('#cartGoBtn').on('click', function() {
		$('.ui.goodsdetailCart.modal').modal('hide');
		location.href = "goodsCartGo";
	});

	$("#myCartTable").off().on('change', '#checkmycart', 'td', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var input = td.eq(0).children().children();
		var qty = td.eq(3).children().val();
		var deliveryVal = Number(td.eq(6).children().val());
		var totalPriceVal = Number(td.eq(7).children().val()) * qty;
		var result = deliveryVal + totalPriceVal;
		var g_seq = td.eq(9).children().val();
		if ($(input).is(":checked") == true) {
			$.ajax({
				type: 'POST',
				data: { g_seq: g_seq },
				datatype: 'json',
				url: 'cartProductCheckYnAjax',
				success: function(data) {
					if (data == "login") {
						location.href = 'shoppingLogin'
					} else {

					}
				},
				error: function(xhr, status, error) {
					alert('ajax error' + xhr.status);
				}
			});
			$('#productPrice').text(Number($('#productPriceVal').val()) + totalPriceVal);
			var productPriceRe = $('#productPrice').text().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
			$('#productPrice').text(productPriceRe)
			$('#productPriceVal').val(Number($('#productPriceVal').val()) + totalPriceVal);

			$('#deliveryPrice').text(Number($('#deliveryPriceVal').val()) + deliveryVal);
			var deliveryPriceRe = $('#deliveryPrice').text().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
			$('#deliveryPrice').text(deliveryPriceRe)
			$('#deliveryPriceVal').val(Number($('#deliveryPriceVal').val()) + deliveryVal);

			$('#finalPrice').text(Number($('#finalPriceVal').val()) + result);
			var finalPriceRe = $('#finalPrice').text().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
			$('#finalPrice').text(finalPriceRe)
			$('#finalPriceVal').val(Number($('#finalPriceVal').val()) + result);

		} else {
			$.ajax({
				type: 'POST',
				data: { g_seq: g_seq },
				datatype: 'json',
				url: 'cartProductNonCheckYnAjax',
				success: function(data) {
					if (data == "login") {
						location.href = 'shoppingLogin'
					}
				},
				error: function(xhr, status, error) {
					alert('ajax error' + xhr.status);
				}
			});
			$('#productPrice').text(Number($('#productPriceVal').val()) - totalPriceVal);
			var productPriceRe = $('#productPrice').text().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
			$('#productPrice').text(productPriceRe)
			$('#productPriceVal').val(Number($('#productPriceVal').val()) - totalPriceVal);

			$('#deliveryPrice').text(Number($('#deliveryPriceVal').val()) - deliveryVal);
			var deliveryPriceRe = $('#deliveryPrice').text().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
			$('#deliveryPrice').text(deliveryPriceRe)
			$('#deliveryPriceVal').val(Number($('#deliveryPriceVal').val()) - deliveryVal);

			$('#finalPrice').text(Number($('#finalPriceVal').val()) - result);
			var finalPriceRe = $('#finalPrice').text().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
			$('#finalPrice').text(finalPriceRe)
			$('#finalPriceVal').val(Number($('#finalPriceVal').val()) - result);
		}
	});



	$("#myCartTable").on('change', '#qtyInputProdetail', 'td', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var qty = td.eq(3).children().val();
		var beforeQty = td.eq(8).children().val();
		var input = td.eq(0).children().children();
		var totalPriceVal = Number(td.eq(7).children().val());
		if ($(input).is(":checked") == true) {
			$('#productPriceVal').val(Number($('#productPriceVal').val()) - beforeQty * totalPriceVal + qty * totalPriceVal);
			$('#productPrice').text(Number($('#productPriceVal').val()));
			var productPriceRe = $('#productPrice').text().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
			$('#productPrice').text(productPriceRe)
			$('#finalPriceVal').val(Number($('#productPriceVal').val()) + Number($('#deliveryPriceVal').val()));
			$('#finalPrice').text(Number($('#finalPriceVal').val()));
			var finalPriceRe = $('#finalPrice').text().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
			$('#finalPrice').text(finalPriceRe);
			td.eq(8).children().val(qty);
		} else {
			td.eq(8).children().val(qty);
		}
	});

	$("#goodsallCheck").click(function() {
		var chk = $("input:checkbox[id='goodsallCheck']").prop("checked");
		if (chk) {
			$(".chBox").prop("checked", true);
		} else {
			$(".chBox").prop("checked", false);
		}
	});

	//	$('#g_qty').on('change', function() {
	//		var price = $('#g_pricehidden').val();
	//		var qty = Number($(this).val());
	//		var result =String(price*qty); 
	//		var commaresult = result.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	//		var delivery = $('#g_delivery').val();
	//		var commafinalresult =String(price*qty + Number(delivery));
	//		var finalResult = commafinalresult.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	//		$('#final_price').text(commaresult+'원');
	//		$('#total_money').text(finalResult+'원');
	//		$('#countupdown').text("총결제금액("+qty+")개");
	//	})

	$('#onePaymentGo').on('click', function() {
		$("#goodsDetailform").attr("action", "onePaymentGo");
		$("#goodsDetailform").submit();
	});


	$('#cartPayment').on('click', function() {

		if ($('input').is(":checked") == true) {
			location.href = 'cartPaymentGo'
		} else {
			$('.ui.paymentGo.modal').modal('show');

		}

	});

	$("#goodinfobtn").click(function() {
		$('#goodinfobtn').attr('class', 'item active');
		$('#goodreviewbtn').attr('class', 'item');
		$('#goodqnabtn').attr('class', 'item');
		$('#goodsinfo').css('display', 'block');
		$('#goodsreview').css('display', 'none');
		$('#goodsqna').css('display', 'none');
	});

	$("#goodreviewbtn").click(function() {
		$('#goodinfobtn').attr('class', 'item');
		$('#goodreviewbtn').attr('class', 'item active');
		$('#goodqnabtn').attr('class', 'item');
		$('#goodsinfo').css('display', 'none');
		$('#goodsreview').css('display', 'block');
		$('#goodsqna').css('display', 'none');
	});

	$("#goodqnabtn").click(function() {
		$('#goodinfobtn').attr('class', 'item');
		$('#goodreviewbtn').attr('class', 'item');
		$('#goodqnabtn').attr('class', 'item active');
		$('#goodsinfo').css('display', 'none');
		$('#goodsreview').css('display', 'none');
		$('#goodsqna').css('display', 'block');
	});
	$("#goodsordernewbtn").click(function() {
		alert("1");
		$('#goodsordernewbtn').attr('class', 'item active');
		$('#goodsorderdepositbtn').attr('class', 'item');
		$('#goodsorderpayokbtn').attr('class', 'item');
		$('#goodsorderdeliveryreadybtn').attr('class', 'item');
		$('#goodsordershippingbtn').attr('class', 'item');
		$('#goodsorderdeliverycompletebtn').attr('class', 'item');
	});

	$("#goodsorderdepositbtn").click(function() {
		alert("2");
		$('#goodsordernewbtn').attr('class', 'item');
		$('#goodsorderdepositbtn').attr('class', 'item active');
		$('#goodsorderpayokbtn').attr('class', 'item');
		$('#goodsorderdeliveryreadybtn').attr('class', 'item');
		$('#goodsordershippingbtn').attr('class', 'item');
		$('#goodsorderdeliverycompletebtn').attr('class', 'item');
	});

	$("#goodsorderpayokbtn").click(function() {
		alert("3");
		$('#goodsordernewbtn').attr('class', 'item');
		$('#goodsorderdepositbtn').attr('class', 'item');
		$('#goodsorderpayokbtn').attr('class', 'item active');
		$('#goodsorderdeliveryreadybtn').attr('class', 'item');
		$('#goodsordershippingbtn').attr('class', 'item');
		$('#goodsorderdeliverycompletebtn').attr('class', 'item');
	});

	$("#goodsorderdeliveryreadybtn").click(function() {
		alert("4");
		$('#goodsordernewbtn').attr('class', 'item');
		$('#goodsorderdepositbtn').attr('class', 'item');
		$('#goodsorderpayokbtn').attr('class', 'item');
		$('#goodsorderdeliveryreadybtn').attr('class', 'item active');
		$('#goodsordershippingbtn').attr('class', 'item');
		$('#goodsorderdeliverycompletebtn').attr('class', 'item');
	});

	$("#goodsordershippingbtn").click(function() {
		alert("5");
		$('#goodsordernewbtn').attr('class', 'item');
		$('#goodsorderdepositbtn').attr('class', 'item');
		$('#goodsorderpayokbtn').attr('class', 'item');
		$('#goodsorderdeliveryreadybtn').attr('class', 'item');
		$('#goodsordershippingbtn').attr('class', 'item active');
		$('#goodsorderdeliverycompletebtn').attr('class', 'item');
	});

	$("#goodsorderdeliverycompletebtn").click(function() {
		alert("6");
		$('#goodsordernewbtn').attr('class', 'item');
		$('#goodsorderdepositbtn').attr('class', 'item');
		$('#goodsorderpayokbtn').attr('class', 'item');
		$('#goodsorderdeliveryreadybtn').attr('class', 'item');
		$('#goodsordershippingbtn').attr('class', 'item');
		$('#goodsorderdeliverycompletebtn').attr('class', 'item active');
	});

	$(document).off().on('click', '#myCartTable td #productDeletebtn', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var g_seq = td.eq(9).children().val();
		$('.ui.modal.prodelete').modal('show');
		$('#deleteok').on('click', function() {
			$.ajax({
				type: 'POST',
				data: { g_seq: g_seq },
				datatype: 'json',
				url: 'ProductDeleteAjax',
				success: function(data) {
					if (data == "y") {
						row.remove();
						$('#resultmessage').text("삭제 되었습니다.");
						location.href = 'goodsCartGo'
					} else {
						$('#resultmessage').text("삭제 되지 않았습니다.");
					}

					$('#successmessage').css('display', "block").delay(1200).queue(function() {
						$('#successmessage').css('display', "none").dequeue();
					});
					$('.ui.modal.prodelete').modal('hide');
				},
				error: function(xhr, status, error) {
					alert('ajax error' + xhr.status);
				}
			});

		});
		$('#deletecancel').on('click', function() {
			$('.ui.modal.delete').modal('hide');
		});
	});



	$("#myCartTable").on('change', '#qtyInputProdetail', 'td', function() {
		var row = $(this).closest('tr');
		var td = row.children();
		var input = td.eq(0).children().children();
		var qty = td.eq(3).children().val();
		var g_seq = td.eq(9).children().val();
		$.ajax({
			type: 'POST',
			data: { g_seq: g_seq, qty: qty },
			datatype: 'json',
			url: 'cartProductQtyAjax',
			success: function(data) {
				if (data == "login") {
					location.href = 'shoppingLogin'
				} else {

				}
			},
			error: function(xhr, status, error) {
				alert('ajax error' + xhr.status);
			}
		});
	});
	
	
	$('.attachbtn').off().on('click', function() {
		$('#g_attach').click();
		$('#g_attach').off().change(function() {
			var filename = $('#g_attach').val();
			$('.g_attachname').attr('value', filename);
			var formData = new FormData($('#uploadForm')[0]);
			formData.append("file",$("input[type='file']")[0].files[0]);
			$.ajax({
				type: 'POST',
				data: formData,
				enctype: 'multipart/form-data',
				processData: false,
				contentType: false,
				url: 'goodsWriteAttach',
				success: function(data) {
				},
				error: function(xhr, status, error) {
					alert('ajax error' + xhr.status);
				}
			});
		});
	});

	/*var objDragAndDrop = $(".dragAndDropDiv");

	$(document).on("dragenter", ".dragAndDropDiv", function(e) {
		e.stopPropagation();
		e.preventDefault();
		$(this).css('border', '2px solid #0B85A1');
	});
	
	$(document).on("dragover", ".dragAndDropDiv", function(e) {
		e.stopPropagation();
		e.preventDefault();
	});
	
	$(document).on("drop", ".dragAndDropDiv", function(e) {

		$(this).css('border', '2px dotted #0B85A1');
		e.preventDefault();
		var files = e.originalEvent.dataTransfer.files;

		handleFileUpload(files, objDragAndDrop);
	});

	$(document).on('dragenter', function(e) {
		e.stopPropagation();
		e.preventDefault();
	});
	
	$(document).on('dragover', function(e) {
		e.stopPropagation();
		e.preventDefault();
		objDragAndDrop.css('border', '2px dotted #0B85A1');
	});
	
	$(document).on('drop', function(e) {
		e.stopPropagation();
		e.preventDefault();
	});
	
	
	
	objDragAndDrop.off().on('click', function(e) {
		$('input[type=file]').trigger('click');
	});
	
	$('input[type=file]').off().on('change', function(e) {
		var files = e.originalEvent.target.files;
		handleFileUpload(files, objDragAndDrop);	
		alert($('#fileUpload').val())
		
	});
	
	function handleFileUpload(files, obj) {
		for (var i = 0; i < files.length; i++) {
			var fd = new FormData();
			fd.append('file', files[i]);
			var status = new createStatusbar(obj); //Using this we can set progress.
			status.setFileNameSize(files[i].name, files[i].size);
			sendFileToServer(fd, status);

		}
	}

	var rowCount = 0;
	function createStatusbar(obj) {

		rowCount++;
		var row =  "odd";
		if (rowCount % 2 == 0) row = "even";
		this.statusbar = $("<div class='statusbar " + row + "'></div>");
		this.filename = $("<div class='filename'></div>").appendTo(this.statusbar);
		this.size = $("<div class='filesize'></div>").appendTo(this.statusbar);
		this.progressBar = $("<div class='progressBar'><div></div></div>").appendTo(this.statusbar);
		this.abort = $("<div class='abort'>중지</div>").appendTo(this.statusbar);
		obj.after(this.statusbar);
	
		this.setFileNameSize = function(name, size) {
			var sizeStr = "";
			var sizeKB = size / 1024;
			if (parseInt(sizeKB) > 1024) {
				var sizeMB = sizeKB / 1024;
				sizeStr = sizeMB.toFixed(2) + " MB";
			} else {
				sizeStr = sizeKB.toFixed(2) + " KB";
			}

			this.filename.html(name);
			this.size.html(sizeStr);
		}

		this.setProgress = function(progress) {
			var progressBarWidth = progress * this.progressBar.width() / 100;
			this.progressBar.find('div').animate({ width: progressBarWidth }, 10).html(progress + "% ");
			if (parseInt(progress) >= 100) {
				this.abort.hide();
			}
		}

		this.setAbort = function(jqxhr) {
			var sb = this.statusbar;
			this.abort.click(function() {
				jqxhr.abort();
				sb.hide();
			});
		}
	}

	function sendFileToServer(formData, status) {
		var uploadURL = "/fileUpload/post"; //Upload URL
		var extraData = {}; //Extra Data.
		var jqXHR = $.ajax({
			xhr: function() {
				var xhrobj = $.ajaxSettings.xhr();
				if (xhrobj.upload) {
					xhrobj.upload.addEventListener('progress', function(event) {
						var percent = 0;
						var position = event.loaded || event.position;
						var total = event.total;
						if (event.lengthComputable) {
							percent = Math.ceil(position / total * 100);
						}
						//Set progress
						status.setProgress(percent);
					}, false);
				}
				return xhrobj;
			},
			url: uploadURL,
			type: "POST",
			contentType: false,
			processData: false,
			cache: false,
			data: formData,
			success: function(data) {
				status.setProgress(100);

				//$("#status1").append("File upload Done<br>");           
			}
		});

		status.setAbort(jqXHR);
	}*/


});
