$(function () {
	var headerEl = 'h1, h2, h3, h4, h5, h6',idArr = {};
	$('#blog-content').children(headerEl).each(function () {
		var headerId = $(this).text().replace(/[\s|\~|`|\!|\@|\#|\$|\%|\^|\&|\*|\(|\)|\_|\+|\=|\||\|\[|\]|\{|\}|\;|\:|\"|\'|\,|\<|\.|\>|\/|\?|\：|\，|\。]/g, '');

		headerId = headerId.toLowerCase();
		if (idArr[headerId]) {
			$(this).attr('id', headerId + '-' + idArr[headerId]);
			idArr[headerId]++;
		}
		else {
			idArr[headerId] = 1;
			$(this).attr('id', headerId);
		}
	});
	tocbot.init({
		tocSelector: '#toc',
		contentSelector: '#blog-content',
		headingSelector: 'h1, h2, h3, h4, h5, h6',
		hasInnerContainers: true,
		collapseDepth: 2
	});
});
