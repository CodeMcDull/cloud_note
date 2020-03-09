package cn.tedu.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("userService")//扫描的spring容器
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	public NoteResult<User> checkLogin(String name, String password) {
		//接收结果数据
		NoteResult<User> result=new NoteResult<User>();
		//按参数name查询数据库
		User user=userDao.findByName(name);
		//检测用户名
		if(user==null){
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result;
		}
		//检测密码
		String md5Password=NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5Password)){
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
		}
		//用户名和密码都正确
		result.setStatus(0);
		result.setMsg("登录成功");
		result.setData(user);
		return result;
	}
	public NoteResult<Object> addUser(String name,String password,String nick) {
		NoteResult<Object> result=new NoteResult<Object>();
		//检查用户
		if(userDao.findByName(name)!=null){
			result.setStatus(1);
			result.setMsg("用户名已存在!");
			return result;
		}
		//创建用户ID
		String id=NoteUtil.createId();
		//设置用户密码
		String password1=NoteUtil.md5(password);
		//添加用户
		User user=new User();
		//添加用户ID
		user.setCn_user_id(id);
		//设置用户名
		user.setCn_user_name(name);
		//设置用户昵称
		user.setCn_user_nick(nick);
		//设置用户密码
		user.setCn_user_password(password1);
		userDao.save(user);
		result.setStatus(0);
		result.setMsg("注册成功");
		return result;
	}
	public NoteResult<User> changePassword(String userName, String last_password, String new_password,
			String final_password) {
		User user=userDao.findByName(userName);
		
		return null;
	}
	
}
