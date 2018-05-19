package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.entity.Page;
import com.blog.service.BlogService;
import com.blog.utils.StringUtil;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(String page){
        ModelAndView model = new ModelAndView();
        if(StringUtil.isEmpty(page)){
            page = "1";
        }
        Page pageBean = new Page(Integer.parseInt(page),10);
        Map<String, Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Blog> blogs = blogService.listAll(map);
        model.addObject("container","/WEB-INF/jsp/container.jsp");
        model.addObject("blogList", blogs);
        model.setViewName("index");
        return model;
    }
}
