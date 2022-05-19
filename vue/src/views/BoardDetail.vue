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
                <el-header>版面介绍</el-header>
                <el-main>一堆文字</el-main>
              </el-container>
              <el-button type="primary">发主题</el-button>

              <!--              分页-->
              <el-row>
                <div class="block">
                  <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="currentPage"
                    :page-size="20"
                    layout="prev, pager, next, jumper"
                    :total="1000">
                  </el-pagination>
                </div>
              </el-row>
              <!--              表格显示帖子-->
              <el-row>
                <el-table
                  :data="tableData"
                  style="width: 100%"
                  :row-class-name="tableRowClassName">
                  <el-table-column
                    prop="title"
                    label="标题"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="author"
                    label="作者"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="views"
                    label="点击"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="replyCount"
                    label="回复"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="lastReply"
                    label="最后回复">
                  </el-table-column>
                </el-table>
              </el-row>
              <!--              分页-->
              <el-row>
                <div class="block">
                  <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="currentPage"
                    :page-size="20"
                    layout="prev, pager, next, jumper"
                    :total="1000">
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
    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }
  },
  data() {
    return {
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄',
      }],
      currentPage: 1,
    }
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
