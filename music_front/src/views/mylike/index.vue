<template>
  <div class="favorite-music-page">
    <!-- 歌单头部 -->
    <div class="playlist-header">
      <div class="cover-area">
        <img :src="playlist.cover" alt="歌单封面" class="playlist-cover" />
        <div class="play-count">
          <i class="icon-play"></i> {{ playlist.playCount }}
        </div>
      </div>
      <div class="info-area">
        <h1 class="playlist-title">我喜欢的音乐</h1>
        <div class="creator-info">
          <img :src="playlist.creatorAvatar" alt="创建者头像" class="creator-avatar" />
          <span class="creator-name">{{ playlist.creatorName }}</span>
          <span class="create-time">{{ playlist.createTime }}</span>
        </div>
        <div class="action-buttons">
          <button class="play-all-btn">
            <i class="icon-play"></i> 播放全部
          </button>
          <button class="download-btn">
            <i class="icon-download"></i> 下载
          </button>
          <button class="more-btn">
            <i class="icon-more"></i>
          </button>
        </div>
      </div>
    </div>

    <!-- 标签栏 -->
    <div class="tab-bar">
      <button class="tab active" id="songs-tab">歌曲 <span class="song-count">{{ playlist.songCount }}</span></button>
      <button class="tab" id="comment-tab">评论</button>
      <button class="tab" id="collector-tab">收藏者</button>
    </div>

    <!-- 搜索栏 -->
    <div class="search-bar">
      <input type="text" placeholder="搜索歌曲" class="search-input" />
    </div>

    <!-- 歌曲列表 -->
    <div class="song-list">
      <div class="list-header">
        <div class="col col-index">#</div>
        <div class="col col-title">标题</div>
        <div class="col col-album">专辑</div>
        <div class="col col-like">喜欢</div>
        <div class="col col-duration">时长</div>
      </div>
      <div 
        v-for="(song, index) in songs" 
        :key="song.id" 
        class="song-item"
        :class="{ playing: index === currentPlayingIndex }"
      >
        <div class="col col-index">{{ index + 1 }}</div>
        <div class="col col-title">
          <img :src="song.cover" alt="歌曲封面" class="song-cover" />
          <div class="song-info">
            <h3 class="song-name">{{ song.title }}</h3>
            <div class="song-meta">
              <span v-for="tag in song.tags" :key="tag" class="song-tag">{{ tag }}</span>
              <span class="artist">{{ song.artist }}</span>
            </div>
          </div>
        </div>
        <div class="col col-album">{{ song.album }}</div>
        <div class="col col-like">
          <button class="like-btn" :class="{ liked: song.isLiked }">
            <i class="icon-heart"></i>
          </button>
        </div>
        <div class="col col-duration">{{ song.duration }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

// 定义数据类型
interface Playlist {
  cover: string;
  playCount: number;
  title: string;
  creatorAvatar: string;
  creatorName: string;
  createTime: string;
  songCount: number;
}

interface Song {
  id: string;
  cover: string;
  title: string;
  tags: string[];
  artist: string;
  album: string;
  duration: string;
  isLiked: boolean;
}

// 模拟歌单数据
const playlist = ref<Playlist>({
  cover: 'https://picsum.photos/300/300?random=80',
  playCount: 161,
  title: '我喜欢的音乐',
  creatorAvatar: 'https://picsum.photos/100/100?random=81',
  creatorName: '菁箜',
  createTime: '2017-12-04创建',
  songCount: 111
});

// 模拟歌曲数据
const songs = ref<Song[]>([
  {
    id: '1',
    cover: 'https://picsum.photos/200/200?random=82',
    title: '剑心 (电视剧《古剑奇谭》片头曲)',
    tags: ['超清母带'],
    artist: '张杰',
    album: '拾',
    duration: '04:09',
    isLiked: true
  },
  {
    id: '2',
    cover: 'https://picsum.photos/200/200?random=83',
    title: 'OKAY',
    tags: ['超清母带'],
    artist: 'Ethan Dufault',
    album: 'What If',
    duration: '02:44',
    isLiked: true
  },
  {
    id: '3',
    cover: 'https://picsum.photos/200/200?random=84',
    title: 'goodbye.',
    tags: ['超清母带', 'VIP'],
    artist: '8bite',
    album: 'goodbye.',
    duration: '02:44',
    isLiked: true
  },
  {
    id: '4',
    cover: 'https://picsum.photos/200/200?random=85',
    title: '落日亲吻银光海',
    tags: ['超清母带', 'VIP'],
    artist: '皮卡丘多多',
    album: '落日亲吻银光海',
    duration: '02:22',
    isLiked: true
  },
  {
    id: '5',
    cover: 'https://picsum.photos/200/200?random=86',
    title: '如风过耳',
    tags: ['超清母带', 'VIP'],
    artist: '少囧',
    album: '如风过耳',
    duration: '03:32',
    isLiked: true
  },
  {
    id: '6',
    cover: 'https://picsum.photos/200/200?random=87',
    title: '天空天空',
    tags: ['超清母带', 'VIP'],
    artist: '刘思鉴',
    album: '奇迹发生前的准备工作',
    duration: '03:24',
    isLiked: true
  },
  {
    id: '7',
    cover: 'https://picsum.photos/200/200?random=88',
    title: 'Too Sad To Cry',
    tags: ['超清母带', 'VIP', 'MV'],
    artist: 'Sasha Alex Sloan',
    album: 'Self Portrait',
    duration: '03:29',
    isLiked: true
  },
  {
    id: '8',
    cover: 'https://picsum.photos/200/200?random=89',
    title: 'Silence',
    tags: ['超清母带', 'VIP', 'MV'],
    artist: 'Before You Exit',
    album: 'Silence',
    duration: '03:20',
    isLiked: true
  }
]);

// 当前播放歌曲索引
const currentPlayingIndex = ref<number | null>(null);




</script>

<style scoped>
/* 页面整体样式 */
.favorite-music-page {
  max-width: 1440px;
  margin: 0 auto;
  padding: 24px;
  background-color: #f0f9f5;
}

/* 歌单头部 */
.playlist-header {
  display: flex;
  gap: 24px;
  margin-bottom: 24px;
}

.cover-area {
  position: relative;
  width: 200px;
  height: 200px;
}

.playlist-cover {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
}

.play-count {
  position: absolute;
  top: 12px;
  left: 12px;
  display: flex;
  align-items: center;
  gap: 4px;
  background-color: rgba(0, 0, 0, 0.5);
  color: #fff;
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 14px;
}

.info-area {
  flex: 1;
}

.playlist-title {
  font-size: 24px;
  font-weight: 600;
  margin-bottom: 12px;
  color: #1d2129;
}

.creator-info {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 16px;
  font-size: 14px;
  color: #666;
}

.creator-avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  object-fit: cover;
}

