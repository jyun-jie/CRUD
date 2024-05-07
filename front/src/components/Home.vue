<template>
    <el-menu
    mode="horizontal"
    >
      <el-menu-item index="1" @click="pushHome">Home</el-menu-item>
      <el-menu-item index="2" @click="istouch">菜單</el-menu-item>
    </el-menu>

    <el-menu
      default-active="2"
      class=" menu"
      v-if="touch == true"
    >
      <el-sub-menu index="1">
        <template #title>
          <el-icon><location /></el-icon>
          <span>管理中心</span>
        </template>
        <el-menu-item-group title="Manage">
            <el-menu-item  @click="ReadHandler">Read</el-menu-item>
            <el-menu-item  @click="toCreate">Create</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>
      <el-menu-item index="2">
        <el-icon><icon-menu /></el-icon>
        <span>關於</span>
      </el-menu-item>
    </el-menu>
    <div><h3> this is home</h3></div>
    <div v-if="touch == true" class="router-view-wrapper">
      <router-view></router-view>
    </div>
    <div v-else>
      <router-view></router-view>
    </div>

</template>

<script setup>
import Read from './Read.vue'
  import Create from './Create.vue'
  import Update from './Update.vue'
  import { onBeforeMount, onMounted, reactive , ref } from 'vue';
  import axios from 'axios';
  import {  Document,  Menu as IconMenu,  Location,  Setting,} from '@element-plus/icons-vue'
  import { useRouter} from 'vue-router'
 
  const touch = ref(false)
  const authen = ref(false)
  const router = useRouter()

  const handleOpen = (key, keyPath) => {
  console.log(key, keyPath)
  }
  const handleClose = (key, keyPath) => {
    console.log(key, keyPath)
  }


  const pushHome = (()=>{
    router.push("/home")
    console.log("tohome")
  })

  const istouch=(()=>{
    touch.value = !touch.value
    console.log(touch)
    return touch
  })

  const ReadHandler = (()=>{
    router.push("/user")
  })

  const toCreate = (()=>{
    router.push("/create")
  })
</script>