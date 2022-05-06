<template>
  <div>
    <el-row
      style="padding: 10px;margin-top: 20px;border: 1px;box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
      <el-col :span="6" style="background-color: #F2F6FC">
        <div class="grid-content">
          <el-container>
            <el-aside width="50px">
              <el-avatar :src=comment.user.avatarUrl></el-avatar>
            </el-aside>
            <el-main>{{ comment.user.username }}</el-main>
          </el-container>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content">
          <el-row style="text-align: left;padding-left: 15px">
            <v-md-preview :text="comment.content"></v-md-preview>
          </el-row>
          <el-row style="text-align: left">
            <i class="el-icon-time" style="margin-right: 10px">{{ comment.gmtCreate | formatDate }}</i>
          </el-row>
        </div>
      </el-col>

    </el-row>
  </div>
</template>

<script>
export default {
  name: "CommentEntry",
  props: ['comment'],
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

</style>
