<template>
  <div>
    <MyTopicEntry v-for="topic in topics" :topic="topic"></MyTopicEntry>
  </div>
</template>

<script>
import MyTopicEntry from './MyTopicEntry'

export default {
  name: "Mytopics",
  components: {
    MyTopicEntry: MyTopicEntry
  },
  data() {
    return {
      topics: ''
    }
  },
  created() {
    this.axios.get(`/myTopics`).then(res => {
      if (res.data.code === 200) {
        this.topics = res.data.data
        console.log(this.topics)
      }
    }).catch(e => {
      alert('服务器故障')
    })
  }

}
</script>

<style scoped>

</style>
