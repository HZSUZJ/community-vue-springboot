<template>
  <div>
    <el-row>
      <el-col :span="6">
        <el-avatar :src=followee.avatarUrl></el-avatar>
        {{ followee.username }}
      </el-col>
      <el-col :span="12">
        <span>贴数：{{ followee.topicNum }}</span>
        <span>粉丝：{{ followee.fansNum }}</span>
      </el-col>
      <el-col :span="6">
        <el-button class="replierBtn" style="float: left" v-if="followee.isFollowee===true"
                   @click="doCancelFollow(`${followee.id}`)">取关
        </el-button>
        <el-button class="replierBtn" style="float: left" v-if="followee.isFollowee===false"
                   @click="doFollow(`${followee.id}`)">关注
        </el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "FolloweeEntry",
  props: ['followee'],
  methods: {
    doFollow(id) {
      this.axios.get(`/addFollow/${id}`).then(res => {
        if (res.data.code === 200) {
          this.followee.isFollowee = true
        }
      }).catch(e => {
        alert('服务器故障')
      })
    },
    doCancelFollow(id) {
      this.axios.get(`/deleteFollow/${id}`).then(res => {
        if (res.data.code === 200) {
          this.followee.isFollowee = false
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
