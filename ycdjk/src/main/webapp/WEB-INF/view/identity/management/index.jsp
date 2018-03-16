<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pgc" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>用户管理</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="/WEB-INF/view/commons/main-css.jsp"></jsp:include>

<link rel="stylesheet"
	href="${pgc }/adminlte/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet"
	href="${pgc }/adminlte/plugins/datatables/select.bootstrap.min.css">
<link rel="stylesheet"
	href="${pgc }/adminlte/plugins/bootstrapvalidator/dist/css/bootstrap-validator.css">


<link rel="stylesheet"
	href="${pgc }/adminlte/plugins/bootstrap-treeview/bootstrap-treeview.min.css">
<link rel="stylesheet" href="${pgc }/adminlte/common/css/base.css">

<link rel="stylesheet"
	href="${pgc }/adminlte/plugins/datepicker/datepicker3.css">
<link rel="stylesheet"
	href="${pgc }/adminlte/plugins/select2/select2.min.css">
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
				<h1>
					用户管理 <small>列表</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href=""><i class="fa fa-dashboard"></i> 首页</a></li>
					<li><a href="#">系统管理</a></li>
					<li class="active">用户管理</li>
				</ol>
			</section>
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="nav-tabs-custom">
							<ul class="nav nav-tabs pull-right">
								<li class=""><a href="#tab-content-edit" data-toggle="tab"
									id="nav-tab-edit" aria-expanded="false"><i
										class="fa fa-edit"></i></a></li>
								<li class="active"><a href="#tab-content-list"
									data-toggle="tab" id="nav-tab-list" aria-expanded="true"><i
										class="fa fa-list-ul"></i></a></li>
								<li class="pull-left header"><i class="fa fa-user"></i><small>用户列表</small></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="tab-content-list">
									<div class="box">
										<!-- /.box-header -->
										<div class="dataTables_filter" id="searchDiv">
											<input type="hidden" id="uid">
											<input placeholder="请输入姓名" id="name" name="name"
												class="form-control" type="search" likeoption="true">
											<input placeholder="请输入登录名" name="loginName" id="loginName"
												class="form-control" type="search" likeoption="true">
											<div class="btn-group">
												<button type="button" class="btn btn-primary"
													data-btn-type="search">查询</button>
												<button type="button" class="btn btn-default"
													data-btn-type="reset">重置</button>
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
											<div id="user_wrapper"
												class="dataTables_wrapper form-inline dt-bootstrap no-footer">

												<table id="user_table"
													class="table table-border dataTable no-footer">
													<thead>
													</thead>
												</table>

											</div>
										</div>
										<!-- /.box-body -->
									</div>
									<!-- /.box -->
								</div>
								<!-- /.tab-pane -->
								<div class="tab-pane" id="tab-content-edit">
									<div class="box box-info">
										<form id="user-form" name="user-form"
											class="form-horizontal bv-form" novalidate="novalidate">
											<input type="hidden" name="id">
											<div class="box-body">
												<div class="col-md-6">
													<div class="form-group has-feedback">
														<label for="name" class="col-sm-3 control-label">姓名</label>
														<div class="col-sm-8">
															<input type="text" class="form-control" id="name"
																name="userName" placeholder="姓名" data-bv-field="name"><i
																class="form-control-feedback" data-bv-icon-for="name"
																style="display: none;"></i> <small
																data-bv-validator="notEmpty"
																data-bv-validator-for="name" class="help-block"
																style="display: none;">请输入姓名</small>
														</div>
													</div>
													<div class="form-group has-feedback">
														<label for="password" class="col-sm-3 control-label">密码</label>

														<div class="col-sm-8">
															<input type="password" class="form-control" id="password" name="password" placeholder="登录名" data-bv-field="loginName">
															<small data-bv-validator="notEmpty" data-bv-validator-for="loginName" class="help-block" style="display: none;">请输入登录名</small>
														<small data-bv-validator="notEmpty" data-bv-validator-for="loginName" class="help-block" style="display: none;">请输入登录名</small></div>
													</div>
													<div class="form-group has-feedback">
														<label for="birthday" class="col-sm-3 control-label">出生日期</label>
														<div class="input-group date col-sm-8">
															<div class="input-group-addon">
																<i class="fa fa-calendar"></i>
															</div>
															<input type="text" class="form-control"
																data-flag="datepicker" data-format="yyyy-mm-dd"
																id="birthday" name="birthdate" placeholder="出生日期"
																data-bv-field="birthday"><i
																class="form-control-feedback"
																data-bv-icon-for="birthday" style="display: none;"></i>
															<small data-bv-validator="notEmpty"
																data-bv-validator-for="birthday" class="help-block"
																style="display: none;">请输入出生日期</small><small
																data-bv-validator="date"
																data-bv-validator-for="birthday" class="help-block"
																style="display: none;">请输入有效日期</small>
														</div>
													</div>

													<div class="form-group has-feedback">
														<label for="email" class="col-sm-3 control-label">Email</label>

														<div class="col-sm-8">
															<input type="text" class="form-control" id="email"
																name="email" placeholder="Email" data-bv-field="email"><i
																class="form-control-feedback" data-bv-icon-for="email"
																style="display: none;"></i> <small
																data-bv-validator="notEmpty"
																data-bv-validator-for="email" class="help-block"
																style="display: none;">请输入邮件</small><small
																data-bv-validator="emailAddress"
																data-bv-validator-for="email" class="help-block"
																style="display: none;">非法的邮件格式</small>
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group has-feedback">
														<label class="col-sm-3 control-label">性别</label>

														<div class="col-sm-8">
															<label class="control-label">
																<div class="iradio_square-green">
																	<input type="radio" name="sex" data-flag="icheck"
																		class="square-green" value="MALE" data-bv-field="sex"
																		style="position: absolute; opacity: 0;">
																	<ins class="iCheck-helper"
																		style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
																</div> 男
															</label> &nbsp; <label class="control-label">
																<div class="iradio_square-green" aria-checked="false"
																	aria-disabled="false" style="position: relative;">
																	<input type="radio" name="sex" data-flag="icheck"
																		class="square-green" value="FEMALE"
																		data-bv-field="sex"
																		style="position: absolute; opacity: 0;">
																	<ins class="iCheck-helper"
																		style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
																</div> <i class="form-control-feedback" data-bv-icon-for="sex"
																style="display: none;"></i> 女
															</label> <small data-bv-validator="notEmpty"
																data-bv-validator-for="sex" class="help-block"
																style="display: none;">请选择性别</small>
														</div>
													</div>
													<div class="form-group has-feedback">
														<label for="loginName" class="col-sm-3 control-label">登录名</label>

														<div class="col-sm-8">
															<input type="text" class="form-control" id="loginName"
																name="loginName" placeholder="登录名"
																data-bv-field="loginName"><i
																class="form-control-feedback"
																data-bv-icon-for="loginName" style="display: none;"></i>
															<small data-bv-validator="notEmpty"
																data-bv-validator-for="loginName" class="help-block"
																style="display: none;">请输入登录名</small>
														</div>
													</div>
													<div class="form-group">
														<label for="mobile" class="col-sm-3 control-label">手机</label>

														<div class="col-sm-8">
															<!--<input type="text" class="form-control" id="mobile" name="mobile" placeholder="手机"
                                                                                   data-inputmask='"mask": "9999999999999"' data-mask>-->
															<input type="text" class="form-control" id="mobile"
																name="phoneNumber" placeholder="手机">
														</div>
													</div>
													<div class="form-group">
														<label for="qq" class="col-sm-3 control-label">QQ</label>
														<div class="col-sm-8">
															<input type="text" class="form-control" id="qq" name="qq"
																placeholder="QQ">
														</div>
													</div>
												</div>
											</div>
											<!-- /.box-body -->
											<div class="box-footer text-right" style="height: 50px;">
												<!--以下两种方式提交验证,根据所需选择-->
												<button type="button" class="btn btn-default"
													data-btn-type="cancel">取消</button>
												<button type="submit" class="btn btn-primary"
													data-btn-type="save">提交</button>
											</div>
											<!-- /.box-footer -->
											<input type="hidden" value="">
										</form>
									</div>
									<!-- /.box -->
								</div>
								<!-- /.tab-pane -->
							</div>
							<!-- /.tab-content -->
						</div>
						<!-- nav-tabs-custom -->
					</div>
					<!-- /.col -->
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
	<!-- DataTables -->
	<script
		src="${pgc }/adminlte/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="${pgc }/adminlte/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script src="${pgc}/adminlte/common/js/dataTables.js"></script>
	<script
		src="${pgc}/adminlte/plugins/bootstrapvalidator/dist/js/bootstrap-validator.js"></script>
	<script src="${pgc }/adminlte/common/js/base.js"></script>
	<script src="${pgc }/adminlte/common/js/base-form.js"></script>
	<script src="${pgc }/adminlte/plugins/select2/select2.full.min.js"></script>
	<script type="text/javascript"
		src="${pgc }/adminlte/common/js/base-modal.js"></script>
	<!-- 日期 -->
	<script
		src="${pgc }/adminlte/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script type="text/javascript">
	//表单验证根据所需进行配置,在此只进行简单验证
    var form = $("#user-form").form();
	
    $("#user-form").bootstrapValidator({
        message: '请输入有效值',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function(validator, userform, submitButton) {
            modals.confirm('确认保存？', function() {
                //Save Data，对应'submit-保存'
                var paarams = form.getFormSimpleData();
				console.log(paarams);
				 $.post('${pgc}/identity/management/save', {'paarams': JSON.stringify(paarams)}, function(data) {
                    //提示以及其他逻辑
                    var list=eval("("+data+")");
					if (list['500']) {
						alert(data[500]);
						return;
					}
					usertable.draw();
                    $('#nav-tab-list').click();
                    
                });
            });
        },
        fields: {
            name: {
                validators: {
                    notEmpty: { message: '请输入姓名' }
                }
            },
            sex: {
                validators: { notEmpty: { message: '请选择性别' } }
            },
            birthday: {
                validators: {
                    notEmpty: { message: '请输入出生日期' },
                    date: { format: 'YYYY/DD/MM' },
                    callback: {
                        message: '请输入有效日期',
                        callback: function(value, validator) {
                            var res = new Date(value)!='Invalid Date';
                            if (res) validator.updateStatus('birthday', 'VALID');

                            return res;
                        }
                    }
                }
            },
            loginName: {
                validators: {
                    notEmpty: { message: '请输入登录名' }
                }
            }
        }
    });
    form.initComponent();
	
	
	
	
		var usertable = $('#user_table').DataTable({
				 "paging": true, //分页
                 "lengthChange": false, //每页记录数可选项
                 "searching": false, //过滤
                 "ordering": true, //排序
                 "info": true, //分页明细
                 "autoWidth": true,
                 "language": { //中文支持
                     "sUrl":  "${pgc}/adminlte/common/json/zh_CN.json"
                 },
                 "processing": true,
                 "serverSide": true,
                 "bProcessing": true,// 是否显示取数据时的那个等待提示
                 "sPaginationType": "full_numbers",//分页样式
                 "displayLength": 10,//每页记录条数，默认为10
                 "bServerSide": true,
                 "sAjaxSource":"${pgc}/identity/management/loadData",
                 "sAjaxDataProp": "data",
                 "fnServerData": loadData, // 获取数据的处理函数
                 'rowId': 'id',
                 "singleSelect": true,  //单选
                 "columns": [
                	 {"sTitle":"序号","sClass": "dt-center", "bSortable" :false,"data": null, 'sClass': 'text-center'},
                	 {'data': 'userName', 'sTitle': '姓名', 'sName': 'name', 'sClass': 'text-center'},
                	 {'data': 'qq', 'sTitle': 'qq', 'sName': 'qq', 'sClass': 'text-center'},
                     {'data': 'sex', 'sTitle': '性别', 'sName': 'sex', 'sClass': 'text-center'},
                     {'data': 'loginName', 'sTitle': '登录名', 'sName': 'loginName', 'sClass': 'text-center'},
                     {'data': 'email', 'sTitle': '邮箱', 'sName': 'email', 'sClass': 'text-center'},
                     {'data': 'phoneNumber', 'sTitle': '手机', 'sName': 'mobile', 'sClass': 'text-center'},
                     {'data': 'birthdate', 'sTitle': '出生日期', 'sName': 'birthdate', 'sClass': 'text-center','format': 'YYYY/DD/MM'},
                     
                 ],
               
      		   "fnDrawCallback"    : function(){
      	        　　this.api().column(0).nodes().each(function(cell, i) {
      	        　　　　cell.innerHTML =  i + 1;
      	        　　});
      	        }
                 
			});

			
		 function loadData(sSource, aoData, fnCallback) {
                var map = getMap(aoData);
            
                var pageInfo = {};
                pageInfo.pageSize = map.iDisplayLength;
                pageInfo.pageNum = map.iDisplayStart % map.iDisplayLength == 0 ? map.iDisplayStart / map.iDisplayLength + 1 : map.iDisplayStart / map.iDisplayLength;
                
                $.ajax({
                    url: sSource,//这个就是请求地址对应sAjaxSource
                    data: {"pInfo": JSON.stringify(pageInfo),'name':$("#name").val(),'loginName':$('#loginName').val()},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
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
		
		$('#user_table tbody').on( 'click', 'tr', function () {
			  var rowData = usertable.row( this ).data();
			  if ( !$(this).hasClass('selected')) {
				  $('tr.selected').removeClass('selected');
                  $(this).addClass('selected');
                  $("#uid").val(rowData.id);
                  
				}else
				{	
					 $(this).toggleClass('selected');
				}
				 	
				 
			} );
		
		
		   //根据Button
	    $('button[data-btn-type]').click(function() {
	        var action = $(this).attr('data-btn-type');
	        switch (action) {
	            case 'search':
	            	usertable.draw();
	                break;
	            case 'add':
	                form.clearForm();
	                $('#nav-tab-edit').click();
	                break;
	            case 'edit':
	            	if (!$('#user_table tbody tr').hasClass('selected')) {
	            		modals.info("请选择要编辑的行")
	            		return 
					}
	                var uid = $("#uid").val();
	                $.post('${pgc}/identity/management/edit', { 'id': uid }, function(data) {
	                	 var list=eval("("+data+")");
	                	 delete list["roles"];
	                	 console.log(list);
	                	form.initFormData(list);
	                    $('#nav-tab-edit').click(); 
	                });
	                break;
	            case 'delete':
	                var uid = $("#uid").val();
	                modals.confirm('确认删除？', function() {
	                	 $.post('${pgc}/identity/management/delete', { 'id': uid }, function(data) {
	                		 var list=eval("("+data+")");
	                		 modals.info(list['message']);
	 	                });
	                   
	                });
	            
	                break;
	           
	            case 'reset':
	                //Clear Form
	                form.clearForm();
	                $('#nav-tab-list').click();
	                break;
	                
	            case 'cancel':
                    //Clear Form
                    form.clearForm();
                    $('#nav-tab-list').click();
                    break;
	        }

	    });
		

	</script>



</body>
</html>
