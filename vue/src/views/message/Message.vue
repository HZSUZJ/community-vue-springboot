<template>
  <div>
    <h1>
      我的消息
    </h1>
    <el-row>
      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content">
          <el-row>
            <el-container>
              <el-aside width="150px">
                <el-tabs :tab-position="tabPosition" style="height: 320px;" @tab-click="handleClick">
                  <el-tab-pane label="回复我的" name="1">
                    <span slot="label">回复我的 <el-badge class="mark" v-if="this.notificationNum.replyCount!==0"
                                                      :value="this.notificationNum.replyCount"/></span>
                  </el-tab-pane>
                  <el-tab-pane label="@ 我的" name="2">
                    <span slot="label">@ 我的 <el-badge class="mark" v-if="this.notificationNum.atCount!==0"
                                                      :value="this.notificationNum.atCount"/></span>
                  </el-tab-pane>
                  <el-tab-pane label="系统通知" name="3">
                    <span slot="label">系统通知 <el-badge class="mark" v-if="this.notificationNum.systemCount!==0"
                                                      :value="this.notificationNum.systemCount"/></span>
                  </el-tab-pane>
                  <el-tab-pane label="我的私信" name="4">
                    <span slot="label">我的私信 <el-badge class="mark" v-if="this.notificationNum.messageCount!==0"
                                                      :value="this.notificationNum.messageCount"/></span>
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
  name: "Message",
  data() {
    return {
      tabPosition: 'left',
      notificationNum: ''
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
  },
  created() {
    this.axios.get(`/notification/unreadCount`).then(res => {
      if (res.data.code === 200) {
        this.notificationNum = res.data.data
      }
    }).catch(e => {
      alert('服务器故障')
    })
  }
}
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
