package com.rawchen.service.impl;

import com.rawchen.domain.Category;
import com.rawchen.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {
	@Override
	public List<Category> selectAllCategory() {
		return categoryMapper.selectAllCategory();
	}

	@Override
	public Integer selectCategoryBySlug(String blogCategory) {
		return categoryMapper.selectCategoryBySlug(blogCategory);
	}

	@Override
	public Category selectByPrimaryKey(Integer cgid) {
		return categoryMapper.selectByPrimaryKey(cgid);
	}

	@Override
	public int selectCountOfCategory() {
		int num = categoryMapper.selectCountOfCategory();
		if (num!=0) {
			return num;
		}else {
			return 0;
		}
	}

	@Override
	public String selectCategoryNameById(Integer cgid) {
		return categoryMapper.selectCategoryNameById(cgid);
	}

	@Override
	public int updateCategory(Category category) {
		return categoryMapper.updateByPrimaryKey(category);
	}

	@Override
	public int deleteCategory(int parseInt) {
		return categoryMapper.deleteByPrimaryKey(parseInt);
	}

	@Override
	public int insertCategory(Category category) {
		return categoryMapper.insert(category);
	}
}
