<template>
  <div class="music-collection-page">
    <!-- 操作栏 -->
    <div class="action-bar">
      <button class="play-all-btn">
        <i class="icon-play"></i> 播放全部
      </button>
      <button class="collect-all-btn">
        <i class="icon-collect"></i> 收藏全部
      </button>
      <button class="more-btn">
        <i class="icon-more"></i>
      </button>
    </div>

    <!-- 歌曲列表头部 -->
    <div class="list-header">
      <div class="col col-index">#</div>
      <div class="col col-title">标题</div>
      <div class="col col-album">专辑</div>
      <div class="col col-like">喜欢</div>
      <div class="col col-play-time">播放时间</div>
    </div>

    <!-- 歌曲列表 -->
    <div class="song-list">
      <div 
        v-for="(song, index) in songs" 
        :key="song.id" 
        class="song-item"
        :class="{ playing: index === currentPlayingIndex }"
      >
        <div class="col col-index">
          <button v-if="index === 0" class="play-btn">
            <i class="icon-play"></i>
          </button>
          {{ index + 1 }}
        </div>
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
        <div class="col col-play-time">{{ song.playTime }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import TabBar from '@/views/recent/TabBar.vue';

// 定义数据类型
interface Song {
  id: string;
  cover: string;
  title: string;
  tags: string[];
  artist: string;
  album: string;
  playTime: string;
  isLiked: boolean;
}

// 模拟歌曲数据
const songs = ref<Song[]>([
  {
    id: '1',
    cover: 'https://picsum.photos/200/200?random=90',
    title: 'Voices',
    tags: ['超清母带'],
    artist: 'Hidden Citizens / Vanessa Campagna',
    album: 'Voices',
    playTime: '02:44',
    isLiked: false
  },
  {
    id: '2',
    cover: 'https://picsum.photos/200/200?random=91',
    title: 'What a Day',
    tags: ['超清母带', 'MV'],
    artist: '蔡徐坤',
    album: 'What a Day',
    playTime: '01:03',
    isLiked: false
  },
  {
    id: '3',
    cover: 'https://picsum.photos/200/200?random=92',
    title: '拉过勾的 (电视剧《我家有喜》插曲)',
    tags: [],
    artist: '陆虎',
    album: '我家有喜 电视剧原声带',
    playTime: '11-05',
    isLiked: false
  },
  {
    id: '4',
    cover: 'https://picsum.photos/200/200?random=93',
    title: '我的未来式 (电视剧《爱情公寓》十周年合唱版)',
    tags: ['超清母带', 'MV'],
    artist: '爱情公寓全体',
    album: '我的未来式',
    playTime: '10-28',
    isLiked: false
  },
  {
    id: '5',
    cover: 'https://picsum.photos/200/200?random=94',
    title: '一生所爱 (电影《大话西游》插曲)',
    tags: ['超清母带', 'VIP'],
    artist: '卢冠廷 / 莫文蔚',
    album: '齐天周大圣之西游记双记 电影歌乐游唱版',
    playTime: '10-28',
    isLiked: false
  },
  {
    id: '6',
    cover: 'https://picsum.photos/200/200?random=95',
    title: '爱情转移 (电影《爱情呼叫转移》主题曲)',
    tags: ['超清母带', 'VIP', 'MV'],
    artist: '陈奕迅',
    album: '认了吧',
    playTime: '10-28',
    isLiked: false
  },
  {
    id: '7',
    cover: 'https://picsum.photos/200/200?random=96',
    title: '一直很安静',
    tags: ['超清母带'],
    artist: '王贰浪',
    album: '一直很安静',
    playTime: '10-28',
    isLiked: false
  },
  {
    id: '8',
    cover: 'https://picsum.photos/200/200?random=97',
    title: '后来的我们',
    tags: ['超清母带', 'VIP', 'MV'],
    artist: '五月天',
    album: '自传',
    playTime: '10-28',
    isLiked: false
  },
  {
    id: '9',
    cover: 'https://picsum.photos/200/200?random=98',
    title: '光辉岁月 (Glorious Years)',
    tags: ['超清母带', 'VIP', 'MV'],
    artist: 'Beyond',
    album: '光辉岁月十五年',
    playTime: '10-28',
    isLiked: false
  },
  {
    id: '10',
    cover: 'https://picsum.photos/200/200?random=99',
    title: '大眠',
    tags: ['超清母带', 'VIP', 'MV'],
    artist: '王心凌',
    album: 'CYNDILOVES2SING 爱。心凌',
    playTime: '10-28',
    isLiked: false
  },
  {
    id: '11',
    cover: 'https://picsum.photos/200/200?random=100',
    title: '倒带 (电视剧《求婚事务所》最热插曲)',
    tags: ['超清母带', 'VIP', 'MV'],
    artist: '蔡依林',
    album: '求婚事务所 电视原声带',
    playTime: '10-28',
    isLiked: false
  }
]);

// 当前播放歌曲索引
const currentPlayingIndex = ref<number | null>(0);




</script>

<style scoped>
/* 页面整体样式 */
.music-collection-page {
  max-width: 1440px;
  margin: 0 auto;
  padding: 24px;
  background-color: #fff;
}

/* 顶部标签栏 */
.tab-bar {
  display: flex;
  gap: 12px;
  margin-bottom: 16px;
  border-bottom: 1px solid #f0f0f0;
  padding-bottom: 8px;
}

.tab {
  padding: 8px 16px;
  background: none;
  border: none;
  cursor: pointer;
  font-size: 14px;
  font-weight: 500;
  color: #333;
  transition: color 0.2s;
}

.tab.active {
  color: #ff3a3a;
  border-bottom: 2px solid #ff3a3a;
  padding-bottom: 6px;
}

.tab:hover {
  color: #ff6b6b;
}

.count {
  background-color: #fde4e4;
  color: #ff3a3a;
  padding: 2px 6px;
  border-radius: 10px;
  font-size: 12px;
  margin-left: 4px;
}

/* 操作栏 */
.action-bar {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  margin-bottom: 16px;
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

.collect-all-btn, .more-btn {
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

.collect-all-btn:hover, .more-btn:hover {
  background-color: #d9d9d9;
}

/* 歌曲列表头部 */
.list-header {
  display: flex;
  background-color: #f5f5f5;
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

.col-play-time {
  width: 120px;
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

.play-btn {
  background: none;
  border: none;
  cursor: pointer;
  color: #ff3a3a;
  font-size: 20px;
  margin-right: 8px;
}

/* 图标样式（可替换为实际图标字体） */
.icon-play::before {
  content: '▶';
}

.icon-collect::before {
  content: '+';
}

.icon-more::before {
  content: '⋮';
}

.icon-heart::before {
  content: '♡';
}
</style>