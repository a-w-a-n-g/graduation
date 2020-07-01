package com.graduation.spring.bizImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.graduation.hibernate.entity.ClassNum;
import com.graduation.hibernate.entity.Department;
import com.graduation.hibernate.entity.Major;
import com.graduation.hibernate.entity.Student;
import com.graduation.hibernate.entity.Topic;
import com.graduation.spring.biz.ClassNumBiz;
import com.graduation.spring.biz.DepartmentBiz;
import com.graduation.spring.biz.MajorBiz;
import com.graduation.spring.biz.StudentBiz;
import com.graduation.spring.biz.TopicBiz;
import com.graduation.spring.dao.HandleEntityDAO;
import fontUtils.ChineseAndUnicode;

/**

* @author awang
* @version 1.0
* @date 2020年2月19日 下午6:55:05
* 
*/

@Service("studentBiz")
public class StudentBizImpl implements StudentBiz{

	@Resource(name="handleEntityDAO")
	private HandleEntityDAO handle;
	
	
	public StudentBizImpl() {
		
	}
	
	@PostConstruct
	public void init() {
		handle.setType("Student");
	}
	
	@Resource
	private ClassNumBiz classNumBiz;
	@Resource
	private MajorBiz majorBiz;
	@Resource
	private DepartmentBiz departmentBiz;
	@Resource
	private TopicBiz topicBiz;
	
	@Override
	public Student loginValidate(String name, String password) {
		if(!Pattern.matches("^[0-9]*$", name)) {
			return null;
		}
		Student login = new Student();
		login.setId(Long.parseLong(name));
		login.setPassword(password);
		List<Student> list = handle.setData(login).setBaseQuery().getResultList();
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public boolean updatePassword(String id, String psw) {
		Student data = new Student();
		data.setId(Long.parseLong(id));
		data = (Student)handle.setData(data).setBaseQuery().getResultList().get(0);
		data.setPassword(psw);
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public Student getStudentById(Long id) {
		Student data = new Student();
		data.setId(id);
		return (Student)handle.setData(data).setBaseQuery().getResultList().get(0);
	}
	
	@Override
	public ArrayList<Student> getAllStu() {
		return (ArrayList<Student>)handle.setData(new Student()).setBaseQuery().getResultList();
	}
	
	@Override
	public boolean updateStudent(Student student) {
		Student data = new Student();
		data.setId(student.getId());
		List resultList = handle.setData(data).setBaseQuery().getResultList();
		if(resultList.size()==0) {
			student.setPassword("123456");
			return classNumBiz.addStudent(student, student.getClassNum().getName());
		}
		data = (Student)resultList.get(0);
		if(data.getName() != student.getName()) {
			data.setName(student.getName());
		}
		if(data.getSex() != student.getSex()) {
			data.setSex(student.getSex());
		}
		if(data.getPhone() != student.getPhone()) {
			data.setPhone(student.getPhone());
		}
		if(data.getMail() != student.getMail()) {
			data.setMail(student.getMail());
		}
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public boolean deleteStudent(Long id) {
		Student data = new Student();
		data.setId(id);
		data = (Student)handle.setData(data).setBaseQuery().getResultList().get(0);
		return handle.setData(data).deleteEntity();
	}
	
	@Override
	public ArrayList<Student> likeSearch(String selection) {
		ArrayList<Student> returnList = new ArrayList<Student>();
		ArrayList<Student> subList;
		subList = (ArrayList<Student>)handle.setData(new Student()).setBaseQuery().setLikeQuery("id", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
		subList = (ArrayList<Student>)handle.setBaseQuery().setLikeQuery("name", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
		subList = (ArrayList<Student>)handle.setBaseQuery().setLikeQuery("phone", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
		subList = (ArrayList<Student>)handle.setBaseQuery().setLikeQuery("mail", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
//		ArrayList<ClassNum> classList = classNumBiz.likeSearch(ChineseAndUnicode.chinaToUnicode(selection));
//		if(classList.size()>0) {
//			for(ClassNum classNum:classList) {
//				Iterator itor = classNum.getStudents().iterator();
//				while(itor.hasNext()) {
//					returnList.add((Student)itor.next());
//				}
//			}
//		}
//		ArrayList<Major> majorList = majorBiz.likeSearch(ChineseAndUnicode.chinaToUnicode(selection));
//		if(majorList.size()>0) {
//			for(Major major:majorList) {
//				Iterator itor = major.getClasses().iterator();
//				while(itor.hasNext()) {
//					ClassNum classNum = (ClassNum)itor.next();
//					Iterator itor2 = classNum.getStudents().iterator();
//					while(itor2.hasNext()) {
//						returnList.add((Student)itor2.next());
//					}
//				}
//			}
//		}
//		ArrayList<Department> departmentList = departmentBiz.likeSearch(ChineseAndUnicode.chinaToUnicode(selection));
//		if(departmentList.size()>0) {
//			for(Department department:departmentList) {
//				Iterator itor = department.getMajors().iterator();
//				while(itor.hasNext()) {
//					Major major = (Major)itor.next();
//					Iterator itor2 = major.getClasses().iterator();
//					while(itor2.hasNext()) {
//						ClassNum classNum = (ClassNum)itor.next();
//						Iterator itor3 = classNum.getStudents().iterator();
//						while(itor3.hasNext()) {
//							returnList.add((Student)itor3.next());
//						}
//					}
//				}
//			}
//		}
		return returnList;
	}
	
	@Override
	public boolean selectTopic(Long stuId, Integer topicId) {
		Student stu = this.getStudentById(stuId);
		Topic topic = topicBiz.getTopicById(topicId);
		topic.setIfSelect(true);
		boolean b = topicBiz.saveTopic(topic);
		topic.setStudent(stu);
		stu.setTopic(topic);
		return b && handle.setData(stu).updateEntity();
	}
	
}
