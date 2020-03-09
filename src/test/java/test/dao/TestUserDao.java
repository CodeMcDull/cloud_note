package test.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteUtil;

public class TestUserDao {
	@Test
	public void TestUserDao(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		UserDao dao=ctx.getBean("userDao", UserDao.class);
		User user=dao.findByName("demo");
		System.out.println(user);
	}
	@Test
	public void testSave(){
		String[] conf={"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		//ʵ����ctx����
		ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
		//��ȡUserDao����
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		User user=new User();
		user.setCn_user_id("123456789");
		user.setCn_user_name("������");
		user.setCn_user_password("123456");
		user.setCn_user_nick("����");
		dao.save(user);
		System.out.println(user);
	}
	@Test
	public void testChange(){
		String[] conf={"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		//ʵ����ctx����
		ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
		//��ȡUserDao����
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		User user=new User();
		user.setCn_user_name("demo");
		user.setCn_user_password(NoteUtil.md5("123456"));
		dao.change(user);
		System.out.println(user);
	}
}
