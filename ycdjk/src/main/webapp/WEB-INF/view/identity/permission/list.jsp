<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pgc" value="${pageContext.request.contextPath}"></c:set>


<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>菜单管理</title>
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
			<!-- Main content -->
			<section class="content">

				<div class="row">
					<div class="col-md-3">

						<!-- Profile Image -->
						<div class="box box-primary">
							<div class="box-body box-profile">
								<div id="tree"></div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
					<div class="col-md-9">
						<div class="box box-primary">
							<div class="box-header with-border">
								<div class="btn-group">
									<button type="button" class="btn btn-default"
										data-btn-type="addRoot">
										<li class="fa fa-plus">&nbsp;新增根字典</li>
									</button>
									<button type="button" class="btn btn-default"
										data-btn-type="add">
										<li class="fa fa-plus">&nbsp;新增下级字典</li>
									</button>
									<button type="button" class="btn btn-default"
										data-btn-type="edit">
										<li class="fa fa-edit">&nbsp;编辑当前字典</li>
									</button>
									<button type="button" class="btn btn-default"
										data-btn-type="delete">
										<li class="fa fa-remove">&nbsp;删除当前字典</li>
									</button>
								</div>
								<!-- /.box-tools -->
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<form class="form-horizontal" id="function-form">
									<input type="hidden" name="parent_id" />
									<div class="form-group">
										<label for="parentName" class="col-sm-2 control-label">上级</label>

										<div class="col-sm-9">
											<input type="text" class="form-control" disabled="disabled"
												id="children"  placeholder="上级">
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-sm-2 control-label">名称</label>

										<div class="col-sm-9">
											<input type="text" class="form-control" id="text" name="text"
												placeholder="名称">
										</div>
									</div>

									<div class="form-group">
										<label for="levelCode" class="col-sm-2 control-label">层级编码</label>

										<div class="col-sm-9">
											<input type="text" class="form-control" id="orderNumber"
												name="orderNumber" placeholder="层级编码">
										</div>
									</div>
									<div class="form-group">
										<label for="url" class="col-sm-2 control-label">URL</label>

										<div class="col-sm-9">
											<input type="text" class="form-control" id="url" name="url"
												placeholder="URL">
										</div>
									</div>


									<div class="box-footer" style="display: none">
										<div class="text-center">
											<button type="button" class="btn btn-default"
												data-btn-type="cancel">
												<i class="fa fa-reply">&nbsp;取消</i>
											</button>
											<button type="submit" class="btn btn-primary">
												<i class="fa fa-save">&nbsp;保存</i>
											</button>
										</div>
									</div>
								</form>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /. box -->
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
	       //初始化form表单
			var form = null;
			var winId='iconWin';
			$(function() {

				form=$('#function-form').form();
				console.log(form);
				initTree(0);
				//初始化校验
				$('#function-form').bootstrapValidator({
					message : '请输入有效值',
					feedbackIcons : {
						valid : 'glyphicon glyphicon-ok',
						invalid : 'glyphicon glyphicon-remove',
						validating : 'glyphicon glyphicon-refresh'
					},
					submitHandler : function(validator, functionform, submitButton) {
						modals.confirm('确认保存？', function() {
							//Save Data，对应'submit-提交'
							var params = form.getFormSimpleData();
								$.post(basePath + '/permission/save', params, function(data, status) {
								if (data.state) {
									//var id=$("input[name='id']").val();
									var selectedArr=$("#tree").data("treeview").getSelected();
									var selectedNodeId=selectedArr.length>0?selectedArr[0].nodeId:0;
								    initTree(selectedNodeId);
								}
							},'json');
						});
					},
					fields : {
						name : {
							validators : {
								notEmpty : {
									message : '请输入名称'
								}
							}
						},
						code : {
							validators : {
								notEmpty : {
									message : '请输入编码'
								},
						        remote:{
						        	url:basePath+"/base/checkUnique",
						        	data: function(validator) {
			                            return {
			                                className:'com.cnpc.framework.base.entity.Function',
			                                fieldName:'code',
			                                fieldValue:$('#code').val(),
			                                id:$('#id').val()
			                            };
			                        },
						        	message:'该编码已被使用'
						        }
							}
						},
						levelCode : {
							validators : {
								notEmpty : {
									message : '请输入层级编码'
								}
							}
						},
						functype:{
							validators : {
								notEmpty : {
									message : '请选择菜单类型'
								}
							}
						},
						deleted : {
							validators : {
								notEmpty : {
									message : '请选择是否可用'
								}
							}
						}
					}
				});
				form.initComponent();
				//按钮事件
				var btntype=null;
				$('button[data-btn-type]').click(function() {
					var action = $(this).attr('data-btn-type');
					var selectedArr=$("#tree").data("treeview").getSelected();
					var selectedNode=selectedArr.length>0?selectedArr[0]:null;
					alert(selectedNode);
					switch (action) {
					case 'addRoot':
						formWritable(action);
						form.clearForm();
						$("#icon_i").removeClass();
						//填充上级菜单和层级编码
						fillParentAndLevelCode(null);
						btntype='add';
						break;
					case 'add':
						if(!selectedNode){
							modals.info('请先选择上级菜单');
							return false;
						}
						formWritable(action);
						form.clearForm();
						$("#icon_i").removeClass();
						//填充上级菜单和层级编码
						fillParentAndLevelCode(selectedNode);
						btntype='add';
						break;
					case 'edit':
						if(!selectedNode){
							modals.info('请先选择要编辑的节点');
							return false;
						}
						if(btntype=='add'){
							fillDictForm(selectedNode);
						}
						formWritable(action);
						btntype='edit';
						break;
					case 'delete':
						if(!selectedNode){
							modals.info('请先选择要删除的节点');
							return false;
						}
						if(btntype=='add')
							fillDictForm(selectedNode);
						formReadonly();
						$(".box-header button[data-btn-type='delete']").removeClass("btn-default").addClass("btn-primary");
					    if(selectedNode.nodes){
					    	modals.info('该节点含有子节点，请先删除子节点');
					    	return false;
					    }
					    modals.confirm('是否删除该节点',function(){
					    	ajaxPost(basePath+"/permission/delete/"+selectedNode.id,null,function(data){
					    		if(data.success){
					    		   modals.correct('删除成功');
					    		}else{
					    			modals.info(data.message);
					    		}
					    		//定位
					    		var brothers=$("#tree").data("treeview").getSiblings(selectedNode);
					    		if(brothers.length>0)
					    		   initTree(brothers[brothers.length-1].nodeId);
					    		else{
					    		   var parent=$("#tree").data("treeview").getParent(selectedNode);
					    		   initTree(parent?parent.nodeId:0);
					    		}
					    	});
					    });
						break;
					case 'cancel':
						if(btntype=='add')
							fillDictForm(selectedNode);
						formReadonly();
						break;
					case 'selectIcon':
						var disabled=$(this).hasClass("disabled");
				        if(disabled)
				        	break;
						var iconName;
						if($("#icon").val())
						   iconName=encodeURIComponent($("#icon").val());
						modals.openWin({
                        	winId:winId,
                        	title:'图标选择器（双击选择）',
                        	width:'1000px',
                        	url:basePath+"/icon/nodecorator/select?iconName="+iconName
                        });
						break;
					}
				});
			})

			function initTree(selectNodeId){
				var treeData = null;
				$.post(basePath + "/permission/treedata", null, function(data) {
					if (data.nodes == null) {
						delete data.nodes;	
					}
					treeData = data;
					console.log(JSON.stringify(treeData));
					$("#tree").treeview({
						data : treeData,
						showBorder : true,
						expandIcon : "glyphicon glyphicon-stop",
						collapseIcon : "glyphicon glyphicon-unchecked",
						levels : 1,
						onNodeSelected : function(event, data) {
							/*   alert("i am selected");
							  alert(data.nodeId); */
							fillDictForm(data);
							formReadonly();
							//console.log(JSON.stringify(data));
						}
					});
					if(treeData.length==0)
						return;
					//默认选中第一个节点
					selectNodeId=selectNodeId||0;
					$("#tree").data('treeview').selectNode(selectNodeId);
					$("#tree").data('treeview').expandNode(selectNodeId);
					$("#tree").data('treeview').revealNode(selectNodeId);
				},'json');
				
			}

			//新增时，带入父级菜单名称id,自动生成levelcode
			function fillParentAndLevelCode(selectedNode){
				$("input[name='parent.text']").val(selectedNode?selectedNode.text:'系统菜单');
			    $("input[name='deleted'][value='0']").prop("checked","checked");
			    if(selectedNode){
			    	$("input[name='parent_id']").val(selectedNode.id);
					// 子菜单
			    	var nodes=selectedNode.nodes;
					
					var levelCode;
					if (nodes.length==0) {
						levelCode = selectedNode?selectedNode.orderNumber:null;
					}else{
						levelCode=nodes?nodes[nodes.length-1].orderNumber:null;
					}
					$("input[name='orderNumber']").val(getNextCode(selectedNode.orderNumber,levelCode,6));
			    }else{
					var parentNode=$("#tree").data("treeview").getNode(0);
					var levelCode = "000000";
					if (parentNode) {
						var brothers = $("#tree").data("treeview").getSiblings(0);
						levelCode = brothers[brothers.length - 1].orderNumber;
					}
					$("input[name='orderNumber']").val(getNextCode("", levelCode, 6));
			    }
			}

			//填充form
			function fillDictForm(node){
				form.clearForm();
				$.post(basePath+"/permission/get/"+node.id,null,function(data){
					form.initFormData(data);
					
					if (data.parent == "" || data.parent ==null) {
						$('#children').val("系统菜单");
						
					}else
					{
						
						$('#children').val(data.parent.text)
						$('input[name="parent"]').val(data.parent.id);
					}
					
				},'json')
			}

			//设置form为只读
			function formReadonly(){
				//所有文本框只读
				$("input[name],textarea[name]").attr("readonly","readonly");
				//隐藏取消、保存按钮
				$("#function-form .box-footer").hide();
				//还原新增、编辑、删除按钮样式
				$(".box-header button").removeClass("btn-primary").addClass("btn-default");
				//选择图标按钮只读
				$("#selectIcon").addClass("disabled");
				//还原校验框
				if($("function-form").data('bootstrapValidator'))
					$("function-form").data('bootstrapValidator').resetForm();
			}

			function formWritable(action){
				$("input[name],textarea[name]").removeAttr("readonly");
				$("#function-form .box-footer").show();
				$(".box-header button").removeClass("btn-primary").addClass("btn-default");
				$("#selectIcon").removeClass("disabled");
				if(action)
					$(".box-header button[data-btn-type='"+action+"']").removeClass("btn-default").addClass("btn-primary");
			}

			//回填图标
			function fillBackIconName(icon_name){
				$("#icon").val(icon_name);
				$("#icon_i").removeClass().addClass("form-control-feedback").addClass(icon_name);
			}

		</script>