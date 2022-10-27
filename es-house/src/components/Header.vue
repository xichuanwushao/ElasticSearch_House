<template>
  <div class="nav-wrap xunwu-header">
    <div class="wrapper ">
      <div class="fl">
        <a class="logo" href="/" title="寻屋" />
      </div>
      <div class="fr nav">
        <div class="fl">
          <ul>
            <li>
              <a class="index-page" href="/">首页</a>
            </li>
            <li>
              <a class="rent-page" href="/rent/house">租房</a>
            </li>
            <li>
              <a class="center-page" href="/user/center">个人中心</a>
            </li>
          </ul>
        </div>
        <div class="fr login">
          <div class="ti-hover">
            <div class="login-panel typeUserInfo">
              <div>
                <span class="welcome">
                  <router-link title="租房" to="/login">免注册登录</router-link>
                </span>
              </div>
              <div v-if="userName != null">
                <a href="/user/center">
                  <span> 用户名 </span>
                </a>
                <span id="indexTipContainer" />
                <span class="welcome">
                  <a class="reg" href="/logout" rel="nofollow">退出</a>
                </span>
              </div>
            </div>
            <div class="user-news" id="userNews">
              <!-- TODO 用户消息 -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      userName: "user"
    };
  }
};
</script>
<style lang="less" scoped>
.xunwu-header {
  height: 55px;
  line-height: 55px;
  background: #0054a5;
  color: #fff;
  .welcome {
    font-size: 12px;
  }
  .logo {
    display: block;
    height: 55px;
    background-image: url(../assets/img/logo_bg.jpg);
    width: 100px;
    background-repeat: no-repeat;
  }
  .nav {
    width: 900px;
    .login {
      .typeShowUser a {
        font-size: 14px;
      }
      .typeShowUser > a {
        margin: 0;
      }
      a {
        font-size: 16px;
        color: #fff;
        margin: 0 5px;
      }
    }
    ul {
      text-align: right;
      padding-top: 0;
      float: right;
      clear: both;
      margin-left: 20px;
      li {
        float: left;
        position: relative;
        a {
          font-size: 16px;
          color: #fff;
          padding: 0 10px;
          display: inline-block;
          text-decoration: none;
        }
        a.on {
          background: #008ea5;
        }
      }
      li.hover:hover {
        z-index: 1005;
        .nav-list {
          opacity: 1;
          position: absolute;
          display: block \9;
          pointer-events: auto;
          -webkit-transform: translateY(0) scale(1);
          -moz-transform: translateY(0) scale(1);
          -ms-transform: translateY(0) scale(1);
          -o-transform: translateY(0) scale(1);
          transform: translateY(0) scale(1);
          z-index: 99999;
        }
      }
      li.hover {
        .nav-list {
          width: 100px;
          position: absolute;
          height: 20px;
          left: 50%;
          margin-left: -38px;
          top: 42px;
          z-index: 99999;
          opacity: 0;
          display: none \9;
          -webkit-transition: all 500ms cubic-bezier(0.34, 1.21, 0.4, 1);
          -moz-transition: all 500ms cubic-bezier(0.34, 1.21, 0.4, 1);
          transition: all 500ms cubic-bezier(0.34, 1.21, 0.4, 1);
          -webkit-transform-origin: 50% 0;
          -moz-transform-origin: 50% 0;
          -ms-transform-origin: 50% 0;
          -o-transform-origin: 50% 0;
          transform-origin: 50% 0;
          -webkit-transform: translateY(0) scale(0);
          -moz-transform: translateY(0) scale(0);
          -ms-transform: translateY(0) scale(0);
          -o-transform: translateY(0) scale(0);
          transform: translateY(0) scale(0);
        }
        .nav-list-xinfang {
          top: 26px;
        }
        dd {
          position: absolute;
          margin-top: 13px;
          background: #f9f9f9;
          border-radius: 2px;
          width: 124px;
          left: -25px;
          padding: 10px 0;
          -webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
          -moz-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
          box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
          img {
            width: 100px;
            height: 100px;
            float: left;
            margin-left: 13px;
          }
          i {
            border-left: 10px solid transparent;
            border-right: 10px solid transparent;
            border-bottom: 10px solid #fff;
            width: 0;
            height: 0;
            display: block;
            position: absolute;
            left: 53px;
            top: -10px;
          }
          dl {
            height: 30px;
            line-height: 30px;
            a {
              color: #333;
              float: left;
              padding: 0;
              margin-left: 20px;
              font-size: 14px;
            }
          }
        }
      }
    }
    .ti-hover {
      position: relative;
      z-index: 99;
    }
    .ti-hover:hover .user-news ul {
      opacity: 1;
      filter: alpha(opacity=100);
      display: block \9;
      pointer-events: auto;
      -webkit-transform: translateY(0) scale(1);
      -moz-transform: translateY(0) scale(1);
      -ms-transform: translateY(0) scale(1);
      -o-transform: translateY(0) scale(1);
      transform: translateY(0) scale(1);
    }
    .typeUserInfo,
    .typeShowUser {
      position: relative;
      z-index: 2;
    }
    .typeUserInfo .login_bubble_tip,
    .typeShowUser .login_bubble_tip {
      background: #e2655c;
      font-size: 0.875em;
      padding: 1px 3px;
      margin-left: 5px;
      width: auto;
      display: inline;
      float: none;
    }
    .typeUserInfo a span,
    .typeShowUser a span {
      /*max-width: 45px;*/
      text-overflow: ellipsis;
      white-space: nowrap;
      overflow: hidden;
      float: left;
    }
    .typeUserInfo a span.reg,
    .typeShowUser a span.reg,
    .typeUserInfo a span.log,
    .typeShowUser a span.log {
      float: none;
      display: inline;
      position: relative;
      top: 1px;
    }
    .user-news {
      position: absolute;
      left: 0;
      right: 0;
      top: 0;
      bottom: 0;
      z-index: 1;
      color: #fff;
      cursor: pointer;
    }
    .user-news {
      .userNewsTriangle {
        border-left: 10px solid transparent;
        border-right: 10px solid transparent;
        border-bottom: 10px solid #fff;
        width: 0;
        height: 0;
        display: block;
        position: absolute;
        left: 53px;
        top: -10px;
      }
      i {
        display: inline-block;
        background: #e2655c;
        font-size: 0.875em;
        text-align: center;
        height: 11px;
        line-height: 11px;
        padding: 1px;
      }
      ul {
        width: 198px;
        background: #f9f9f9;
        border-radius: 2px;
        padding-top: 0;
        position: absolute;
        left: 50%;
        top: 50px;
        margin-left: -120px;
        filter: alpha(opacity=0);
        display: none \9;
        opacity: 0;
        -webkit-transition: all 500ms cubic-bezier(0.34, 1.21, 0.4, 1);
        -moz-transition: all 500ms cubic-bezier(0.34, 1.21, 0.4, 1);
        transition: all 500ms cubic-bezier(0.34, 1.21, 0.4, 1);
        -webkit-transform-origin: 50% 0;
        -moz-transform-origin: 50% 0;
        -ms-transform-origin: 50% 0;
        -o-transform-origin: 50% 0;
        transform-origin: 50% 0;
        -webkit-transform: translateY(0) scale(0);
        -moz-transform: translateY(0) scale(0);
        -ms-transform: translateY(0) scale(0);
        -o-transform: translateY(0) scale(0);
        transform: translateY(0) scale(0);
        padding: 10px 0;
        border: 1px solid #e4e4e4;
        li {
          clear: both;
          color: #999;
          height: 25px;
          font-size: 12px;
          line-height: 25px;
          text-align: center;
          width: 201px;
          a {
            color: #999;
            font-size: 12px;
            text-shadow: none;
            font-weight: normal;
            margin-left: 0;
            .unreadNumber {
              float: none;
              display: inline-block;
              font-size: 0.875em;
              text-align: center;
              height: 11px;
              line-height: 11px;
              padding: 1px;
              color: #f46a1f;
              font-weight: 700;
              margin: 0 3px;
            }
          }
        }
        li.s-li {
          height: 0;
          position: relative;
          left: 25px;
          top: -10px;
        }
      }
    }
    .user-news > i {
      position: absolute;
      top: 18px;
      right: 37px;
      padding: 0 5px;
      height: 16px;
      line-height: 16px;
      text-align: center;
      background-color: #e2655c;
      color: #fff;
    }
  }
  .logo_dl {
    width: 259px !important;
    display: block !important;
    height: 55px !important;
    background: url(/static/images/logo_bg.jpg) !important;
    background-repeat: no-repeat !important;
  }
  .nav_dl {
    width: 740px !important;
    ul {
      width: 604px !important;
    }
  }
}

@media only screen and (-webkit-min-device-pixel-ratio: 1.5),
  only screen and (min--moz-device-pixel-ratio: 1.5),
  only screen and (-o-min-device-pixel-ratio: 3/2),
  only screen and (min-device-pixel-ratio: 1.5) {
  .xunwu-header .logo {
    background: url(/static/images/logo_bg.jpg);
    background-size: 110px 55px;
  }
}
</style>
