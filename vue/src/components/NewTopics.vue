<template>
  <div>
    <div id="nt-root">
      <div class="el-row">

      </div>
      <div id="tp-area">
        <el-row>
          <el-col :span="6">
            <div class="grid-content bg-purple"></div>
          </el-col>
<!--          帖子主体-->
          <el-col :span="12">
            <div class="grid-content">
              <el-row style="padding: 10px;border: 1px;box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
                <el-col :span="6" style="background-color: #F2F6FC">
                  <div class="grid-content">
                    <el-container>
                      <el-aside width="50px">
                          <el-avatar> user</el-avatar>
                      </el-aside>
                      <el-main>用户名</el-main>
                    </el-container>
                  </div>
                </el-col>
                <el-col :span="12">
                  <div class="grid-content">
                      <el-row style="text-align: left;padding-left: 15px">
                        本易社死人看完98领npy的故事真的有点想冲matchus了
                      </el-row>
                    <el-row style="text-align: left">
                      <i class="el-icon-time" style="margin-right: 10px">1分钟前</i>
                      <i class="el-icon-view" style="margin-right: 10px">5</i>
                      <span style="margin-right: 10px">最后回复：匿名</span>
                      <span style="margin-right: 10px">5分钟前</span>
                    </el-row>
                  </div>
                </el-col>
                <el-col :span="4">
                  <div class="grid-content" >
                    <span>版面名字</span>
                  </div>
                </el-col>
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
  name: "NewTopics"
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
