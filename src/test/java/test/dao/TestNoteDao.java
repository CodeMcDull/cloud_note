package test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import test.TestBase;

public class TestNoteDao extends TestBase{
	private NoteDao noteDao;
	@Before
	public void init(){
		noteDao=super.getContext().getBean("noteDao",NoteDao.class);
	}
	@Test
	public void test(){
		List<Map> list=noteDao.findByBookId("fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		for(Map note:list){
			System.out.println(
				note.get("cn_note_id")+","+note.get("cn_note_title"));
		}
	}
	@Test
	public void test1(){
		String noteId="24097647-fdb5-4617-ba74-2e1d567fa938";
		Note note=noteDao.findByNoteId(noteId);
		System.out.println(note.getCn_note_body());
		System.out.println(note.getCn_note_id());
		
	}
	@Test
	public void test2(){
		Note note=new Note();
		note.setCn_note_id("24097647-fdb5-4617-ba74-2e1d567fa938");
		note.setCn_note_title("小犊子");
		note.setCn_note_body("小犊子小犊子小犊子小犊子小犊子");
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		int num=noteDao.updateNote(note);
		System.out.println(num);
	}
	@Test
	public void test3(){
		Note note=new Note();
		note.setCn_note_id("8e3510ca28684f0fa7946e8cc55a0b38");
		note.setCn_user_id("48595f52-b22c-4485-9244-f4004255b972");
		note.setCn_note_title("小犊子");
		noteDao.save(note);
	}
	@Test
	public void test4(){
		Note note =new Note();
		note.setCn_note_id("24097647-fdb5-4617-ba74-2e1d567fa938");
		note.setCn_note_title("小犊子");
		note.setCn_note_body("小犊子小犊子小犊子小犊子小犊子");
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_status_id("1");
		int rows=noteDao.delete(note);
	}
	@Test
	public void testUpdateNoteByMap(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("title", "Java");
		map.put("noteId", "24097647-fdb5-4617-ba74-2e1d567fa938");
		map.put("body", "小犊子小犊子");
		//故意省略了参数 body 和 time
		noteDao.updateNoteByMap(map);
	}
	@Test
	public void testDeleteNotes(){
		Map<String,Object> map=new HashMap<String,Object>();
		String[] ids={"28c8e303d09e4dd6a4d36a2e0d67e431",
				"f6cd9da233ba4219b279821362555038",
				"ff39f9ce600840b9849592eaf321d9fc"};
		map.put("ids", ids);
		map.put("status", 2);
		int n=noteDao.deleteNotes(map);
		System.out.println(n);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
