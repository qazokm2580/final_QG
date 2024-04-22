<script>
import { ElButton } from 'element-plus'
export default {
  components: { ElButton },
}
</script>

<template>
  <el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="用户名">
      <el-input v-model="form.username"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input type="password" v-model="form.password"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('form')">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      form: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('/api/login', this.form)
              .then(response => {
                if (response.data.success) {
                  this.$message.success('登录成功')
                } else {
                  this.$message.error('登录失败')
                }
              })
        } else {
          this.$message.error('请填写完整的表单')
          return false
        }
      })
    }
  }
}
</script>