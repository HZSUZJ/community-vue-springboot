<template>
  <div>
    <topicEntry v-for="topic in topics" :topic="topic"></topicEntry>
  </div>
</template>

<script>
import topicEntry from '../topic/TopicEntry'

export default {
  name: "board",
  data() {
    return {
      topics: ''
    }
  },
  created() {
    this.axios.get(`/focus/board`).then(res => {
      if (res.data.code === 200) {
        this.topics = res.data.data
      }
    }).catch(e => {
      alert('服务器故障')
    })
  }, components: {
    topicEntry: topicEntry
  }
}
</script>

<style scoped>

</style>
