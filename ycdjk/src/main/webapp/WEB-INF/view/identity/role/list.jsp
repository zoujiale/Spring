<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pgc" value="${pageContext.request.contextPath}"></c:set>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>角色管理</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="/WEB-INF/view/commons/main-css.jsp"></jsp:include>

</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!-- Site wrapper -->
	<div class="wrapper">

		<jsp:include page="/WEB-INF/view/commons/main-header.jsp"></jsp:include>

		<!-- =============================================== -->

		<!-- Left side column. contains the sidebar -->
		<jsp:include page="/WEB-INF/view/commons/main-sidebar.jsp"></jsp:include>
		<!-- =============================================== -->


		<div class="content-wrapper" id="mainDiv" style="min-height: 429px;">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>角色管理</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li><a href="#">系统管理</a></li>
					<li class="active">角色管理</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">

				<div class="row">

					<!-- /.col -->
					<div class="col-md-7">
						<div class="box box-primary">
							<!-- /.box-header -->
							<div class="dataTables_filter" id="searchDiv">
								<input placeholder="请输入名称" id="role_name" name="name" class="form-control"
									type="search" likeOption="true"  />
								<div class="btn-group">
									<button type="button" class="btn btn-primary"
										data-btn-type="search">查询</button>
								</div>
								<div class="btn-group">
									<button type="button" class="btn btn-default"
										data-btn-type="add">新增</button>
									<button type="button" class="btn btn-default"
										data-btn-type="edit">编辑</button>
									<button type="button" class="btn btn-default"
										data-btn-type="delete">删除</button>
								</div>
							</div>
							<div class="box-body">
								<table id="role_table"
									class="table table-bordered table-striped table-hover">
								</table>
							</div>
							<!-- /.box-body -->
						</div>
					</div>
					<div class="col-md-5">
						<!-- Profile Image -->
						<div class="box box-primary">
							<!-- /.box-header -->
							<div class="dataTables_filter" id="searchDiv_userRole">
							<input type="hidden" name="roleId"  id="roleId" />
							<input type="hidden" name="roleId"  id="roleId1" />
								<input type="hidden" name="roleName"  id="roleName" /> <input
									placeholder="请输入用户名" name="user.name" class="form-control"
									type="search" likeOption="true" id="user_name"/>
								<div class="btn-group">
									<button type="button" class="btn btn-primary"
										data-btn-type="search">查询</button>
								</div>
								<div class="btn-group">
									<button type="button" class="btn btn-default"
										data-btn-type="selectUserRole">选择</button>
									<button type="button" class="btn btn-default"
										data-btn-type="deleteUserRole">删除</button>
								</div>
							</div>
							<div class="box-body">
								<table id="userRole_table"
									class="table table-bordered table-striped table-hover">
								</table>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
				</div>
				<!-- /.row -->

			</section>
		</div>
	</div>
	<!-- ./wrapper -->

	<!-- main-footer -->
	<jsp:include page="/WEB-INF/view/commons/main-footer.jsp"></jsp:include>
	<!--main-footer 結束  -->

	<!-- 通用js开始 -->
	<jsp:include page="/WEB-INF/view/commons/main-js.jsp"></jsp:include>
	<!-- 通用js结束 -->

	<script>
		//tableId,queryId,conditionContainer
		var roleTable,userRoleTable;
		var winId="roleWin";
		var rowId = $('#roleId').val();
		var rowName = $('#roleName').val();
		var basePath = "${pgc}/identity"
		$(function() { 
			
			

					   roleTable = $('#role_table').DataTable({		
						   'bInfo':false,
						   'bPaginate':false,
			                 "lengthChange": false, //每页记录数可选项
			                 "searching": false, //过滤
			                 "ordering": false, //排序
			                 "autoWidth": true,
			                 "language": { //中文支持
			                     "sUrl":  "${pgc}/adminlte/common/json/zh_CN.json"
			                 },
			                 "processing": true,
			                 "serverSide": true,
			                 "bProcessing": true,// 是否显示取数据时的那个等待提示
			                 "bServerSide": true,
			                 "sAjaxSource":"${pgc}/identity/role",
			                 "sAjaxDataProp": "data",
			                 "fnServerData": loadData, // 获取数据的处理函数
			                 'rowId': 'id',
			                 "singleSelect": true,  //单选
			                 "columns": [
			                	 {"sTitle":"序号","sClass": "dt-center", "bSortable" :false,"data": null, 'sClass': 'text-center'},
			                	 {'data': 'name', 'sTitle': '角色名称', 'sName': 'name', 'sClass': 'text-center'},
			                	 {'data': 'remark', 'sTitle': '备注', 'sName': '备注', 'sClass': 'text-center'},
			                     {'data': 'enable', 'sTitle': '状态', 'sName': '状态', 'sClass': 'text-center'},
			                     /* {'data': 'createDateTime', 'sTitle': '创建日期', 'sName': '创建日期', 'sClass': 'text-center','format': 'YYYY/DD/MM'}, */
			                     
			                 ],
			               
			      		   "fnDrawCallback"    : function(){
			      	        　　this.api().column(0).nodes().each(function(cell, i) {
			      	        　　　　cell.innerHTML =  i + 1;
			      	        　　});
			      	        }
			                 
						});
						 userRoleTable = $("#userRole_table").DataTable({		
				           	  "displayLength": 10,//每页记录条数，默认为10 
				           	  'bInfo':false,
								   'bPaginate':true,
					                 "lengthChange": false, //每页记录数可选项
					                 "searching": false, //过滤
					                 "ordering": false, //排序
					                 "autoWidth": true,
					                 "language": { //中文支持
					                     "sUrl":  "${pgc}/adminlte/common/json/zh_CN.json"
					                 },
					                 "processing": true,
					                 "serverSide": true,
					                 "bProcessing": true,// 是否显示取数据时的那个等待提示
					                 "bServerSide": true,
					                 "sAjaxSource":"${pgc}/identity/role/roleuser",
					                 "sAjaxDataProp": "data",
					                 "fnServerData": loadData, // 获取数据的处理函数
					                 'rowId': 'id',
					                 "singleSelect": true,  //单选
					                 "columns": [
					                	 {"sTitle":"序号","sClass": "dt-center", "bSortable" :false,"data": null, 'sClass': 'text-center'},
					                	 {'data': 'userName', 'sTitle': '用户名', 'sName': 'name', 'sClass': 'text-center'},
					                	 {'data': 'loginName', 'sTitle': '登陆名', 'sName': '备注', 'sClass': 'text-center'},
					                     /* {'data': 'createDateTime', 'sTitle': '创建日期', 'sName': '创建日期', 'sClass': 'text-center','format': 'YYYY/DD/MM'}, */
					                     
					                 ],
					               
					      		   "fnDrawCallback"    : function(){
					      	        　　this.api().column(0).nodes().each(function(cell, i) {
					      	        　　　　cell.innerHTML =  i + 1;
					      	        　　});
					      	        }
					                 
								});
						   
			
	
		
			$('#role_table tbody').on( 'click', 'tr', function () {
				 $("#roleId").val("");
				 $('#roleName').val("");
				var rowData = roleTable.row( this ).data();
			  if ( !$(this).hasClass('selected')) {
				  $('tr.selected').removeClass('selected');
                  $(this).addClass('selected');
                  $("#roleId").val(rowData.id);
                  $('#roleName').val(rowData.name);
                  userRoleTable.draw();
				}
				 	
				 
			} );
		
	
			
			
			 function loadData(sSource, aoData, fnCallback) {
	             var map = getMap(aoData);
	         
	             var pageInfo = {};
	             pageInfo.pageSize = map.iDisplayLength;
	             pageInfo.pageNum = map.iDisplayStart % map.iDisplayLength == 0 ? map.iDisplayStart / map.iDisplayLength + 1 : map.iDisplayStart / map.iDisplayLength;
	             var inputInfo = {'role.name':$("#role_name").val(),'user.name':$("#user_name").val()};
	             
	             $.ajax({
	                 url: sSource,//这个就是请求地址对应sAjaxSource
	                 data: {"pInfo": JSON.stringify(pageInfo),'inputInfo':JSON.stringify(inputInfo),'id':$("#roleId").val()},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
	                 type: 'post',
	                 dataType: 'json',
	                 async: false,
	                 success: function (page) {
	                	 console.log(page);
	                     //console.log(JSON.stringify(result));
	                     var obj = {};
	                     obj['data'] = page.recordList;
	                     
	                     //obj.sEcho=result.pageInfo.pageNum;//第几次
	                     obj.iTotalRecords = page.recordCount;
	                     obj.iTotalDisplayRecords = page.recordCount;
	                     
	                    
	                     fnCallback(obj);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
	                 },
	                 error: function (msg) {
	                     /* alert(msg); */
	                 }
	             });
	         }
			
		
             
				function getMap(aData) {
		            var map = {};
		            for (var i = 0; i < aData.length; i++) {
		                var item = aData[i];
		                if (!map[item.name]) {
		                    map[item.name] = item.value;
		                }
		            }
		            return map;
		        }
				
			
			
			 
			//button event   
			$('button[data-btn-type]').click(function() {
				var action = $(this).attr('data-btn-type');
				var rowId = $('#roleId').val();
				switch (action) {
				 case 'search':
					 	roleTable.draw();
					 	userRoleTable.draw();
		                break;
				case 'add':
					$('#roleId').val("");
                       modals.openWin({
                       	winId:winId,
                       	title:'新增角色',
                       	width:'600px',
                       	url:basePath+"/role/edit"
                       });   
                     
					break;
				case 'edit':
					console.log(roleId);
					if (rowId =="") {
	            		modals.info("请选择要编辑的行")
	            		return
					}
					
					modals.openWin({
                       	winId:winId,
                       	title:'编辑角色【 '+roleName+"】",
                       	width:'600px',
                       	url:basePath+"/role/edit/?id=" +rowId
                       }); 
				   break;
				case 'delete':
					if (rowId =="") {
	            		modals.info("请选择要删除的行")
	            		return
					}
					modals.confirm("是否要删除该行数据？",function(){
						$.post(basePath+"/role/delete/",{'id':rowId},function(data){
							var list=eval("("+data+")");
		                	if(list["state"]==true){     
								//modals.correct("已删除该数据");
								roleTable.draw();
							}else{  
								//setTimeout(function(){modals.info(data.message)},2000);
								modals.info(data.message); 
							}  
						});  
					})
					break;					
				case 'selectUserRole':
					if(rowId==""){
						modals.info('请选择角色');
						return;
					}
					modals.openWin({
						winId:'userRoleWin',
						width:1000,
						title:'角色【'+roleName+'】绑定用户',
						url:basePath+'/role/select?roleId='+rowId, 
					    hideFunc:function(){userRoleTable.draw();}
					})
					break;
				case 'deleteUserRole':
					var rowId_ur=userRoleTable.getSelectedRowId();
					if(!rowId_ur){
						modals.info("请选择要删除的用户");
						return false;
					}
					modals.confirm("是否要删除该行数据",function(){
						ajaxPost(basePath+"/userrole/delete",{ids:rowId_ur},function(data){
							if(data.success){
								userRoleTable.reloadData();
							}else{ 
								modals.info(data.message);
							}
						})
					});
					break;
				}

			});
			//form_init();
		})
		
		
	</script>

</body>
</html>
