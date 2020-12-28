$(function () {
  /**
   * 初始化标签输入框
   */
  $('#tagInput').selectize({
    create: true,
    createOnBlur: true,
    maxItems: 10
  });

  /**
   * 新增标签按钮事件
   */
  $('#addTagBtn').on('click', function () {
    alert($('#tagInput').val().split(','));
  });
});