// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import App from './App'
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
// highlightjs
import hljs from 'highlight.js';

VMdPreview.use(githubTheme, {
  Hljs: hljs,
});

Vue.use(VMdPreview);


VueMarkdownEditor.use(vuepressTheme);

Vue.use(VueMarkdownEditor);

Vue.config.productionTip = false

axios.defaults.baseURL = "http://10.12.11.142:8081"
// axios.defaults.baseURL = "http://localhost:8081"
Vue.use(VueAxios, axios)
Vue.use(ElementUI)
/* eslint-disable no-new */

axios.interceptors.request.use(config => {
  // 为请求头添加Authorization字段为服务端返回的token
  config.headers.token = localStorage.getItem('token')
  // return config是固定用法 必须有返回值
  return config
})
//假设token过期
axios.interceptors.response.use(res => {
  let data = res.data
  if (res.data.code === 50000) {
    localStorage.removeItem('token')
  }
  return data
})

new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>',
  render: h => h(App)
})
