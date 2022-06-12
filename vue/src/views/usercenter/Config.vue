<template>
  <div>
    <h3>修改头像</h3>
    <el-row>
      <el-avatar :src=profile.avatarUrl></el-avatar>
      <el-upload
        class="upload-demo"
        action="https://jsonplaceholder.typicode.com/posts/"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :before-remove="beforeRemove"
        multiple
        :limit="1"
        :on-exceed="handleExceed"
        :file-list="fileList">
        <el-button size="small" type="primary">选择本地图片</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>
    </el-row>
    <hr>
    <h3>修改签名档</h3>
    <el-row>
      <v-md-editor v-model="signature" height="100%" :disabled-menus="[]"
                   @upload-image="onUploadImage"></v-md-editor>
      <el-button type="primary" @click="onSubmit">保存</el-button>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Config",
  data() {
    return {
      profile: '',
      signature: ''
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    onSubmit() {
      let param = new FormData()
      param.append('signature', this.signature)
      this.axios.post(`/updateSignature`, param).then(res => {
        if (res.data.code === 200) {
          this.$message({
            message: '保存成功',
            type: 'success'
          })
        }
      }).catch(e => {
        alert('服务器错误')
      })
    }
  },
  created() {
    this.axios.get(`/getOwnProfile`).then(res => {
      if (res.data.code === 200) {
        this.profile = res.data.data
        this.signature = this.profile.signature
      }
    }).catch(e => {
      alert('服务器故障')
    })
  }

}
</script>

<style scoped>

</style>
