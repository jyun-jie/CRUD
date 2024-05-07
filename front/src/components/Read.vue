<template>

  <el-table :data="tableData.value" style="width: 100%">
    <el-table-column prop="id" label="編號" width="100" />
    <el-table-column prop="username" label="名稱" width="100" />
    <el-table-column prop="birthday" label="生日" width="140" />
    <el-table-column label="操作" width="200" >
    <template v-slot="{ row }">
      <el-button type="primary" @click="goToUpdate(row)">更新</el-button>
      <el-button type="danger" @click="godel(row)">刪除</el-button>
    </template>
    </el-table-column>

  </el-table>
  <router-view></router-view>
  <hr>
</template>

<script setup>
  import axios from 'axios'
  import {reactive , onMounted  } from 'vue'
  import ElementPlus from 'element-plus'
  import { useRouter } from 'vue-router';
  import Update from './Update.vue'

  const router = useRouter();

  const tableData = reactive([])
  /*const props = defineProps(['data'])*/

  const config = {
    headers: {
    'Content-Type': 'application/json',
  }
  }


  const goToUpdate=((row)=>{
    router.push("/user/update/"+row.id)
  })

  const godel = ((row)=>{
    router.push("/user/del/"+row.id)
  })
 
  onMounted(()=>{
    axios.get("http://localhost:8088/user").then((response)=>{
      console.log(response.data);
      tableData.value = response.data
    }).catch((error)=>{
      console.log(error);
    })
  })

</script>

<style scoped>
  .router-link {
    /* 清除 router-link 默认样式 */
    text-decoration: none;
    color: inherit;
  }
</style>

