<template>
  <div>
    <el-row>
      <el-col :span="8">
        <el-row>
          <el-link type="primary" :href="`/board/${notification.board.id}`" target="_blank">
            {{
              notification.board.name
            }}
          </el-link>
          {{ notification.gmtCreate | formatDate }}
        </el-row>
        <el-row>
          <el-link type="primary" :href="`/user/${notification.user.id}`" target="_blank">{{
              notification.user.username
            }}
          </el-link>
          在《
          <el-link type="primary"
                   @click="readReply(`/topic/${notification.topic.id}/${Math.ceil(notification.comment.floor/10)}#${notification.comment.floor%10===0?10:notification.comment.floor%10}`)">
            {{
              notification.topic.title
            }}
          </el-link>
          》回复了你
        </el-row>
      </el-col>
      <el-col :span="16">
        <el-button type="success" icon="el-icon-check" circle v-if="notification.status===1"></el-button>
        <el-button type="danger" icon="el-icon-s-opportunity" circle v-if="notification.status===0"></el-button>
      </el-col>
    </el-row>

    <hr>
  </div>
</template>

<script>
export default {
  name: "ResponseEntry",
  props: ['notification'],
  filters: {
    formatDate: function (value) {
      let curdate = new Date().getTime()
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
  },
  methods: {
    readReply(url) {
      let routeUrl = this.$router.resolve({
        path: `${url}`
      });
      window.open(routeUrl.href, '_blank');
      this.axios.get(`/notification/readReply/${this.notification.id}`).then(res => {
        if (res.data.code === 200) {
          this.notification.status = 1
        }
      }).catch(e => {
        alert('服务器故障')
      })
    }
  }
}
</script>

<style scoped>

</style>
