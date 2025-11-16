<template>
  <div class="chart-item">
    <div class="chart-header">
      <h3 class="chart-name">{{ chart.name }}</h3>
      <span class="update-info">{{ chart.updateInfo }}</span>
    </div>
    <div class="song-list">
      <div 
        v-for="song in chart.songs" 
        :key="song.rank" 
        class="song-item"
      >
        <img :src="chart.cover" alt="榜单封面" class="song-cover" />
        <div class="song-info">
          <span class="rank">{{ song.rank }}</span>
          <span class="song-title">{{ song.title }}</span>
          <span class="artist">{{ song.artist }}</span>
        </div>
        <span 
          v-if="song.tag" 
          class="tag" 
          :class="`tag-${song.tag}`"
        >
          {{ song.tag }}
        </span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { Chart } from '@/types/index';

defineProps<{
  chart: Chart;
}>();
</script>

<style scoped>
.chart-item {
  width: 45%;
  background: #fff;
  border-radius: 8px;
  padding: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  margin-bottom: 16px;
}

.chart-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.chart-name {
  font-size: 16px;
  font-weight: 600;
}

.update-info {
  font-size: 12px;
  color: #999;
}

.song-list {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.song-item {
  display: flex;
  align-items: center;
  gap: 8px;
}

.song-cover {
  width: 40px;
  height: 40px;
  border-radius: 4px;
}

.song-info {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.rank {
  font-size: 12px;
  color: #999;
  margin-bottom: 2px;
}

.song-title {
  font-size: 14px;
  font-weight: 500;
}

.artist {
  font-size: 12px;
  color: #999;
}

.tag {
  font-size: 12px;
  padding: 2px 4px;
  border-radius: 4px;
}

.tag-新 {
  background: #27ae60;
  color: #fff;
}

.tag-↑ {
  background: #e74c3c;
  color: #fff;
}

.tag-↓ {
  background: #3498db;
  color: #fff;
}

.tag-- {
  background: #999;
  color: #fff;
}
</style>