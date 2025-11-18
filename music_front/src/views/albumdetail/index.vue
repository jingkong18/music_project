<template>
  <div class="album-detail" v-if="album">
    <!-- 专辑头部 -->
    <div class="header">
      <img :src="album.cover" alt="专辑封面" class="cover" />
      <div class="info">
        <h1 class="name">{{ album.name }}</h1>
        <p class="meta">
          <img :src="album.artistAvatar" alt="歌手头像" class="avatar" />
          <span>{{ album.artist }}</span>
          <span>{{ album.releaseTime }}发布</span>
        </p>
      </div>
    </div>

    <!-- 操作按钮 -->
    <div class="action-buttons">
      <button class="play-all">播放全部</button>
      <button class="collect">{{ album.collectCount }}万</button>
      <button class="download">下载</button>
      <button class="more">...</button>
    </div>

    <!-- 标签栏 -->
    <div class="tabs">
      <button class="tab active">歌曲 {{ album.songCount }}</button>
      <button class="tab">评论 {{ album.commentCount }}</button>
      <button class="tab">专辑详情</button>
    </div>

    <!-- 搜索栏 -->
    <div class="search-bar">
      <input type="text" placeholder="搜索" />
    </div>

    <!-- 歌曲列表 -->
    <div class="song-list">
      <div class="list-header">
        <div class="col">#</div>
        <div class="col">标题</div>
        <div class="col">喜欢</div>
        <div class="col">时长</div>
        <div class="col">热度</div>
      </div>
      <div 
        class="song-item" 
        v-for="(song, idx) in album.songs" 
        :key="song.id"
        :class="{ active: idx === 1 }" 
      >
        <div class="col">{{ idx + 1 }}</div>
        <div class="col">
          <button class="play-btn" v-if="idx === 1">▶</button> <!-- 播放中按钮 -->
          <div class="song-info">
            <span class="title">{{ song.title }}</span>
            <div class="tags">
              <span class="tag" v-for="tag in song.tags" :key="tag">{{ tag }}</span>
            </div>
            <span class="artist">{{ song.artist }}</span>
          </div>
        </div>
        <div class="col"><button class="like-btn">♡</button></div>
        <div class="col">{{ song.duration }}</div>
        <div class="col">
          <div class="heat-bar" :style="{ width: song.heat + '%' }"></div>
        </div>
      </div>
    </div>
  </div>
  <div v-else>加载中...</div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import { useRoute } from 'vue-router';

// 定义专辑类型
interface Album {
  id: string;
  cover: string;
  name: string;
  artist: string;
  artistAvatar: string;
  releaseTime: string;
  collectCount: number;
  songCount: number;
  commentCount: number;
  songs: {
    id: string;
    title: string;
    tags: string[];
    artist: string;
    duration: string;
    heat: number; // 热度（0-100）
  }[];
}

const route = useRoute();
const album = ref<Album | null>(null);

// 模拟所有专辑数据源（实际从后端接口获取）
const allAlbums: Album[] = [
  {
    id: '1',
    cover: 'https://picsum.photos/200/200?random=50',
    name: 'David Tao(陶喆同名专辑)',
    artist: '陶喆',
    artistAvatar: 'https://picsum.photos/40/40?random=60',
    releaseTime: '1997-12-06',
    collectCount: 19.2,
    songCount: 15,
    commentCount: 6649,
    songs: [
      {
        id: 's1',
        title: 'Airport Take Off',
        tags: ['超清母带'],
        artist: '陶喆',
        duration: '00:10',
        heat: 30
      },
      {
        id: 's2',
        title: '飞机场的10:30 (Airport at 10:30)',
        tags: ['超清母带', 'VIP'],
        artist: '陶喆',
        duration: '04:40',
        heat: 90
      },
      {
        id: 's3',
        title: 'Airport Arrival',
        tags: ['超清母带'],
        artist: '陶喆',
        duration: '00:15',
        heat: 20
      }
      // 更多歌曲...
    ]
  },
  {
    id: '2',
    cover: 'https://picsum.photos/200/200?random=51',
    name: '最伟大的作品',
    artist: '周杰伦',
    artistAvatar: 'https://picsum.photos/40/40?random=61',
    releaseTime: '2022-07-06',
    collectCount: 500,
    songCount: 12,
    commentCount: 100000,
    songs: [/* 歌曲列表 */]
  }
];

// 根据专辑ID获取数据
const fetchAlbumData = () => {
  const albumId = route.params.id as string;
  album.value = allAlbums.find(item => item.id === albumId) || null;
};

// 首次挂载加载数据
onMounted(() => {
  fetchAlbumData();
});

// 监听路由ID变化，重新加载数据
watch(
  () => route.params.id,
  () => {
    fetchAlbumData();
  }
);
</script>

<style scoped>
/* 基础样式（可根据需求美化） */
.album-detail { padding: 24px; max-width: 1440px; margin: 0 auto; }
.header { display: flex; gap: 20px; margin-bottom: 20px; }
.cover { width: 160px; height: 160px; border-radius: 8px; }
.info .name { font-size: 24px; font-weight: 600; margin: 8px 0; }
.action-buttons { display: flex; gap: 12px; margin-bottom: 20px; }
.play-all { background: #ff3a3a; color: #fff; border: none; padding: 8px 16px; border-radius: 4px; }
.song-list { margin-top: 20px; }
.list-header { display: flex; background: #f5f5f5; padding: 12px; font-weight: 500; }
.col { flex: 1; text-align: left; }
.song-item { display: flex; align-items: center; padding: 12px; border-bottom: 1px solid #eee; }
.song-item.active { background: #f8f9fa; }
.play-btn { background: none; border: none; color: #ff3a3a; margin-right: 8px; }
.tags { display: inline-block; margin-left: 8px; }
.tag { font-size: 12px; color: #999; margin-right: 4px; }
.heat-bar { height: 4px; background: #ff3a3a; border-radius: 2px; }
</style>