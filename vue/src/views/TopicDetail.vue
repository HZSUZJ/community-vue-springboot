<template>
  <h1>
    {{ this.$route.params.id }}
  </h1>
</template>

<script>
export default {
  name: "TopicDetail",
  created() {
    this.axios.get(`/getTopicDetail`).then(res => {
      if (res.data.code === 200) {
        console.log(res.data.data)

      }
    }).catch(e => {
      alert('服务器故障')
    })
  }


}
</script>

<style scoped>

</style>
