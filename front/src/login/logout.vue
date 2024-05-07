<template>
  <button @click = "logout">確定登出</button>

</template>

<script setup>
  import axios from 'axios'
  import store from '@/store'
  import { useRouter } from 'vue-router'

  const router = useRouter()
  const config = {
    headers: {
    'Content-Type': 'application/json',
  }
  }
  const logout=(()=>{
      axios.get('http://localhost:8088/logout',config.value)
        .then(response => {
          // 处理响应，注销成功
          console.log('Logout successful:', response.data)
          store.commit('DET_TOKEN')
          router.push('/')
        })
        .catch(error => {
          // 处理错误，注销失败
          console.error('Error during logout:', error)
        })
  })
  

</script>