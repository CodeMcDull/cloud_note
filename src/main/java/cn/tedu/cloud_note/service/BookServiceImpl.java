package cn.tedu.cloud_note.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;
@Service("bookService")
public class BookServiceImpl implements BookService{
	@Resource
	private BookDao bookDao;
	public NoteResult<List<Book>> loadUserBooks(String userId) {
		List<Book> list=bookDao.findByUserId(userId);
		//构建返回结果result
		NoteResult<List<Book>> result=new NoteResult<List<Book>>();
		
		result.setStatus(0);
		result.setMsg("查询笔记本成功");
		result.setData(list);
		return result;
	}
	public NoteResult<Book> addBook(String userId, String bookName) {
		String bookId=NoteUtil.createId();
		Book book=new Book();
		book.setCn_notebook_id(bookId);
		book.setCn_notebook_name(bookName);
		book.setCn_user_id(userId);
		bookDao.save(book);
		NoteResult<Book> result=new NoteResult<Book>();
		result.setStatus(0);
		result.setMsg("添加成功");
		result.setData(book);
		return result;
		
	}
	
}
