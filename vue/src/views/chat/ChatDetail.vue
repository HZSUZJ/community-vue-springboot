<template>
  <div>
    <el-container>
      <el-header style="text-align: left; font-size: 15px;">
        <h1 v-if="username!==null">与{{ username }}的私信</h1>
        <h1 v-else>私信</h1>
      </el-header>
      <el-main>
        <div class="chat-detail-box" ref="bscroll" style="height: 400px">
          <ChatDialog :item="item" v-for="item in dialogData"></ChatDialog>
        </div>
      </el-main>
      <el-footer>
        <el-input
          type="textarea"
          :rows="5"
          placeholder="请输入内容"
          v-model="inputValue">
        </el-input>
        <el-button type="primary" round @click="onSendChat">发送</el-button>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import ChatDialog from "./ChatDialog"

export default {
  name: 'ChatDetail',
  components: {
    ChatDialog
  },
  props: ['userId'],
  watch: {
    userId(curId, oldVal) {
      this.axios.get(`/message/user/${curId}`).then(res => {
        if (res.data.code === 200) {
          this.dialogData = res.data.data
        }
      }).catch(e => {
        alert('服务器故障')
      })
      this.axios.get(`/user/basic/${curId}`).then(res => {
        if (res.data.code === 200) {
          this.username = res.data.data.username
        }
      }).catch(e => {
        alert('服务器故障')
      })
    }
  },
  data() {
    return {
      inputValue: '',
      dialogData: '',
      username: null
    }
  },
  methods: {
    onSendChat() {
      if (this.inputValue === '') {
        alert("输入东西")
      } else {
        this.axios({
          method: 'post',
          url: '/message/send',
          params: {
            otherId: this.userId,
            content: this.inputValue,
            gmtCreate: new Date().getTime()
          }
        }).then((res) => {
          if (res.data.code === 200) {
            this.inputValue = ''
            this.axios.get(`/message/user/${this.userId}`).then(res => {
              if (res.data.code === 200) {
                this.dialogData = res.data.data
              }
            }).catch(e => {
              alert('服务器故障')
            })
          } else {
            alert("网络故障")
          }
        })
          .catch((error) => {
            console.log(error);
          });

      }
    }
  },
  created() {
    if (this.userId !== null) {
      this.axios.get(`/user/basic/${this.userId}`).then(res => {
        if (res.data.code === 200) {
          this.username = res.data.data.username
        }
      }).catch(e => {
        alert('服务器故障')
      })
    }
  }
}
</script>

<style scoped>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>
