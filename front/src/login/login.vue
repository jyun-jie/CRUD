<template>
    <div>
        <el-form
                :rules="rules"
                ref="loginFormRef"
                v-loading="loading"
                element-loading-text="正在登录..."
                element-loading-spinner="el-icon-loading"
                element-loading-background="rgba(0, 0, 0, 0.8)"
                :model="loginForm"
                class="loginContainer">
            <h3 class="loginTitle">系统登录</h3>
            <el-form-item prop="username">
                <el-input size="normal" type="text" v-model="loginForm.username" auto-complete="off"
                          placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input size="normal" type="password" v-model="loginForm.password" auto-complete="off"
                          placeholder="请输入密码"></el-input>
            </el-form-item>
            <!--<el-form-item prop="code">
                <el-input size="normal" type="text" v-model="loginForm.code" auto-complete="off"
                          placeholder="点击图片更换验证码" @keydown.enter.native="submitLogin" style="width: 250px"></el-input>
                <img :src="vcUrl" @click="updateVerifyCode" alt="" style="cursor: pointer">
            </el-form-item>
            <el-checkbox size="normal" class="loginRemember" v-model="checked"></el-checkbox>-->
            <el-button size="normal" type="primary" style="width: 100%;" @click="submitLogin">登录</el-button>
        </el-form>
    </div>
</template>

<script setup>
import {reactive, ref } from 'vue'
import axios from 'axios'
import {useRouter} from 'vue-router'
import store from '@/store'

    const router = useRouter()
    const loading = ref(false)
    const vcUrl =ref('/verifyCode?time='+new Date())
    const checked = ref(true)
    const rules = ref({
      username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
      password: [{required: true, message: '请输入密码', trigger: 'blur'}],
      //code: [{required: true, message: '请输入验证码', trigger: 'blur'}]
    })
    const loginForm= reactive( {
          username: 'javaboy',
          password: '123',
          //code:''
    })

    const config = {
        headers: {
        'Content-Type': 'application/json',
    }
    }

    const  loginFormRef = ref(null)

    const submitLogin =(()=> {
        loginFormRef.value.validate(async(valid) => {
          if (valid) {
              let response = await axios.post('http://localhost:8088/login?username='+loginForm.username+'&password='+loginForm.password,config.value)
              if(response.data == "admin" || response.data == "user"){
                const token = response.data;
                //設置token 的值
                store.commit('SET_TOKEN',token)
                router.push("/home")
              }
          } else {
              router.push("/")
          }
      });
    })
</script>


<style>
    .loginContainer {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 15px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .loginTitle {
        margin: 15px auto 20px auto;
        text-align: center;
        color: #505458;
    }

    .loginRemember {
        text-align: left;
        margin: 0px 0px 15px 0px;
    }
    .el-form-item__content{
        display: flex;
        align-items: center;
    }
</style>