<template>
  <div>
    <MyfavoriteEntry v-for="topic in topics" :topic="topic"></MyfavoriteEntry>
  </div>
</template>

<script>
import MyfavoriteEntry from './MyfavoriteEntry'

export default {
  name: "Myfavorites",
  components: {MyfavoriteEntry: MyfavoriteEntry},
  data() {
    return {
      topics: ''
    }
  },
  created() {
    this.axios.get(`/myCollections`).then(res => {
      if (res.data.code === 200) {
        this.topics = res.data.data
      }
    }).catch(e => {
      alert('服务器故障')
    })
  }
}
</script>

<style scoped>

</style>
