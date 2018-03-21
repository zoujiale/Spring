<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pgc" value="${pageContext.request.contextPath}"></c:set>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>角色授权</title>
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
				<h1>菜单管理</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li><a href="#">系统管理</a></li>
					<li class="active">菜单管理</li>
				</ol>
			</section>
			<section class="content">

				<div class="row">
					<!-- /.col -->
					<div class="col-md-8">
						<div class="box box-primary">
							<!-- /.box-header -->
							<div class="dataTables_filter" id="searchDiv">
								<input placeholder="请输入名称" name="name" class="form-control"
									type="search" likeOption="true" />
								<div class="btn-group">
									<button type="button" class="btn btn-primary"
										data-btn-type="search">查询</button>
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
					<div class="col-md-4">
						<!-- Profile Image -->
						<div class="box box-primary">
							<!-- /.box-header -->
							<div class="dataTables_filter" style="margin: 10px 10px 0 0"
								id="searchDiv_roleFunc">
								<input type="hidden" name="roleId" value="-1" id="roleId" /> <input
									placeholder="请输入功能名" name="name" id="functionName"
									class="form-control" type="search" likeOption="true" />
								<div class="btn-group">
									<button type="button" class="btn btn-primary"
										data-btn-type="searchRoleFunc">查询</button>
								</div>
								<div class="btn-group">
									<button type="button" class="btn btn-default"
										data-btn-type="selectRoleFunc">授权</button>
									<button type="button" class="btn btn-default"
										data-btn-type="deleteRoleFunc">删除</button>
								</div>
							</div>
							<div class="box-body">
								<div id="rftree"></div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
				</div>
				<!-- /.row -->

			</section>
		</div>
		<!-- main-footer -->
		<jsp:include page="/WEB-INF/view/commons/main-footer.jsp"></jsp:include>
		<!--main-footer 結束  -->

		<!-- 通用js开始 -->
		<jsp:include page="/WEB-INF/view/commons/main-js.jsp"></jsp:include>
		<!-- 通用js结束 -->
		<script>
			var basePath = "${pgc}/identity";
		</script>

		<script>
			var roleTable;

			var roleObj = {
				loadData : function() {
					var role_config = {
						//定义选中行事件
						rowClick : function(row, isSelected) {
							$("#roleId").val(isSelected ? row.id : "-1");
							$("#roleName").remove();
							if (isSelected)
								$("#searchDiv_roleFunc").prepend(
										"<h5 id='roleName' class='pull-left'>【"
												+ row.name + "】</h5>");
							roleObj.reloadTreeData();
						},
						//数据加载后，默认选中第一行
						loadComplete : function() {
							roleTable.selectFirstRow(true);
						}
					}
					$('#role_table tbody').on('click', 'tr', function () {
					    if ($(this).hasClass('selected') ) {
					       $(this).removeClass('selected');
					    } else {
					       table.$('tr.selected').removeClass('selected');
					       $(this).addClass('selected');
					       
					    }
					});
				

					roleTable =$('#role_table').DataTable({		
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
					this.reloadTreeData();
				},
				//菜单树的初始化
				reloadTreeData : function() {
					var selectNodeId = 0;
					var treeData = null;
					var rowId = '4028802461ff65e80161ff764cb30000';
					if (!rowId)
						return;
					$.post(basePath + "/permission/listtree/" + rowId, null,
							function(data) {
								treeData = data;
								$("#rftree").treeview({
									data : treeData,
									showBorder : true,
									levels : 3,
									showCheckbox : false,
									showIcon : false
								});
							},'json');
				
					if (treeData.length == 0)
						return;
					//默认选中第一个节点
					selectNodeId = selectNodeId || 0;
					$("#rftree").data('treeview').selectNode(selectNodeId);
					$("#rftree").data('treeview').expandNode(selectNodeId);
					$("#rftree").data('treeview').revealNode(selectNodeId);
				},
				searchRoleFunc : function() {
					$('#rftree').treeview('search',
							[ $("#functionName").val(), {
								ignoreCase : true, // case insensitive
								exactMatch : false, // like or equals
								revealResults : true, // reveal matching nodes
							} ], 'name');
				},
				//授权
				selectRoleFunc : function(rowId) {
					if (!rowId) {
						modals.info('请选择角色');
						return;
					}
					var self = this;
					modals.openWin({
						winId : 'roleFuncWin',
						width : 1000,
						title : '角色【' + roleTable.getSelectedRowData().name
								+ '】绑定功能',
						url : basePath + '/rolefunc/select/' + rowId,
						hideFunc : function() {
							self.reloadTreeData();
						}
					});
				},
				deleteRoleFunc : function(rowId) {
					var nodes = $("#rftree").data('treeview').getSelected();
					if (!nodes || nodes.length == 0) {
						modals.info("请选择要解绑的功能");
						return false;
					}
					console.log(nodes);
					if (this.hasChildNode(nodes[0].levelCode)) {
						modals.info("当前选中节点含有子节点，请先删除子节点");
						return false;
					}
					modals.confirm("是否要删除该行数据", function() {
						ajaxPost(basePath + "/rolefunc/deleterf/" + rowId + "/"
								+ nodes[0].id, null, function(data) {
							if (data.success) {
								roleObj.reloadTreeData();
							} else {
								modals.info(data.message);
							}
						})
					});
				},
				//校验当前的节点是否含有下级菜单
				hasChildNode : function(levelCode) {
					var nodes = $("#rftree").data('treeview').findNodes(
							levelCode + "0", 'g', 'levelCode');
					console.log('--------------------' + nodes);
					if (nodes && nodes.length > 0)
						return true;
					return false;
				}

			}
			
			 function loadData(sSource, aoData, fnCallback) {
	                var map = getMap(aoData);
	            
	                var pageInfo = {};
	                pageInfo.pageSize = map.iDisplayLength;
	                pageInfo.pageNum = map.iDisplayStart % map.iDisplayLength == 0 ? map.iDisplayStart / map.iDisplayLength + 1 : map.iDisplayStart / map.iDisplayLength;
	                var inputInfo = {'role.name':$("input[name = 'name']").val()}
	                $.ajax({
	                    url: sSource,//这个就是请求地址对应sAjaxSource
	                    data: {"pInfo": JSON.stringify(pageInfo),'inputInfo':JSON.stringify(inputInfo),'loginName':$('#loginName').val()},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
	                    type: 'post',
	                    dataType: 'json',
	                    async: false,
	                    success: function (page) {
	                        //console.log(JSON.stringify(result));
	                        var obj = {};
	                        obj['data'] = page.recordList;
	                        
	                        //obj.sEcho=result.pageInfo.pageNum;//第几次
	                        obj.iTotalRecords = page.recordCount;
	                        obj.iTotalDisplayRecords = page.recordCount;
	                        
	                       
	                        console.log(obj);
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
			$(function() {
				//初始化数据

				roleObj.loadData();
				//button event
				$('button[data-btn-type]').click(function() {
					var action = $(this).attr('data-btn-type');
					var rowId = roleTable.getSelectedRowId();
					switch (action) {
					case'search':
						roleTable.draw()
						break;
					case 'selectRoleFunc':
						roleObj.selectRoleFunc(rowId);
						break;
					case 'deleteRoleFunc':
						roleObj.deleteRoleFunc(rowId);
						break;
					case 'searchRoleFunc':
						roleObj.searchRoleFunc(rowId);
						break;
					default:
						break;
					}

				});
			})
		</script>
	</div>
</body>