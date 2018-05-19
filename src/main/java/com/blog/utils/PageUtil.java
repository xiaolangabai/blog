package com.blog.utils;

public class PageUtil {

    public static String getPagination(String targetUrl,long totalNum,int currentPage,int pageSize,String param){
        long totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        if(totalPage == 0){
            return "";
        }else {
            StringBuilder pageInfo = new StringBuilder();
            pageInfo.append();
        }
    }
}
