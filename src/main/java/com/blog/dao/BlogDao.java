package com.blog.dao;

import com.blog.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogDao {

    public List<Blog> countList();

    public List<Blog> list(Map<String, Object> param);

    public List<Blog> listAll(Map<String, Object> param);

    public Long getTotal(Map<String, Object> param);

    public Blog findById(Integer id);

    public Integer update(Blog blog);

    public Blog getLastBlog(Integer id);

    public Blog getNextBLog(Integer id);

    public Integer add(Blog blog);

    public Integer delete(Integer id);

    public Integer getBlogByTypeId(Integer typeId);
}
