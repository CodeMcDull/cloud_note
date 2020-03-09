package cn.tedu.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("userService")//ɨ���spring����
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	public NoteResult<User> checkLogin(String name, String password) {
		//���ս������
		NoteResult<User> result=new NoteResult<User>();
		//������name��ѯ���ݿ�
		User user=userDao.findByName(name);
		//����û���
		if(user==null){
			result.setStatus(1);
			result.setMsg("�û���������");
			return result;
		}
		//�������
		String md5Password=NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5Password)){
			result.setStatus(2);
			result.setMsg("�������");
			return result;
		}
		//�û��������붼��ȷ
		result.setStatus(0);
		result.setMsg("��¼�ɹ�");
		result.setData(user);
		return result;
	}
	public NoteResult<Object> addUser(String name,String password,String nick) {
		NoteResult<Object> result=new NoteResult<Object>();
		//����û�
		if(userDao.findByName(name)!=null){
			result.setStatus(1);
			result.setMsg("�û����Ѵ���!");
			return result;
		}
		//�����û�ID
		String id=NoteUtil.createId();
		//�����û�����
		String password1=NoteUtil.md5(password);
		//����û�
		User user=new User();
		//����û�ID
		user.setCn_user_id(id);
		//�����û���
		user.setCn_user_name(name);
		//�����û��ǳ�
		user.setCn_user_nick(nick);
		//�����û�����
		user.setCn_user_password(password1);
		userDao.save(user);
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
		return result;
	}
	public NoteResult<User> changePassword(String userName, String last_password, String new_password,
			String final_password) {
		User user=userDao.findByName(userName);
		
		return null;
	}
	
}
