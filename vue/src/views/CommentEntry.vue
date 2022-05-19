<template>
  <div>
    <el-row class="msg_menu">
      <el-container>
        <el-aside width="250px" style="background-color: #79b8ca">
          <div class="userMessage" style="margin-top: 24px;">
            <div class="userMessage-left" style="float:left; width: 76px; margin-top:16px; padding-left: 24px;">
              <el-row style="text-align: left; font-size: 20px">{{ comment.user.username }}</el-row>
            </div>
            <div class="userMessage-right" style="float:right; width: 94px; padding-right: 10px;">
              <el-avatar :src=comment.user.avatarUrl></el-avatar>
              <div class="userMessageBtn" style="margin-top: 20px">
                <a href="#"><button class="replierBtn" id="follow" style="float: left">关注</button></a>
                <a href="#"><button class="replierBtn" style="float: right">私信</button></a>
              </div>
            </div>
          </div>
                <!--              <el-avatar :src=comment.user.avatarUrl></el-avatar>-->
        </el-aside>

        <el-main :src=comment.user.avatarUrl>
          <el-row style="text-align: left;padding-left: 15px">
            <v-md-preview :text="comment.content"></v-md-preview>
          </el-row>
          <el-row style="text-align: left">
            <i class="el-icon-time" style="margin-right: 10px">{{ comment.gmtCreate | formatDate }}</i>
          </el-row>
        </el-main>
      </el-container>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'CommentEntry',
  props: ['comment'],
  filters: {
    formatDate: function (value) {
      let curdate = new Date().getTime()
      let dffdate = (curdate - value) / 1000 / 60
      if (dffdate < 60) {
        if (parseInt(dffdate) === 0) { return '刚刚' }
        return parseInt(dffdate) + '分钟前'
      }
      let date = new Date(value)
      let y = date.getFullYear()
      let MM = date.getMonth() + 1
      MM = MM < 10 ? ('0' + MM) : MM
      let d = date.getDate()
      d = d < 10 ? ('0' + d) : d
      let h = date.getHours()
      h = h < 10 ? ('0' + h) : h
      let m = date.getMinutes()
      m = m < 10 ? ('0' + m) : m
      let s = date.getSeconds()
      s = s < 10 ? ('0' + s) : s
      return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s
    }
  }
}
</script>

<style scoped>

.msg_menu {
  margin-top: 20px;
  border: 2px solid #79b8ca;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

button {
  background-color: #79b8ca;
  color: #fff;
  border: #fff solid thin;
  border-radius: 0.63rem;
  width: 2.6rem;
  height: 1.4rem;
  cursor: pointer;
  font-size: .6rem;
  overflow: visible;
}

</style>
