<template>
  <div class="playlist-detail" v-if="playlist">
    <!-- 歌单头部 -->
    <div class="header">
      <img :src="playlist.cover" alt="歌单封面" class="cover" />
      <div class="info">
        <div class="play-count">♡{{ playlist.playCount }}万</div>
        <h1 class="name">{{ playlist.name }}</h1>
        <p class="desc">{{ playlist.desc }}</p>
        <p class="meta">
          <img :src="playlist.creatorAvatar" alt="创建者头像" class="avatar" />
          <span>{{ playlist.creator }}</span>
          <span>标签: {{ playlist.tags.join(' / ') }}</span>
          <span>{{ playlist.createTime }}创建</span>
        </p>
      </div>
    </div>

    <!-- 操作按钮 -->
    <div class="action-buttons">
      <button class="play-all">播放全部</button>
      <button class="collect">{{ playlist.collectCount }}万</button>
      <button class="download">下载</button>
      <button class="more">...</button>
    </div>

    <!-- 标签栏 -->
    <div class="tabs">
      <button class="tab active">歌曲 {{ playlist.songCount }}</button>
      <button class="tab">评论 {{ playlist.commentCount }}</button>
      <button class="tab">收藏者 {{ playlist.collectCount }}万</button>
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
        <div class="col">专辑</div>
        <div class="col">喜欢</div>
        <div class="col">时长</div>
      </div>
      <div class="song-item" v-for="(song, idx) in playlist.songs" :key="song.id" @click="handlePlaySong(song)">
        <div class="col">{{ idx + 1 }}</div>
        <div class="col">
          <img :src="song.cover" alt="歌曲封面" />
          <div class="song-info">
            <span class="title">{{ song.title }}</span>
            <span class="tags" v-for="tag in song.tags" :key="tag">{{ tag }}</span>
            <span class="artist">{{ song.artist }}</span>
          </div>
        </div>
        <div class="col">{{ song.album }}</div>
        <div class="col"><button class="like-btn">♡</button></div>
        <div class="col">{{ song.duration }}</div>
      </div>
    </div>
  </div>
  <div v-else>加载中...</div>
</template>

<script setup lang="ts">
// 在script setup部分添加store导入和播放歌曲方法
import { ref, onMounted, watch } from 'vue';
import {useRoute } from 'vue-router';
import { usePlayerStore } from '@/store/song';

// 创建store实例
const playerStore = usePlayerStore();

// 定义歌单类型
interface Playlist {
  id: string;
  cover: string;
  name: string;
  playCount: number;
  desc: string;
  creator: string;
  creatorAvatar: string;
  tags: string[];
  createTime: string;
  collectCount: number;
  songCount: number;
  commentCount: number;
  songs: {
    id: string;
    cover: string;
    title: string;
    tags: string[];
    artist: string;
    album: string;
    duration: string;
  }[];
}

const route = useRoute();


const playlist = ref<Playlist | null>(null);

// 模拟所有歌单的数据源（实际项目中从后端接口获取，接口地址带歌单ID）
const allPlaylists: Playlist[] = [
  {
    id: '1',
    cover: 'https://picsum.photos/200/200?random=10',
    name: '写作业必备(学霸刷题)中文安静',
    playCount: 7782.9,
    desc: '写作业必听安静歌单～本人学生一枚 不定时更新～按时完成作业 好好学习 每个人都是学霸～',
    creator: 'Linz05-',
    creatorAvatar: 'https://picsum.photos/40/40?random=20',
    tags: ['学习', '华语', '安静'],
    createTime: '2017-05-15',
    collectCount: 97.3,
    songCount: 102,
    commentCount: 5274,
    songs: [
      {
        id: '1',
        cover: 'https://picsum.photos/40/40?random=30',
        title: '食梦',
        tags: ['超清母带'],
        artist: 'Y-1-U / 西几Lionkin',
        album: '食梦',
        duration: '02:59'
      },
      {
        id: '2',
        cover: 'https://picsum.photos/40/40?random=31',
        title: 'Why Silence【Op.1】',
        tags: ['超清母带'],
        artist: '陈海容',
        album: 'Why Silence',
        duration: '02:08'
      }
      // 更多歌曲...
    ]
  },
  {
    id: '2',
    name: '分享歌单',
    cover: 'https://picsum.photos/200/200?random=11',
    playCount: 100,
    desc: '我的分享歌单',
    creator: '用户123',
    creatorAvatar: 'https://picsum.photos/40/40?random=21',
    tags: ['流行'],
    createTime: '2023-01-01',
    collectCount: 1,
    songCount: 10,
    commentCount: 0,
    songs: [/* 歌曲列表 */]
  }
];

// 核心函数：根据歌单ID获取数据
const fetchPlaylistData = () => {
  const playlistId = route.params.id as string; // 获取当前路由的歌单ID
  // 匹配对应ID的歌单（接口请求则替换为 axios.get(`/api/playlist/${playlistId}`)）
  playlist.value = allPlaylists.find(item => item.id === playlistId) || null;
};


// 1. 组件首次挂载时加载数据（原有逻辑）
onMounted(() => {
  fetchPlaylistData();
});

// 2. 监听路由参数id变化，重新加载数据（新增核心逻辑）
watch(
  () => route.params.id, // 监听的目标：路由参数id
  () => {
    fetchPlaylistData(); // id变化时，重新获取数据
  }
);
// 添加播放歌曲方法
const handlePlaySong = (song: any) => {
  // 调用store中的playSong方法
  playerStore.playSong(song);
};

</script>

<style scoped>
/* 样式仅做示例，可根据需求美化 */
.playlist-detail { padding: 24px; max-width: 1440px; margin: 0 auto; }
.header { display: flex; gap: 20px; margin-bottom: 20px; }
.cover { width: 160px; height: 160px; border-radius: 8px; }
.info .play-count { color: #999; font-size: 14px; }
.info .name { font-size: 24px; font-weight: 600; margin: 8px 0; }
.action-buttons { display: flex; gap: 12px; margin-bottom: 20px; }
.action-buttons button { padding: 8px 16px; border-radius: 4px; border: none; cursor: pointer; }
.play-all { background: #ff3a3a; color: #fff; }
.song-list { margin-top: 20px; }
.list-header { display: flex; background: #f5f5f5; padding: 12px; font-weight: 500; }
.col { flex: 1; text-align: left; }
.song-item { display: flex; align-items: center; padding: 12px; border-bottom: 1px solid #eee; }
.song-info { margin-left: 8px; }
</style>