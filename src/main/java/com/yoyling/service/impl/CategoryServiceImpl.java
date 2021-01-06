package com.yoyling.service.impl;

import com.yoyling.domain.Category;
import com.yoyling.service.CategoryService;
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
}
