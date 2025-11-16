<template>
  <div class="vip-chart-container">
    <!-- 标题区域 -->
    <div class="section-header">
      <h2 class="section-title">黑胶VIP 排行榜</h2>
    </div>

    <!-- 排行榜网格布局 -->
    <div class="chart-grid">
      <!-- 循环渲染3个榜单 -->
      <div v-for="(chart, idx) in chartData" :key="idx" class="chart-card">
        <!-- 榜单头部：名称+播放按钮 -->
        <div class="chart-card-header">
          <span class="chart-name">{{ chart.name }}</span>
          <button class="play-all-btn" @click="handlePlayAll(chart.name)">
            <i class="icon-play">▶</i> 播放
          </button>
        </div>

        <!-- 榜单歌曲列表 -->
        <div class="chart-song-list">
          <div v-for="(song, songIdx) in chart.songs" :key="songIdx" class="chart-song-item"
            @mouseenter="handleSongHover(idx, songIdx, true)" @mouseleave="handleSongHover(idx, songIdx, false)">
            <!-- 排名 -->
            <div class="song-rank" :class="{ top3: song.rank <= 3 }">
              {{ song.rank }}
            </div>

            <!-- 封面 -->
            <img :src="song.cover" alt="歌曲封面" class="song-cover" />

            <!-- 歌曲信息 -->
            <div class="song-info">
              <h4 class="song-title">{{ song.title }}</h4>
              <div class="song-meta">
                <span v-for="(tag, tagIdx) in song.tags" :key="tagIdx" class="song-tag">
                  {{ tag }}
                </span>
                <span class="song-artist">{{ song.artist }}</span>
              </div>
            </div>

            <!-- 状态标签（热门/飙升等） -->
            <div v-if="song.status" class="song-status" :class="`status-${song.status.toLowerCase()}`">
              {{ song.status }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

// 定义数据类型
interface ChartSong {
  rank: number; // 排名
  title: string; // 歌曲名
  artist: string; // 歌手
  tags: string[]; // 标签（VIP/SQ/MV）
  status?: '热门' | '飙升' | '新晋' | '霸榜'; // 状态
  cover: string; // 封面图
}

interface VipChart {
  name: string; // 榜单名称
  songs: ChartSong[]; // 歌曲列表
}

// 模拟榜单数据
const chartData: VipChart[] = [
  {
    name: '热歌榜',
    songs: [
      {
        rank: 1,
        title: '恋人',
        artist: '李荣浩',
        tags: ['VIP', 'MV'],
        status: '霸榜',
        cover: 'https://picsum.photos/200/200?random=60'
      },
      {
        rank: 2,
        title: '其实',
        artist: '薛之谦',
        tags: ['VIP', 'MV'],
        status: '飙升',
        cover: 'https://picsum.photos/200/200?random=61'
      },
      {
        rank: 3,
        title: '唯一',
        artist: 'G.E.M.邓紫棋',
        tags: ['VIP'],
        status: '飙升',
        cover: 'https://picsum.photos/200/200?random=62'
      }
    ]
  },
  {
    name: '新歌榜',
    songs: [
      {
        rank: 1,
        title: '昨日歌',
        artist: '蔡鹤峰',
        tags: ['VIP'],
        status: '热门',
        cover: 'https://picsum.photos/200/200?random=63'
      },
      {
        rank: 2,
        title: 'Hunting Soul',
        artist: 'トシロウ (CV.谷山纪章)',
        tags: ['VIP'],
        status: '新晋',
        cover: 'https://picsum.photos/200/200?random=64'
      },
      {
        rank: 3,
        title: 'The Fate of Ophelia',
        artist: 'Taylor Swift',
        tags: ['VIP'],
        status: '飙升',
        cover: 'https://picsum.photos/200/200?random=65'
      }
    ]
  },
  {
    name: '爱搜榜',
    songs: [
      {
        rank: 1,
        title: '其实',
        artist: '薛之谦',
        tags: ['VIP', 'MV'],
        status: '热门',
        cover: 'https://picsum.photos/200/200?random=66'
      },
      {
        rank: 2,
        title: '我是一只鱼',
        artist: '梓渝',
        tags: ['VIP', 'SQ'],
        status: '新晋',
        cover: 'https://picsum.photos/200/200?random=67'
      },
      {
        rank: 3,
        title: '打火机',
        artist: 'Penny',
        tags: ['VIP'],
        status: '飙升',
        cover: 'https://picsum.photos/200/200?random=68'
      }
    ]
  }
];

// 歌曲悬停状态（控制封面放大）
const hoveredSongIdx = ref<{ [key: number]: number | null }>({
  0: null,
  1: null,
  2: null
});

// 处理歌曲悬停
const handleSongHover = (chartIdx: number, songIdx: number, isHover: boolean) => {
  hoveredSongIdx.value[chartIdx] = isHover ? songIdx : null;
};

// 处理播放全部
const handlePlayAll = (chartName: string) => {
  console.log(`播放${chartName}全部歌曲`);
  // 实际项目中可添加播放逻辑
};
</script>

<style scoped>
/* 组件外层容器 */
.vip-chart-container {
  max-width: 1440px;
  margin: 0 auto;
  padding: 24px;
}

/* 标题区域 */
.section-header {
  margin-bottom: 20px;
}

.section-title {
  font-size: 22px;
  font-weight: 600;
  color: #1d2129;
  display: flex;
  align-items: center;
}

.section-title::before {
  content: '';
  width: 4px;
  height: 20px;
  background-color: #ff3a3a;
  margin-right: 8px;
  border-radius: 2px;
}

/* 榜单网格布局 */
.chart-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
}

