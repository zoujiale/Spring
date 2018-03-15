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
                        <button type="button" class="btn btn-default" data-btn-type="addRoot">
                            <li class="fa fa-plus">&nbsp;新增根字典</li>
                        </button>
                        <button type="button" class="btn btn-default" data-btn-type="add">
                            <li class="fa fa-plus">&nbsp;新增下级字典</li>
                        </button>
                        <button type="button" class="btn btn-default" data-btn-type="edit">
                            <li class="fa fa-edit">&nbsp;编辑当前字典</li>
                        </button>
                        <button type="button" class="btn btn-default" data-btn-type="delete">
                            <li class="fa fa-remove">&nbsp;删除当前字典</li>
                        </button>
                    </div>
                    <!-- /.box-tools -->
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <form class="form-horizontal" id="dict-form">
                        <input type="hidden" name="parentId"/>
                        <div class="form-group">
                            <label for="parentName" class="col-sm-2 control-label">上级</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" disabled="disabled" id="parentName"
                                       name="parentName" placeholder="上级">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">名称</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="name" name="name" placeholder="名称">
                            </div>
                        </div>
                      
                        <div class="form-group">
                            <label for="levelCode" class="col-sm-2 control-label">层级编码</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="levelCode" name="levelCode"
                                       placeholder="层级编码">
                            </div>
                        </div>
                       
                       
                        <div class="form-group">
                            <label for="remark" class="col-sm-2 control-label">说明</label>

                            <div class="col-sm-9">
                                <textarea class="form-control" id="remark" name="remark" placeholder="说明"></textarea>
                            </div>
                        </div>
                        <div class="box-footer" style="display:none">
                            <div class="text-center">
                                <button type="button" class="btn btn-default" data-btn-type="cancel">
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
    var form = null;
   
    $(function () {
        //初始化form表单
        form = $("#dict-form").form();

        initTree(1);
        //初始化校验
        $("#dict-form").bootstrapValidator({
            message: '请输入有效值',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            submitHandler: function (validator, dictform, submitButton) {
                modals.confirm('确认保存？', function () {
                    //Save Data，对应'submit-提交'
                    var params = form.getFormSimpleData();
                    ajaxPost(basePath + '/dict/save', params, function (data, status) {
                        if (data.success) {
                            //var id=$("input[name='id']").val();
                            var selectedArr = $("#tree").data("treeview").getSelected();
                            var selectedNodeId = selectedArr.length > 0 ? selectedArr[0].nodeId : 0;
                            initTree(selectedNodeId);
                        }
                    });
                });
            },
            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: '请输入名称'
                        }
                    }
                },
                code: {
                    validators: {
                        notEmpty: {
                            message: '请输入编码'
                        },
                        remote: {
                            url: basePath + "/base/checkUnique",
                            data: function (validator) {
                                return {
                                    className: 'com.cnpc.framework.base.entity.Dict',
                                    fieldName: 'code',
                                    fieldValue: $('#code').val(),
                                    id: $('#id').val()
                                };
                            },
                            message: '该编码已被使用'
                        }
                    }
                },
                levelCode: {
                    validators: {
                        notEmpty: {
                            message: '请输入层级编码'
                        }
                    }
                },
                deleted: {
                    validators: {
                        notEmpty: {
                            message: '请选择是否可用'
                        }
                    }
                }
            }
        });

        form.initComponent();
        //按钮事件
        var btntype = null;
        $('button[data-btn-type]').click(function () {
            var action = $(this).attr('data-btn-type');
            var selectedArr = $("#tree").data("treeview").getSelected();
            var selectedNode = selectedArr.length > 0 ? selectedArr[0] : null;
            switch (action) {
                case 'addRoot':
                    formWritable(action);
                    form.clearForm();
                    //填充上级字典和层级编码
                    fillParentAndLevelCode(null);
                    btntype = 'add';
                    break;
                case 'add':
                    if (!selectedNode) {
                        modals.info('请先选择上级字典');
                        return false;
                    }
                    formWritable(action);
                    form.clearForm();
                    //填充上级字典和层级编码
                    fillParentAndLevelCode(selectedNode);
                    btntype = 'add';
                    break;
                case 'edit':
                    if (!selectedNode) {
                        modals.info('请先选择要编辑的节点');
                        return false;
                    }
                    if (btntype == 'add') {
                        fillDictForm(selectedNode);
                    }
                    formWritable(action);
                    btntype = 'edit';
                    break;
                case 'delete':
                    if (!selectedNode) {
                        modals.info('请先选择要删除的节点');
                        return false;
                    }
                    if (btntype == 'add')
                        fillDictForm(selectedNode);
                    formReadonly();
                    $(".box-header button[data-btn-type='delete']").removeClass("btn-default").addClass("btn-primary");
                    if (selectedNode.nodes) {
                        modals.info('该节点含有子节点，请先删除子节点');
                        return false;
                    }
                    modals.confirm('是否删除该节点', function () {
                        ajaxPost(basePath + "/dict/delete/" + selectedNode.id, null, function (data) {
                            if (data.success) {
                                modals.correct('删除成功');
                            } else {
                                modals.info(data.message);
                            }
                            //定位
                            var brothers = $("#tree").data("treeview").getSiblings(selectedNode);
                            if (brothers.length > 0)
                                initTree(brothers[brothers.length - 1].nodeId);
                            else {
                                var parent = $("#tree").data("treeview").getParent(selectedNode);
                                initTree(parent ? parent.nodeId : 0);
                            }
                        });
                    });
                    break;
                case 'cancel':
                    if (btntype == 'add')
                        fillDictForm(selectedNode);
                    formReadonly();
                    break;
            }
        });
    })

    function initTree(selectNodeId) {
    	var treeData = null;
        $.post(basePath+"/permission/treedata", null, function (data) {
            
            $('#tree').treeview({
          	  data: data,         // data is not optional
          	  levels: 1,
          	});  
        });
      
    }

    //新增时，带入父级字典名称id,自动生成levelcode
    function fillParentAndLevelCode(selectedNode) {
        $("input[name='parentName']").val(selectedNode ? selectedNode.text : '系统字典');
        $("input[name='deleted'][value='0']").prop("checked", "checked");
        if (selectedNode) {
            $("input[name='parentId']").val(selectedNode.id);
            var nodes = selectedNode.nodes;
            alert(nodes);
            var levelCode = nodes ? nodes[nodes.length - 1].orderNumber : null;
            $("input[name='levelCode']").val(getNextCode(selectedNode.levelCode, levelCode, 6));
        } else {
            var parentNode = $("#tree").data("treeview").getNode(0);
            var levelCode = "00000";
            if (parentNode) {
                var brothers = $("#tree").data("treeview").getSiblings(0);
                levelCode = brothers[brothers.length - 1].orderNumber;
            }
            $("input[name='levelCode']").val(getNextCode("", levelCode, 6));
        }
    }

    //填充form
    function fillDictForm(node) {
        form.clearForm();
        	$.post( basePath+"/permission/get/" + node.id, null, function (data) {
            form.initFormData(data);
        })
    }

    //设置form为只读
    function formReadonly() {
        //所有文本框只读
        $("input[name],textarea[name]").attr("readonly", "readonly");
        //隐藏取消、保存按钮
        $("#dict-form .box-footer").hide();
        //还原新增、编辑、删除按钮样式
        $(".box-header button").removeClass("btn-primary").addClass("btn-default");
        //还原校验框
        if ($("#dict-form").data('bootstrapValidator'))
            $("#dict-form").data('bootstrapValidator').resetForm();
    }

    function formWritable(action) {
        $("input[name],textarea[name]").removeAttr("readonly");
        $("#dict-form .box-footer").show();
        $(".box-header button").removeClass("btn-primary").addClass("btn-default");
        if (action)
            $(".box-header button[data-btn-type='" + action + "']").removeClass("btn-default").addClass("btn-primary");
    }


</script>
