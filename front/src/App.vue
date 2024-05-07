<template>
    <div id="app">
      <router-view/>
    </div>
    
    
</template>


<script setup>


</script>

<style>
  .nav {
    width: 70%;
    height: 100%;
    background-color: #ffffff;
    flex-direction: column;
    align-items: center;
  }
  .router-view-wrapper {
  /* 根据需要添加样式 */
    margin-top: 0px;
    margin-left: 20%;
    width: 60%;
  }
  .menu{
    position: fixed;
    width: 19%;
    top :70px;
  }

</style>
