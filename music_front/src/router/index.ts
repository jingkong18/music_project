// src/router/index.ts
import { createRouter, createWebHashHistory } from 'vue-router'

// 导入页面组件（TS 会自动校验组件类型）
import Home from '@/views/home/index.vue'
import Login from '@/views/login/index.vue'


// 创建路由实例
const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL), // 基于 hash 模式的路由
  routes: [
    { path: '/', name: 'Home', component: Home }, // 首页
    { path: '/login', name: 'Login', component: Login } // 登录页
  ]
});

export default router;