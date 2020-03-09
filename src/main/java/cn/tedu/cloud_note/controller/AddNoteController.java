package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class AddNoteController {
	@Resource
	private NoteService noteService;
	@ResponseBody
	@RequestMapping("/add.do")
	public NoteResult<Note> execute(String userId,String noteTitle,String bookId){
		NoteResult<Note> result =noteService.addNote(userId, noteTitle, bookId);
		return result;
	}
}
