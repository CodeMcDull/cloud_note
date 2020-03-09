package test.dao;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.dao.RelationDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.entity.User;
import test.TestBase;

public class TestRelationDao extends TestBase{
	@Resource
	private RelationDao rdao;
	
	@Before
	public void init(){
		rdao=super.getContext().getBean("relationDao",RelationDao.class);
	}
	@Test
	public void testMany(){
		User user=rdao.findUserAndBooks1("48595f52-b22c-4485-9244-f4004255b972");
		System.out.println("=======用户信息=========");
		System.out.println("名字"+user.getCn_user_name());
		System.out.println("昵称"+user.getCn_user_nick());
		System.out.println("笔记本数量"+user.getBooks().size());
		System.out.println("======笔记本列表===========");
		for(Book book:user.getBooks()){
			System.out.println(book.getCn_notebook_name());
		}
	}
	@Test
	public void  testSingle(){
		List<Book> list=rdao.findBookAndUser1();
		for(Book book :list){
			System.out.println(book.getCn_notebook_name()+","+book.getCn_notebook_createtime());
			if(book.getUser()!=null){
				System.out.println(book.getUser().getCn_user_name());
			}
		}
	}
	
}
