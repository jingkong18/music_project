<template>
  <div class="playlist-collection-page">
    <!-- 列表头部 -->
    <div class="list-header">
      <div class="col col-index">#</div>
      <div class="col col-title">标题</div>
      <div class="col col-song-count">歌曲数</div>
      <div class="col col-play-time">播放时间</div>
    </div>

    <!-- 歌单列表 -->
    <div class="playlist-list">
      <div 
        v-for="(playlist, index) in playlists" 
        :key="playlist.id" 
        class="playlist-item"
        :class="{ active: index === 3 }"
      >
        <div class="col col-index">
          {{ index + 1 }}
          <span v-if="playlist.tag" class="playlist-tag">{{ playlist.tag }}</span>
        </div>
        <div class="col col-title">
          <img :src="playlist.cover" alt="歌单封面" class="playlist-cover" />
          <span class="playlist-name">{{ playlist.name }}</span>
          <button v-if="index === 3" class="play-btn">
            <i class="icon-play">▶</i>
          </button>
          <div v-if="index === 3" class="action-icons">
            <i class="icon-add">+</i>
            <i class="icon-more">⋮</i>
          </div>
        </div>
        <div class="col col-song-count">-</div>
        <div class="col col-play-time">{{ playlist.playTime }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// 定义数据类型
interface Playlist {
  id: string;
  cover: string;
  name: string;
  tag?: string;
  playTime: string;
}

// 模拟歌单数据
const playlists = [
  {
    id: '1',
    cover: 'https://picsum.photos/200/200?random=101',
    name: '飙升榜',
    tag: '飙升榜',
    playTime: '01:02'
  },
  {
    id: '2',
    cover: 'https://picsum.photos/200/200?random=102',
    name: '90后经典旋律 | 重温那段激情又无忧的时光',
    playTime: '11-05'
  },
  {
    id: '3',
    cover: 'https://picsum.photos/200/200?random=103',
    name: '一人一首成名曲 | 青春回忆杀 火爆华语乐坛',
    playTime: '10-09'
  },
  {
    id: '4',
    cover: 'https://picsum.photos/200/200?random=104',
    name: '怀念神仙打架的年代，华语金曲一百首00-09',
    playTime: '09-19'
  },
  {
    id: '5',
    cover: 'https://picsum.photos/200/200?random=105',
    name: '小鳄鱼之歌 歌单',
    playTime: '09-18'
  },
  {
    id: '6',
    cover: 'https://picsum.photos/200/200?random=106',
    name: '神仙打架时期佳作 | 一人一首成名曲',
    playTime: '09-11'
  },
  {
    id: '7',
    cover: 'https://picsum.photos/200/200?random=107',
    name: '华语 | 100首经典老歌精选 90后回忆杀',
    playTime: '09-11'
  },
  {
    id: '8',
    cover: 'https://picsum.photos/200/200?random=108',
    name: '综艺《花儿与少年7·同心季》',
    playTime: '09-09'
  },
  {
    id: '9',
    cover: 'https://picsum.photos/200/200?random=109',
    name: '经典『神仙打架的华语乐坛』_华语经典歌',
    playTime: '09-08'
  },
  {
    id: '10',
    cover: 'https://picsum.photos/200/200?random=110',
    name: '薛之谦 10w+歌曲',
    playTime: '08-26'
  },
  {
    id: '11',
    cover: 'https://picsum.photos/200/200?random=111',
    name: '薛之谦',
    playTime: '08-26'
  }
];
</script>

<style scoped>
/* 页面整体样式 */
.playlist-collection-page {
  max-width: 1440px;
  margin: 0 auto;
  padding: 24px;
  background-color: #f9fafc;
}

/* 列表头部 */
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
  position: relative;
}

.col-title {
  flex: 3;
  display: flex;
  align-items: center;
}

.col-song-count {
  width: 100px;
  justify-content: center;
}

.col-play-time {
  width: 120px;
  justify-content: center;
}

/* 歌单条目 */
.playlist-item {
  display: flex;
  border-bottom: 1px solid #e5e5e5;
  transition: background-color 0.2s;
}

.playlist-item:hover, .playlist-item.active {
  background-color: #ffffff;
}

.playlist-cover {
  width: 40px;
  height: 40px;
  object-fit: cover;
  border-radius: 4px;
  margin-right: 12px;
}

.playlist-name {
  font-size: 14px;
  color: #333;
}

.playlist-tag {
  position: absolute;
  left: 12px;
  top: 12px;
  background-color: #6c5ce7;
  color: #fff;
  font-size: 10px;
  padding: 2px 4px;
  border-radius: 2px;
}

/* 播放按钮和操作图标 */
.play-btn {
  background: none;
  border: none;
  color: #ff3a3a;
  font-size: 18px;
  margin-right: 8px;
  cursor: pointer;
}

.action-icons {
  display: flex;
  gap: 16px;
  margin-left: auto;
  color: #999;
  cursor: pointer;
}

.action-icons i:hover {
  color: #333;
}

/* 图标样式 */
.icon-play::before {
  content: '▶';
}

.icon-add::before {
  content: '+';
}

.icon-more::before {
  content: '⋮';
}
</style>