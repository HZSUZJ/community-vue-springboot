<template>
  <div class="chat-item" @click="getItemId(`${item.userId}`)">
    <div class="chat-left">
      <img class="chat-avatar" :src="item.other.avatarUrl" alt="">
    </div>
    <div class="chat-center">
      <span class="chat-name">{{ item.other.username }}</span>
      <div class="chat-content">
        {{ item.content | ellipsis }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ChatListItem',
  props: ['item'],
  data() {
    return {id: ''}
  },
  methods: {
    getItemId(id) {
      this.id = id
      this.$emit('func', this.id)
    }
  },
  filters: {
    ellipsis(value) {
      if (!value) return ''
      if (value.length > 8) {
        return value.slice(0, 8) + '...'
      }
      return value
    }
  }

}
</script>

<style scoped>
.chat-item {
  display: flex;
  flex-direction: row;
  width: 250px;
  height: 80px;
  border: 1px solid #E5E4E4;
  background-color: #FFFFFF;
}

.chat-left {
  height: 80px;
  width: 80px;
}

.chat-avatar {
  width: 70px;
  height: 70px;
  border-radius: 5%;
  margin-top: 5px;
  margin-left: 5px;
}

.chat-center {
  display: flex;
  flex-direction: column;
  width: 235px;
}

.chat-name {
  display: inline-block;
  margin-top: 10px;
  margin-left: 10px;
}

.chat-content {
  margin-top: 15px;
  margin-left: 10px;
}

</style>
