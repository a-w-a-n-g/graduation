package com.graduation.spring.daoImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.graduation.hibernate.entity.Administrator;
import com.graduation.hibernate.entity.Bernal;
import com.graduation.hibernate.entity.ClassNum;
import com.graduation.hibernate.entity.Department;
import com.graduation.hibernate.entity.Description;
import com.graduation.hibernate.entity.GuidanceRecord;
import com.graduation.hibernate.entity.InterimReport;
import com.graduation.hibernate.entity.LoginLog;
import com.graduation.hibernate.entity.Major;
import com.graduation.hibernate.entity.ModifyTopic;
import com.graduation.hibernate.entity.Notice;
import com.graduation.hibernate.entity.Student;
import com.graduation.hibernate.entity.Teacher;
import com.graduation.hibernate.entity.ThesisPaper;
import com.graduation.hibernate.entity.Topic;
import com.graduation.spring.dao.HandleEntityDAO;

/**

* 对数据库关联实体的查询、添加、更新、删除进行操作
* @author awang
* @version 1.0
* @date 2020年2月12日 下午9:35:09
* 
*/

@Repository("handleEntityDAO")
@Scope("prototype")    	//此处不配置默认为singleton，只会生成一个给全局使用，这样对不同操作对象来说肯定不行
public class HandleEntityDAOImpl implements HandleEntityDAO {
	
	private static Log logger = LogFactory.getLog(HandleEntityDAOImpl.class);
	
	private Object entity;				//用于存储更新数据以及查询数据
	private String entityClassName;		//用于存储操作数据类型名
	private Class entityClass;			//用于存储操作实体类
	private Session session;			//会话对象
	private Transaction tx;				//事务对象
	private SessionFactory sessionFactory;		//Session建造器
	private CriteriaBuilder criteriaBuilder;	//查询接口建造器
	private CriteriaQuery criteriaQuery;		//查询接口
	private Root root;					//根对象，用于获取数据库表的属性
	private List<Predicate> predicates = new ArrayList<Predicate>();		//存储查询条件
	private int beginIndex;
	private int num;
	private boolean ifPage = false;
	
	public HandleEntityDAOImpl() {
		
	}

