$(function () {
  /**
   * 目录生成
   */
  tocbot.init({
    // Where to render the table of contents.
    tocSelector: '#toc',
    // Where to grab the headings to build the table of contents.
    contentSelector: '#blog-content',
    // Which headings to grab inside of the contentSelector element.
    headingSelector: 'h1, h2, h3',
    // For headings inside relative or absolute positioned containers within content.
    hasInnerContainers: true,
    // How many heading levels should not be collapsed.
    // For example, number 6 will show everything since
    // there are only 6 heading levels and number 0 will collapse them all.
    // The sections that are hidden will open
    // and close as you scroll to headings within them.
    collapseDepth: 6,
  });
});
