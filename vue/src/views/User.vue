<template>
  <div>
    <h1>
      用户详情
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

              </el-aside>
              <el-main>
                <el-row>
                  <el-col :span="4">
                    <el-avatar :src=profile.avatarUrl></el-avatar>
                    <br>
                    {{ profile.username }}
                  </el-col>
                  <el-col :span="12">

                    <el-row>
                      <el-col :span="12">性别：{{ profile.sex }}</el-col>
                      <el-col :span="12">发帖数：{{ profile.topicNum }}</el-col>
                    </el-row>
                    <el-row>
                      <el-col :span="12">粉丝数：{{ profile.fansNum }}</el-col>
                      <el-col :span="12">注册时间：{{ profile.gmtRegistration|formatDate }}</el-col>
                    </el-row>
                    <el-row>
                      <el-col :span="12">登录时间：{{ profile.gmtLogin |formatDate }}</el-col>
                    </el-row>
                  </el-col>
                </el-row>
                <el-row>
                  <h3>个性签名</h3>
                  <v-md-preview :text="profile.signature"></v-md-preview>
                </el-row>
                <el-row>
                  <MyTopicEntry v-for="topic in profile.topics" :topic="topic"></MyTopicEntry>
                </el-row>

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
import MyTopicEntry from './usercenter/MyTopicEntry'

export default {
  name: "User",
  components: {
    MyTopicEntry: MyTopicEntry
  },
  data() {
    return {
      profile: ''
    }
  },
  filters: {
    formatDate: function (value) {
      let curdate = new Date().getTime();
      let dffdate = (curdate - value) / 1000 / 60;
      if (dffdate < 60) {
        if (parseInt(dffdate) === 0)
          return '刚刚';
        return parseInt(dffdate) + '分钟前';
      }
      let date = new Date(value);
      let y = date.getFullYear();
      let MM = date.getMonth() + 1;
      MM = MM < 10 ? ('0' + MM) : MM;
      let d = date.getDate();
      d = d < 10 ? ('0' + d) : d;
      let h = date.getHours();
      h = h < 10 ? ('0' + h) : h;
      let m = date.getMinutes();
      m = m < 10 ? ('0' + m) : m;
      let s = date.getSeconds();
      s = s < 10 ? ('0' + s) : s;
      return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;
    }
  },
  created() {
    this.axios.get(`/user/${this.$route.params.id}`).then(res => {
      if (res.data.code === 200) {
        this.profile = res.data.data
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
