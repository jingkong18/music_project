<template>
  <router-link :to="playlist.route" class="playlist-card">
    <div class="cover">
      <img :src="playlist.cover" alt="歌单封面" />
      <div class="play-count">{{ playlist.playCount }}万</div>
    </div>
    <div class="info">
      <h3 class="title">{{ playlist.title }}</h3>
      <p class="desc">{{ playlist.description }}</p>
    </div>
  </router-link>
</template>

<script setup lang="ts">
import type { Playlist } from '@/types/index';

defineProps<{
  playlist: Playlist;
}>();
</script>

<style scoped>
.playlist-card {
  display: block;
  width: 200px;
  border-radius: 8px;
  overflow: hidden;
  background: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  text-decoration: none;
  color: #333;
  transition: transform 0.3s, box-shadow 0.3s;
}

.playlist-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.cover {
  position: relative;
  height: 160px;
}

.cover img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.play-count {
  position: absolute;
  bottom: 8px;
  right: 8px;
  color: #fff;
  font-size: 12px;
  text-shadow: 0 1px 2px rgba(0, 0, 0, 0.5);
}

.info {
  padding: 8px;
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
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>