<template>
  <div class="local-music-page">
    <!-- 操作栏 -->
    <div class="action-bar">
      <button class="play-all-btn"><el-icon><CaretRight /></el-icon>播放全部
      </button>
      <button class="collect-btn">
        <el-icon><StarFilled /></el-icon>收藏
      </button>
      <button class="delete-list-btn">
       <el-icon><DeleteFilled /></el-icon> 删除
      </button>
      <div class="search-filter">
        <input type="text" placeholder="搜索本地音乐" class="search-input" />
      </div>
    </div>
    <!-- 歌曲列表头部 -->
    <div class="list-header">
     <div class="col col-select">
        <input type="checkbox" id="select-all" />

      </div>
      <div class="col col-title">标题</div>
      <div class="col col-album">专辑</div>
      <div class="col col-duration">时长</div>
      <div class="col col-size">大小</div>
    </div>

    <!-- 歌曲列表 -->
    <div class="song-list">
      <div 
        v-for="(song, index) in songs" 
        :key="song.id" 
        class="song-item"
        @mouseenter="hoverIndex = index"
        @mouseleave="hoverIndex = -1"
      >
        <div class="col col-index">{{ index + 1 }}</div>
        <div class="col col-title">
          <span class="song-name">{{ song.title }}</span>
          <span v-if="song.tag" :class="`song-tag tag-${song.tag}`">{{ song.tag }}</span>
          <span class="artist">{{ song.artist }}</span>
        </div>
        <div class="col col-album">{{ song.album }}</div>
        <div class="col col-duration">{{ song.duration }}</div>
        <div class="col col-size">{{ song.size }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { CaretRight, StarFilled, DeleteFilled  } from '@element-plus/icons-vue';

// 定义本地音乐数据类型
interface LocalSong {
  id: string;
  title: string;
  artist: string;
  album: string;
  duration: string;
  size: string;
  tag?: 'MV' | 'VIP';
}

// 模拟本地音乐数据
const songs: LocalSong[] = [
  {
    id: '1',
    title: '仰望星空',
    artist: '张杰',
    album: '明天过后',
    duration: '04:24',
    size: '10.4M'
  },
  {
    id: '2',
    title: '听妈妈的话',
    artist: '周杰伦',
    album: '依然范特西',
    duration: '04:25',
    size: '10.1M'
  },
  {
    id: '3',
    title: '天下',
    artist: '张杰',
    album: '明天过后',
    duration: '03:39',
    size: '8.6M',
    tag: 'MV'
  },
  {
    id: '4',
    title: '如果爱',
    artist: '张杰',
    album: '再爱我一回',
    duration: '04:22',
    size: '11.0M',
    tag: 'VIP'
  },
  {
    id: '5',
    title: 'Il Buono, Il Cattivo, Il Brutto',
    artist: 'Ennio Morricone',
    album: 'The Good, the Bad and the Ugly (Original Motion Picture S...)',
    duration: '02:42',
    size: '6.7M'
  }
];

// 鼠标悬停的歌曲索引
const hoverIndex = ref(-1);
</script>

<style scoped>
/* 页面整体样式 */
.local-music-page {
  max-width: 1440px;
  margin: 0 auto;
  padding: 24px;
  background-color: #fff;
}
/* 操作栏 */
.action-bar {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 16px;
}

.play-all-btn {
  background-color: #ff3a3a;
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 8px 16px;
  display: flex;
  align-items: center;
  gap: 6px;
  cursor: pointer;
}

.collect-btn, .delete-list-btn {
  background-color: #f5f5f5;
  color: #333;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  width: 80px;
  height: 36px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

.search-filter {
  margin-left: auto;
  display: flex;
  align-items: center;
  gap: 12px;
}

.search-input {
  padding: 8px 12px;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  width: 200px;
}

.filter-tabs {
  display: flex;
  gap: 8px;
}

.filter-tab {
  background: none;
  border: none;
  padding: 4px 8px;
  cursor: pointer;
  font-size: 14px;
  color: #999;
}

.filter-tab.active {
  color: #333;
  font-weight: 500;
}

/* 列表头部 */
.list-header {
  display: flex;
  background-color: #f5f5f5;
  color: #666;
  font-weight: 500;
}

.col {
  padding: 12px;
  display: flex;
  align-items: center;
}

.col-index {
  width: 50px;
  justify-content: center;
}

.col-title {
  flex: 3;
  display: flex;
  flex-direction: column;
}

.col-album {
  width: 200px;
}

.col-duration {
  width: 100px;
  justify-content: center;
}

.col-size {
  width: 100px;
  justify-content: center;
}

/* 歌曲条目 */
.song-item {
  display: flex;
  border-bottom: 1px solid #e5e5e5;
  transition: background-color 0.2s;
}

.song-item:hover {
  background-color: #fafafa;
}

.song-name {
  font-size: 14px;
  color: #333;
}

.song-tag {
  font-size: 10px;
  padding: 2px 4px;
  border-radius: 4px;
  margin-right: 8px;
}

.tag-MV {
  background-color: #ff9f43;
  color: #fff;
}

.tag-VIP {
  background-color: #ff3a3a;
  color: #fff;
}

.artist {
  font-size: 12px;
  color: #999;
}
</style>