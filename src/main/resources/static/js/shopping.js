
function test01() {
	document.getElementById("name2").value = $("#name1").val();
	document.getElementById("phone2").value = $("#phone1").val();
};
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
		if ($(input).is(":checked") == true) {
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

	$('#shoppingPaiging').on('click', function() {
		var g_seq = $('#hiddeng_seq').val()
		var qty = $('#qtyInputProdetail').val();
		var price = $('#priceHiddenDetail').val();
		var result = String(price * qty);
		var commaresult = result.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		var delivery = $('#g_delivery').val();
		var commafinalresult = String(price * qty + Number(delivery));
		var finalResult = commafinalresult.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		$('#final_price').text(commaresult + '원');
		$('#total_money').text(finalResult + '원');
		$('#countupdown').text("총결제금액(" + qty + ")개");
		document.location.href = "goodsDelivery?g_seq=" + g_seq + "&qty=" + qty;
	});

	$('#completebtn').on('click', function() {
		$("#Complete").attr("action", "paymentComplete");
		$("#Complete").submit();

	});

});