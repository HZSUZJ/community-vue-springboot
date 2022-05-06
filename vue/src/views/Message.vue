<template>
  <div>
    <h1>
      我的消息
    </h1>
    <el-container>
      <el-aside width="200px">
          <el-tabs :tab-position="tabPosition" style="height: 200px;" @tab-click="handleClick">
            <el-tab-pane label="回复我的" name="1">
            </el-tab-pane>
            <el-tab-pane label="@ 我的" name="2">
            </el-tab-pane>
            <el-tab-pane label="系统通知" name="3">
            </el-tab-pane>
            <el-tab-pane label="我的私信" name="4">
            </el-tab-pane>
          </el-tabs>
      </el-aside>
      <el-main>
          <router-view></router-view>
      </el-main>
    </el-container>


  </div>
</template>

<script>
export default {
  name: "Message",
  data() {
    return {
      tabPosition: 'left'
    };
  },
  methods: {
    handleClick(tab, event) {
      // console.log(tab, event);
      switch (tab.name) {
        case '1':
          this.$router.push({path: '/message/response'})
          break
        case '2':
          this.$router.push({path: '/message/attme'})
          break
        case '3':
          this.$router.push({path: '/message/system'})
          break
        case '4':
          this.$router.push({path: '/message/directMessages'})
          break
      }
    }
  }
}
</script>

<style scoped>

</style>
