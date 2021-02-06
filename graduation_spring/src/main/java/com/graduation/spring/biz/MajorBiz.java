package com.graduation.spring.biz;

import java.util.ArrayList;

import com.graduation.hibernate.entity.ClassNum;
import com.graduation.hibernate.entity.Major;

/**

* @author awang
* @version 1.0
* @date 2020年4月21日 上午2:35:56
* 
*/

public interface MajorBiz {

	Major search(Major major);
	
	ArrayList<Major> likeSearch(String selection);
	
	ArrayList<Major> getAllMajor();
	
	boolean updateMajor(Major major);
	
	boolean deleteMajor(Integer id);
	
	boolean addClassNum(ClassNum classNum, String majorName);
	
}
