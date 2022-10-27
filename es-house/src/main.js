import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
// import "./assets/css/global.css";
import VueResource from "vue-resource";

import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

import "bootstrap/dist/js/bootstrap.min";
import "bootstrap/dist/css/bootstrap.min.css";

Vue.config.productionTip = false;
Vue.use(VueResource)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
