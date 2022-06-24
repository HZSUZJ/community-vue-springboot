<template>
  <div>
    <div id="nt-root">
      <div class="el-row">

      </div>
      <div id="tp-area" style="margin-top: 20px">
        <el-row>
          <el-col :span="6">
            <div class="grid-content bg-purple"></div>
          </el-col>
          <!--          帖子主体-->
          <el-col :span="12">
            <div class="grid-content">

              <topicEntry v-for="topic in topics" :topic="topic"></topicEntry>

            </div>
          </el-col>


          <el-col :span="6">
            <div class="grid-content bg-purple"></div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>


</template>

<script>
import topicEntry from './TopicEntry'

export default {
  name: "NewTopics",
  data() {
    return {
      topics: ''
    }
  },
  created() {
    this.axios.get(`/getNewTopics`).then(res => {
      if (res.data.code === 200) {
        this.topics = res.data.data
      }
    }).catch(e => {
      alert('服务器故障')
    })
  },
  components: {
    topicEntry: topicEntry
  }
}
</script>

<style scoped>

.infinite-list-item {
  list-style: none;
  margin: 0 auto;
  width: 1140px;
  height: 80px;
  margin-bottom: 80px;
  border: 1px solid #79b8ca;
}


.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

</style>
