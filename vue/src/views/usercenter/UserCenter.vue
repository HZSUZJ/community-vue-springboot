<template>
  <div>
    <h1>
      个人中心
    </h1>
    <el-row>
      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>

      <el-col :span="12">
        <div class="grid-content">
          <el-row>
            <el-container>
              <el-aside width="100px">
                <el-tabs :tab-position="tabPosition" style="height: 320px;" @tab-click="handleClick">
                  <el-tab-pane label="个人主页" name="1">
                  </el-tab-pane>
                  <el-tab-pane label="修改资料" name="2">
                  </el-tab-pane>
                  <el-tab-pane label="我的主题" name="3">
                  </el-tab-pane>
                  <el-tab-pane label="我的回复" name="4">
                  </el-tab-pane>
                  <el-tab-pane label="我的收藏" name="5">
                  </el-tab-pane>
                  <el-tab-pane label="关注版面" name="6">
                  </el-tab-pane>
                  <el-tab-pane label="关注用户" name="7">
                  </el-tab-pane>
                  <el-tab-pane label="我的粉丝" name="8">
                  </el-tab-pane>
                </el-tabs>
              </el-aside>
              <el-main>
                <router-view></router-view>
              </el-main>
            </el-container>
          </el-row>
        </div>
      </el-col>

      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>

export default {
  name: "UserCenter",
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
          this.$router.push({path: '/usercenter/detail'})
          break
        case '2':
          this.$router.push({path: '/usercenter/config'})
          break
        case '3':
          this.$router.push({path: '/usercenter/mytopics'})
          break
        case '4':
          this.$router.push({path: '/usercenter/myposts'})
          break
        case '5':
          this.$router.push({path: '/usercenter/myfavorites'})
          break
        case '6':
          this.$router.push({path: '/usercenter/myfocusboards'})
          break
        case '7':
          this.$router.push({path: '/usercenter/myfollowings'})
          break
        case '8':
          this.$router.push({path: '/usercenter/myfans'})
          break
      }
    }
  }
}
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
