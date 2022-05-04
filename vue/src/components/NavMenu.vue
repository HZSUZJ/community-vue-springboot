<template>
  <div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1" id="b">版面列表</el-menu-item>
      <el-menu-item index="2">新帖</el-menu-item>
      <el-menu-item index="3">关注</el-menu-item>
      <el-menu-item index="4">
        <el-input
          placeholder="请输入内容"
          prefix-icon="el-icon-search"
          v-model="input1">
        </el-input>
      </el-menu-item>
      <el-submenu index="5">
        <template slot="title">消息中心</template>
        <el-menu-item index="5-1">回复我的</el-menu-item>
        <el-menu-item index="5-2">@我的</el-menu-item>
        <el-menu-item index="5-3">系统中心</el-menu-item>
        <el-menu-item index="5-4">我的私信</el-menu-item>
      </el-submenu>
      <el-submenu index="6">
        <template slot="title">
          <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
          suzj
        </template>
        <el-menu-item index="6-1">个人中心</el-menu-item>
        <el-menu-item index="6-2">注销</el-menu-item>
      </el-submenu>
    </el-menu>
    <div class="line"></div>
  </div>
</template>

<script>

export default {
  name: "NavMenu",
  data() {
    return {
      input1: ''
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      switch (key) {
        case '1':


      }
      console.log(key, keyPath)
    }
  }
}
</script>

<style scoped>
#b {
  margin-left: 600px;
}
</style>
