<template>
  <div class="login-form form-bg" style="padding: 90px 0;">
    <div class="container">
      <div class="row">
        <div class="col-md-offset-3 col-md-6">
          <form
            class="form-horizontal"
            action="#"
            th:action="@{/login}"
            method="post"
          >
            <div class="heading">
              <a
                ><span class="select" data-bind="phone"
                  >免密登录&nbsp;&nbsp;&nbsp;</span
                ></a
              >|
              <a
                ><span class="" data-bind="password"
                  >&nbsp;&nbsp;&nbsp;密码登录</span
                ></a
              >
            </div>
            <span class="error-message" style="color: red;"> </span>
            <div class="phone-panel">
              <div class="form-group">
                <input
                  name="telephone"
                  type="tel"
                  class="form-control phone"
                  id="phone"
                  placeholder="手机号"
                />
                <i class="fa fa-user"></i>
              </div>
              <div class="form-group help">
                <input
                  name="smsCode"
                  type="text"
                  class="form-control password"
                  id="validateCode"
                  placeholder="6位验证码"
                />
                <i class="fa fa-lock"></i>
                <div id="captcha" v-on:click="getCaptchaImg()">
                  <window :show.sync="showFlag" />
                  验证码
                </div>
                <!--<a href="#" class="fa fa-question-circle"></a>-->
                <button
                  id="smsCodeBtn"
                  type="button"
                  class="valide-code btn btn-default"
                >
                  获取验证码
                </button>
              </div>
            </div>
            <div class="password-panel" style="display: none;">
              <!--<span class="heading">免注册登录</span>-->
              <div class="form-group">
                <input
                  name="username"
                  type="text"
                  class="form-control username"
                  id="nameOrPhone"
                  placeholder="用户名/手机号"
                />
                <i class="fa fa-user"></i>
              </div>
              <div class="form-group help">
                <input
                  name="password"
                  type="password"
                  class="form-control code"
                  id="password"
                  placeholder="密码"
                />
                <i class="fa fa-lock"></i>
                <!--<a href="#" class="fa fa-question-circle"></a>-->
              </div>
            </div>
            <div class="form-group">
              <!--<div class="main-checkbox">-->
              <!--<input type="checkbox" value="None" id="checkbox1" name="check"/>-->
              <!--<label for="checkbox1"></label>-->
              <!--</div>-->
              <!--<span class="text">Remember me</span>-->
              <button id="submit-btn" type="submit" class="btn btn-default">
                登录
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import $http from "vue-resource";
// import window from "./window";
// import http from "../assets/js/http";
export default {
  data() {
    return {
      showFlag: false
    };
  },
  methods: {
    getCaptchaImg: function() {
      this.$http.get("http://localhost:1080/createImgValidate").then(function(data) {
        console.log("请求");
      });
    }
  },
  components: {
    window
  }
};
</script>

<style lang="css" scoped>
@import "../assets/css/main.css";
@import "../assets/css/common.css";
@import "../assets/css/user/login.css";
</style>
