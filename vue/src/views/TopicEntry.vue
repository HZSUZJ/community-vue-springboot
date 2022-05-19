<template>
  <div class="mag_one">
    <a href="#" @click="getTopicDetail(topic.id)">
      <el-row>
        <el-col :span="6">
          <div class="grid-content">
            <el-container>
              <el-aside width="80px" style="padding: 17px 15px;">
                <el-avatar :src=topic.user.avatarUrl></el-avatar>
              </el-aside>
              <el-main style="height:80px; padding-left:10px; text-align: left">{{ topic.user.username }}</el-main>
            </el-container>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="grid-content">
            <el-row class="pinglun">{{ topic.title }}</el-row>
            <el-row style="text-align: left; padding-left: 10px;">
              <i class="el-icon-time" style="margin-right: 10px">{{ topic.gmtCreate | formatDate }}</i>
              <i class="el-icon-view" style="margin-right: 10px">{{ topic.views }}</i>
              <span style="margin-right: 10px">最后回复：匿名</span>
              <span style="margin-right: 10px">5分钟前</span>
            </el-row>
          </div>
        </el-col>
        <el-col :span="4" style="float: right;">
          <div class="grid-content msg_xy" style="margin-top: 20px; border-left: 1px solid #000">
            <span>{{ topic.board }}</span>
          </div>
        </el-col>
      </el-row>
    </a>
  </div>
</template>

<script>
export default {
  name: "topicEntry",
  props: ['topic'],
  methods: {
    getTopicDetail(id) {
      let routeUrl = this.$router.resolve({
        path: `/topic/${id}`
      });
      window.open(routeUrl.href, '_blank');
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
  }

}
</script>

<style scoped>

a {
  text-decoration: none;
  color: #333;
}

.mag_one {
  /*width: 1140px;*/
  height: 80px;
  margin-bottom: 15px;
  border: 1px solid #79b8ca;
  /*background-color: pink;*/
}

.el-col-6 {
  width: 180px;
  height: 80px;
  /*background-color: #F2F6FC;*/
  background-color: #79b8ca;
}

.grid-content {
  line-height: 40px;
}

.pinglun {
  text-align: left;
  padding-left: 15px;
}


</style>
