<template>
  <div class="music-library-page">
    <!-- 顶部操作栏 -->
    <div class="top-actions">
      <button class="play-all-btn">
        <el-icon><CaretRight /></el-icon> 播放全部
      </button>
      <button class="cloud-btn">
        <el-icon><Upload /></el-icon>
      </button>
      <div class="dropdown-actions">
        <button class="download-btn"><el-icon><Download /></el-icon>下载</button>
        <button class="collect-btn"><el-icon><StarFilled /></el-icon>收藏</button>
        <button class="delete-btn"><el-icon><DeleteFilled /></el-icon>删除</button>
      </div>
      <div class="search-bar">
        <input type="text" placeholder="搜索" class="search-input" />
      </div>
    </div>

    <!-- 列表头部 -->
    <div class="list-header">
      <div class="col col-select">
        <input type="checkbox" id="select-all" />
        <label for="select-all"></label>
      </div>
      <div class="col col-title">标题</div>
      <div class="col col-album">专辑</div>
      <div class="col col-format">格式</div>
      <div class="col col-upload-time">上传时间</div>
      <div class="col col-size">大小</div>
    </div>

    <!-- 歌曲列表 -->
    <div class="song-list">
      <div 
        v-for="(song, index) in songs" 
        :key="index" 
        class="song-item"
        @mouseenter="hoverIndex = index"
        @mouseleave="hoverIndex = -1"
      >
        <div class="col col-select">
          <input type="checkbox" :id="`song-${index}`" />
        </div>
        <div class="col col-title">
          <img :src="song.cover" alt="歌曲封面" class="song-cover" />
          <div class="song-info">
            <span class="song-name">{{ song.title }}</span>
            <div class="song-meta">
              <span v-if="song.tag" :class="`song-tag tag-${song.tag}`">{{ song.tag }}</span>
              <span v-if="song.vip" class="vip-tag">VIP</span>
              <span class="artist">{{ song.artist }}</span>
            </div>
          </div>
        </div>
        <div class="col col-album">{{ song.album }}</div>
        <div class="col col-format">{{ song.format }}</div>
        <div class="col col-upload-time">{{ song.uploadTime }}</div>
        <div class="col col-size">{{ song.size }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { CaretRight, StarFilled, DeleteFilled, Download, Upload } from '@element-plus/icons-vue';

// 定义歌曲数据类型
interface LibrarySong {
  cover: string;
  title: string;
  artist: string;
  album: string;
  format: string;
  uploadTime: string;
  size: string;
  tag?: 'SQ' | '超清母带' | 'MV';
  vip?: boolean;
}

// 模拟歌曲数据
const songs: LibrarySong[] = [
  {
    cover: 'https://picsum.photos/200/200?random=130',
    title: 'Screenrecorder-2023-09-03-23-28-45-738',
    artist: '未知歌手',
    album: '未知专辑',
    format: 'mp3',
    uploadTime: '2023-09-03',
    size: '109.2M',
    tag: 'SQ'
  },
  {
    cover: 'https://picsum.photos/200/200?random=131',
    title: '水中花',
    artist: '未知歌手',
    album: '未知专辑',
    format: 'mp3',
    uploadTime: '2023-06-16',
    size: '5.2M'
  },
  {
    cover: 'https://picsum.photos/200/200?random=132',
    title: '呓语',
    artist: '未知歌手',
    album: '未知专辑',
    format: 'mp3',
    uploadTime: '2023-06-16',
    size: '6.3M'
  },
  {
    cover: 'https://picsum.photos/200/200?random=133',
    title: 'A1b',
    artist: 'tailuowang',
    album: '未知专辑',
    format: 'mp3',
    uploadTime: '2019-03-21',
    size: '1.7M',
    tag: 'SQ'
  },
  {
    cover: 'https://picsum.photos/200/200?random=134',
    title: 'Unpredictable',
    artist: '易烊千玺',
    album: 'Unpredictable',
    format: 'mp3',
    uploadTime: '2019-01-29',
    size: '8.8M',
    tag: '超清母带',
    vip: true
  },
  {
    cover: 'https://picsum.photos/200/200?random=135',
    title: 'Symphony No. 97 in C Major, Hob. I:97: IV. Finale. Presto assai',
    artist: 'George Szell / Joseph Haydn / The Cleveland Orchestra',
    album: 'Szell Conducts Haydn Symphonies - Sony Classical Masters',
    format: 'mp3',
    uploadTime: '2019-01-29',
    size: '6.4M',
    tag: 'SQ',
    vip: true
  },
  {
    cover: 'https://picsum.photos/200/200?random=136',
    title: '雪落下的声音 (电视剧《延禧攻略》片尾曲)',
    artist: '秦岚',
    album: '雪落下的声音',
    format: 'mp3',
    uploadTime: '2019-01-29',
    size: '11.7M',
    tag: 'SQ'
  },
  {
    cover: 'https://picsum.photos/200/200?random=137',
    title: '我的梦 (华为消费者业务品牌歌曲;电视剧《为了你我愿意热爱整个世界》主题曲)',
    artist: '张靓颖',
    album: '我的梦',
    format: 'mp3',
    uploadTime: '2019-01-29',
    size: '8.4M',
    tag: 'SQ',
    vip: true
  },
  {
    cover: 'https://picsum.photos/200/200?random=138',
    title: 'Dream It Possible (华为Mate8发布会主题曲)',
    artist: 'Delacey',
    album: 'Dream It Possible',
    format: 'mp3',
    uploadTime: '2019-01-29',
    size: '3.2M',
    tag: '超清母带',
    vip: true
  }
];

// 鼠标悬停的歌曲索引
const hoverIndex = ref(-1);
</script>

<style scoped>
/* 页面整体样式 */
.music-library-page {
  max-width: 1440px;
  margin: 0 auto;
  padding: 16px;
  background-color: #fff;
  border-radius: 8px;
}

/* 顶部操作栏 */
.top-actions {
  display: flex;
  align-items: center;
  gap: 8px;
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

.cloud-btn {
  background-color: #f5f5f5;
  color: #333;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  width: 36px;
  height: 36px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

.dropdown-actions {
  display: flex;
  gap: 4px;
}

.download-btn, .collect-btn, .delete-btn {
  background-color: #f5f5f5;
  color: #333;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  padding: 8px 12px;
  cursor: pointer;
}

.search-bar {
  margin-left: auto;
}

.search-input {
  padding: 8px 12px;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  width: 200px;
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

.col-select {
  width: 100px;
  display: flex;
  align-items: center;
  gap: 8px;
}

.col-title {
  flex: 3;
  display: flex;
  align-items: center;
}

.col-album {
  width: 200px;
}

.col-format {
  width: 80px;
  justify-content: center;
}

.col-upload-time {
  width: 120px;
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

.song-cover {
  width: 40px;
  height: 40px;
  object-fit: cover;
  border-radius: 4px;
  margin-right: 12px;
}

.song-info {
  display: flex;
  flex-direction: column;
}

.song-name {
  font-size: 14px;
  color: #333;
}

.song-meta {
  display: flex;
  align-items: center;
  gap: 6px;
  font-size: 12px;
  color: #999;
}

.song-tag {
  padding: 1px 5px;
  border-radius: 4px;
  font-size: 10px;
  color: #fff;
}

.tag-SQ {
  background-color: #00a1d6;
}

.tag-超清母带 {
  background-color: #ffb400;
}

.tag-MV {
  background-color: #9f7aea;
}

.vip-tag {
  color: #ff3a3a;
  font-size: 10px;
}

/* 图标样式 */
.icon-play::before {
  content: '▶';
}

.icon-cloud::before {
  content: '☁';
}
</style>