//删除笔记
function deleteNote(){
//	alert("=");
	//获取参数
	$li=$("#note_ul a.checked").parent();
	var noteId=$li.data("noteId");
	$.ajax({
		url:path+"/note/delete.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//删除li
				 $li.remove();
				 //提示成功
				 alert(result.msg);
			}
		},
		error:function(){
			alert("删除失败");
		}
	});
};
//分享笔记
function shareNote(){
					//alert("=");
					//获取参数
					$li=$(this).parents("li");
					var noteId=$li.data("noteId");
					$.ajax({
						url:path+"/share/add.do",
						type:"post",
						data:{"noteId":noteId},
						dataType:"json",
						success:function(result){
							var noteTitle=$li.text();
							var sli="";
							sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
							sli+=noteTitle;
							sli+='<i class="fa fa-sitemap"></i>'
							sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';	
							//将笔记li元素的<a>标记内容替换
							$li.find("a").html(sli);
							alert(result.msg);
						},
						error:function(){
							alert("分享笔记失败");
						}
					});
				};
//添加笔记
function addNote(){
	//获取参数
	var userId=getCookie("userId");
	var noteTitle=$("#input_note").val().trim();
	var bookId=$("#book_ul a.checked").parent().data("bookId");
//	alert(userId+","+noteTitle+","+bookId);
	//数据格式检查
	var ok=true;
	if(noteTitle==""){//判断是否为空
		ok=false;
		$("#title_span").html("标题不能为空!");
	}
	if(userId==null){
		ok=false;
		window.location.href="log_in.html";
	}
	if(ok){
		$.ajax({//发送请求
			url:path+"/note/add.do",
			type:"post",
			data:{"userId":userId,"noteTitle":noteTitle,"bookId":bookId},
			dataType:"json",
			success:function(result){
				var note=result.data;
				if(result.status==0){
					var noteId=note.cn_note_id;
					var noteTitle=note.cn_note_title;
					createNoteLi(noteId,noteTitle);
					alert(result.msg);
				}
			},
			error:function(){
				alert("创建笔记失败");
			}
		});
		
	}
	
};
//更新笔记信息
function updataNote(){
					//获取参数
					var $li=$("#note_ul a.checked").parent();
					//获取笔记id
					var noteId=$li.data("noteId");
					//获取笔记的标题和内容
					var noteTitle=$("#input_note_title").val().trim();
					var noteBody=um.getContent();
					alert(noteId+","+noteTitle+","+noteBody);
					$.ajax({
						url:path+"/note/update.do",
						type:"post",
						data:{"noteId":noteId,"noteTitle":noteTitle,"noteBody":noteBody},
						dataType:"json",
						success:function(result){
							if(result.status==0){
								var str="";
								str+= '	<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
								str+= noteTitle;
								str+= '		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
								//将str替换到li的a元素里
								$li.find("a").html(str);
								//提示成功
								alert(result.msg);
							}
						},
						error:function(){
							alert("保存失败");
						}
						});
					};
//显示笔记信息
function loadnote(){
					//设置选中效果
					$("#note_ul a").removeClass("checked");
					$(this).find("a").addClass("checked");
					//获取请求参数
					var noteId=$(this).data("noteId");
					//alert(noteId);
					$.ajax({
						url:path+"/note/load.do",
						type:"post",
						data:{"noteId":noteId},
						dataType:"json",
						success:function(result){
							if(result.status==0){
								//获取返回的笔记标题
								var title=result.data.cn_note_title;
								//获取返回的笔记内容
								var body=result.data.cn_note_body;
								//设置笔记标题
								$("#input_note_title").val(title);
								//设置笔记内容
								um.setContent(body);
							}
							
						},
						error:function(){
							alert("笔记信息加载失败");
						}
					});
				};
//加载笔记本笔记
function loadBookNotes() {
					//设置选中效果
					$("#book_ul a").removeClass("checked");
					$(this).find("a").addClass("checked");
					//获取参数
					var bookId=$(this).data("bookId");
					$.ajax({
						url:path+"/note/loadnotes.do",
						type:"post",
						data:{"bookId":bookId},
						dataType:"json",
						success:function(result){
								//获取笔记信息
								var notes=result.data;
								//清除原列表信息
								$("#note_ul").empty();
								//$("#note_ul li").remove();
								//循环添加li
								for(var i=0;i<notes.length;i++){
									//获取笔记ID
									var noteId=notes[i].cn_note_id;
									//获取笔记标题
									var noteTitle=notes[i].cn_note_title;
									//生成笔记li
									createNoteLi(noteId,noteTitle);
								};
						},
						error:function(){
							alert("笔记加载失败");
						}
					});
				};
function createNoteLi(noteId,noteTitle){
	var sli="";
	sli = sli
	+ '<li class="online">'
	+ '<a  >'
	+ '	<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'
	+ noteTitle
	+ '		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'
	+ '</a>'
	+ '<div class="note_menu" tabindex="-1">'
	+ '	<dl>'
	+ '	<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'
	+ '		<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'
	+ '		<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'
	+ '	</dl>' + '</div>' + '</li>';
	//转换成jQuery对象
	var $li=$(sli);
	//保存noteId
	$li.data("noteId",noteId);
	//将li追加到ul中
	$("#note_ul").append($li);
};