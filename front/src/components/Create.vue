<template>
  <div >
    <el-form :model="formData" :rules="rules">
      <el-form-item label="名稱" prop="username">
        <el-input v-model="formData.username"/>
      </el-form-item>
      <el-form-item label="密碼" prop="password">
        <el-input
          v-model="formData.password"
          type="password"
        />
      </el-form-item>
      <el-form-item label="生日" prop="birthday">
        <el-input v-model="formData.birthday" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitFormHandler" >Submit</el-button>
        <el-button type="danger" @click="userpush" >cancel</el-button>
      </el-form-item>
    </el-form>
    <hr>
  </div>
</template>

<script setup>
  import axios from 'axios'
  import {ref } from 'vue'
  import {useRouter} from 'vue-router'

  const router = useRouter()
  const formData = ref({
    username: '',
    password: '',
    birthday: '',
  });

  

  const config = {
    headers: {
    'Content-Type': 'application/json',
  }
  }

  const userpush=(()=>{
    router.push("/user")
  })


  const submitFormHandler = async () => {
    try{
        const responsesub = await axios.post('http://localhost:8088/user',formData.value,config.value);
        console.log(responsesub);
        userpush()
      } catch(error){
        console.log(error);
      }
  }

  const rules = {
    username: [
      { required: true, message: '請輸入帳號', trigger: 'blur' }
    ],
    password: [
      { required: true, message: '請輸入密碼', trigger: 'blur' }
    ]
  };


</script>