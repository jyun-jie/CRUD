<template>
  <h3>{{route.params.Id}}</h3>
  <div >
    <el-form :model="updateData" >
      <el-form-item label="名稱" prop="username">
        <el-input v-model="updateData.username"/>
      </el-form-item>
      <el-form-item label="密碼" prop="password">
        <el-input
          v-model="updateData.password"
          type="password"
        />
      </el-form-item>
      <el-form-item label="生日" prop="birthday">
        <el-input v-model="updateData.birthday" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateForm" >Update</el-button>
        <el-button type="danger" @click="userpush" >cancel</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
  import axios from 'axios'
  import {ref , inject , onMounted} from 'vue'
  import { useRoute , useRouter} from 'vue-router'

  const updateData = ref({});
  const route = useRoute()
  const router = useRouter()

  onMounted (()=>{
    axios.get("http://localhost:8088/user/update/"+route.params.Id).then((response)=>{
      updateData.value = response.data
    }).catch((error)=>{
      console.log(error);
    })
  })

  const config = {
    headers: {
    'Content-Type': 'application/json',
  }
  }


  const userpush=(()=>{
    router.push("/user")
  })
  
  const updateForm = async () => {
    try{
    const response = await axios.post("http://localhost:8088/user/update",updateData.value,config.value)
    console.log(response)
    userpush()
    }catch(error){
        console.log(error);
    }
  }
  


</script>