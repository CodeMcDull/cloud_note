package test.dao;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.dao.EmpDao;
import cn.tedu.cloud_note.entity.Emp;
import test.TestBase;

public class TestEmpDao extends TestBase{
	@Resource
	private EmpDao empDao;
	@Before
	public void init(){
		empDao=super.getContext().getBean("empDao",EmpDao.class);
	}
	@Test
	public void test(){
		Emp emp=new Emp();
		emp.setAge(88);
		emp.setName("’≈∑…");
		empDao.save(emp);
		int id=emp.getId();
		System.out.println("∏’≤Â»Îµƒid:"+id);
	}
}
