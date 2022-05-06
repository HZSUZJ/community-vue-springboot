<template>
  <div>
    <h1>
      关注总页面
    </h1>
    <el-container>
      <el-header>
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="关注版面" name="1">
          </el-tab-pane>
          <el-tab-pane label="关注用户" name="2">
          </el-tab-pane>
          <el-tab-pane label="收藏更新" name="3">
          </el-tab-pane>
        </el-tabs>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Focus",
  data() {
    return {
      activeName: 'first'
    };
  },
  methods: {
    handleClick(tab) {
      console.log(tab.name);
      switch (tab.name) {
        case '1':
          this.$router.push({path: '/focus/board'})
          break
        case '2':
          this.$router.push({path: '/focus/user'})
          break
        case '3':
          this.$router.push({path: '/focus/favorite'})
          break
      }

    }
  }
}
</script>

<style scoped>

</style>
