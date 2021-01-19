package com.yoyling.service;

import com.yoyling.domain.Category;

import java.util.List;

public interface CategoryService {

	List<Category> selectAllCategory();

	Integer selectCategoryBySlug(String blogCategory);

	Category selectByPrimaryKey(Integer cgid);

	int selectCountOfCategory();

	String selectCategoryNameById(Integer cgid);
}
