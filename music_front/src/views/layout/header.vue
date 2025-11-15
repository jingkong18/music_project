<template>
  <div class="header-bar">
    <!-- 左侧区域 -->
    <div class="left-section">
    <!-- 左侧返回按钮 -->
     <el-button type="text" :icon="Back" class="back-btn" />
      <!-- 搜索框区域 -->
      <el-input
        v-model="searchValue"
        placeholder="搜索"
        :prefix-icon="Search"
        class="search-input"
      />
    </div>

    <!-- 右侧功能区 -->
    <div class="right-section">
      <!-- 用户信息 -->
      <el-avatar :size="32" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" class="avatar" />
      <span class="nickname">菁箜</span>
      <el-tag type="danger" effect="dark" class="vip-tag">VIP·肆</el-tag>
      <el-icon class="dropdown-icon"><ArrowDown /></el-icon>

      <!-- 功能图标 -->
      <el-button type="text" :icon="Message" class="icon-btn">
        <el-badge is-dot />
      </el-button>
      <el-button type="text" :icon="Setting" class="icon-btn" />
      <el-button type="text" :icon="Grid" class="icon-btn" />
      <el-button type="text" :con="Minus" class="icon-btn" />
      <el-button type="text" :icon="FullScreen" class="icon-btn" />
      <el-button type="text" :icon="Close" class="icon-btn" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import {
  Back, Search, Message, Setting, Grid,
  Minus, FullScreen, Close, ArrowDown
} from '@element-plus/icons-vue';

const searchValue = ref('张杰');
</script>

<style scoped>
.header-bar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 56px;
  padding: 0 24px;
  background-color: #fff;
}



.back-btn {
  font-size: 18px;
  color: #666;
}

.search-input {
  margin-left: 20px; 
  width: 300px;
  border-radius: 20px;
  border: 1px solid #eee;
  margin-right: 8px;
}

.mic-btn {
  background-color: #f5f7fa;
  border-radius: 50%;
  width: 36px;
  height: 36px;
  display: flex;
  align-items: center;
  justify-content: center;
}


.avatar {
  margin-right: 8px;
}

.nickname {
  margin-right: 6px;
  font-size: 14px;
  color: #333;
}

.vip-tag {
  height: 20px;
  line-height: 20px;
  font-size: 10px;
  padding: 0 4px;
  margin-right: 8px;
}

.dropdown-icon {
  font-size: 14px;
  color: #999;
  margin-right: 12px;
}

.icon-btn {
  margin-left: 16px;
  font-size: 16px;
  color: #666;
}
</style>