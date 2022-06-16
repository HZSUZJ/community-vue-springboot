<template>
  <div>
    <el-button type="primary" @click="readAllReply">全部标为已读</el-button>
    <ResponseEntry v-for="notification in notifications" :notification="notification"></ResponseEntry>
  </div>
</template>

<script>
import ResponseEntry from './ResponseEntry'

export default {
  name: "Response",
  components: {
    ResponseEntry: ResponseEntry
  },
  data() {
    return {
      notifications: ''
    }
  },
  created() {
    this.axios.get(`/notification/reply`).then(res => {
      if (res.data.code === 200) {
        this.notifications = res.data.data
      }
    }).catch(e => {
      alert('服务器故障')
    })
  },
  methods: {
    readAllReply() {
      this.axios.get(`/notification/readAllReply`).then(res => {
        if (res.data.code === 200) {
          for (let i = 0; i < this.notifications.length; i++) {
            this.notifications[i].status = 1;
          }
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
