$(document).ready(function() {

	$('#boardDeleteBtn').on('click', function() {
		$('.ui.boardDeleteAjax.modal').modal('show');
	});
	
	$('#boardDeleteOkBtn').on('click', function() {
		$('.ui.boardDeleteAjax.modal').modal('hide');
		var b_seq = $('#hidden_seq').val();
		$.ajax({
				type: 'POST',
				data: { b_seq: b_seq },
				datatype: 'json',
				url: 'boardDeleteAjax',
				contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
				success: function(data) {
					location.href='boardPageList';
				},
				error: function(xhr, status, error) {
					alert('ajax error' + xhr.status);
				}
			});
	})

});