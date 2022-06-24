<template>
  <div>
    <el-container style="height: 700px; border: 1px solid #eee">
      <el-aside width="280px" style="background-color: rgb(238, 241, 246)">
        <ChatList @func="getUserId" :newUser="user"></ChatList>
      </el-aside>

      <el-container>
        <el-main style="height: 700px;padding: 0">
          <ChatDetail :userId="passId"></ChatDetail>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import ChatList from "../chat/ChatList";
import ChatDetail from "../chat/ChatDetail";

export default {
  name: "Message",
  components: {ChatList, ChatDetail},
  data() {
    return {
      passId: null,
      user: null,
      newUserFlag: 0
    }
  },
  methods: {
    getUserId(data) {
      this.passId = data
    }
  },
  created() {
    if (this.$route.params.id != null) {
      this.axios.get(`/user/basic/${this.$route.params.id}`).then(res => {
        if (res.data.code === 200) {
          this.user = res.data.data
          this.passId = this.user.id
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
