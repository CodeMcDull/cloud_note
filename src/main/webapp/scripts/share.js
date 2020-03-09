//按回车，加载搜索结果的第一页
function sureSearchShare(event){
					var code=event.keyCode;
					if(code==13){
						$("#search_ul li").remove();
						//获取参数
						var keyword=$("#search_note").val().trim();
						//alert(key);
						page=1;
						loadPageShare(keyword,page);
					}
				};
				
//按更多按钮，加载结果的下一页				
function moreSearchShare(){
	//获取参数
	var keyword=$("#search_note").val().trim();
	page=page+1;
	//发送ajax请求加载列表
	loadPageShare(keyword,page);
};

function loadPageShare(keyword,page){
	$.ajax({
		url:path+"/share/search.do",
		type:"post",
		data:{"keyword":keyword,"page":page},
		dataType:"json",
		success:function(result){
			//获取数据
			var shares=result.data;
			for(var i=0;i<shares.length;i++){
				//记录shareId
				var shareId=shares[i].cn_share_id;
				//获取shareTitle
				var shareTitle=shares[i].cn_share_title;
				//获取li对象
				var sli="";
				sli = sli
				+ '<li class="online">'
				+ '<a  >'
				+ '	<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'
				+ shareTitle
				+ '	<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'
				+ '</a>'
				+ '</li>';
				//绑定shareId
				var $li=$(sli);
				$li.data("shareId",shareId);
				//将里对象添加到ul当中
				$("#search_ul").append($li);
				//切换显示区
				$("#pc_part_6").show();
				$("#pc_part_2").hide();
			}
		},
		error:function(){
			alert("搜索失败");
		}
	});
};