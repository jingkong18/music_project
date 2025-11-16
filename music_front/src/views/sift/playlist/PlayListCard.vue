<template>
  <div class="playlist-card">
    <div class="cover">
      <img :src="item.cover" alt="歌单封面" />
      <span class="play-count">{{ item.playCount }}万</span>
      <button class="play-btn">▶</button>
    </div>
    <h3 class="title">{{ item.title }}</h3>
    <p class="desc">{{ item.desc }}</p> <!-- 新增：歌单简介 -->
  </div>
</template>

<script setup lang="ts">
defineProps<{
  item: {
    cover: string;
    title: string;
    playCount: number;
    desc: string; // 新增：简介字段
    isPlayable?: boolean; // 可选：是否显示播放按钮
  };
}>();
</script>

<style scoped>
.playlist-card {
  width: 140px;
  cursor: pointer;
  transition: all 0.3s;
}

.playlist-card:hover {
  transform: translateY(-4px);
}

.cover {
  position: relative;
  width: 100%;
  height: 140px;
  border-radius: 8px;
  overflow: hidden;
  margin-bottom: 8px;
}

.cover img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s;
}

.playlist-card:hover .cover img {
  transform: scale(1.05);
}

.play-count {
  position: absolute;
  bottom: 6px;
  right: 6px;
  color: #fff;
  font-size: 12px;
  text-shadow: 0 1px 2px rgba(0,0,0,0.5);
}

.play-btn {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 40px;
  height: 40px;
  border: none;
  border-radius: 50%;
  background: rgba(255,255,255,0.8);
  color: #ff3a3a;
  opacity: 0;
  transition: opacity 0.3s;
}

.playlist-card:hover .play-btn {
  opacity: 1;
}

.title {
  font-size: 14px;
  font-weight: 500;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  margin-bottom: 4px;
}

.desc {
  font-size: 12px;
  color: #666;
  display: -webkit-box;
  -webkit-line-clamp: 2; /* 简介最多显示2行 */
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>