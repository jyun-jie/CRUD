<template>
  <h3>{{route.params.Id}}</h3>
  <el-form :model="delData" >
    <el-form-item label="名稱" prop="username">
        <el-text >{{delData.username}}</el-text>
      </el-form-item>
      <el-form-item label="密碼" prop="password">
        <el-text >{{delData.password}}</el-text>
      </el-form-item>
      <el-form-item label="生日" prop="birthday">
        <el-text >{{delData.birthday}}</el-text>
      </el-form-item>
    <el-form-item>
          <el-button type="primary" @click="delForm" >certain</el-button>
          <el-button type="danger" @click="userpush" >cancel</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
  import { useRoute , useRouter} from 'vue-router'
  import { onMounted , ref} from 'vue'
  import axios from 'axios'
  const route = useRoute()
  const router = useRouter()
  const delData = ref([])

  const config = {
    headers: {
    'Content-Type': 'application/json',
  }
  }

  onMounted (()=>{
    axios.get("http://localhost:8088/user/update/"+route.params.Id).then((response)=>{
      delData.value = response.data
    }).catch((error)=>{
      console.log(error);
    })
  })

  const userpush=(()=>{
    router.push("/user")
  })

  const delForm = async () => {
    try{
    const response = await axios.delete("http://localhost:8088/user?id="+route.params.Id)
    console.log(response)
    userpush()
    }
    catch(error){
        console.log(error);
    }
  }


</script>