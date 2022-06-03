<template>
  <div>
    <FolloweeEntry v-for="followee in followees" :followee="followee"></FolloweeEntry>
  </div>
</template>

<script>
import FolloweeEntry from './FolloweeEntry'

export default {
  name: "MyFans",
  components: {FolloweeEntry: FolloweeEntry},
  data() {
    return {
      followees: ''
    }
  },
  created() {
    this.axios.get(`/getFans`).then(res => {
      if (res.data.code === 200) {
        this.followees = res.data.data
      }
    }).catch(e => {
      alert('服务器故障')
    })
  }
}
</script>

<style scoped>

</style>
