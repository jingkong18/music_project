<template>
  <div class="album-collection-page">
    <!-- 顶部标签栏 -->
    <div class="top-tabs">
      <button class="tab active">专辑</button>
      <div class="search-bar">
        <input type="text" placeholder="搜索" class="search-input" />
      </div>
    </div>

    <!-- 子标签栏 -->
    <div class="sub-tabs">
      <button 
        class="sub-tab" 
        :class="{ active: currentTab === 'collected' }"
        @click="switchTab('collected')"
      >
        收藏专辑
      </button>
      <button 
        class="sub-tab" 
        :class="{ active: currentTab === 'purchased' }"
        @click="switchTab('purchased')"
      >
        已购专辑
      </button>
    </div>

    <!-- 专辑列表（根据当前标签切换内容） -->
    <div class="album-list">
      <!-- 收藏专辑界面 -->
      <div v-if="currentTab === 'collected'" class="album-grid">
        <div 
          v-for="(album, index) in collectedAlbums" 
          :key="index" 
          class="album-item"
          @click="ToAlbumDetail(album.id)"
        >
          <img :src="album.cover" alt="专辑封面" class="album-cover" />
          <div class="album-info">
            <h3 class="album-title">{{ album.title }}</h3>
            <p class="album-artist">{{ album.artist }}</p>
            <p class="album-tag">{{ album.tag }}</p>
          </div>
        </div>
      </div>

      <!-- 已购专辑界面 -->
      <div v-else-if="currentTab === 'purchased'" class="album-grid">
        <div 
          v-for="(album, index) in purchasedAlbums" 
          :key="index" 
          class="album-item"
        >
          <img :src="album.cover" alt="专辑封面" class="album-cover" />
          <div class="album-info">
            <h3 class="album-title">{{ album.title }}</h3>
            <p class="album-artist">{{ album.artist }}</p>
            <div class="purchase-info">
              <span class="purchase-time">{{ album.purchaseTime }}</span>
              <span class="vip-tag" v-if="album.isVIP">VIP专享</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';

// 路由实例
const router = useRouter();

// 跳转专辑详情页
const ToAlbumDetail = (id: string) => {
  router.push({ name: 'AlbumDetail', params: { id } });
};



// 定义当前激活的子标签（默认收藏专辑）
const currentTab = ref('collected');

// 定义专辑数据类型
interface Album {
  id: string;
  cover: string;
  title: string;
  artist: string;
  tag?: string;
  purchaseTime?: string;
  isVIP?: boolean;
}

// 收藏专辑数据
const collectedAlbums: Album[] = [
  {
    id: '1',
    cover: 'https://picsum.photos/300/300?random=120',
    title: 'BBC新闻100篇',
    artist: 'BBC官方',
    tag: '英语听力'
  },
  {
    id: '2',
    cover: 'https://picsum.photos/300/300?random=121',
    title: '华语经典老歌精选',
    artist: 'Various Artists',
    tag: '经典华语'
  },
  {
    id: '3',
    cover: 'https://picsum.photos/300/300?random=122',
    title: 'Midnight Melodies',
    artist: 'Luna Wave',
    tag: '纯音乐 / 放松'
  },
  {
    id: '4',
    cover: 'https://picsum.photos/300/300?random=123',
    title: '青春修炼手册',
    artist: 'TFBOYS',
    tag: '流行 / 青春'
  }
];

// 已购专辑数据
const purchasedAlbums: Album[] = [
  {
    id: '5',
    cover: 'https://picsum.photos/300/300?random=124',
    title: '最伟大的作品',
    artist: '周杰伦',
    purchaseTime: '2024-03-15',
    isVIP: true
  },
  {
    id: '6',
    cover: 'https://picsum.photos/300/300?random=125',
    title: '希忘Hope',
    artist: '华晨宇',
    purchaseTime: '2024-01-20',
    isVIP: true
  },
  {
    id: '7',
    cover: 'https://picsum.photos/300/300?random=126',
    title: 'Autumn Leaves',
    artist: 'Ella Fitzgerald',
    purchaseTime: '2023-11-05',
    isVIP: false
  },
  {
    id: '8',
    cover: 'https://picsum.photos/300/300?random=127',
    title: '我的歌声里',
    artist: '曲婉婷',
    purchaseTime: '2023-09-18',
    isVIP: false
  },
  {
    id: '9',
    cover: 'https://picsum.photos/300/300?random=128',
    title: '莲',
    artist: '张艺兴',
    purchaseTime: '2024-05-22',
    isVIP: true
  }
];

