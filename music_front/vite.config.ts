import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import * as path from 'path'  // 导入path模块

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  // 添加别名配置
  resolve: {
    alias: {
      '@': path.resolve(__dirname, 'src'),
    },
  },
})
