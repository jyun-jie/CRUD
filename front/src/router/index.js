import { createRouter, createWebHistory } from 'vue-router'
import Read from '../components/Read.vue'


const routes = 
    [
      {
        path: '/',
        name: 'login',
        component: () => import('../login/login.vue')
      },
      {
        path: '/home',
        name: 'home',
        component : ()=> import('../components/Home.vue')
      },
      { 
        path: '/user',
        component: Read,
      },
      {
        path: '/user/update/:Id',
        name : 'updateUser',
        component: () => import('../components/Update.vue'),
      },
      {
        path: '/create',
        component: ()=>import('../components/Create.vue')
      },
      {
        path: '/user/del/:Id',
        component: ()=>import('../components/delete.vue')
      },
      {
        path: '/logout',
        component: ()=>import('../login/logout.vue')
      },
    ]


const router = createRouter({
  // 4. Provide the history implementation to use. We
  // are using the hash history for simplicity here.
  history: createWebHistory(),
  routes // short for `routes: routes`
});


/*router.beforeEach((to, from, next) => {
  if (to.path == '/') {
      next();
  } else {
      if (window.sessionStorage.getItem("user")) {
          initMenu(router, store);
          next();
      } else {
          next('/?redirect=' + to.path);
      }
  }
})*/

router.beforeEach((to, from, next) => {
  //獲取 sessionStorage token值 如果為ok
  const isAuthenticated = sessionStorage.getItem("token");
  /* 根据您的认证逻辑判断用户是否已登录，例如从后端获取用户信息或检查本地存储中的用户凭证 */

  //如果為ok 就過關
  if (to.name !== 'login' && isAuthenticated!="admin" && isAuthenticated!="user") {
    next({ name: 'login' }) // 未登录用户访问非登录页面时重定向到登录页面
  } else {
    next() // 已登录用户或访问登录页面时放行
  }
})


export default router