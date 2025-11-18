<template>
  <div class="song-detail-page">
    <!-- 主体内容：黑胶唱片机 + 信息区 -->
    <div class="main-container">
      <!-- 黑胶唱片机区域 -->
      <div class="vinyl-area">
        <!-- 唱臂（播放时摆动） -->
        <div class="tone-arm" :class="{ playing: isPlaying }"></div>
        <!-- 黑胶唱片（播放时旋转） -->
        <div class="vinyl-record" :class="{ spinning: isPlaying }">
          <img :src="songData.cover" alt="歌曲封面" class="vinyl-cover" />
        </div>
      </div>

      <!-- 右侧信息+歌词区 -->
      <div class="info-area">
        <!-- 歌曲标题栏 -->
        <div class="song-header">
          <h1 class="song-title">
            {{ songData.title }}
            <span class="local-tag">{{ songData.tag }}</span>
          </h1>
          <p class="meta">
            专辑: {{ songData.album }} · 歌手: {{ songData.artist }} · 来源: {{ songData.source }}
          </p>
        </div>

        <!-- 创作信息 -->
        <div class="creator-info">
          <p>作词: {{ songData.lyricist }}</p>
          <p>作曲: {{ songData.composer }}</p>
        </div>

        <!-- 歌词区域 -->
        <div class="lyric-area">
          <div class="lyric-line" v-for="(line, idx) in songData.lyrics" :key="idx">
            {{ line }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';

// 虚拟歌曲数据（完全匹配设计图）
const songData = {
  id: 1,
  title: "如果爱",
  tag: "本地",
  album: "再爱我一回",
  artist: "张杰",
  source: "最近播放",
  cover: "https://picsum.photos/id/237/400/400", // 模拟封面图
  lyricist: "黎沸挥",
  composer: "黎沸挥",
  lyrics: [
    "如果爱",
    "ah~~~ah~~~",
    "我的心从没有搬到另一个地址",
    "还是和你用同样一室的钥匙",
    "你的眼泪一滴一滴将回忆淋湿",
    "你的拥抱却让呼吸变得真实",
    "相爱的人 我能如何选择",
    "伤痛和快乐全都是重复的规则"
  ]
};

// 播放状态（控制黑胶旋转/唱臂动画）
const isPlaying = ref(true);

// 页面加载后自动播放
onMounted(() => {
  isPlaying.value = true;
});
</script>

<style scoped>
/* 页面整体样式（匹配设计图棕褐色背景） */
.song-detail-page {
  width: 100vw;
  height: 100vh;
  background-color: #5a4839;
  color: #e0d6c9;
  font-family: "Microsoft Yahei", "PingFang SC", sans-serif;
  padding: 20px;
  box-sizing: border-box;
  overflow: hidden;
}

/* 顶部操作栏 */
.top-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

.action-btn {
  background: transparent;
  border: none;
  color: #e0d6c9;
  font-size: 18px;
  cursor: pointer;
  width: 36px;
  height: 36px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.right-actions {
  display: flex;
  align-items: center;
  gap: 16px;
}

.player-mode {
  font-size: 14px;
  color: #e0d6c9;
}

.win-btn {
  background: transparent;
  border: none;
  color: #e0d6c9;
  font-size: 16px;
  cursor: pointer;
  width: 24px;
  height: 24px;
}

.close-btn {
  color: #ff5252;
}

/* 主体容器 */
.main-container {
  display: flex;
  align-items: center;
  gap: 80px;
  height: calc(100vh - 100px);
}

/* 黑胶唱片机区域 */
.vinyl-area {
  position: relative;
  width: 400px;
  height: 400px;
}

/* 唱臂样式 */
.tone-arm {
  position: absolute;
  top: 50px;
  left: 250px;
  width: 180px;
  height: 4px;
  background: #e0d6c9;
  border-radius: 2px;
  transform-origin: left center;
  transform: rotate(-45deg);
  transition: transform 1s ease-in-out;
  z-index: 10;
}

.tone-arm::after {
  content: '';
  position: absolute;
  right: -8px;
  top: -8px;
  width: 20px;
  height: 20px;
  background: #e0d6c9;
  border-radius: 50%;
}

/* 播放时唱臂摆动到唱片上 */
.tone-arm.playing {
  transform: rotate(-15deg);
}

/* 黑胶唱片样式 */
.vinyl-record {
  width: 400px;
  height: 400px;
  border-radius: 50%;
  background: #222;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.5);
  position: relative;
  overflow: hidden;
}

/* 播放时唱片旋转动画 */
.vinyl-record.spinning {
  animation: spin 8s linear infinite;
}

@keyframes spin {
  from { transform: rotate(0deg); }
  to { transform: rotate(360deg); }
}

/* 唱片封面 */
.vinyl-cover {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  object-fit: cover;
  border: 4px solid #333;
}

/* 右侧信息区 */
.info-area {
  flex: 1;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

/* 歌曲标题栏 */
.song-header {
  margin-bottom: 20px;
}

.song-title {
  font-size: 32px;
  color: #fff;
  margin: 0 0 8px 0;
  display: flex;
  align-items: center;
  gap: 12px;
}

.local-tag {
  font-size: 12px;
  background: #888;
  color: #fff;
  padding: 2px 8px;
  border-radius: 4px;
}

.meta {
  font-size: 14px;
  color: #ccc;
  margin: 0;
}

/* 创作信息 */
.creator-info {
  font-size: 16px;
  margin-bottom: 30px;
  line-height: 1.8;
}

/* 歌词区域 */
.lyric-area {
  line-height: 2.2;
  font-size: 18px;
}

.lyric-line {
  transition: color 0.3s;
}

.lyric-line:hover {
  color: #fff;
}
</style>