package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value="/list/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Blog list(@PathVariable Integer id){
        Blog blog = blogService.findById(id);
        return blog;
    }

    @RequestMapping(value="/view", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("paginate", "/WEB-INF/jsp/paginate.jsp");
        model.addObject("container","/WEB-INF/jsp/container.jsp");
        return model;
    }
}
