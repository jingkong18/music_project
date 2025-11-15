// src/router/index.ts
import { createRouter, createWebHashHistory } from 'vue-router'

// 创建路由实例
const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL), // 基于 hash 模式的路由
  routes: [
    { path: '/', 
      name: 'Layout',
      children: [
       { 
        path: '/recommend',
        name: 'Recommend',
        component: () => import('@/views/recommend/index.vue') },

] }  ]
});

export default router;