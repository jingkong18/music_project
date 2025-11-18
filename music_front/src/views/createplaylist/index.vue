<template>
  <div class="playlist-manager">
    <!-- 歌单下拉区域 -->
    <div class="playlist-dropdown">
      <button 
        class="dropdown-trigger" 
        @click="toggleDropdown"
      >
        创建的歌单 {{ createdPlaylists.length }}
        <i :class="isDropdownOpen ? 'icon-up' : 'icon-down'"></i>
      </button>
      <button 
        class="add-btn" 
        @click="showCreateMenu = true"
      >
        +
      </button>
    </div>

    <!-- 下拉菜单（已创建歌单） -->
    <div 
      class="dropdown-menu" 
      v-if="isDropdownOpen"
    >
      <div class="playlist-item" v-for="(item, idx) in createdPlaylists" :key="idx" @click="ToPlaylistDetail(item.id)">
        <img :src="item.cover" alt="歌单封面" class="playlist-cover" />
        <span class="playlist-name">{{ item.name }}</span>
      </div>
    </div>

    <!-- 创建歌单的菜单（增加关闭按钮+美化） -->
    <div 
      class="create-menu" 
      v-if="showCreateMenu"
    >
      <!-- 关闭按钮 -->
      <button class="menu-close-btn" @click="showCreateMenu = false">×</button>
      <button 
        class="menu-item" 
        @click="showCreateModal = true; showCreateMenu = false"
      >
        创建歌单
      </button>
    </div>

    <!-- 创建歌单弹窗 -->
    <div 
      class="modal-overlay" 
      v-if="showCreateModal"
    >
      <div class="modal-content">
        <div class="modal-header">
          <h3>创建歌单</h3>
          <button class="close-btn" @click="showCreateModal = false">×</button>
        </div>
        <div class="modal-body">
          <input 
            type="text" 
            placeholder="输入歌单标题" 
            class="playlist-title-input"
            v-model="newPlaylistTitle"
            maxlength="40"
          />
          <span class="char-count">{{ newPlaylistTitle.length }}/40</span>
          <label class="private-checkbox">
            <input type="checkbox" v-model="isPrivatePlaylist" />
            设为隐私歌单
          </label>
        </div>
        <div class="modal-footer">
          <button 
            class="create-btn" 
            @click="handleCreatePlaylist"
            :disabled="!newPlaylistTitle.trim()"
          >
            创建
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';



const router = useRouter();
const ToPlaylistDetail = (id: number) => {
  router.push({ name: 'PlaylistDetail', params: { id } });
};



const isDropdownOpen = ref(false);
const showCreateMenu = ref(false);
const showCreateModal = ref(false);
const newPlaylistTitle = ref('');
const isPrivatePlaylist = ref(false);

const createdPlaylists = ref([
  { id: 4, cover: 'https://picsum.photos/40/40?random=140', name: '分享歌单' },
  { id: 5, cover: 'https://picsum.photos/40/40?random=141', name: '@青空的十年精选辑' },
  { id: 6, cover: 'https://picsum.photos/40/40?random=142', name: '本地音乐' }
]);

const toggleDropdown = () => {
  isDropdownOpen.value = !isDropdownOpen.value;
  if (isDropdownOpen.value) showCreateMenu.value = false;
};

const handleCreatePlaylist = () => {
  if (!newPlaylistTitle.value.trim()) return;
  createdPlaylists.value.push({
    id: createdPlaylists.value.length + 1,
    cover: 'https://picsum.photos/40/40?random=' + Math.floor(Math.random() * 1000),
    name: newPlaylistTitle.value
  });
  newPlaylistTitle.value = '';
  isPrivatePlaylist.value = false;
  showCreateModal.value = false;
};
</script>

<style scoped>
/* 基础样式 */
.playlist-manager {
  position: relative;
  width: 180px;
  margin: 16px;
}

