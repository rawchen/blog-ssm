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
}
