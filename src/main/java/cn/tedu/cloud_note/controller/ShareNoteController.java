package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;

@RequestMapping("/share")
@Controller
public class ShareNoteController {
	@Resource
	private ShareService shareService;
	@ResponseBody
	@RequestMapping("/add.do")
	public NoteResult<Share> execute(String noteId){
		NoteResult<Share> result=shareService.shareNote(noteId);
		return result;
	}
	@ResponseBody
	@RequestMapping("/search.do")
	public NoteResult<List<Share>> execute1(String keyword,int page){
		NoteResult<List<Share>> result=shareService.searchNote(keyword,page);
		return result;
	}
}
