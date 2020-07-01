package com.graduation.spring.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

/**

* @author awang
* @version 1.0
* @date 2020年2月12日 下午9:27:07
* 
*/

public interface HandleEntityDAO {

	public HandleEntityDAO setType(String entityClassName);
	
	public HandleEntityDAO setData(Object entity);
	
	public HandleEntityDAO setBaseQuery();

	public HandleEntityDAO setSelectProperties(String... selectProperties);
	
	public HandleEntityDAO setNullQuery(String property, boolean ifNull);
	
	public HandleEntityDAO setLikeQuery(String property, String pattern);
	
	public HandleEntityDAO setOrder(String property, String order);
	
	public HandleEntityDAO setTimeQuery(String timeProperty, String beginTime, String endTime);
	
	public HandleEntityDAO setResultPage(int beginIndex, int num);
	
	public boolean insertEntity();
	
	public boolean updateEntity();
	
	public boolean deleteEntity();
	
	public List getResultList();
	
}
