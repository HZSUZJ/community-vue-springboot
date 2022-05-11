<template>
  <div>
    <div id="nt-root" style="margin-top: 50px">
      <div class="el-row">
      </div>
      <div id="tp-area">
        <el-row>
          <el-col :span="6">
            <div class="grid-content bg-purple"></div>
          </el-col>
          <!--          帖子标题-->
          <el-col :span="12">
            <div class="grid-content">
              <el-row
                style="padding: 10px;border: 1px;box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
                <el-col :span="12">
                  <div class="grid-content">
                    <el-row style="text-align: left;padding-left: 15px">
                      {{ topic.title }}
                    </el-row>
                    <el-row style="text-align: left">
                      <i class="el-icon-time" style="margin-right: 10px">{{ topic.gmtCreate | formatDate }}</i>
                      <i class="el-icon-view" style="margin-right: 10px">5</i>
                    </el-row>
                  </div>
                </el-col>
                <el-col :span="4">
                  <div class="grid-content">
                    <span>{{ topic.board }}</span>
                  </div>
                </el-col>
              </el-row>
              <!--              内容区-->
              <commentEntry :comment="topic"></commentEntry>
              <!--              评论区-->


              <!--              写评论区-->

              <v-md-editor v-model="comment" height="100%" :disabled-menus="[]"
                           @upload-image="onUploadImage"></v-md-editor>

              <el-button type="primary" @click="onSubmit">回复</el-button>
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
import commentEntry from './CommentEntry'

export default {
  name: "TopicDetail",
  data() {
    return {
      topic: '',
      comment: ''
    }
  },
  methods: {
    onUploadImage(event, insertImage, files) {
      const file = files[0]
      let param = new FormData()
      param.append('file', file)
      this.axios.post(`/uploadFile`, param).then(r => {
        if (r.data.code == 200) {
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
      param.append("board", 1)
      this.axios.post(`/publish`, param).then(res => {
        if (res.data.code == 200) {
          alert("发表成功")
        }
      }).catch(e => {
        alert('发表失败')
      })
    }
  },
  created() {
    let param = new FormData()
    param.append("id", this.$route.params.id)
    this.axios.get(`/getTopicDetail/${this.$route.params.id}`).then(res => {
      if (res.data.code === 200) {
        console.log(res.data.data)
        this.topic = res.data.data
      }
    }).catch(e => {
      alert('服务器故障')
    })
  },
  components: {
    commentEntry: commentEntry
  },
  filters: {
    formatDate: function (value) {
      let curdate = new Date().getTime();
      let dffdate = (curdate - value) / 1000 / 60;
      if (dffdate < 60) {
        if (parseInt(dffdate) === 0)
          return '刚刚';
        return parseInt(dffdate) + '分钟前';
      }
      let date = new Date(value);
      let y = date.getFullYear();
      let MM = date.getMonth() + 1;
      MM = MM < 10 ? ('0' + MM) : MM;
      let d = date.getDate();
      d = d < 10 ? ('0' + d) : d;
      let h = date.getHours();
      h = h < 10 ? ('0' + h) : h;
      let m = date.getMinutes();
      m = m < 10 ? ('0' + m) : m;
      let s = date.getSeconds();
      s = s < 10 ? ('0' + s) : s;
      return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;
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
