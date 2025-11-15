<template>
  <div class="ranking-section">
    <div class="section-header">
      <h3 class="section-title">榜单精选</h3>
    </div>
    <div class="ranking-grid">
      <div 
        v-for="(ranking, index) in rankingList" 
        :key="index" 
        class="ranking-card"
    
      >
        <div class="card-header">
          <h4 class="ranking-name">{{ ranking.name }}</h4>
          <span class="update-frequency">{{ ranking.update }}</span>
        </div>
        <div class="card-content">
          <div class="cover-container">
            <img :src="ranking.cover" alt="榜单封面" class="ranking-cover" />
            <el-icon  class="play-icon"><CaretRight /></el-icon><
          </div>
          <ul class="song-list">
            <li v-for="(song, songIndex) in ranking.songs" :key="songIndex" class="song-item">
              <span class="song-rank">{{ songIndex + 1 }}</span>
              <span class="song-name">{{ song.name }} - {{ song.artist }}</span>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { CaretRight} from '@element-plus/icons-vue';
const rankingList = ref([
  {
    name: '飙升榜',
    update: '每天更新',
    cover: 'https://picsum.photos/id/1076/100/100',
    songs: [
      { name: 'What a Day', artist: '蔡徐坤', label: '新' },
      { name: '我是一只鱼', artist: '任贤齐', label: '新' },
      { name: '晚安', artist: '颜人中', label: '新' }
    ]
  },
  {
    name: '新歌榜',
    update: '每天更新',
    cover: 'https://picsum.photos/id/96/100/100',
    songs: [
      { name: '左转灯 (1000 Times +1)', artist: '派...', trend: 'down' },
      { name: 'Black Veil Bride', artist: 'Rosy赵露思', trend: 'down' },
      { name: '冬与你', artist: '万乐体/鱼骨妹', trend: 'up' }
    ]
  },
  {
    name: '热歌榜',
    update: '每天更新',
    cover: 'https://picsum.photos/id/1019/100/100',
    songs: [
      { name: '恋人', artist: '李荣浩' },
      { name: '如果呢', artist: '郑润泽' },
      { name: '罗生门 (Follow)', artist: '梨冻紧/...' }
    ]
  },
  {
    name: '原创榜',
    update: '每周四更新',
    cover: 'https://picsum.photos/id/1082/100/100',
    songs: [
      { name: '一杯', artist: 'Mr.岑', label: '新' },
      { name: '花伞', artist: '亞細亞曠世奇才/诺米...', label: '新' },
      { name: '回声', artist: '杭亦凡/王栎鑫/法老', label: '新' }
    ]
  },
  {
    name: '数字专辑畅销榜',
    update: '每30分钟更新',
    cover: 'https://picsum.photos/id/1025/100/100',
    songs: [
      { name: '和自己对话', artist: '林俊杰', trend: 'up' },
      { name: '希忘Hope', artist: '华晨宇', trend: 'up' },
      { name: '光与盐 (上)', artist: '周峻纬', trend: 'up' }
    ]
  },
  {
    name: '歌曲畅销指数榜',
    update: '每1小时更新',
    cover: 'https://picsum.photos/id/1062/100/100',
    songs: [
      { name: '我是一只鱼', artist: '梓渝' },
      { name: '下一页', artist: '梓渝' },
      { name: '晨昏线', artist: '梓渝' }
    ]
  }
]);
</script>

<style scoped>
.ranking-section {
  padding: 24px;
}

.section-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 24px;
}

.section-title {
  font-size: 20px;
  font-weight: bold;
}

.refresh-icon {
  cursor: pointer;
  color: #999;
}

.ranking-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 24px;
}

.ranking-card {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  padding: 16px;
  cursor: pointer;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.ranking-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.ranking-name {
  font-size: 16px;
  font-weight: 500;
}

.update-frequency {
  font-size: 12px;
  color: #999;
}

.card-content {
  display: flex;
  align-items: flex-start;
}

.cover-container {
  position: relative;
  margin-right: 12px;
}

.ranking-cover {
  width: 80px;
  height: 80px;
  object-fit: cover;
  border-radius: 4px;
}

.play-icon {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 32px;
  height: 32px;
  border-radius: 50%;
  background-color: rgba(255, 45, 85, 0.8);
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0;
  transition: opacity 0.2s ease;
}

.ranking-card:hover .play-icon {
  opacity: 1;
}

.song-list {
  list-style: none;
  padding: 0;
  margin: 0;
  width: 100%;
}

.song-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 4px 0;
  font-size: 14px;
}

.song-rank {
  margin-right: 8px;
  color: #999;
}

.song-name {
  flex: 1;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.song-label {
  color: #00c853;
  margin-left: 8px;
}

.trend-icon {
  font-size: 12px;
}
</style>