package test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.util.NoteUtil;
import test.TestBase;

public class TestShareDao extends TestBase{
	@Resource
	private ShareDao shareDao;
	@Before
	public void init(){
		shareDao=super.getContext().getBean("shareDao",ShareDao.class);
	}
	@Test
	public void test(){
		Share share=new Share();
		share.setCn_note_id("24097647-fdb5-4617-ba74-2e1d567fa938");
		share.setCn_share_id(NoteUtil.createId());
		share.setCn_share_title("小犊子");
		share.setCn_share_body("小犊子小犊子小犊子小犊子小犊子");
		shareDao.save(share);
	}
	@Test
	public void test1(){
		String title="测试";
		int page=3;
		Map<String,Object> params=new HashMap();
		params.put("title", title);
		params.put("page", page);
		List<Share> list=shareDao.findLikeTitle(params);
		for(Share share:list){
			System.out.println(share.getCn_share_title());
		}
	}
}
