<template>
  <div>
    <!-- NAV头部搜索模块 -->
    <div class="searchs">
      <div class="wrapper">
        <div class="fl" log-mod="search">
          <div class="search-txt">
            <form class="clear" id="searchForm" action="#" method="get">
              <div class="search-tab">
                <i class="icon" data-bl="switch"></i>
                <div data-bl="switch" class="check">关键词</div>
                <div class="txt-search">
                  <input
                    class="left txt"
                    name="keyword"
                    autocomplete="off"
                    placeholder="如小区名、地铁站等"
                    id="keyword-box"
                    th:value="${searchBody.keywords != null} ? ${searchBody.keywords} : ''"
                  />
                </div>
              </div>
              <button
                type="submit"
                class="act-search btn home-ico ico-search LOGCLICKEVTID"
              >
                搜索
              </button>
            </form>
          </div>
        </div>
        <div class="fr last">
          <div class="ditu fr">
            <a
              th:href="@{/rent/house/map(cityEnName=${searchBody.cityEnName})}"
              target="_blank"
            >
              <i></i>
              地图找房
            </a>
          </div>
        </div>
      </div>
    </div>

    <!-- 面包屑模块 -->
    <div class="intro clear" mod-id="lj-common-bread">
      <div class="container">
        <div class="fl l-txt">
          <i class="icon"></i>&nbsp;<a href="/">寻屋</a
          ><span class="stp">&nbsp;&gt;&nbsp;</span>[<a
            href="/"
            title="选择城市"
          >
            <span th:text="${currentCity.cnName}">城市</span></a
          >]租房
        </div>
        <div class="fr r-txt"></div>
      </div>
    </div>

    <div class="wrapper">
      <div class="filter-box">
        <div class="hd clear">
          <ul class="tab-lst">
            <li class="on">
              <a th:href="@{/rent/house(cityEnName=${searchBody.cityEnName})}"
                ><span>全部租房</span></a
              >
            </li>
          </ul>
          <div class="info"><span class="num">真实房源，假一赔亿！</span></div>
        </div>
        <div>
          <div class="bd" id="filter-options">
            <dl class="dl-lst clear">
              <dt>区域：</dt>
              <dd data-index="0">
                <div class="option-list region-select">
                  <a
                    data-bind="*"
                    onclick="changeSimpleCondition('regionEnName', '*')"
                    >不限</a
                  >
                  <tr th:each="region, regionStat: ${regions}">
                    <a
                      th:attr="data-bind=${region.enName}"
                      href="javascript:void(0);"
                      th:onclick="'changeSimpleCondition(\'regionEnName\', \'' + ${region.enName} + '\')'"
                      th:text="${region.cnName}"
                    ></a>
                  </tr>
                </div>
              </dd>
            </dl>
            <dl class="dl-lst clear">
              <dt>租金：</dt>
              <dd data-index="1">
                <div class="option-list price-select">
                  <a
                    data-bind="*"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('priceBlock', '*')"
                    >不限
                  </a>
                  <tr th:each="priceBlock : ${priceBlocks}">
                    <a
                      th:attr="data-bind=${priceBlock.key}"
                      href="javascript:void(0);"
                      th:onclick="'changeSimpleCondition(\'priceBlock\',\'' + ${priceBlock.key} + '\')'"
                      th:text="${priceBlock.value.min < 0} ? (${priceBlock.value.max} + '元以下' ):
                                    (${priceBlock.value.max < 0} ? ${priceBlock.value.min} + '元以上' :
                                     ${priceBlock.value.min} + '-' + ${priceBlock.value.max} + '元')"
                    >
                    </a>
                  </tr>
                </div>
              </dd>
            </dl>
            <dl class="dl-lst clear">
              <dt>面积：</dt>
              <dd data-index="2">
                <div class="option-list area-select">
                  <a
                    data-bind="*"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('areaBlock', '*')"
                    >不限
                  </a>
                  <tr th:each="areaBlock : ${areaBlocks}">
                    <a
                      th:attr="data-bind=${areaBlock.key}"
                      href="javascript:void(0);"
                      th:onclick="'changeSimpleCondition(\'areaBlock\',\'' + ${areaBlock.key} + '\')'"
                      th:text="${areaBlock.value.min < 0} ? (${areaBlock.value.max} + '平以下' ):
                                    (${areaBlock.value.max < 0} ? ${areaBlock.value.min} + '平以上' :
                                     ${areaBlock.value.min} + '-' + ${areaBlock.value.max} + '平')"
                    >
                    </a>
                  </tr>
                </div>
              </dd>
            </dl>
            <dl class="dl-lst clear">
              <dt>房型：</dt>
              <dd data-index="3">
                <div class="option-list room-select">
                  <!--th:class="${searchBody.layout == 0} ? 'on' : ''"-->
                  <a
                    data-bind="0"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('layout', 0)"
                    >不限</a
                  >
                  <a
                    data-bind="1"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('layout', 1)"
                    >一室</a
                  >
                  <a
                    data-bind="2"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('layout', 2)"
                    >二室</a
                  >
                  <a
                    data-bind="3"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('layout', 3)"
                    >三室</a
                  >
                  <a
                    data-bind="4"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('layout', 4)"
                    >四室</a
                  >
                  <a
                    data-bind="5"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('room', 5)"
                    >五室及以上
                  </a>
                </div>
              </dd>
            </dl>
            <dl class="dl-lst clear">
              <dt>朝向：</dt>
              <dd data-index="4">
                <div class="option-list direction-select">
                  <!--th:class="${searchBody.direction == 0} ? 'on' : ''-->
                  <a
                    data-bind="0"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('direction', 0)"
                  >
                    不限</a
                  >
                  <a
                    data-bind="1"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('direction', 1)"
                  >
                    朝东</a
                  >
                  <a
                    data-bind="2"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('direction', 2)"
                  >
                    朝南</a
                  >
                  <a
                    data-bind="3"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('direction', 3)"
                  >
                    朝西</a
                  >
                  <a
                    data-bind="4"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('direction', 4)"
                  >
                    朝北</a
                  >
                  <a
                    data-bind="5"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('direction', 5)"
                  >
                    南北</a
                  >
                </div>
              </dd>
            </dl>
            <dl class="dl-lst clear">
              <dt>租赁方式：</dt>
              <dd data-index="5">
                <div class="option-list rent-way-select">
                  <a
                    data-bind="-1"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('rentWay',
                            -1)"
                    >不限</a
                  >
                  <a
                    data-bind="0"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('rentWay', 0)"
                    >整租</a
                  >
                  <a
                    data-bind="1"
                    href="javascript:void(0);"
                    onclick="changeSimpleCondition('rentWay', 1)"
                    >合租</a
                  >
                </div>
              </dd>
            </dl>
          </div>
          <div class="filter-bar01">
            <div class="sort-bar" id="sort-bar">
              <span>排序：</span>
              <div class="sort-parent" data-bind="lastUpdateTime">
                <a
                  href="javascript:void(0);"
                  onclick="changeSort('lastUpdateTime', 'desc')"
                >
                  <span>默认</span>
                </a>
              </div>
              <div class="sort-parent" data-bind="createTime">
                <a
                  href="javascript:void(0);"
                  onclick="changeSort('createTime', 'desc')"
                >
                  <span>最新</span>
                </a>
              </div>
              <div class="sort-parent" data-bind="price">
                <a
                  href="javascript:void(0);"
                  onclick="changeSort('price', 'asc')"
                >
                  <span>租金低</span>
                </a>
              </div>
              <div class="sort-parent" data-bind="area">
                <span>面积</span><i></i>
                <ul class="sort-children">
                  <li>
                    <a
                      href="javascript:void(0);"
                      onclick="changeSort('area', 'asc')"
                      >面积从小到大</a
                    >
                  </li>
                  <li>
                    <a
                      href="javascript:void(0);"
                      onclick="changeSort('area', 'desc')"
                      >面积从大到小</a
                    >
                  </li>
                </ul>
              </div>
              <div class="sort-parent" data-bind="distanceToSubway">
                <a
                  href="javascript:void(0);"
                  onclick="changeSort('distanceToSubway', 'asc')"
                >
                  <span>地铁距离近</span>
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="main-box clear">
        <div id="sem_card"></div>
        <div class="con-box">
          <div class="list-head clear">
            <h2>
              共有<span th:text="${total}">18364</span>套<span
                th:text="${currentCity.cnName}"
              ></span
              >在租房源
            </h2>
            <div class="view-type" id="viewType">
              <div class="modeshows modeshow">
                <span class="l-show view-mod" data-type="real" id="lshow"
                  ><i></i>
                  <span th:text="${currentCity.cnName} + '房源'"></span>
                </span>
              </div>
            </div>
          </div>
          <div class="list-wrap">
            <ul id="house-lst" class="house-lst">
              <tr th:each="house, houseStat: ${houses}">
                <li>
                  <div class="pic-panel">
                    <a
                      target="_blank"
                      th:href="'/rent/house/show/' + ${house.id}"
                    >
                      <img th:src="${house.cover}" th:alt="${house.title}" />
                    </a>
                  </div>
                  <div class="info-panel">
                    <div>
                      <h2>
                        <a
                          target="_blank"
                          th:href="'/rent/house/show/' + ${house.id}"
                          th:title="${house.title}"
                          th:text="${house.title}"
                        >
                        </a>
                      </h2>
                    </div>
                    <div class="col-1">
                      <div class="where">
                        <a
                          th:href="@{/rent/house(cityEnName=${searchBody.cityEnName}, search=${house.district})}"
                          class="laisuzhou"
                        >
                          <span
                            class="region"
                            th:text="${house.district} + '&nbsp;&nbsp;'"
                          ></span>
                        </a>
                        <span class="zone">
                          <span
                            th:text="${house.room} + '室' + ${house.parlour} + '厅&nbsp;&nbsp;'"
                          ></span>
                        </span>
                        <span
                          class="meters"
                          th:text="${house.area} + '平米&nbsp;&nbsp;'"
                        ></span>
                        <span>朝向</span>
                      </div>
                      <div class="other">
                        <div class="con">
                          <span
                            th:text="${house.floor} + '楼(共' + ${house.totalFloor} + '层)'"
                          >
                          </span>
                          <span>/</span>
                          <span th:text="${house.buildYear} + '年建'"></span>
                        </div>
                      </div>
                      <div class="chanquan">
                        <div class="left agency">
                          <div class="view-label left">
                            <span
                              th:if="${house.houseDetail.subwayLineName != null}"
                            >
                              <span class="fang-subway"></span>
                              <span class="fang-subway-ex">
                                <span
                                  >距离地铁
                                  <span
                                    th:text="${house.houseDetail.subwayLineName}"
                                  ></span>
                                  <span
                                    th:text="${house.houseDetail.subwayStationName}"
                                  ></span
                                  >站
                                  <span
                                    th:text="${house.distanceToSubway}"
                                  ></span
                                  >米</span
                                >
                              </span>
                            </span>
                            <span th:each="tag: ${house.tags}">
                              <span class="decoration"></span>
                              <span class="decoration-ex">
                                <span th:text="${tag}"></span>
                              </span>
                            </span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-3">
                      <div class="price">
                        <span class="num" th:text="${house.price}">价格</span
                        >元/月
                      </div>
                      <div class="price-pre">
                        <span
                          th:text="${#dates.format(house.lastUpdateTime, 'yyyy.MM.dd')} + ' 更新'"
                        >
                          更新时间
                        </span>
                      </div>
                    </div>
                    <div class="col-2">
                      <div class="square">
                        <div>
                          <span class="num" th:text="${house.watchTimes}"
                            >0</span
                          >人
                        </div>
                        <div class="col-look">看过此房</div>
                      </div>
                    </div>
                  </div>
                </li>
              </tr>
            </ul>
          </div>

          <div
            th:if="${!houses.isEmpty()}"
            id="pageable"
            class="page-box house-lst-page-box"
          ></div>
          <div th:if="${houses.isEmpty()}" class="page-box house-lst-page-box">
            没有找到相关结果&gt;_&lt;，换个姿势去搜索相关数据吧~
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style lang="css">
@import "../assets/css/main.css";
@import "../assets/css/list.css";
</style>
<script>
export default {};
</script>