.create-time {
  color: #86868b;
}

.action-buttons {
  display: flex;
  gap: 12px;
}

.play-all-btn {
  display: flex;
  align-items: center;
  gap: 6px;
  padding: 8px 20px;
  background-color: #ff3a3a;
  color: #fff;
  border: none;
  border-radius: 20px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.play-all-btn:hover {
  background-color: #e94343;
}

.download-btn, .more-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 8px 20px;
  background-color: #e5e5e5;
  color: #333;
  border: none;
  border-radius: 20px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.download-btn:hover, .more-btn:hover {
  background-color: #d9d9d9;
}

/* 标签栏 */
.tab-bar {
  display: flex;
  gap: 12px;
  margin-bottom: 16px;
}

.tab {
  padding: 8px 16px;
  background-color: #e5e5e5;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.2s;
}

.tab.active {
  background-color: #fff;
  font-weight: 500;
}

.tab:hover {
  background-color: #d9d9d9;
}

.song-count {
  background-color: #d9f2e6;
  color: #27ae60;
  padding: 2px 6px;
  border-radius: 10px;
  font-size: 12px;
  margin-left: 4px;
}

/* 搜索栏 */
.search-bar {
  margin-bottom: 16px;
}

.search-input {
  width: 200px;
  padding: 8px 12px;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  outline: none;
  font-size: 14px;
}

.search-input:focus {
  border-color: #3498db;
}

/* 歌曲列表 */
.song-list {
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
}

.list-header {
  display: flex;
  background-color: #f5f5f5;
  border-bottom: 1px solid #e5e5e5;
  font-weight: 500;
  color: #666;
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
}

.col-album {
  flex: 2;
}

.col-like {
  width: 80px;
  justify-content: center;
}

.col-duration {
  width: 80px;
  justify-content: center;
}

/* 歌曲条目 */
.song-item {
  display: flex;
  border-bottom: 1px solid #f0f0f0;
  transition: background-color 0.2s;
}

.song-item:hover {
  background-color: #fafafa;
}

.song-item.playing {
  background-color: #fde4e4;
}

.song-cover {
  width: 40px;
  height: 40px;
  object-fit: cover;
  border-radius: 4px;
  margin-right: 12px;
}

.song-info {
  flex: 1;
}

.song-name {
  font-size: 14px;
  font-weight: 500;
  margin-bottom: 4px;
  color: #1d2129;
}

.song-meta {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 6px;
  font-size: 12px;
  color: #86868b;
}

.song-tag {
  padding: 1px 5px;
  border-radius: 4px;
  font-size: 10px;
  color: #fff;
}

.song-tag:nth-child(1):contains('超清母带') {
  background-color: #ffb400;
}

.song-tag:nth-child(2):contains('VIP') {
  background-color: #ff6b6b;
}

.song-tag:nth-child(2):contains('MV') {
  background-color: #9f7aea;
}

.like-btn {
  background: none;
  border: none;
  cursor: pointer;
  color: #e5e5e5;
  font-size: 20px;
  transition: color 0.2s;
}

.like-btn.liked {
  color: #ff3a3a;
}

.like-btn:hover {
  color: #ff6b6b;
}

/* 图标样式（可替换为实际图标字体） */
.icon-play::before {
  content: '▶';
}

.icon-download::before {
  content: '↓';
}

.icon-more::before {
  content: '⋮';
}

.icon-heart::before {
  content: '♥';
}
</style>