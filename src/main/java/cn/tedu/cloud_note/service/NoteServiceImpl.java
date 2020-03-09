package cn.tedu.cloud_note.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;
@Service("noteService")
public class NoteServiceImpl implements NoteService{
	@Resource
	private NoteDao noteDao;
	public NoteResult<List<Map>> loadBookNotes(String bookId) {
		//返回数据集合
		List<Map> list=noteDao.findByBookId(bookId);
		//构建Result
		NoteResult<List<Map>> result=new NoteResult<List<Map>>();
		result.setMsg("加载笔记成功!");
		result.setStatus(0);
		result.setData(list);
		
		return result;
	}
	public NoteResult<Note> loadNote(String noteId) {
		Note note=noteDao.findByNoteId(noteId);
		NoteResult<Note> result=new NoteResult<Note>();
		if(note==null){
			result.setStatus(1);
			result.setMsg("未找到数据!");
			return result;
		}else{
			result.setStatus(0);
			result.setMsg("加载笔记信息成功!");
			result.setData(note);
			return result;
		}
	}
	public NoteResult<Object> updateNote(String noteId,String noteTitle,String noteBody) {
		//创建note参数
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(noteTitle);
		note.setCn_note_body(noteBody);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		//更新数据库记录
		int rows=noteDao.updateNote(note);
		//构建result
		NoteResult<Object> result=new NoteResult<Object>();
		if(rows==1){
			result.setStatus(0);
			result.setMsg("保存笔记成功");
			return result;
		}else{
			result.setStatus(1);
			result.setMsg("保存笔记失败");
			return result;
		}
		
	}
	public NoteResult<Note> addNote(String userId, String noteTitle, String bookId) {
		Note note=new Note();
		note.setCn_note_id(NoteUtil.createId());
		note.setCn_user_id(userId);
		note.setCn_note_title(noteTitle);
		note.setCn_notebook_id(bookId);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_body("");
		//类型:1-normal 2-favor(收藏) 3-share(分享)
		note.setCn_note_type_id("1");
		//状态：1-normal 2-delete
		note.setCn_note_status_id("1");
		noteDao.save(note);
		NoteResult<Note> result= new NoteResult<Note>();
		result.setStatus(0);
		result.setMsg("创建成功");
		result.setData(note);
		return result;
	}
	public NoteResult<Note> deleteNote(String noteId) {
		Note note=noteDao.findByNoteId(noteId);
		note.setCn_note_status_id("2");
		int rows=noteDao.delete(note);
		NoteResult<Note> result=new NoteResult<Note>();
		if(rows>=1){
			result.setStatus(0);
			result.setMsg("删除笔记成功");
		}else{
			result.setStatus(1);
			result.setMsg("删除笔记失败");
		}
		return result;
	}
	@Transactional
	public void deleteNotes(String... ids) {
		//String...就是String[]
		for(String id:ids){
			int n=noteDao.deleteNote(id);
			if(n!=1){
				//抛出异常触发，事务的回滚！
				throw  new RuntimeException("删错了");
			}
		}
	}


}
