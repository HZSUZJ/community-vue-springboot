import Vue from 'vue'
import Vuex from 'vuex';

Vue.use(Vuex);
const actions = {}
const mutations = {
  handleUserName: (state, user_name) => {
    state.user_name = user_name
    // 把登录的用户的名保存到localStorage中，防止页面刷新，导致vuex重新启动，用户名就成为初始值（初始值为空）的情况
    localStorage.setItem('uname', user_name)
  }
}
const state = {
  user_name: '' || localStorage.getItem('uname')
}
// getters 只会依赖 state 中的成员去更新
const getters = {
  userName: (state) => state.user_name
}

const store = new Vuex.Store({
  actions,
  mutations,
  state,
  getters
})
export {store}
