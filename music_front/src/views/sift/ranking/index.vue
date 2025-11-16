<template>
  <div class="chart-page">
    <h2 class="page-title">榜单推荐</h2>
    <Chartcategory :categories="chartCategories" />
    
    <h2 class="page-title">官方榜</h2>
    <div class="chart-grid">
      <ChartItem v-for="chart in officialCharts" :key="chart.id" :chart="chart" />
    </div>
  </div>
</template>

<script setup lang="ts">
import type { ChartCategory, Chart } from '@/types/index';
import Chartcategory from '@/views/sift/ranking/ChartCategory.vue';
import ChartItem from '@/views/sift/ranking/ChartItem.vue';

// 榜单分类数据
const chartCategories: ChartCategory[] = [
  { id: '1', name: '国风榜', cover: 'https://picsum.photos/300/300?random=20' },
  { id: '2', name: '民谣榜', cover: 'https://picsum.photos/300/300?random=21' }
];

// 官方榜数据
const officialCharts: Chart[] = [
  {
    id: '1',
    name: '飙升榜',
    updateInfo: '刚刚更新',
    cover: 'https://picsum.photos/300/300?random=22',
    songs: [
      { rank: 1, title: '我是一只鱼', artist: '梓渝', tag: '新' },
      { rank: 2, title: '如果呢', artist: '郑润泽', tag: '↑' },
      { rank: 3, title: '记忆嗅觉', artist: 'Top Barry/INDEc...', tag: '新' }
    ]
  },
  {
    id: '2',
    name: '新歌榜',
    updateInfo: '刚刚更新',
    cover: 'https://picsum.photos/300/300?random=23',
    songs: [
      { rank: 1, title: '我是一只鱼', artist: '梓渝', tag: '新' },
      { rank: 2, title: '左转灯 (1000 Times +1)', artist: '派伟俊 / mac ova seas', tag: '↓' },
      { rank: 3, title: 'Black Veil Bride', artist: 'Rosy赵露思', tag: '↓' }
    ]
  },
  {
    id: '3',
    name: '热歌榜',
    updateInfo: '更新20首',
    cover: 'https://picsum.photos/300/300?random=24',
    songs: [
      { rank: 1, title: '我是一只鱼', artist: '梓渝', tag: '新' },
      { rank: 2, title: '恋人', artist: '李荣浩', tag: '↓' },
      { rank: 3, title: '如果呢', artist: '郑润泽', tag: '↓' }
    ]
  },
  {
    id: '4',
    name: '原创榜',
    updateInfo: '每周四更新',
    cover: 'https://picsum.photos/300/300?random=25',
    songs: [
      { rank: 1, title: '一杯', artist: 'Mr.岑', tag: '新' },
      { rank: 2, title: '花伞', artist: '亞細亞驥世奇才/诺米...', tag: '新' },
      { rank: 3, title: '回声', artist: '杭异凡/王栎鑫/法老', tag: '新' }
    ]
  },
  {
    id: '5',
    name: '数字专辑畅销榜',
    updateInfo: '每30分钟更新',
    cover: 'https://picsum.photos/300/300?random=26',
    songs: [
      { rank: 1, title: '和自己对话', artist: '林俊杰', tag: '↑' },
      { rank: 2, title: '反深代词', artist: '周深', tag: '↑' },
      { rank: 3, title: '希忘Hope', artist: '华晨宇', tag: '-' }
    ]
  },
  {
    id: '6',
    name: '歌曲畅销指数榜',
    updateInfo: '每1小时更新',
    cover: 'https://picsum.photos/300/300?random=27',
    songs: [
      { rank: 1, title: '我是一只鱼', artist: '梓渝', tag: '-' },
      { rank: 2, title: '下一页', artist: '梓渝', tag: '-' },
      { rank: 3, title: 'What a Day', artist: '蔡徐坤', tag: '-' }
    ]
  }
];
</script>

<style scoped>
.chart-page {
  max-width: 1440px;
  margin: 0 auto;
  padding: 24px;
}

.page-title {
  font-size: 20px;
  font-weight: 600;
  margin: 24px 0 16px;
  color: #333;
}

.chart-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 24px;
  justify-content: space-between;
}
</style>