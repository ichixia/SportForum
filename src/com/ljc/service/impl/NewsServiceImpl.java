package com.ljc.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljc.dao.NewsDao;
import com.ljc.entity.News;
import com.ljc.entity.PageBean;
import com.ljc.service.NewsService;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsDao newsDao;

	@Override
	public List<News> getNewsList() {
		return newsDao.getNewsList();
	}

	@Override
	public News getNewsByID(Integer nid) {
		return newsDao.getNewsByID(nid);
	}

	@Override
	public int addNews(String title, String content, Timestamp timestamp,
			Integer aid, String lable) {
		return newsDao.addNews(title, content, timestamp, aid, lable);
	}

	@Override
	public List<News> getNewsByAID(Integer aid) {
		return newsDao.getNewsByAID(aid);
	}

	@Override
	public int deleteNewsByID(Integer nid) {
		return newsDao.deleteNewsByID(nid);
	}

	@Override
	public PageBean getNewsPageList(int currentPage, int pageSize) {
		int count = newsDao.getNewsList().size();
		int totalPage = (int) Math.ceil(count * 1.0 / pageSize);// 总页数
		List<News> newsList = newsDao.getNewsPageList(
				(currentPage - 1) * pageSize, pageSize);
		PageBean pageBean = new PageBean(currentPage, pageSize, count,
				totalPage, newsList);
		return pageBean;
	}

	@Override
	public List<News> searchNewsByKey(String key) {
		return newsDao.searchNewsByKey(key);
	}

	@Override
	public List<News> getNewsListByStatus(List<Integer> statusList) {
		return newsDao.getNewsListByStatus(statusList);
	}

}
