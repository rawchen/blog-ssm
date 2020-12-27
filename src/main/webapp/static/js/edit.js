$(function () {
  /**
   * Initialize Select2
   */
  $('.select2').select2();

  /**
   * Initialize SweetAlert2
   */
  const Toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: true,
    timer: 3000
  });

  /**
   * Initialize editor.md
   */
  var blogEditor = editormd("editormd", {
    width: "100%",
    height: 580,
    htmlDecode: true,
    // taskList: true,
    toolbarIcons: function () {
      // Using "||" set icons align right.
      return [
        "undo", "redo", "|",
        "bold", "del", "italic", "quote", "ucwords", "uppercase", "lowercase", "|",
        "h1", "h2", "h3", "h4", "h5", "h6", "|",
        "list-ul", "list-ol", "hr", "|",
        "link", "reference-link", "image", "code", "preformatted-text", "code-block", "table", "datetime", "html-entities", "pagebreak", "|",
        "goto-line", "watch", "preview", "clear", "search", "|",
        "help", "info"
      ];
    },
    path: './plugins/editor.md/lib/',
    disabledKeyMaps: [
      "F11"  // disable some default keyboard shortcuts handle
    ],
    onload: function () {
      var keyMap = {
        "Ctrl-S": function (cm) {
          $('#isPublish').val(false);
          if (validateBlogForm()) {
            $.ajax({
              url: '',
              type: 'POST',
              data: $('#blogForm').serialize(),
              dataType: 'json',
              success: function (data) {

              },
              error: function (error) {

              }
            });
          }
        },
      };
      this.addKeyMap(keyMap);
    }
  });

  /**
   * 判断字符串是否为空
   * @param str
   * @returns {boolean}
   */
  function isNull(str) {
    return str === null || str === undefined || str.trim() === '';
  }

  /**
   * 校验博客表单，标题、分类、描述、内容不能为空
   * @returns {boolean}
   */
  function validateBlogForm() {
    var blogTitle = $('#title').val();
    var blogCategoryId = $('#categoryId').val();
    var blogDescription = $('#description').val();
    var blogContent = blogEditor.getMarkdown();

    if (isNull(blogTitle)) {
      Toast.fire({
        icon: 'error',
        title: '博客标题不能为空！'
      });
      return false;
    }

    if (isNull(blogCategoryId)) {
      Toast.fire({
        icon: 'error',
        title: '请选择博客分类！'
      });
      return false;
    }

    if (isNull(blogDescription)) {
      Toast.fire({
        icon: 'error',
        title: '博客描述不能为空！'
      });
      return false;
    }

    if (isNull(blogContent)) {
      Toast.fire({
        icon: 'error',
        title: '博客内容不能为空！'
      });
      return false;
    }

    return true;
  }

  /**
   * 发布按钮事件
   */
  $('#publishBtn').on('click', function () {
    if (validateBlogForm()) {
      $('#isPublish').val(true);
      $('#blogForm').submit();
    }
    return false;
  });

  /**
   * 保存按钮事件
   */
  $('#saveBtn').on('click', function () {
    if (validateBlogForm()) {
      $('#isPublish').val(false);
      $('#blogForm').submit();
    }
    return false;
  });
});