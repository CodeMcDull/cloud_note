function userLogin(){
	//���԰��¼�
	//alert("mua!���С����!");
	//��ȡ����
	var name=$("#count").val().trim();
	var password=$("#password").val().trim();
	//alert(name+","+password);
	//��ʽ���
	$("#count_span").html("");
	$("#password_span").html("");
	var ok=true;
	if(name==""){
		$("#count_span").html("�û�������Ϊ��");
		ok=false;
	}
	if(password==""){
		$("#password_span").html("���벻��Ϊ��");
		ok=false;
	}
	
	if(ok){   //����ʽͨ��
		//����ajax����
		$.ajax({
			url:path+"/user/login.do",
			type:"post",
			data:{"name":name,"password":password},
			dataType:"json",
			success:function(result){
				//result�Ƿ��������ص�JSON���
				if(result.status==0){
					//���û���Ϣ���浽Cookie
					var userId=result.data.cn_user_id;
					var userName=result.data.cn_user_name;
					addCookie("userId",userId,2);
					addCookie("userName",userName,2);
					window.location.href="edit.html";
				}else if(result.status==1){//�û�������
					$("#count_span").html(result.msg);
				}else if(result.status==2){
					$("#password_span").html(result.msg);
				}
			},
			error:function(){
				alert("��¼ʧ��!");
			}
		});	
	}
}