// 切换子标签
const switchTab = (tab: 'collected' | 'purchased') => {
  currentTab.value = tab;
};
</script>

<style scoped>
/* 页面整体样式 */
.album-collection-page {
  max-width: 1440px;
  margin: 0 auto;
  padding: 24px;
  background-color: #fff;
}

/* 顶部标签栏 */
.top-tabs {
  display: flex;
  align-items: center;
  margin-bottom: 24px;
  border-bottom: 1px solid #f0f0f0;
  padding-bottom: 8px;
}

.tab {
  background: none;
  border: none;
  font-size: 18px;
  font-weight: 500;
  color: #999;
  padding: 8px 16px;
  cursor: pointer;
  transition: all 0.2s;
}

.tab.active {
  color: #333;
  border-bottom: 2px solid #ff3a3a;
  padding-bottom: 6px;
}

.tab:hover:not(.active) {
  color: #666;
}

.search-bar {
  margin-left: auto;
}

.search-input {
  padding: 8px 12px;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  width: 220px;
  font-size: 14px;
  outline: none;
  transition: border-color 0.2s;
}

.search-input:focus {
  border-color: #3498db;
}

/* 子标签栏 */
.sub-tabs {
  display: flex;
  gap: 16px;
  margin-bottom: 32px;
}

.sub-tab {
  padding: 8px 20px;
  border: 1px solid #e5e5e5;
  border-radius: 24px;
  background: #fff;
  cursor: pointer;
  font-size: 14px;
  color: #333;
  transition: all 0.2s;
}

.sub-tab.active {
  background-color: #fde4e4;
  color: #ff3a3a;
  border-color: #fde4e4;
  font-weight: 500;
}

.sub-tab:hover:not(.active) {
  border-color: #d9d9d9;
  background-color: #fafafa;
}

/* 专辑列表网格布局 */
.album-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(160px, 1fr));
  gap: 24px;
}

/* 专辑卡片样式 */
.album-item {
  display: flex;
  flex-direction: column;
  cursor: pointer;
  transition: transform 0.2s, box-shadow 0.2s;
}

.album-item:hover {
  transform: translateY(-6px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.08);
}

.album-cover {
  width: 100%;
  height: 160px;
  object-fit: cover;
  border-radius: 8px;
  margin-bottom: 12px;
}

.album-info {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.album-title {
  font-size: 14px;
  font-weight: 500;
  color: #333;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.album-artist {
  font-size: 12px;
  color: #999;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.album-tag {
  font-size: 12px;
  color: #666;
  background-color: #f5f5f5;
  width: fit-content;
  padding: 2px 8px;
  border-radius: 12px;
  margin-top: 4px;
}

/* 已购专辑专属样式 */
.purchase-info {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-top: 4px;
  font-size: 12px;
}

.purchase-time {
  color: #999;
}

.vip-tag {
  color: #ff3a3a;
  background-color: #fef7f7;
  padding: 2px 8px;
  border-radius: 12px;
}

/* 响应式适配 */
@media (max-width: 768px) {
  .album-grid {
    grid-template-columns: repeat(auto-fill, minmax(140px, 1fr));
    gap: 16px;
  }

  .album-cover {
    height: 140px;
  }

  .top-tabs {
    flex-wrap: wrap;
    gap: 8px;
  }

  .search-bar {
    margin-left: 0;
    margin-top: 12px;
    width: 100%;
  }

  .search-input {
    width: 100%;
  }
}

@media (max-width: 480px) {
  .album-grid {
    grid-template-columns: repeat(2, 1fr);
  }

  .sub-tabs {
    flex-wrap: wrap;
  }

  .sub-tab {
    flex: 1;
    text-align: center;
  }
}
</style>