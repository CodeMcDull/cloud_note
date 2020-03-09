package cn.tedu.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("shareService")
@Transactional("dbcp")
public class ShareServiceImpl implements ShareService{
	@Resource
	private ShareDao shareDao;
	@Resource
	private NoteDao noteDao;
	public NoteResult<Share> shareNote(String noteId) {
		Share share=new Share();
		share.setCn_note_id(noteId);
		String shareId=NoteUtil.createId();
		share.setCn_share_id(shareId);
		Note note=noteDao.findByNoteId(noteId);		
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		shareDao.save(share);
		
//		//ģ���쳣
//		String str=null;
//		str.length();
		
		NoteResult<Share> result=new NoteResult<Share>();
		result.setStatus(0);
		result.setMsg("����ɹ�");
		return result;
	}

	public NoteResult<List<Share>> searchNote(String keyword,int page) {
		//ģ����ѯ
		String title="%"+keyword+"%";
		int begin=(page-1)*3;//����ץȡ��¼�����
		Map<String,Object> params=new HashMap();
		params.put("title", title);
		params.put("begin", begin);
		List<Share> list=shareDao.findLikeTitle(params);
		//�������ؽ��
		NoteResult<List<Share>> result=new NoteResult<List<Share>>();
			result.setStatus(0);
			result.setMsg("��ѯ���");
			result.setData(list);
		return result;
	}

}
