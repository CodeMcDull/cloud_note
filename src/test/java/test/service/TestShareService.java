package test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;
import test.TestBase;

public class TestShareService extends TestBase{
	@Resource
	private ShareService shareService;
	@Before
	public void init(){
		shareService=super.getContext().getBean("shareService", ShareService.class);
	}
	@Test
	public void test(){
		NoteResult<Share> result=shareService.shareNote("24097647-fdb5-4617-ba74-2e1d567fa938");
		System.out.println(result);
	}
	@Test
	public void test1(){
		String keyword="≤‚ ‘";
		int page=3;
		NoteResult<List<Share>> result=shareService.searchNote(keyword,page);
		System.out.println(result.getStatus()+","+result.getMsg()+","+result.getData());
	}
}
