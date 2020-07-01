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

	public Major search(Major major);
	
	public ArrayList<Major> likeSearch(String selection);
	
	public ArrayList<Major> getAllMajor();
	
	public boolean updateMajor(Major major);
	
	public boolean deleteMajor(Integer id);
	
	public boolean addClassNum(ClassNum classNum, String majorName);
	
}