/* 下拉触发区域 */
.playlist-dropdown {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 0 0 0;
  /* border: 1px solid #e0e0e0; */
  border-radius: px;
  cursor: pointer;
  background-color: #fff;
}


.dropdown-trigger {
  background: none;
  border: none;
  cursor: pointer;
  font-size: 12px;
  color: #979797;
  display: flex;
  align-items: center;
}

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

.add-btn {
  width: 24px;
  height: 24px;
  border: 1px solid #e0e0e0;
  border-radius: 4px;
  background: #fff;
  cursor: pointer;
  font-size: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
  transition: background-color 0.2s;
}
.add-btn:hover {
  background-color: #f5f5f5;
}

/* 下拉歌单列表 */
.dropdown-menu {
  position: absolute;
  top: calc(100% + 4px);
  left: 0;
  width: 100%;
  border: 1px solid #e0e0e0;
  border-radius: 6px;
  background-color: #fff;
  z-index: 10;
  box-shadow: 0 4px 12px rgba(0,0,0,0.08);
  overflow: hidden;
}

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
}

/* 创建歌单菜单（增加关闭按钮+美化） */
.create-menu {
  position: absolute;
  top: calc(100% + 4px);
  right: 0;
  width: 140px;
  border: 1px solid #e0e0e0;
  border-radius: 6px;
  background-color: #fff;
  z-index: 10;
  box-shadow: 0 4px 12px rgba(0,0,0,0.08);
  padding-top: 8px;
  padding-bottom: 8px;
}

/* 菜单关闭按钮 */
.menu-close-btn {
  position: absolute;
  top: 4px;
  right: 4px;
  width: 20px;
  height: 20px;
  background: none;
  border: none;
  font-size: 14px;
  cursor: pointer;
  color: #999;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  transition: background-color 0.2s;
}
.menu-close-btn:hover {
  background-color: #f5f5f5;
}

.menu-item {
  width: 100%;
  padding: 10px 16px;
  border: none;
  background: #fff;
  text-align: left;
  cursor: pointer;
  font-size: 14px;
  color: #333;
  transition: background-color 0.2s;
}
.menu-item:hover {
  background-color: #f8f9fa;
}

/* 创建歌单弹窗 */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 20;
  backdrop-filter: blur(2px);
}

.modal-content {
  width: 320px;
  background-color: #fff;
  border-radius: 8px;
  padding: 24px;
  box-shadow: 0 8px 24px rgba(0,0,0,0.12);
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.modal-header h3 {
  font-size: 18px;
  font-weight: 600;
  margin: 0;
  color: #222;
}
.close-btn {
  background: none;
  border: none;
  font-size: 20px;
  cursor: pointer;
  color: #999;
  width: 24px;
  height: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  transition: background-color 0.2s;
}
.close-btn:hover {
  background-color: #f5f5f5;
}

.playlist-title-input {
  width: 100%;
  padding: 12px 16px;
  border: 1px solid #e0e0e0;
  border-radius: 6px;
  margin-bottom: 8px;
  outline: none;
  font-size: 14px;
  transition: border-color 0.2s;
}
.playlist-title-input:focus {
  border-color: #4096ff;
  box-shadow: 0 0 0 2px rgba(64, 154, 255, 0.1);
}

.char-count {
  font-size: 12px;
  color: #999;
  margin-bottom: 16px;
  display: block;
}

.private-checkbox {
  font-size: 14px;
  color: #333;
  margin-bottom: 24px;
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
}
.private-checkbox input {
  width: 16px;
  height: 16px;
  cursor: pointer;
}

.create-btn {
  width: 100%;
  padding: 12px;
  border: none;
  border-radius: 6px;
  background-color: #ff7d97;
  color: #fff;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.2s;
}
.create-btn:disabled {
  background-color: #e0e0e0;
  cursor: not-allowed;
}
.create-btn:hover:not(:disabled) {
  background-color: #ff5c7c;
}
</style>