/* 单个榜单卡片 */
.chart-card {
  background-color: #fff;
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.04);
  transition: box-shadow 0.3s ease;
}

.chart-card:hover {
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

/* 榜单头部 */
.chart-card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}

.chart-name {
  font-size: 18px;
  font-weight: 500;
  color: #1d2129;
  cursor: pointer;
  transition: color 0.2s;
}

.chart-name:hover {
  color: #ff3a3a;
}

/* 播放全部按钮 */
.play-all-btn {
  display: flex;
  align-items: center;
  gap: 6px;
  padding: 6px 14px;
  border: 1px solid #e5e5e5;
  border-radius: 20px;
  background-color: #fff;
  color: #1d2129;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.play-all-btn:hover {
  border-color: #ff3a3a;
  color: #ff3a3a;
  background-color: #fef7f7;
}

.play-all-btn .icon-play {
  font-size: 12px;
}

/* 榜单歌曲列表 */
.chart-song-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

/* 单个歌曲条目 */
.chart-song-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 8px 0;
  cursor: pointer;
  border-radius: 8px;
  transition: background-color 0.2s;
}

.chart-song-item:hover {
  background-color: #fafafa;
}

/* 排名样式 */
.song-rank {
  width: 24px;
  height: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 14px;
  font-weight: 500;
  color: #86868b;
}

/* 前三名排名特殊样式 */
.song-rank.top3:nth-child(1) {
  color: #ff3a3a;
}

.song-rank.top3:nth-child(2) {
  color: #ff7d00;
}

.song-rank.top3:nth-child(3) {
  color: #ffb400;
}

/* 歌曲封面 */
.song-cover {
  width: 48px;
  height: 48px;
  border-radius: 6px;
  object-fit: cover;
  transition: transform 0.2s ease;
}

/* 悬停时封面放大 */
.chart-song-item:hover .song-cover {
  transform: scale(1.08);
}

/* 歌曲信息区域 */
.song-info {
  flex: 1;
  overflow: hidden;
}

.song-title {
  font-size: 15px;
  font-weight: 500;
  color: #1d2129;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  margin-bottom: 4px;
}

.song-meta {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 6px;
  font-size: 12px;
  color: #86868b;
}

/* 歌曲标签 */
.song-tag {
  padding: 1px 5px;
  border-radius: 4px;
  font-size: 10px;
  color: #fff;
}

.song-tag:nth-child(1):contains('VIP') {
  background-color: #ff6b6b;
}

.song-tag:nth-child(2):contains('SQ') {
  background-color: #00a1d6;
}

.song-tag:nth-child(2):contains('MV') {
  background-color: #9f7aea;
}

/* 歌曲状态标签 */
.song-status {
  font-size: 12px;
  font-weight: 500;
  padding: 2px 8px;
  border-radius: 12px;
}

/* 状态标签颜色 */
.status-热门 {
  color: #27ae60;
  background-color: #e6f4ea;
}

.status-飙升 {
  color: #e94343;
  background-color: #fde4e4;
}

.status-新晋 {
  color: #3498db;
  background-color: #e6f1ff;
}

.status-霸榜 {
  color: #9f7aea;
  background-color: #f3e8ff;
}

/* 响应式适配 */
@media (max-width: 1200px) {
  .chart-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 768px) {
  .chart-grid {
    grid-template-columns: 1fr;
  }

  .vip-chart-container {
    padding: 16px;
  }

  .section-title {
    font-size: 20px;
  }

  .chart-card {
    padding: 16px;
  }

  .song-cover {
    width: 40px;
    height: 40px;
  }
}
</style>