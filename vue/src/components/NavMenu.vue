<template>
  <div>
    <el-container>
      <el-header>
        <div class="grid-content bg-purple-light">
          <el-menu class="el-menu-demo" mode="horizontal" @select="handleSelect">
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
              <template slot="title">消息中心
                <el-badge :value="notificationNum.totalCount" v-if="notificationNum.totalCount!==0" class="item"/>
              </template>
              <el-menu-item index="5-1">回复我的
                <el-badge :value="notificationNum.replyCount" v-if="notificationNum.replyCount!==0" class="item"/>
              </el-menu-item>
              <el-menu-item index="5-3">系统通知
                <el-badge :value="notificationNum.systemCount" v-if="notificationNum.systemCount!==0" class="item"/>
              </el-menu-item>
              <el-menu-item index="5-4">我的私信
                <el-badge :value="notificationNum.messageCount" v-if="notificationNum.messageCount!==0" class="item"/>
              </el-menu-item>
            </el-submenu>
            <el-submenu index="6">
              <template slot="title">
                <el-avatar :src=uavatar></el-avatar>
                {{ uname }}
              </template>
              <el-menu-item index="6-1">个人中心</el-menu-item>
              <el-menu-item index="6-2">注销</el-menu-item>
            </el-submenu>
          </el-menu>
        </div>
      </el-header>
    </el-container>
  </div>
</template>

<script>

export default {
  name: "NavMenu",
  data() {
    return {
      input1: '',
      uname: '',
      uavatar: '',
      notificationNum: ''
    };
  },
  created() {
    this.uname = localStorage.username
    this.uavatar = localStorage.uavatar
    this.axios.get(`/notification/unreadCount`).then(res => {
      if (res.data.code === 200) {
        this.notificationNum = res.data.data
      }
    }).catch(e => {
      alert('服务器故障')
    })


  },
  methods: {
    handleSelect(key, keyPath) {
      switch (key) {
        case '1':
          this.$router.push({path: '/boardList'})
          break
        case '2':
          this.$router.push({path: '/newTopics'})
          break
        case '3':
          this.$router.push({path: '/focus'})
          break
        case '5-1':
          this.$router.push({path: '/message/response'})
          break
        case '5-3':
          this.$router.push({path: '/message/system'})
          break
        case '5-4':
          this.$router.push({path: '/message/directMessages'})
          break
        case '6-1':
          this.$router.push({path: '/usercenter'})
          break

      }
      console.log(key)
    }
  }
}
</script>

<style scoped>
.bg-purple-light {
  margin: 0 auto;
  width: 800px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

</style>
