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
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="消息提醒">
              <el-switch v-model="form.delivery"></el-switch>
            </el-form-item>
            <el-form-item label="主题内容">
              <el-button type="primary" plain>切换编辑模式</el-button>
            </el-form-item>
            <v-md-editor v-model="form.desc" height="100%" :disabled-menus="[]" @upload-image="onUploadImage"></v-md-editor>
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
        name: '',
        delivery: true,
        desc: ''
      }
    };
  }
  ,
  methods: {
    onUploadImage(event, insertImage, files) {
      const file = files[0],
        index = path.lastIndexOf("\/")
      let param = new FormData()
      param.append('image', file)
      axios.post(`/uploadfile`, param).then(r => {
        if(r.data.code == 200) {
          const last = path.slice(0, index)
          insertImage({
            url: last + r.data.data.url,
            desc: files[0].name
          })
        }
      }).catch(e => {
        this.Toast(2, '图片上传出了点小问题，请稍后重试')
      })
    },
    onSubmit() {
      console.log('submit!');
    }
  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

</style>
