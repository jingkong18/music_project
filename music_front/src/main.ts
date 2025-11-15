import { createApp } from 'vue'
import App from '@/App.vue'
import router from '@/router' // 导入路由模块
import pinia from '@/store' // 导入 Pinia 实例
import ElementPlus from 'element-plus'; // UI 组件库
import 'element-plus/dist/index.css'; // 样式
import * as ElementPlusIconsVue from '@element-plus/icons-vue'; // 图标

// 挂载路由
createApp(App).use(router).use(pinia).use(ElementPlus).mount('#app')

// 注册所有图标（TS 校验组件类型）
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  createApp(App).component(key, component);
}


