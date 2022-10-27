<template>
  <!-- <div class="home"> -->
  <!-- <img alt="Vue logo" src="../assets/logo.png" /> -->
  <!-- </div> -->
  <div class="home city-change animated bounceIn" style="display: block;">
    <span class="close"></span>
    <div id="hotCity" class="title">
      选择城市
      <span class="city-tab">
        <span class="code1">热门城市:</span>
        <router-link
          v-for="city in cities"
          :key="city.num"
          title="租房"
          to="/rent-list"
          >{{ city.name }}</router-link
        >
      </span>
    </div>
    <div class="title-line"></div>
    <div class="fc-main clear">
      <div class="fl citys-l">
        <ul>
          <li class="clear">
            <span class="code-title fl">B</span>
            <div class="city-enum fl">
              <a href="./rent-list.html" title="北京租房">北京</a>
            </div>
          </li>
          <li class="clear">
            <span class="code-title fl">C</span>
            <div class="city-enum fl">
              <a href="/rent/house?cityEnName=cd" title="成都租房">成都</a>
              <a href="/rent/house?cityEnName=cq" title="重庆租房">重庆</a>
              <a href="/rent/house?cityEnName=cs" title="长沙租房">长沙</a>
            </div>
          </li>
          <li class="clear">
            <span class="code-title fl">D</span>
            <div class="city-enum fl">
              <a href="/rent/house?cityEnName=dl" title="大连租房">大连</a>
              <a href="/rent/house?cityEnName=dg" title="东莞租房">东莞</a>
            </div>
          </li>
          <li class="clear">
            <span class="code-title fl">F</span>
            <div class="city-enum fl">
              <a href="/rent/house?cityEnName=fs" title="佛山租房">佛山</a>
            </div>
          </li>
          <li class="clear">
            <span class="code-title fl">G</span>
            <div class="city-enum fl">
              <a href="/rent/house?cityEnName=gz" title="广州租房">广州</a>
            </div>
          </li>
          <li class="clear">
            <span class="code-title fl">H</span>
            <div class="city-enum fl">
              <a href="/rent/house?cityEnName=hz" title="杭州租房">杭州</a>
              <a href="/rent/house?cityEnName=hui" title="惠州租房">惠州</a>
              <a href="/rent/house?cityEnName=hk" title="海口租房">海口</a>
              <a href="/rent/house?cityEnName=hf" title="合肥租房">合肥</a>
            </div>
          </li>
        </ul>
      </div>
      <div class="fl citys-r">
        <ul>
          <li class="clear">
            <span class="code-title fl">L</span>
            <div class="city-enum fl">
              <a href="/rent/house?cityEnName=ls" title="陵水租房">陵水</a>
              <a href="/rent/house?cityEnName=lf" title="廊坊租房">廊坊</a>
            </div>
          </li>
          <li class="clear">
            <span class="code-title fl">N</span>
            <div class="city-enum fl">
              <a href="/rent/house?cityEnName=nj" title="南京租房">南京</a>
            </div>
          </li>
          <li class="clear">
            <span class="code-title fl">Q</span>
            <div class="city-enum fl">
              <a href="/rent/house?cityEnName=qd" title="青岛租房">青岛</a>
              <a href="/rent/house?cityEnName=qh" title="琼海租房">琼海</a>
            </div>
          </li>
          <li class="clear">
            <span class="code-title fl">S</span>
            <div class="city-enum fl">
              <a href="/rent/house?cityEnName=sh" title="上海租房">上海</a>
              <a href="/rent/house?cityEnName=sz" title="深圳租房">深圳</a>
              <a href="/rent/house?cityEnName=su" title="苏州租房">苏州</a>
              <a href="/rent/house?cityEnName=sjz" title="石家庄租房">石家庄</a>
              <a href="/rent/house?cityEnName=sy" title="沈阳租房">沈阳</a>
              <a href="/rent/house?cityEnName=sanya" title="三亚租房">三亚</a>
            </div>
          </li>
          <li class="clear">
            <span class="code-title fl">T</span>
            <div class="city-enum fl">
              <a href="/rent/house?cityEnName=tj" title="天津租房">天津</a>
              <a href="/rent/house?cityEnName=ty" title="太原租房">太原</a>
            </div>
          </li>
          <li class="clear">
            <span class="code-title fl">W</span>
            <div class="city-enum fl">
              <a href="/rent/house?cityEnName=wh" title="武汉租房">武汉</a>
              <a href="/rent/house?cityEnName=wx" title="无锡租房">无锡</a>
              <a href="/rent/house?cityEnName=wc" title="文昌租房">文昌</a>
              <a href="/rent/house?cityEnName=wn" title="万宁租房">万宁</a>
            </div>
          </li>
          <li class="clear">
            <span class="code-title fl">X</span>
            <div class="city-enum fl">
              <a href="/rent/house?cityEnName=xm" title="厦门租房">厦门</a>
              <a href="/rent/house?cityEnName=xa" title="西安租房">西安</a>
              <a href="/rent/house?cityEnName=xsbn" title="西双版纳租房"
                >西双版纳</a
              >
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: "Home",
  components: {
    // HelloWorld
  },
  data() {
    return {
      cities: [
        { name: "北京", num: 1 },
        { name: "上海", num: 2 },
        { name: "广州", num: 3 },
        { name: "深圳", num: 4 }
      ]
    };
  },
  created() {},
  methods: {
    getadd() {
      // afdsjgldk
    }
  }
};
</script>
<style lang="css">
@import "../assets/css/main.css";
</style>

<style lang="less">
.msgTip {
  font-size: x-large;
  margin: 80px;
  text-align: center;
}
.city-change {
  .fc-main {
    padding: 0 40px;
    height: 335px;
    overflow-y: auto;
    .citys-l {
      width: 50%;
      height: 100%;
    }
    .citys-r {
      width: 50%;
      height: 100%;
    }
    ul {
      display: inline-block;
    }
    li {
      line-height: 41px;
      .code-title {
        display: inline-block;
        width: 12px;
        margin-right: 10px;
        color: #999999;
      }
      .city-enum {
        max-width: 250px;
        a {
          margin-left: 7px;
          display: inline-block;
          color: #333333;
          &:hover {
            color: #00b064;
          }
        }
      }
    }
  }
  .title-line {
    height: 1px;
    background-color: #eeeeee;
    margin: 0 auto;
    margin-bottom: 25px;
    margin-top: 10px;
  }
  .title {
    text-align: left;
    margin: 30px 40px 0 40px;
    font-size: 22px;
    color: #394043;
    font-weight: bold;
    position: relative;
    .city-tab {
      display: inline-block;
      span {
        font-size: 14px;
        font-weight: normal;
        display: inline-block;
        margin-right: 9px;
        color: #999999;
      }
      a {
        font-size: 14px;
        font-weight: normal;
        margin-left: 10px;
        color: #394043;
        &:hover {
          color: #00b064;
        }
      }
    }
  }
}
.clear {
  display: block;
}
</style>
