<template>
  <div class="footer">
    <div class="wrapper">
      <div class="f-title">
        <div class="fl">
          <ul>
            <li><a href="/" rel="nofollow" target="_blank">首页</a></li>
            <li><a href="/" rel="nofollow" target="_blank">了解寻屋</a></li>
            <li><a href="/" rel="nofollow" target="_blank">关于寻屋</a></li>
            <li><a href="/" rel="nofollow" target="_blank">联系客服</a></li>
            <li>
              <a href="/admin/login" rel="nofollow" target="_blank"
                >管理员登录</a
              >
            </li>
          </ul>
        </div>
        <div class="fr">官方客服 xxxx-xxxx-xxxx</div>
      </div>
      <div class="clear"></div>
    </div>
    <div class="bottom">
      <div class="copyright fl">
        Copyright &copy; 2020. All rights reserved.
      </div>
    </div>
  </div>
</template>
<script>
export default {};
</script>
<style lang="less" scoped>
.footer {
  background: url(/static/images/footer-bg.jpg) no-repeat center top #222;
  clear: both;
  /*width: 100%;*/
  height: 150px;
  margin-top: 45px;
  /*position:fixed;*/
  /*bottom: 0;*/
  .f-title {
    height: 75px;
    border-bottom: 1px solid #393939;
    line-height: 75px;
    margin: 0;
    .fr {
      color: #7dcfa0;
    }
    .fl {
      li {
        float: left;
        a {
          color: #fff;
          margin-right: 15px;
          font-size: 14px;
        }
        a:hover {
          text-decoration: none;
        }
      }
      li.nav_f {
        position: relative;
        text-align: left;
      }
      li.nav_f:hover .sitemap-quick {
        display: block;
      }
      li.nav_f {
        .sitemap-quick {
          display: none;
          width: 200px;
          position: absolute;
          background: rgba(71, 78, 88, 0.95);
          padding: 15px;
          line-height: 20px;
          -webkit-border-radius: 3px;
          -moz-border-radius: 3px;
          border-radius: 3px;
          right: -70px;
          top: 49px;
          z-index: 999;
          box-shadow: 0 2px 5px rgba(0, 0, 0, 0.25);
          background: #333 \0;
          dd a {
            height: auto;
            margin: 5px 0;
            color: #dbece1;
          }
          p {
            color: #fff3be;
            font-size: 14px;
            font-weight: 700;
          }
          .nav_area {
            clear: both;
            width: 200px;
            border-bottom: 1px solid #777;
            margin-bottom: 10px;
            padding-bottom: 10px;
          }
          .last {
            border: none;
            margin: 0;
            padding: 0;
          }
          a {
            color: #ddd;
            font-size: 12px;
            margin-right: 9px;
            height: 20px;
            line-height: 20px;
            padding: 0;
            float: none;
            display: inline-block;
          }
        }
      }
    }
  }
  .wrapper {
    width: 1150px;
  }
  .xunwu-link-box {
    padding: 13px 0 20px 0;
    border-bottom: 1px solid #393939;
    background: none;
    .fl {
      width: 1000px;
      .tab {
        height: 40px;
        span {
          display: inline-block;
          padding: 8px 10px;
          background: #121212;
          color: #888c8e;
          float: left;
          margin-right: 12px;
          margin-bottom: 12px;
          cursor: pointer;
          font-size: 12px;
          -webkit-transition: all 0.3s ease-in-out;
          -moz-transition: all 0.3s ease-in-out;
          -o-transition: all 0.3s ease-in-out;
          -ms-transition: all 0.3s ease-in-out;
          transition: all 0.3s ease-in-out;
        }
        span:empty {
          display: none;
        }
        span.hover {
          background: #394043;
          color: #fff;
        }
      }
      .link-list {
        clear: both;
        div {
          display: none;
          min-height: 89px;
        }
      }
      dd a {
        color: #888c8e;
        font-size: 12px;
        display: inline-block;
        margin: 10px 10px 0 0;
      }
    }
    .fr {
      width: 140px;
      img {
        border: 5px solid #fff;
        border-radius: 2px;
        width: 129px;
        height: 129px;
      }
    }
  }
  .bottom {
    padding-top: 23px;
    .copyright {
      color: #888c8e;
      font-size: 12px;
      line-height: 20px;
      position: relative;
      left: 38%;
    }
    .hot-frauds {
      display: none;
    }
  }
}

.footer-bottom {
  background: #191919;
  padding: 1em 0;
  position: absolute;
  bottom: 0;
  width: 100%;
  a {
    color: #6cbc35;
    font-size: 1em;
    line-height: 1.8em;
    text-decoration: none;
    :hover {
      color: #a1a1a1;
    }
  }
  p {
    font-size: 0.875em;
    color: #a1a1a1;
    line-height: 1.8em;
    text-align: center;
  }
  span {
    color: #3498db;
  }
}
</style>
