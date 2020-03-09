package cn.tedu.cloud_note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;
@Controller
@RequestMapping("/note")
public class LoadNoteController {
	@Resource
	private NoteService noteService;
	@ResponseBody
	@RequestMapping("/loadnotes.do")
	public NoteResult<List<Map>> execute(String bookId){
		NoteResult<List<Map>> result=noteService.loadBookNotes(bookId);
		return result;
	}
	@ResponseBody
	@RequestMapping("/load.do")
	public NoteResult<Note> execute1(String noteId){
		NoteResult<Note> result=noteService.loadNote(noteId);
		return result;
	}
	@ResponseBody
	@RequestMapping("/update.do")
	public NoteResult<Object> execute2(String noteId,String noteTitle,String noteBody){
		NoteResult<Object> result=noteService.updateNote(noteId,noteTitle,noteBody);
		return result;
	}
}
