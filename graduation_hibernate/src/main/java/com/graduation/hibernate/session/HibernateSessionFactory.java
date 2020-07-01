package com.graduation.hibernate.session;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**

* @author awang
* @version 1.0
* @date 2020年2月8日 下午10:25:00
* 
*/

public class HibernateSessionFactory {

	// 指定Hibernate配置文件路径
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	// 创建ThreadLocal对象
	private static final ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<Session>();
	// 创建Configuration对象
	private static Configuration configuration = new Configuration();
	// 定义SessionFactory对象
	private static SessionFactory sessionFactory;
	// 定义configFile属性并赋值
	private static String configFile = CONFIG_FILE_LOCATION;

	static {
		try {
			// 读取配置文件hibernate.cfg.xml
			configuration.configure();
			// 创建sessionFactory
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
			sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	// 创建无参的HibernateSessionFactory构造方法
	private HibernateSessionFactory() {

	}

	// 获得SessionFactory对象
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// 重建SessionFactory
	public static void rebuildSessionFactory() {
		synchronized (sessionFactory) {
			try {
				configuration.configure(configFile);
				StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);
			} catch (HibernateException e) {
				e.printStackTrace();
			}
		}
	}

	// 获得Session对象
	public static Session getSession() {
		// 获得ThreadLocal对象管理的Session对象
		Session session = (Session) sessionThreadLocal.get();
		try {
			// 判断Session对象是否已经存在或是否打开
			if (session == null || !session.isOpen()) {
				// 如果Session对象为空或未打开，再判断SessionFactory对象是否为空
				if (sessionFactory == null) {
					// 如果sessionFactory为空，则创建SessionFactory
					rebuildSessionFactory();
				}
				// 如果sessionFactory不为空，则打开Session
				session = (sessionFactory != null) ? sessionFactory.openSession() : null;
				sessionThreadLocal.set(session);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return session;
	}

	// 关闭Session对象
	public static void closeSession() {
		Session session = (Session) sessionThreadLocal.get();
		sessionThreadLocal.set(null);
		try {
			if (session != null && session.isOpen()) {
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	// configFile属性的set方法
	public static void setConfigFile(String configFile) {
		HibernateSessionFactory.configFile = configFile;
		sessionFactory = null;
	}

	// configuration属性的get方法
	public static Configuration getConfiguration() {
		return configuration;
	}

}
