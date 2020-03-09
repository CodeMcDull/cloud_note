package cn.tedu.cloud_note.service;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;

public interface UserService {
	public NoteResult<User> checkLogin(String name,String password);
	public NoteResult<Object> addUser(String name,String password,String nick);
	public NoteResult<User> changePassword(String userName,String last_password,String new_password,String final_password);
}
