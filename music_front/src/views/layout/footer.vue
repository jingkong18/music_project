<template>
  <div class="player-footer">
    <!-- 左侧歌曲信息区 -->
    <div class="left-section">
      <div class="album-cover">
        <img src="https://p1.music.126.net/7P01229zDZkGjGm2c13D9A==/109951168388004340.jpg" alt="专辑封面" />
      </div>
      <div class="song-info">
        <div class="song-name">听妈妈的话 - 周杰伦</div>
        <div class="actions">
          <el-button type="text" :icon="Plus" class="action-btn" />
          <el-button type="text" :icon="DeleteFilled" class="action-btn" />
          <el-button type="text" :icon="RefreshRight" class="action-btn" />
          <el-button type="text" :icon="Headset" class="action-btn" />
        </div>
      </div>
    </div>

    <!-- 中间控制区 -->
    <div class="control-section">
      <div class="control-buttons">
        <el-button type="text" :icon="Star" class="control-btn" />
        <el-button type="text" :icon="ArrowLeft" class="control-btn" />
        <el-button type="text" :icon="CaretRight" class="play-btn" />
        <el-button type="text" :icon="ArrowRight" class="control-btn" />
        <el-button type="text" :icon="Refresh" class="control-btn" />
      </div>
      <div class="progress-bar">
        <span class="time">01:38</span>
        <el-slider v-model="progress" class="slider" />
        <span class="time">04:25</span>
      </div>
    </div>

    <!-- 右侧功能区 -->
    <div class="right-section">
      <el-button type="text" :icon="Document" class="func-btn">本地</el-button>
      <el-button type="text" class="func-btn">词</el-button>
      <el-button type="text" :icon="User" class="func-btn" />
      <el-button type="text" :icon="Headset" class="func-btn" />
      <el-button type="text" :icon="Grid" class="func-btn" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import {
  Plus, DeleteFilled, RefreshRight, Headset,Star,ArrowLeft,ArrowRight,
  Refresh, Document,CaretRight, User, Grid
} from '@element-plus/icons-vue';

const progress = ref(30); // 进度条百分比
</script>

<style scoped>
.player-footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 90px;
  padding: 0 24px;
  background-color: #fff;
  border-top: 1px solid #eee;
 
  /* 原有样式保持不变 */
  position: fixed;   /* 固定定位，脱离文档流 */
  bottom: 0;         /* 距离屏幕底部 0px */
  left: 0;           /* 距离屏幕左侧 0px */
  right: 0;          /* 距离屏幕右侧 0px */
  z-index: 999;      /* 提高层级，避免被其他元素覆盖 */
}


/* 左侧歌曲信息 */
.left-section {
  display: flex;
  align-items: center;
}

.album-cover {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  overflow: hidden;
  margin-right: 12px;
}

.album-cover img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.song-info {
  display: flex;
  flex-direction: column;
}

.song-name {
  font-size: 14px;
  font-weight: 500;
  margin-bottom: 4px;
}

.actions {
  display: flex;
}

.action-btn {
  margin-right: 8px;
  color: #999;
}

/* 中间控制区 */
.control-section {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.control-buttons {
  display: flex;
  align-items: center;
}

.control-btn {
  margin: 0 8px;
  color: #666;
}

.play-btn {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: #ff2d55;
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
}

.progress-bar {
  display: flex;
  align-items: center;
}

.time {
  font-size: 12px;
  color: #999;
  margin: 0 8px;
}

.slider {
  width: 400px;
}

/* 右侧功能区 */
.right-section {
  display: flex;
  align-items: center;
}

.func-btn {
  margin-left: 16px;
  color: #999;
}
</style>