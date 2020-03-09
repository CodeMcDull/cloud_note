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
	 * map ����Ҫ�������������
	 * map={ids:[id1,id2,id3....],status:2}
	 * ids ����ɾ���ʼǵ�ID�б�
	 * status ����ɾ���ʼǵ�״̬����
	 * @param map
	 * @return
	 */
	public int deleteNotes(Map<String,Object> map);
	int deleteNote(String id);
}
