package com.ljc.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ljc.entity.News;

public interface NewsDao {

	public List<News> getNewsList();

	public News getNewsByID(@Param("nid")Integer nid);

	public int addNews(@Param("title") String title,
			@Param("content") String content,
			@Param("date") Timestamp timestamp,
			@Param("aid") Integer aid,
			@Param("lable") String lable);

	public List<News> getNewsByAID(@Param("aid")Integer aid);

	public int deleteNewsByID(@Param("nid")Integer nid);

	public List<News> getNewsPageList(@Param("currentPage") int currentPage,@Param("pageSize") int pageSize);

	public List<News> searchNewsByKey(@Param("key")String key);
	
	public List<News> getNewsListByStatus(List<Integer> statusList);
}
