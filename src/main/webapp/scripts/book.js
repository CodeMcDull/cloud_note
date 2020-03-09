//添加笔记本
function addBook(){
					//获取参数
					var bookName=$("#input_notebook").val().trim();
					var userId=getCookie("userId");
					//alert(bookTitle+","+userId);
					var ok=true;
					if(bookName==""){
						ok=false;
						$("#title_span").html("标题不能为空");
					}
					if(userId==null){
						ok=false;
						window.location.href="log_in.html";
					}
					if(ok){
						//发送ajax请求
						$.ajax({
							url:path+"/book/add.do",
							type:"post",
							data:{"bookName":bookName,"userId":userId},
							dataType:"json",
							success:function(result){
								var book=result.data;
								if(result.status==0){
									var bookId=book.cn_note_id;
									var bookName=book.cn_notebook_name;
									createBookLi(bookId,bookName);
									alert(result.msg);
								}
							},
							error:function(){
								alert("创建笔记本失败");
							}
						});
					}
					
				};
//根据用户id显示笔记本列表
function loadUserBooks(){
	//获取userId
	var userId=getCookie("userId");
//	alert(userId);
	//判断是否获取到有效useId
	if(userId==null){
		window.location.href="log_in.html";
	}else{
		$.ajax({//发送ajax请求
			url:path+"/book/loadbooks.do",
			type:"post",
			data:{"userId":userId},
			dataType:"json",
			success:function(result){
				//判断查询是否成功
				if(result.status==0){
					//获取笔记本集合
					var books=result.data;
					for(var i=0;i<books.length;i++){
						//获取笔记本ID
						var bookId=books[i].cn_notebook_id;
						//获取笔记本名称
						var bookName=books[i].cn_notebook_name;
						//创建一个笔记本列表的li元素
						createBookLi(bookId,bookName);
					}
				}				
			},
			error:function(){
				alert("笔记本加载失败!");
			}
		});
	}
};
//创建一个笔记本li元素
function createBookLi(bookId,bookName){
	var sli="";
	sli+='<li class="online">';
	sli+='<a>';
	sli+='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
	sli+='</i>';
	sli+=bookName;
	sli+='</a></li>';
	//将sli字符串转换成jQuery对象li元素
	var $li=$(sli);
	//将bookId值与jquery对象绑定
	$li.data("bookId",bookId);
	//将li元素添加到笔记本ul列表区
	$("#book_ul").append($li);
}