	public HandleEntityDAOImpl(String entityClassName) {
		this.entityClassName = entityClassName;
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 设置实体类名及数据实体
	 * @param(entityClassName)  所要操作的实体类名
	 */
	@Override
	public HandleEntityDAO setType(String entityClassName) {
		this.entityClassName = entityClassName;
		return this;
	}
	
	/**
	 * 设置数据实体，以进行数据查询、更新操作
	 */
	@Override
	public HandleEntityDAO setData(Object entity) {
		if(entityClassName == "" || entityClassName.equals("")){
			logger.warn("SetData方法：类属性entityClassName未设置");
		}else{
			switch(entityClassName) {
			case "Administrator":
				this.entity = (Administrator)entity;
				this.entityClass = Administrator.class;
				break;
			case "ClassNum":
				this.entity = (ClassNum)entity;
				this.entityClass = ClassNum.class;
				break;			
			case "Department":
				this.entity = (Department)entity;
				this.entityClass = Department.class;
				break;
			case "LoginLog":
				this.entity = (LoginLog)entity;
				this.entityClass = LoginLog.class;
				break;
			case "Major":
				this.entity = (Major)entity;
				this.entityClass = Major.class;
				break;
			case "ModifyTopic":
				this.entity = (ModifyTopic)entity;
				this.entityClass = ModifyTopic.class;
				break;
			case "Notice":
				this.entity = (Notice)entity;
				this.entityClass = Notice.class;
				break;
			case "Student":
				this.entity = (Student)entity;
				this.entityClass = Student.class;
				break;
			case "Teacher":
				this.entity = (Teacher)entity;
				this.entityClass = Teacher.class;
				break;
			case "Topic":
				this.entity = (Topic)entity;
				this.entityClass = Topic.class;
				break;
			case "Description":
				this.entity = (Description)entity;
				this.entityClass = Description.class;
				break;
			case "Bernal":
				this.entity = (Bernal)entity;
				this.entityClass = Bernal.class;
				break;
			case "InterimReport":
				this.entity = (InterimReport)entity;
				this.entityClass = InterimReport.class;
				break;
			case "GuidanceRecord":
				this.entity = (GuidanceRecord)entity;
				this.entityClass = GuidanceRecord.class;
				break;
			case "ThesisPaper":
				this.entity = (ThesisPaper)entity;
				this.entityClass = ThesisPaper.class;
				break;
			default:
				logger.warn("setData方法：未找到与类属性entityClassName匹配的实体类");
			}
		}
		return this;
	}
	
	/**
	 * 通过实体条件对象设置查询条件，配置其它条件时需先调用该方法
	 */
	@Override
	public HandleEntityDAO setBaseQuery() {
		criteriaQuery = criteriaBuilder.createQuery(entityClass);
		root = criteriaQuery.from(entityClass);
		if(entity == null){
			
		}else{
			switch(entityClassName) {
			case "Administrator":
				Administrator condition1 = (Administrator)entity;
				if(condition1.getId() != null) {
					Predicate id = criteriaBuilder.equal(root.get("id"), condition1.getId());
					predicates.add(id);
				}
				if(condition1.getName() != null) {
					Predicate name = criteriaBuilder.equal(root.get("name"), condition1.getName());
					predicates.add(name);
				}
				if(condition1.isPower() != null) {
					Predicate power = criteriaBuilder.equal(root.get("power"), condition1.isPower());
					predicates.add(power);
				}
				break;
			case "ClassNum":
				ClassNum condition2 = (ClassNum)entity;
				if(condition2.getId() != null) {
					Predicate id = criteriaBuilder.equal(root.get("id"), condition2.getId());
					predicates.add(id);
				}
				if(condition2.getName() != null) {
					Predicate name = criteriaBuilder.equal(root.get("name"), condition2.getName());
					predicates.add(name);
				}
				if(condition2.getMajor() != null) {
					Predicate major = criteriaBuilder.equal(root.get("major"), condition2.getMajor());
					predicates.add(major);
				}
				break;
			case "Department":
				Department condition3 = (Department)entity;
				if(condition3.getId() != null) {
					Predicate id = criteriaBuilder.equal(root.get("id"), condition3.getId());
					predicates.add(id);
				}
				if(condition3.getName() != null) {
					Predicate name = criteriaBuilder.equal(root.get("name"), condition3.getName());
					predicates.add(name);
				}
				break;
			case "LoginLog":
				LoginLog condition4 = (LoginLog)entity;
				if(condition4.getId() != null) {
					Predicate id = criteriaBuilder.equal(root.get("id"), condition4.getId());
					predicates.add(id);
				}
				if(condition4.getId() != null) {
					Predicate id = criteriaBuilder.equal(root.get("id"), condition4.getId());
					predicates.add(id);
				}
				if(condition4.getIdentity() != null) {
					Predicate identity = criteriaBuilder.equal(root.get("identity"), condition4.getIdentity());
					predicates.add(identity);
				}
				if(condition4.getName() != null) {
					Predicate name = criteriaBuilder.equal(root.get("name"), condition4.getName());
					predicates.add(name);
				}
				if(condition4.getEntityId() != null) {
					Predicate entityId = criteriaBuilder.equal(root.get("entityId"), condition4.getEntityId());
					predicates.add(entityId);
				}
				break;
			case "Major":
				Major condition5 = (Major)entity;
				if(condition5.getId() != null) {
					Predicate id = criteriaBuilder.equal(root.get("id"), condition5.getId());
					predicates.add(id);
				}
				if(condition5.getName() != null) {
					Predicate name = criteriaBuilder.equal(root.get("name"), condition5.getName());
					predicates.add(name);
				}
				if(condition5.getDepartment() != null) {
					Predicate department = criteriaBuilder.equal(root.get("department"), condition5.getDepartment());
					predicates.add(department);
				}
				break;
			case "ModifyTopic":
				ModifyTopic condition6 = (ModifyTopic)entity;
				if(condition6.getId() != null) {
					Predicate id = criteriaBuilder.equal(root.get("id"), condition6.getId());
					predicates.add(id);
				}
				if(condition6.getTitle() != null) {
					Predicate title = criteriaBuilder.equal(root.get("title"), condition6.getTitle());
					predicates.add(title);
				}
				if(condition6.getDifficulty() != null) {
					Predicate difficulty = criteriaBuilder.equal(root.get("difficulty"), condition6.getDifficulty());
					predicates.add(difficulty);
				}
				if(condition6.getDepartmentName() != null) {
					Predicate departmentName = criteriaBuilder.equal(root.get("departmentName"), condition6.getDepartmentName());
					predicates.add(departmentName);
				}
				if(condition6.getTopicId() != null) {
					Predicate topicId = criteriaBuilder.equal(root.get("topicId"), condition6.getTopicId());
					predicates.add(topicId);
				}
				break;
			case "Notice":
				Notice condition7 = (Notice)entity;
				if(condition7.getId() != null) {
					Predicate id = criteriaBuilder.equal(root.get("id"), condition7.getId());
					predicates.add(id);
				}
				if(condition7.getWriter() != null) {
					Predicate writer = criteriaBuilder.equal(root.get("writer"), condition7.getWriter());
					predicates.add(writer);
				}
				if(condition7.getWriterName() != null) {
					Predicate writerName = criteriaBuilder.equal(root.get("writerName"), condition7.getWriterName());
					predicates.add(writerName);
				}
				if(condition7.getTarget() != null) {
					Predicate target = criteriaBuilder.equal(root.get("target"), condition7.getTarget());
					predicates.add(target);
				}
				break;
			case "Student":
				Student condition8 = (Student)entity;
				if(condition8.getId() != null) {
					Predicate id = criteriaBuilder.equal(root.get("id"), condition8.getId());
					predicates.add(id);
				}
				if(condition8.getName() != null) {
					Predicate name = criteriaBuilder.equal(root.get("name"), condition8.getName());
					predicates.add(name);
				}
				if(condition8.getPassword() != null) {
					Predicate password = criteriaBuilder.equal(root.get("password"), condition8.getPassword());
					predicates.add(password);
				}
				if(condition8.getClassNum() != null) {
					Predicate classNum = criteriaBuilder.equal(root.get("classNum"), condition8.getClassNum());
					predicates.add(classNum);
				}
				if(condition8.getTopic() != null) {
					Predicate topic = criteriaBuilder.equal(root.get("topic"), condition8.getTopic());
					predicates.add(topic);
				}
				break;
			case "Teacher":
				Teacher condition9 = (Teacher)entity;
				if(condition9.getId() != null) {
					Predicate id = criteriaBuilder.equal(root.get("id"), condition9.getId());
					predicates.add(id);
				}
				if(condition9.getName() != null) {
					Predicate name = criteriaBuilder.equal(root.get("name"), condition9.getName());
					predicates.add(name);
				}
				if(condition9.getPassword() != null) {
					Predicate password = criteriaBuilder.equal(root.get("password"), condition9.getPassword());
					predicates.add(password);
				}
				if(condition9.getTitle() != null) {
					Predicate title = criteriaBuilder.equal(root.get("title"), condition9.getTitle());
					predicates.add(title);
				}
				break;
			case "Topic":
				Topic condition10 = (Topic)entity;
				if(condition10.getId() != null) {
					Predicate id = criteriaBuilder.equal(root.get("id"), condition10.getId());
					predicates.add(id);
				}
				if(condition10.getTitle() != null) {
					Predicate title = criteriaBuilder.equal(root.get("title"), condition10.getTitle());
					predicates.add(title);
				}
				if(condition10.getTeacher() != null) {
					Predicate teacher = criteriaBuilder.equal(root.get("teacher"), condition10.getTeacher());
					predicates.add(teacher);
				}
				if(condition10.getDepartmentName() != null) {
					Predicate departmentName = criteriaBuilder.equal(root.get("departmentName"), condition10.getDepartmentName());
					predicates.add(departmentName);
				}
				if(condition10.isIfPass() != null) {
					Predicate ifCheck = criteriaBuilder.equal(root.get("ifPass"), condition10.isIfPass());
					predicates.add(ifCheck);
				}
				if(condition10.getDifficulty() != null) {
					Predicate difficulty = criteriaBuilder.equal(root.get("difficulty"), condition10.getDifficulty());
					predicates.add(difficulty);
				}
				if(condition10.isIfSelect() != null) {
					Predicate ifSelect = criteriaBuilder.equal(root.get("ifSelect"), condition10.isIfSelect());
					predicates.add(ifSelect);
				}
				break;
			case "Description":
				Description condition11 = (Description)entity;
				if(condition11.getTopicId() != null) {
					Predicate topicId = criteriaBuilder.equal(root.get("topicId"), condition11.getTopicId());
					predicates.add(topicId);
				}
				break;
			case "Bernal":
				Bernal condition12 = (Bernal)entity;
				if(condition12.getTopicId() != null) {
					Predicate topicId = criteriaBuilder.equal(root.get("topicId"), condition12.getTopicId());
					predicates.add(topicId);
				}
				break;
			case "InterimReport":
				InterimReport condition13 = (InterimReport)entity;
				if(condition13.getTopicId() != null) {
					Predicate topicId = criteriaBuilder.equal(root.get("topicId"), condition13.getTopicId());
					predicates.add(topicId);
				}
				break;
			case "GuidanceRecord":
				GuidanceRecord condition14 = (GuidanceRecord)entity;
				if(condition14.getTopicId() != null) {
					Predicate topicId = criteriaBuilder.equal(root.get("topicId"), condition14.getTopicId());
					predicates.add(topicId);
				}
				break;
			case "ThesisPaper":
				ThesisPaper condition15 = (ThesisPaper)entity;
				if(condition15.getTopicId() != null) {
					Predicate topicId = criteriaBuilder.equal(root.get("topicId"), condition15.getTopicId());
					predicates.add(topicId);
				}
				break;
			}
		}
		return this;
	}
	
	/**
	 * 设置所要查找的单个或多个属性，若不设置默认为所有属性
	 * 需要对应的实体类中存在与参数匹配的构造方法
	 * @param(selectProperties)  所要查找的属性名 
	 */
	@Override
	public HandleEntityDAO setSelectProperties(String... selectProperties) {
		List<Expression> expressions = new ArrayList<Expression>(selectProperties.length);
		for(int i = 0; i < selectProperties.length; i++) {
			expressions.add(root.get(selectProperties[i]));
		}
		criteriaQuery.multiselect(expressions);
		return this;
	}
	
	/**
	 * 设置null查询
	 * @param(property)   所要查找的属性
	 * @param(ifNull)		属性是否为null
	 */
	@Override
	public HandleEntityDAO setNullQuery(String property, boolean ifNull) {
		if(ifNull) {
			predicates.add(criteriaBuilder.isNull(root.get(property)));
		}else{
			predicates.add(criteriaBuilder.isNotNull(root.get(property)));
		}
		return this;
	}
	
	/**
	 * 设置模糊查询
	 * @param(property)  所要查找的属性
	 * @param(pattern)	所要查找属性值的通式
	 */
	@Override
	public HandleEntityDAO setLikeQuery(String property, String pattern) {
		predicates.add(criteriaBuilder.like(root.get(property).as(String.class), pattern));
		return this;
	}
	
	/**
	 * 设置结果的获取顺序
	 * @param(property)  配置所需排序的属性
	 * @param(order)   配置顺序，值为desc或者asc
	 */
	@Override
	public HandleEntityDAO setOrder(String property, String order) {
		if(order == "desc" || order.equals("desc")) {
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get(property)));
		}
		if(order == "asc" || order.equals("asc")) {
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(property)));
		}
		return this;
	}
	
	/**
	 * 设置时间查询
	 * @param(timeProperty) 所要查询的属性名
	 * @param(beginTime)   限制的开始时间，字符串格式为yyyy-MM-dd
	 * @param(endTime)		限制的结束时间，字符串格式为yyyy-MM-dd
	 */
	@Override
	public HandleEntityDAO setTimeQuery(String timeProperty, String beginTime, String endTime) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;
		try {
			beginDate = format.parse(beginTime);
			endDate = format.parse(endTime);
		} catch (ParseException e) {
			logger.warn(e.getMessage());
		}
		Predicate time = criteriaBuilder.between(root.get(timeProperty), beginDate, endDate);
		predicates.add(time);
		return this;
	}

	/**
	 * 设置查询结果的获取部分
	* @param(beginIndex) 设置获取结果的开始位置
	* @param(num)   	设置所要显示的结果数量
	*/
	@Override
	public HandleEntityDAO setResultPage(int beginIndex, int num) {
		this.beginIndex = beginIndex;
		this.num = num;
		this.ifPage = true;
		return this;
	}
	
	/**
	 * 进行数据添加操作，需先通过setData()设置数据实体
	 */
	@Override
	public boolean insertEntity() {
		if(entity == null) {
			logger.warn("HandleEntityDAOImpl类的配置实体为null");
			return false;
		}else{
			try{
				tx = session.beginTransaction();
				session.save(entity);		//把transient状态的对象变成persistent，也就是新创建未存进数据库的对象存进库
				tx.commit();
				reset();
			}catch(Exception e){
				logger.error(e.getMessage());
				tx.rollback();
				return false;
			}
			return true;
		}
	}

	/**
	 * 进行数据修改操作，需先通过setData()设置数据实体
	 */
	@Override
	public boolean updateEntity() {
		if(entity == null) {
			logger.warn("HandleEntityDAOImpl类的配置实体为null");
			return false;
		}else{
			try{
				tx = session.beginTransaction();
				session.update(entity);			//把detached状态的对象变成persistent，也就是与数据库连接的数据即从数据库中取的数据对象改变后重新存进库
				tx.commit();
				reset();
			}catch(Exception e){
				logger.error(e.getMessage());
				tx.rollback();
				return false;
			}
			return true;
		}
	}
	
	/**
	 * 进行数据删除操作，需先通过setData()设置数据实体
	 */
	@Override
	public boolean deleteEntity() {
		if(entity == null) {
			logger.warn("HandleEntityDAOImpl类的配置实体为null");
			return false;
		}else{
			try{
				tx = session.beginTransaction();
				session.delete(entity);
				tx.commit();
				reset();
			}catch(Exception e){
				logger.error(e.getMessage());
				tx.rollback();
				return false;
			}
			return true;
		}
	}
	
	/**
	 * 获取查询结果
	 */
	@Override
	public List getResultList() {
		if(predicates.size() != 0){
			Predicate[] predicateArray = new Predicate[predicates.size()];
			Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(predicateArray));
			criteriaQuery.where(finalPredicate);			
		}
		List list = null;
		if(criteriaQuery == null){
			logger.warn("getResultList方法：类属性criteriaQuery为null");
		}else{
			Query query = session.createQuery(criteriaQuery);
			if(ifPage) {
				query.setFirstResult(beginIndex);
				query.setMaxResults(num);
			}
			list = query.list();
			reset();
		}
		return list;
	}
	
	/**
	 * 每次操作完后需要重置
	 */
	private void reset() {
		this.entity = null;
		predicates.clear();
		ifPage = false;
	}
	
	@PostConstruct
	public void init() {
		session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			criteriaBuilder = session.getCriteriaBuilder();
			tx.commit();			
		}catch(Exception e) {
			tx.rollback();
			logger.warn("init方法：获取criteriaBuilder属性失败");
		}
	}
	
	@PreDestroy
	public void destroy() {
		this.session.close();
	}

}
