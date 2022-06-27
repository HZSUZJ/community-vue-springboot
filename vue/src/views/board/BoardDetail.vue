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
              <el-container>
                <el-header>
                  <el-row>
                    <el-col :span="12">
                      {{ board.name }}
                    </el-col>
                    <el-col :span="6">
                      <span>今日贴数:{{ board.todayCount }}</span>
                      <span>总主题数:{{ board.postCount }}</span>
                    </el-col>
                    <el-col :span="3">
                      <el-button @click="doFollowBoard" v-if="board.isFollowBoard===false">关注</el-button>
                      <el-button @click="doCancelFollowBoard" v-if="board.isFollowBoard===true">取关</el-button>
                    </el-col>


                  </el-row>
                </el-header>
                <el-main>{{ board.description }}</el-main>
              </el-container>
              <el-button type="primary" @click="onButton">发主题</el-button>

              <!--              分页-->
              <el-row>
                <div class="block">
                  <el-pagination
                    background
                    @current-change="changePage"
                    :current-page.sync="currentPage"
                    :page-size="20"
                    layout="prev, pager, next, jumper"
                    :total="total">
                  </el-pagination>
                </div>
              </el-row>
              <!--              表格显示帖子-->
              <el-row>
                <el-table
                  :data="tableData"
                  style="width: 100%"
                  :row-class-name="tableRowClassName"
                  @row-click="clickOnRow">
                  <el-table-column
                    prop="title"
                    label="标题"
                    width="180"
                    align="left"
                    header-align="center">
                  </el-table-column>
                  <el-table-column
                    prop="user.username"
                    label="作者"
                    width="180"
                    align="center"
                    header-align="center">
                  </el-table-column>
                  <el-table-column
                    prop="views"
                    label="点击"
                    width="180"
                    align="center"
                    header-align="center">
                  </el-table-column>
                  <el-table-column
                    prop="commentCount"
                    label="回复"
                    width="180"
                    align="center"
                    header-align="center">
                  </el-table-column>
                  <el-table-column
                    prop="lastReply"
                    label="最新回复"
                    align="center"
                    header-align="center">
                  </el-table-column>
                </el-table>
              </el-row>
              <!--              分页-->
              <el-row>
                <div class="block">
                  <el-pagination
                    background
                    @current-change="changePage"
                    :current-page.sync="currentPage"
                    :page-size="20"
                    layout="prev, pager, next, jumper"
                    :total="total">
                  </el-pagination>
                </div>
              </el-row>

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
export default {
  name: "BoardDetail",
  methods: {
    changePage(val) {
      this.currentPage = val.toString()
      this.$router.push({path: `/board/${this.board.id}/${val}`})
      this.axios.get(`/topicsByBoardAndPage/${this.$route.params.id}/${this.currentPage}`).then(res => {
        if (res.data.code === 200) {
          this.tableData = res.data.data
        }
      }).catch(e => {
        alert('服务器故障')
      })
    },
    clickOnRow(val) {
      this.$router.push({path: `/topic/${val.id}`})
    },
    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    },
    onButton() {
      this.$router.push({path: `/postTopic/${this.$route.params.id}`})
    },
    doFollowBoard() {
      this.axios.get(`/addFollowBoard/${this.board.id}`).then(res => {
        if (res.data.code === 200) {
          this.board.isFollowBoard = true
        }
      }).catch(e => {
        alert('服务器故障')
      })
    },
    doCancelFollowBoard() {
      this.axios.get(`/deleteFollowBoard/${this.board.id}`).then(res => {
        if (res.data.code === 200) {
          this.board.isFollowBoard = false
        }
      }).catch(e => {
        alert('服务器故障')
      })
    }
  },
  data() {
    return {
      tableData: [{}],
      currentPage: '1',
      board: '',
      topics: '',
      total: ''
    }
  },
  created() {
    if (this.$route.params.page != null) {
      this.currentPage = this.$route.params.page
    }
    this.axios.get(`/board/${this.$route.params.id}`).then(res => {
      if (res.data.code === 200) {
        this.board = res.data.data
      }
    }).catch(e => {
      alert('服务器故障')
    })
    this.axios.get(`/getTopicTotalCountByBoardId/${this.$route.params.id}`).then(res => {
      if (res.data.code === 200) {
        this.total = res.data.total
      }
    }).catch(e => {
      alert('服务器故障')
    })
    this.axios.get(`/topicsByBoardAndPage/${this.$route.params.id}/${this.currentPage}`).then(res => {
      if (res.data.code === 200) {
        this.tableData = res.data.data
      }
    }).catch(e => {
      alert('服务器故障')
    })
  }
}
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}

.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
