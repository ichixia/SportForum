<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 弹出对话框 -->
<div class="modal fade" id="adminModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">登录/注册</h4>
      </div>
      <div class="modal-body">
        <input type="text" id="admin_name" maxlength="20"  class="form-control" placeholder="管理员名"><br>
        <input type="password" id="a_password" maxlength="20"  class="form-control" placeholder="密码"><br>
        <input type="text" id="aid_num" maxlength="20"  class="form-control" placeholder="身份证号"><br>
        <input type="text" id="authorization_code" maxlength="20"  class="form-control" placeholder="授权码">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" onclick="admin_regist()">注册</button>
        <button type="button" class="btn btn-primary" onclick="admin_login()">登录</button>
      </div>
    </div>
  </div>
</div>