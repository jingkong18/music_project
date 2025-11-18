<template>
  <div class="player-footer">
    <!-- 左侧歌曲信息区 - 修改为始终显示，使用默认值 -->
    <div class="left-section"  @click="gotoDetail(displaySong.id)">
      <div class="album-cover">
        <img :src="displaySong.cover" alt="专辑封面" />
      </div>
      <div class="song-info">
        <div class="song-name">{{ displaySong.title }} - {{ displaySong.artist }}</div>
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
        <el-button type="text" :icon="ArrowLeft" class="control-btn" @click="prevSong" />
        <el-button 
          type="text" 
          :icon="isPlaying ? 'Pause' : 'CaretRight'" 
          class="play-btn" 
          @click="togglePlay" 
        />
        <el-button type="text" :icon="ArrowRight" class="control-btn" @click="nextSong" />
        <el-button type="text" :icon="Refresh" class="control-btn" />
      </div>
      <div class="progress-bar">
        <span class="time">00:00</span>
        <el-slider v-model="progress" class="slider" :disabled="!isPlaying" />
        <span class="time">{{ displaySong.duration }}</span>
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
import { ref, computed } from 'vue';
import { usePlayerStore } from '@/store/song';
import {
  Plus, DeleteFilled, RefreshRight, Headset, Star, ArrowLeft, ArrowRight,
  Refresh, Document,User, Grid
} from '@element-plus/icons-vue';
import { useRouter } from 'vue-router';

// 创建路由实例
const router = useRouter();

// 跳转详情页
const gotoDetail = (id: string) => {
  router.push({ name: 'SongDetail', params: { id } });
};



// 创建store实例
const playerStore = usePlayerStore();

// 计算属性获取当前播放歌曲，添加默认值
const currentSong = computed(() => playerStore.currentSong);
const isPlaying = computed(() => playerStore.isPlaying);
const progress = ref(0); // 进度条百分比，默认为0

// 提供默认值的显示歌曲计算属性
const displaySong = computed(() => {
  if (!currentSong.value) {
    // 当没有歌曲时，返回默认的"未知"信息
    return {
      id: '1',
      title: '未知歌曲',
      artist: '未知艺术家',
      album: '未知专辑',
      cover: 'https://picsum.photos/40/40?random=999', // 默认封面
      duration: '00:00'
    };
  }
  return currentSong.value;
});

// 添加播放控制方法
const togglePlay = () => {
  if (currentSong.value) {
    playerStore.togglePlay();
  }
};

const prevSong = () => {
  if (currentSong.value) {
    playerStore.prevSong();
  }
};

const nextSong = () => {
  if (currentSong.value) {
    playerStore.nextSong();
  }
};
</script>

<style scoped>
/* 原有样式保持不变 */
.player-footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 90px;
  padding: 0 24px;
  background-color: #fff;
  border-top: 1px solid #eee;
 
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 999;
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