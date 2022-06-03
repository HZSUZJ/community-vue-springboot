<template>
  <div>
    <el-row>
      <el-col :span="6">
        <el-avatar>{{ board.name }}</el-avatar>
      </el-col>
      <el-col :span="12">
        <span>{{ board.name }}</span><br>
        <span>今日贴数：{{ board.todayCount }}</span>/
        <span>总贴数：{{ board.postCount }}</span>
      </el-col>
      <el-col :span="6">
        <el-button v-if="board.isFollowBoard===true" @click="doCancelFollowBoard(`${board.id}`)">取消关注</el-button>
        <el-button v-if="board.isFollowBoard===false" @click="doFollowBoard(`${board.id}`)">重新关注</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "FocusBoardEntry",
  props: ['board'],
  methods: {
    doFollowBoard(id) {
      this.axios.get(`/addFollowBoard/${id}`).then(res => {
        if (res.data.code === 200) {
          this.board.isFollowBoard = true
        }
      }).catch(e => {
        alert('服务器故障')
      })
    },
    doCancelFollowBoard(id) {
      this.axios.get(`/deleteFollowBoard/${id}`).then(res => {
        if (res.data.code === 200) {
          this.board.isFollowBoard = false
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
