<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="ruleForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password" type="password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>

export default {
  name: 'Login',
  data() {
    return {
      ruleForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.axios({
            method: 'post',
            url: '/login',
            params: {
              username: this.ruleForm.username,
              password: this.ruleForm.password
            }
          }).then((res) => {
            console.log(res.data.code)
            if (res.data.code === '200') {
              localStorage.token = res.data.token;
              this.$message({
                message: '登录成功',
                type: 'success'
              });
              localStorage.username = res.data.username;
              localStorage.uid = res.data.uid;
              localStorage.uavatar = res.data.uavatar;
              this.$router.push('/');
            } else {
              this.$message.error('用户名或密码错误');
            }
          })
            .catch((error) => {
              console.log(error);
            });
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
