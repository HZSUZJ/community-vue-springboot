<template>
  <a href="#" @click="getTopicDetail(board.id)">
    <el-col :span="6">
      <div class="grid-content bg-purple">
        <div>
          <el-avatar>{{ board.name }}</el-avatar>
          <span>{{ board.name }}</span><br>
          <span>今日贴数：{{ board.todayCount }}</span><br>
          <span>总贴数：{{ board.postCount }}</span>
        </div>
      </div>
    </el-col>
  </a>
</template>

<script>
export default {
  name: "BoardListEntry",
  props: ['board'],
  methods: {
    getTopicDetail(id) {
      this.$router.push({path: `/board/${id}`})
    }
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
  color: #333;
}
</style>
