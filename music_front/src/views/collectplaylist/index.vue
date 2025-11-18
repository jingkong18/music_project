<template>
  <div class="collected-playlist-container">

    <!-- 触发按钮 -->
    <button 
      class="dropdown-trigger" 
      @click="toggleDropdown"
    >
      收藏的歌单 {{ collectedPlaylists.length }}
      <i :class="isDropdownOpen ? 'icon-up' : 'icon-down'"></i>
    </button>
    

    <!-- 收藏歌单列表 -->
    <div 
      class="dropdown-menu" 
      v-if="isDropdownOpen"
    >
      <div 
        class="playlist-item" 
        v-for="(item) in collectedPlaylists" 
        :key="item.id"
        @click="ToPlaylistDetail(item.id)"
      >
        <img :src="item.cover" alt="歌单封面" class="playlist-cover" />
        <span class="playlist-name">{{ item.name }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';

// 下拉菜单状态
const isDropdownOpen = ref(false);

// 收藏的歌单数据
const collectedPlaylists = ref([
  { 
    id: 1,
    cover: 'https://picsum.photos/40/40?random=150', 
    name: '经典「神仙打架的华语乐坛」_华语经典...' 
  },
  { 
    id: 2,
    cover: 'https://picsum.photos/40/40?random=151', 
    name: '橙子青提' 
  },
  { 
    id: 3,
    cover: 'https://picsum.photos/40/40?random=152', 
    name: '韩剧《十八岁的瞬间》BGM&OST' 
  },
  { 
    id: 4,
    cover: 'https://picsum.photos/40/40?random=153', 
    name: '正在审核中...' 
  }
]);

// 切换下拉菜单显示/隐藏
const toggleDropdown = () => {
  isDropdownOpen.value = !isDropdownOpen.value;
};

// 点击跳转到详情页，传递歌单ID
const router = useRouter();
const ToPlaylistDetail = (id: number) => {
  router.push({ name: 'PlaylistDetail', params: { id } });
};
</script>

<style scoped>
/* 容器样式 */
.collected-playlist-container {
  width: 180px;
  margin: 16px;
  position: relative;
}

/* 触发按钮 */
.dropdown-trigger {
  width: 100%;
  padding: 8px 5px 0px 5px;
  border-radius: 6px;
  background-color: #fff;
  cursor: pointer;
  font-size: 12px;
  color: #a2a2a2;
  display: flex;
  justify-content: space-between;
  /* transition: border-color 0.2s; */
  border: none; /* 去掉边框 */
}
.dropdown-trigger:hover {
  border-color: #ccc;
}

/* 箭头图标 */
.icon-down::after {
  content: '▼';
  font-size: 12px;
  color: #999;
}
.icon-up::after {
  content: '▲';
  font-size: 12px;
  color: #999;
}

/* 下拉菜单 */
.dropdown-menu {
  position: absolute;
  top: calc(100% + 4px);
  left: 0;
  width: 100%;
  border-radius: 6px;
  background-color: #fff;
  z-index: 10;
  overflow: hidden;
}

/* 歌单条目 */
.playlist-item {
  display: flex;
  align-items: center;
  padding: 10px 12px;
  cursor: pointer;
  transition: background-color 0.2s;
}
.playlist-item:hover {
  background-color: #f8f9fa;
}

.playlist-cover {
  width: 32px;
  height: 32px;
  border-radius: 4px;
  margin-right: 8px;
  object-fit: cover;
}

.playlist-name {
  font-size: 14px;
  color: #333;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>