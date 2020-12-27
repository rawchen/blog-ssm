$(function () {
  /**
   * 初始化分类输入框
   */
  $('#categoryInput').selectize({
    create: true,
    createOnBlur: true,
    maxItems: 10
  });

  /**
   * 新增分类按钮事件
   */
  $('#addCategoryBtn').on('click', function () {
    alert($('#categoryInput').val().split(','));
  });
});