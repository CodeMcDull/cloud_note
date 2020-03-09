package test.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteUtil;

public class TestBookDao {
	@Test
	public void test(){
		String[] conf={"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
		BookDao dao=ctx.getBean("bookDao",BookDao.class);
		List<Book> list=dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
		for(Book book:list){
			System.out.println(book.getCn_notebook_id()+","+book.getCn_notebook_name());
		}
	}
	@Test
	public void test1(){
		String[] conf={"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
		BookDao dao=ctx.getBean("bookDao",BookDao.class);
		String noteId=NoteUtil.createId();
		Book book=new Book();
		book.setCn_notebook_id(noteId);
		book.setCn_user_id("48595f52-b22c-4485-9244-f4004255b972");
		book.setCn_notebook_name("Ð¡¶¿×Ó");
		dao.save(book);
	}
}
