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

	HandleEntityDAO setType(String entityClassName);
	
	HandleEntityDAO setData(Object entity);
	
	HandleEntityDAO setBaseQuery();

	HandleEntityDAO setSelectProperties(String... selectProperties);
	
	HandleEntityDAO setNullQuery(String property, boolean ifNull);
	
	HandleEntityDAO setLikeQuery(String property, String pattern);
	
	HandleEntityDAO setOrder(String property, String order);
	
	HandleEntityDAO setTimeQuery(String timeProperty, String beginTime, String endTime);
	
	HandleEntityDAO setResultPage(int beginIndex, int num);
	
	boolean insertEntity();
	
	boolean updateEntity();
	
	boolean deleteEntity();
	
	List getResultList();
	
}
