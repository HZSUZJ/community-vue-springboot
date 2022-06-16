<template>
  <div id="BoardList">
    <h1>版面列表</h1>
    <el-row>
      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>

      <el-col :span="12">
        <div class="grid-content">
          <el-row>
            <boardListEntry v-for="board in boards" :board="board"></boardListEntry>
          </el-row>
        </div>
      </el-col>

      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import boardListEntry from './BoardListEntry'


export default {
  name: "BoardList",
  data() {
    return {
      boards: ''
    }
  },
  created() {
    this.axios.get(`/getBoardList`).then(res => {
      if (res.data.code === 200) {
        console.log(res.data.data)
        this.boards = res.data.data
      }
    }).catch(e => {
      alert('服务器故障')
    })
  },
  components: {
    boardListEntry: boardListEntry
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
  color: #333;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
