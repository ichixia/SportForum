package com.ljc.service;

import java.sql.Timestamp;
import java.util.List;

import com.ljc.entity.Article;
import com.ljc.entity.News;
import com.ljc.entity.PageBean;

public interface NewsService {
	
	/**
	 * 获取公告所有数据
	 * @return
	 */
	public List<News> getNewsList();

	/**
	 * 根据id获取公告数据
	 * @param aid 帖子id
	 * @return
	 */
	public News getNewsByID(Integer nid);

	/**
	 * 发表新公告
	 * @param title
	 * @param content
	 * @param timestamp
	 * @param aid //管理员id
	 * @param lable 
	 * @return
	 */
	public int addNews(String title, String content,Timestamp timestamp,Integer aid, String lable);

	/**
	 * 根据aid获得管理员的公告数据
	 * @param aid
	 * @return
	 */
	public List<News> getNewsByAID(Integer aid);

	/**
	 * 根据id删除公告数据
	 * @param nid
	 * @return
	 */
	public int deleteNewsByID(Integer nid);

	/**
	 * 分页查询公告数据
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageBean getNewsPageList(int currentPage, int pageSize);

	/**
	 * 关键字搜索
	 * @param key
	 * @return
	 */
	public List<News> searchNewsByKey(String key);
	
	/**
	 * 通过staus关键字查询帖子
	 * @param statusList
	 * @return
	 */
	public List<News> getNewsListByStatus(List<Integer> statusList);

}
