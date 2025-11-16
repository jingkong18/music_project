<template>
  <div class="home">
    <!-- 页面标题 -->
    <h2 class="page-title">推荐歌单</h2>
    
    <!-- 歌单列表：横向滚动+网格布局结合 -->
    <div class="playlist-list">
      <!-- 循环渲染歌单卡片 -->
   <PlayListCard 
      v-for="item in playlistData" 
      :key="item.title" 
      :item="item" 
    />
    </div>
  </div>
</template>

<script setup lang="ts">
import PlayListCard from '@/views/sift/playlist/PlayListCard.vue';

const playlistData = [
  {
    cover: 'https://picsum.photos/300/300?random=1',
    title: '全球RNB趋势',
    playCount: 1518.4,
    desc: 'Summer Walker新专诠释自我救赎...'
  },
  {
    cover: 'https://picsum.photos/300/300?random=2',
    title: '华语流行趋势',
    playCount: 2181.1,
    desc: '韦礼安,张韶涵,吴克群领衔近期华语新歌'
  },
  {
    cover: 'https://picsum.photos/300/300?random=3',
    title: '假日游戏时间',
    playCount: 56.8,
    desc: '燃动假期 极致快乐'
  },
  {
    cover: 'https://picsum.photos/300/300?random=4',
    title: '云村流行热播',
    playCount: 1395,
    desc: '五月天知足和300首品质好歌',
    isPlayable: true // 显示播放按钮
  },
  {
    cover: 'https://picsum.photos/300/300?random=5',
    title: 'ACG新歌到',
    playCount: 134.6,
    desc: '无刺有刺/偶像梦幻祭,Bangdream和更...'
  },
  {
    cover: 'https://picsum.photos/300/300?random=6',
    title: '泰语新歌到',
    playCount: 320.4,
    desc: 'NONT TANONT,BUS领衔带来...'
  },
  {
    cover: 'https://picsum.photos/300/300?random=7',
    title: '经典华语怀旧',
    playCount: 5542.3,
    desc: '『一百首』经典华语怀旧老歌合集'
  },
  {
    cover: 'https://picsum.photos/300/300?random=8',
    title: '乙游破防曲合集',
    playCount: 835.8,
    desc: '角色跃出屏幕与你共舞'
  }
];
</script>

<style scoped>
.home {
  padding: 24px;
  max-width: 1440px;
  margin: 0 auto;
}

.page-title {
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 16px;
  color: #333;
}

/* 横向滚动列表：核心样式不变，简化代码 */
.playlist-list {
  display: flex;
  gap: 16px;
  flex-wrap: nowrap;
  overflow-x: auto;
  padding-bottom: 8px;
}

/* 隐藏滚动条（可选，更美观） */
.playlist-list::-webkit-scrollbar {
  display: none;
}
</style>