package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Note;

public interface NoteDao {
	public List<Map> findByBookId(String bookId);
	public Note findByNoteId(String noteId);
	public int updateNote(Note note);
	public int updateNoteByMap(Map<String,Object> map);
	public void save(Note note);
	public int delete(Note note);
	/**
	 * map 中需要添加两个参数：
	 * map={ids:[id1,id2,id3....],status:2}
	 * ids 代表被删掉笔记的ID列表
	 * status 代表被删除笔记的状态属性
	 * @param map
	 * @return
	 */
	public int deleteNotes(Map<String,Object> map);
	int deleteNote(String id);
}
