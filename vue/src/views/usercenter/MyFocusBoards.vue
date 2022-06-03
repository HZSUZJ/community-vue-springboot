<template>
  <div>
    <FocusBoardEntry v-for="board in boards" :board="board"></FocusBoardEntry>
  </div>
</template>

<script>
import FocusBoardEntry from './FocusBoardEntry'

export default {
  name: "MyFocusBoards",
  components: {FocusBoardEntry: FocusBoardEntry},
  data() {
    return {
      boards: ''
    }
  },
  created() {
    this.axios.get(`/getFocusBoards`).then(res => {
      if (res.data.code === 200) {
        this.boards = res.data.data
      }
    }).catch(e => {
      alert('服务器故障')
    })
  },
}
</script>

<style scoped>

</style>
