<template>
  <div class="recommend-songs-section">
    <div class="section-header">
      <h3 class="section-title">推荐歌曲</h3>
    </div>
    <div class="songs-grid">
      <div v-for="(song, index) in songsList" :key="index" class="song-card" @mouseenter="activeCard = index"
        @mouseleave="activeCard = -1">
        <div class="song-cover">
          <img :src="song.cover" alt="歌曲封面" />
          <el-icon v-if="activeCard === index" class="play-icon">
            <CaretRight />
          </el-icon>
        </div>
        <div class="song-info">
          <div class="song-name">{{ song.name }}</div>
          <div class="song-meta">
            <span v-if="song.isLiked" class="like-icon">
              <StarFilled />
            </span>
            <span v-else class="like-icon">
              <Star />
            </span>
            <span v-if="song.vip" class="vip-tag">VIP</span>
            <span v-if="song.mv" class="mv-tag">MV</span>
            <span class="origin-tag">原唱</span>
            <span class="artist">{{ song.artist }}</span>
          </div>
        </div>
        <div v-if="activeCard === index" class="hover-actions">
          <el-icon class="action-icon">
            <Download />
          </el-icon>
          <el-icon class="action-icon">
            <StarFilled />
          </el-icon>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import {
  CaretRight, Star, StarFilled,
  Download
} from '@element-plus/icons-vue';

const activeCard = ref(-1);


// 模拟数据
const songsList = ref([
  {
    cover: 'https://picsum.photos/id/1076/100/100',
    name: '落在生命里的光',
    isLiked: true,
    vip: true,
    mv: false,
    artist: '尹昔眠'
  },
  {
    cover: 'https://picsum.photos/id/96/100/100',
    name: 'insomnia',
    isLiked: true,
    vip: true,
    mv: false,
    artist: '16 / KURO'
  },
  {
    cover: 'https://picsum.photos/id/1019/100/100',
    name: '幸福了 然后呢',
    isLiked: false,
    vip: true,
    mv: true,
    artist: '黄丽玲'
  },
  {
    cover: 'https://picsum.photos/id/1082/100/100',
    name: '如风过耳',
    isLiked: false,
    vip: true,
    mv: false,
    artist: '少囧'
  },
  {
    cover: 'https://picsum.photos/id/1025/100/100',
    name: '爱情讯息',
    isLiked: false,
    vip: true,
    mv: true,
    artist: '郭静'
  },
  {
    cover: 'https://picsum.photos/id/1062/100/100',
    name: '此生不换',
    isLiked: true,
    vip: true,
    mv: true,
    artist: '青鸟飞鱼'
  },
  {
    cover: 'https://picsum.photos/id/1074/100/100',
    name: '吻你吻上太空',
    isLiked: false,
    vip: false,
    mv: false,
    artist: '管羿皓'
  },
  {
    cover: 'https://picsum.photos/id/1027/100/100',
    name: 'This Is the Life',
    isLiked: true,
    vip: true,
    mv: false,
    artist: 'Angie Miller'
  },
  {
    cover: 'https://picsum.photos/id/1080/100/100',
    name: '24/7, 365',
    isLiked: false,
    vip: false,
    mv: true,
    artist: 'elijah woods'
  }
]);
</script>

<style scoped>
.recommend-songs-section {
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

.play-all-btn {
  margin-right: 16px;
}

.refresh-icon {
  cursor: pointer;
  color: #999;
}

.songs-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 24px;
}

.song-card {
  display: flex;
  align-items: center;
  padding: 12px;
  border-radius: 8px;
  transition: background-color 0.2s ease;
}

.song-card:hover {
  background-color: #f8f9fa;
}

.song-cover {
  position: relative;
  width: 80px;
  height: 80px;
  margin-right: 16px;
}

.song-cover img {
  width: 100%;
  height: 100%;
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

.song-card:hover .play-icon {
  opacity: 1;
}

.song-info {
  flex: 1;
}

.song-name {
  font-size: 16px;
  font-weight: 500;
  margin-bottom: 4px;
}

.song-meta {
  display: flex;
  align-items: center;
  font-size: 14px;
  color: #999;
}

.like-icon {
  color: #ff2d55;
  margin-right: 4px;
}

.vip-tag {
  background-color: #ff2d55;
  color: #fff;
  font-size: 10px;
  padding: 1px 4px;
  border-radius: 4px;
  margin: 0 4px;
}

.mv-tag {
  background-color: #ff7a00;
  color: #fff;
  font-size: 10px;
  padding: 1px 4px;
  border-radius: 4px;
  margin: 0 4px;
}

.origin-tag {
  color: #ff2d55;
  margin: 0 4px;
}

.hover-actions {
  display: flex;
  margin-left: 16px;
}

.action-icon {
  margin-left: 16px;
  color: #999;
  cursor: pointer;
}

.action-icon:hover {
  color: #ff2d55;
}
</style>