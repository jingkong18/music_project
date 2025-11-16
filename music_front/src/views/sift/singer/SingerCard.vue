<template>
  <div class="singer-card">
    <img :src="singer.avatar" alt="歌手头像" class="avatar" />
    <h3 class="name">{{ singer.name }}</h3>
    <p class="song-count">单曲: {{ singer.songCount }}</p>
  </div>
</template>

<script setup lang="ts">
import type  { Singer } from '@/types/index';

defineProps<{
  singer: Singer;
}>();
</script>

<style scoped>
.singer-card {
  width: 140px;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 16px;
  cursor: pointer;
  transition: transform 0.3s;
}

.singer-card:hover {
  transform: translateY(-4px);
}

.avatar {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  object-fit: cover;
  margin-bottom: 8px;
}

.name {
  font-size: 16px;
  font-weight: 500;
  margin-bottom: 4px;
}

.song-count {
  font-size: 12px;
  color: #999;
}
</style>