<template>
  <div class="nav-menu">
    <!-- 精选 -->
    <router-link to="/recentSong" active-class="active" exact>
      单曲
    </router-link>
    <!-- 歌单广场 -->
    <router-link to="/recentPlayList" active-class="active">
      歌单
    </router-link>
    <!-- 排行榜 -->
    <router-link to="/recentAlbum" active-class="active">
      专辑
    </router-link>
  </div>
</template>

<script setup lang="ts">
// 无需额外逻辑，路由跳转由router-link自动处理
</script>

<style scoped>
.nav-menu {
  display: flex;
  gap: 24px;
  /* 菜单项之间的间距 */
  align-items: center;
}

/* 选中样式 */
.nav-menu a.active {
  color: #000;
  font-weight: bold;
  border-bottom: 2px solid #000;
  /* 底部下划线标识选中 */
  padding-bottom: 4px;
}

/* 未选中样式 */
.nav-menu a {
  color: #666;
  text-decoration: none;
  transition: all 0.3s;
}

.nav-menu a:hover {
  color: #000;
}
</style>
