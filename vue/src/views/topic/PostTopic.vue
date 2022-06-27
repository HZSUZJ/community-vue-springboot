<template>
  <div>
    <el-row>
      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="主题标题">
              <el-input v-model="form.title"></el-input>
            </el-form-item>
            <el-form-item label="消息提醒">
              <el-switch v-model="form.delivery"></el-switch>
            </el-form-item>
            <el-form-item label="主题内容">
              <!--              <el-button type="primary" plain>切换编辑模式</el-button>-->
            </el-form-item>
            <v-md-editor v-model="form.content" height="100%" :disabled-menus="[]"
                         @upload-image="onUploadImage"></v-md-editor>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">发帖</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "postTopic",
  data() {
    return {
      form: {
        title: '',
        delivery: true,
        content: ''
      }
    };
  }
  ,
  methods: {
    onUploadImage(event, insertImage, files) {
      const file = files[0]
      let param = new FormData()
      param.append('file', file)
      this.axios.post(`/uploadFile`, param).then(r => {
        if (r.data.code === 200) {
          insertImage({
            url: r.data.url,
            desc: files[0].name
          })
        }
      }).catch(e => {
        alert('图片上传出了点小问题，请稍后重试')
      })
    },
    onSubmit() {
      let param = new FormData()
      param.append("title", this.form.title)
      param.append("content", this.form.content)
      param.append("notify", this.form.delivery)
      param.append("board", this.$route.params.id)
      this.axios.post(`/publish`, param).then(res => {
        if (res.data.code === 200) {
          this.$router.push({path: `/topic/${res.data.topicId}`})
        }
      }).catch(e => {
        alert('发表失败')
      })

    }
  }
}
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

</style>
