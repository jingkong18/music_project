<template>
  <section class="official-playlists">
    <div class="section-header">
      <h2>官方歌单</h2>
      <router-link to="/playlist/all">更多 ></router-link>
    </div>
    <div class="playlist-list">
      <PlaylistCard 
        v-for="playlist in playlists" 
        :key="playlist.id" 
        :playlist="playlist" 
      />
    </div>
  </section>
</template>

<script setup lang="ts">
import PlaylistCard from '@/views/sift/PlaylistCard.vue';
import type { Playlist } from '@/types/index';

// 模拟歌单数据（实际项目中可从接口请求）
const playlists: Playlist[] = [
  {
    id: '1',
    cover: 'https://picsum.photos/300/300?random=1',
    title: '全球RNB趋势',
    playCount: 1518.4,
    description: 'Summer Walker新专诠释自我救赎...',
    route: '/playlist/1'
  },
  {
    id: '2',
    cover: 'https://picsum.photos/300/300?random=2',
    title: '华语流行趋势',
    playCount: 2181.1,
    description: '韦礼安,张韶涵,吴克群领衔近期华语新歌',
    route: '/playlist/2'
  },
  {
    id: '3',
    cover: 'https://picsum.photos/300/300?random=3',
    title: '假日游戏时间',
    playCount: 56.8,
    description: '燃动假期 极致快乐',
    route: '/playlist/3'
  },
  {
    id: '4',
    cover: 'https://picsum.photos/300/300?random=4',
    title: '云村流行热播',
    playCount: 1395,
    description: '五月天知足和300首品质好歌',
    route: '/playlist/4'
  },
  {
    id: '5',
    cover: 'https://picsum.photos/300/300?random=5',
    title: 'ACG新歌到',
    playCount: 134.6,
    description: '无刺有刺/偶像梦幻祭,Bangdream和更...',
    route: '/playlist/5'
  },
  {
    id: '6',
    cover: 'https://picsum.photos/300/300?random=6',
    title: '泰语新歌到',
    playCount: 320.4,
    description: 'NONT TANONT,BUS领衔带来...',
    route: '/playlist/6'
  }
];
</script>

<style scoped>
.official-playlists {
  margin: 24px 0;
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}

.section-header h2 {
  font-size: 20px;
  font-weight: 600;
}

.section-header a {
  color: #666;
  text-decoration: none;
  font-size: 14px;
}

.playlist-list {
  display: flex;
  gap: 16px;
  flex-wrap: nowrap;
  overflow-x: auto; /* 横向滚动 */
  padding-bottom: 8px;
}

/* 隐藏滚动条（可选） */
.playlist-list::-webkit-scrollbar {
  display: none;
}
</style>