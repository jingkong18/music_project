<template>
  <el-menu class="music-sidebar" :default-active="activeMenu" router :collapse="isCollapse" @select="handleMenuSelect">
    <!-- 顶部Logo区域 -->
    <div class="sidebar-header">
      <el-icon class="logo-icon">
       <Headset />
      </el-icon>
      <span class="logo-text">music</span>
    </div>

    <!-- 导航菜单 -->
    <el-menu-item index="/recommend"><el-icon><House /></el-icon>推荐</el-menu-item>
    <el-menu-item index="/sift"><el-icon><Collection /></el-icon>精选</el-menu-item>
    <el-menu-item index="/follow"><el-icon><UserFilled /></el-icon>关注</el-menu-item>

    <!-- 分割线 -->
    <el-menu-item disabled class="divider"></el-menu-item>

    <!-- 我的音乐区域 -->
    <div class="my-music-title">我的</div>
    <el-menu-item index="/mylike"><el-icon><Star /></el-icon>我喜欢的音乐</el-menu-item>
    <el-menu-item index="/recent"><el-icon><Clock /></el-icon>最近播放</el-menu-item>
    <el-menu-item v-if="!isExpanded" @click="isExpanded = true"><el-icon><Expand /></el-icon>更多</el-menu-item>
    <div v-else class="expanded-menu">
      <el-menu-item index="/download" ><el-icon><Download /></el-icon>下载管理</el-menu-item>
      <el-menu-item index="/local" ><el-icon><Goods /></el-icon>本地音乐</el-menu-item>
      <el-menu-item index="/collect" ><el-icon><FolderAdd /></el-icon>我的收藏</el-menu-item>
      <el-menu-item index="/disk" ><el-icon><UploadFilled /></el-icon>我的网盘音乐</el-menu-item>
      <el-menu-item @click="isExpanded = false">
        收起
      </el-menu-item>
    </div>
    <!-- 分割线 -->
    <el-menu-item disabled class="divider"></el-menu-item>

    <!-- 歌单区域 -->
    <!-- 创建歌单 -->
     <CreatePlaylist />
     <!-- 收藏的歌单 -->
     <CollectPlaylist />
  </el-menu>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import {
  House, Collection, UserFilled, UploadFilled,
  Star, Clock, Expand, Plus, Headset, Download,Goods,FolderAdd
} from '@element-plus/icons-vue';
import CreatePlaylist from '@/views/createplaylist/index.vue';
import CollectPlaylist from '@/views/collectplaylist/index.vue';

const activeMenu = ref('/recommend');
const isCollapse = ref(false);

// 控制展开/折叠状态
const isExpanded = ref(false);

const handleMenuSelect = (key: string) => {
  activeMenu.value = key;
  // 可在此处添加路由跳转或其他业务逻辑
};
</script>

<style scoped>
.music-sidebar {
  width: 240px;
  background-color: #fff;
  border-right: 1px solid #eee;
  height: 100vh;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.sidebar-header {
  display: flex;
  align-items: center;
  padding: 20px 24px;
  border-bottom: 1px solid #eee;
}

.logo-icon {
  font-size: 30px;
  color: #2d66ec;
  margin-right: 8px;
}

.logo-text {
  font-size: 18px;
  font-weight: bold;
  color: #333;
}

.el-menu {
  border-right: none;
}

.el-menu-item {
  height: 48px !important;
  line-height: 48px !important;
  font-size: 14px;
}

.el-menu-item.divider {
  height: 1px !important;
  background-color: #eee;
  margin: 10px 0;
}

.my-music-title,
.playlist-title {
  font-size: 12px;
  color: #999;
  padding: 12px 24px;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.heart-icon,
.add-icon {
  float: right;
  margin-top: 12px;
  color: #999;
}

.playlist-count {
  margin: 0 8px;
  color: #333;
}
</style>