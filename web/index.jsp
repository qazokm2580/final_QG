<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
123123
</body>
<meta http-equiv="Refresh" content="5; URL=https://4399.com/">
</html>
<div class="login-box-body">
  <p class="login-box-msg">登录系统</p>

  <form action="${pageContext.request.contextPath}/TeacherController?action=login" method="post">
    <div class="form-group has-feedback">
      <input type="text" class="form-control" placeholder="用户名" name="name"value="${cookie.name.value}">${error.name}
      <span class="glyphicon glyphicon-user form-control-feedback"></span>
    </div>
    <div class="form-group has-feedback">
      <input type="password" class="form-control" placeholder="密码" name="password"value="${cookie.password.value}">${error.password}
      <span class="glyphicon glyphicon-lock form-control-feedback"></span>
    </div>
    <div class="form-group has-feedback" >
      <input type="text" class="form-control" placeholder="验证码" style="width: 210px; float: left;" name="checkcode"> &nbsp
      <a style="cursor: hand" onclick="reload()"><img alt="加载中……" src="${pageContext.request.contextPath}/CheckCodeServlet" id="img" border="1"></a>
      <span style="color: red">${codefail}</span>
      <span style="color:red;">${fail}</span>
    </div>
    <div class="row">
      <div class="col-xs-8">
        <div class="checkbox icheck">
          <label><input type="checkbox"> 记住 下次自动登录</label>
        </div>
      </div>
      <!-- /.col -->
      <div class="col-xs-4">
        <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
      </div>
      <!-- /.col -->
    </div>
  </form>

  <!-- <div class="social-auth-links text-center">
      <p>- 或者 -</p>
      <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-qq"></i> 腾讯QQ用户登录</a>
      <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-weixin"></i> 微信用户登录</a>
  </div> -->
  <!-- /.social-auth-links -->

  <a href="#">忘记密码</a><br>
  <a href="${pageContext.request.contextPath}/register_teacher.jsp" class="text-center">教师注册</a>&nbsp&nbsp&nbsp
  <a href="${pageContext.request.contextPath}/register_student.jsp" class="text-center">学生注册</a>


</div>