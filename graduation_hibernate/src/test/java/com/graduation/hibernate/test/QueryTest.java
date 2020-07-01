package com.graduation.hibernate.test;

import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.graduation.hibernate.entity.Administrator;
import com.graduation.hibernate.entity.ClassNum;
import com.graduation.hibernate.entity.Department;
import com.graduation.hibernate.entity.LoginLog;
import com.graduation.hibernate.entity.Major;
import com.graduation.hibernate.entity.ModifyTopic;
import com.graduation.hibernate.entity.Notice;
import com.graduation.hibernate.entity.Student;
import com.graduation.hibernate.entity.Teacher;
import com.graduation.hibernate.entity.Topic;
import com.graduation.hibernate.session.HibernateSessionFactory;

/**

* @author awang
* @version 1.0
* @date 2020年2月10日 下午11:43:23
* 
*/

public class QueryTest {

	//当前项目排除了hibernate配置文件，当要测试时需在pom中设置
	public static void main(String[] args) {
		
		Session session = HibernateSessionFactory.getSession();
		
		Administrator admin = (Administrator)session.get(Administrator.class, 1);
		
		ClassNum classes = (ClassNum)session.get(ClassNum.class, 1);
		
		Department department = (Department)session.get(Department.class, 3);
		
		LoginLog log = (LoginLog)session.get(LoginLog.class, 1);
		
		Major major = (Major)session.get(Major.class, 2);
		
		ModifyTopic modify = (ModifyTopic)session.get(ModifyTopic.class, 1);
		
		Notice notice = (Notice)session.get(Notice.class, 1);
		
		Student condition = new Student();
		condition.setName("阿汪");
		Student student = (Student)session.createCriteria(Student.class).add(Example.create(condition)).list().get(0);
		
		Teacher condition2 = new Teacher();
		condition2.setName("wangjian");
		Teacher teacher = (Teacher)session.createCriteria(Teacher.class).add(Example.create(condition2)).list().get(0);
		
		Topic topic = (Topic)session.get(Topic.class, 1);
		
		System.out.println(admin.getName() + ":" + admin.getPassword());
		System.out.println(department.getName() + "," + department.getMajors().iterator().next().getName());
		System.out.println(classes.getName() + "," + classes.getMajor().getName() + "," + classes.getStudents().iterator().next().getName());
		System.out.println(major.getName() + "," + major.getDepartment().getName() + "," + major.getClasses().iterator().next().getName());
		System.out.println(student.getName() + "," + student.getClassNum().getName() + "," + student.getTopic().getTitle());
		System.out.println(teacher.getName() + "," + teacher.getTopics().iterator().next().getTitle());
		System.out.println(topic.getTitle() + "," + topic.getStudent().getName() + "," + topic.getTeacher().getName());
		System.out.println(modify.getTitle() + "," + modify.getModifyTime());
		
		session.close();
	}
	
}
