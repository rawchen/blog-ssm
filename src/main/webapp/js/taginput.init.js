$(function () {
	/**
	 * 初始化标签输入框
	 */
	$('#tagInput').selectize({
		plugins: ['remove_button'],
		create: true,
		createOnBlur: true,
		maxItems: 10
	});

	$('#tagInput2').selectize({
		create: true,
		createOnBlur: true,
		maxItems: 10
	});

	$('#categoryId').select2({
		minimumResultsForSearch: -1
	});

	/**
	 * 新增标签按钮事件
	 */
	$('#addTagBtn').on('click', function () {
		alert($('#tagInput').val().split(','));
	});
});