package test.service;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;
import test.TestBase;

public class TestNoteService extends TestBase{
	private NoteService noteService;
	@Before
	public void init(){
		noteService=super.getContext().getBean("noteService",NoteService.class);
	}
	@Test
	public void test(){
		NoteResult<List<Map>> result=noteService.loadBookNotes("fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
		for(Map map:result.getData()){
			System.out.println(map.get("cn_note_id"));
			System.out.println(map.get("cn_note_title"));
		}
	}
	@Test
	public void test1(){
		NoteResult<Note> result=noteService.loadNote("24097647-fdb5-4617-ba74-2e1d567fa938");
		System.out.println(result.getMsg());
		System.out.println(result.getStatus());
		System.out.println(result.getData());
	}
	@Test
	public void test2(){
		String noteId="24097647-fdb5-4617-ba74-2e1d567fa938";
		String noteTitle="肖";
		String noteBody="小犊子小犊子小犊子小犊子小犊子小犊子小犊子小犊子小犊子";
		NoteResult<Object> result=noteService.updateNote(noteId,noteTitle,noteBody);
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	@Test
	public void test3(){
		String userId="48595f52-b22c-4485-9244-f4004255b972";
		String noteTitle="小犊子";
		String bookId="48595f52-b22c-4485-9244-f4004255b972";
		NoteResult<Note> result=noteService.addNote(userId, noteTitle, bookId);
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	@Test
	public void test4(){
		String noteId="24097647-fdb5-4617-ba74-2e1d567fa938";
		NoteResult<Note> result=noteService.deleteNote(noteId);
		System.out.println(result.getStatus()+","+result.getMsg());
	}
	@Test
	public void testDeleteNotes(){
//		String[] ids={"id1","id2","id3"};
//		noteService.deleteNotes(ids);
		//调用动态参数时候，可以不创建数组，直接写参数		
		noteService.deleteNotes( "9187ffd3-4c1e-4768-9f2f-c600e835b823",
				"ebd65da6-3f90-45f9-b045-782928a5e2c0","ok");
	}
}
