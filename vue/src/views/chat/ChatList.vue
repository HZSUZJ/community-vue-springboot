<template>
  <div class="container">
    <NewChatListItem v-if="newUserFlag===1" :item="newUser" @func="getUserId"></NewChatListItem>
    <ChatListItem :item="item" v-for="item in dialogListData" @func="getUserId"></ChatListItem>
  </div>
</template>

<script>
import ChatListItem from './ChatListItem'
import NewChatListItem from "./NewChatListItem";

export default {
  name: "ChatList",
  props: ['newUser'],
  components: {
    ChatListItem, NewChatListItem
  },
  data() {
    return {
      id: '',
      dialogListData: '',
      newUserFlag: 0
    }
  },
  methods: {
    getUserId(data) {
      this.id = data
      this.$emit('func', this.id)
    }
  },
  created() {
    if (this.newUser === null) {
      this.newUserFlag = 1;
    }
    this.axios.get(`/message/conversations`).then(res => {
      if (res.data.code === 200) {
        this.dialogListData = res.data.data
      }
    }).catch(e => {
      alert('服务器故障')
    })
  }
}
</script>

<style scoped>
</style>
