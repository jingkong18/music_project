<template>
  <div class="download-manage-page">
    <!-- 顶部操作栏 -->
    <div class="top-actions">
      <button class="play-btn" ><el-icon><CaretRight /></el-icon>
        播放全部
      </button>
      <button class="add-btn"><el-icon><Plus /></el-icon>
       添加到播放列表
      </button>
      <button class="collect-btn"><el-icon><StarFilled /></el-icon>
       收藏
      </button>
      <button class="delete-btn"><el-icon><DeleteFilled /></el-icon>
        删除
      </button>
    </div>

    <!-- 已选歌曲提示 -->
    <div class="selected-tip">
      <input type="checkbox" checked disabled />
      <span>已选 1 首歌曲</span>
    </div>

    <!-- 歌曲列表头部 -->
    <div class="list-header">
      <div class="col col-select"></div>
      <div class="col col-title">标题</div>
      <div class="col col-album">专辑</div>
      <div class="col col-download-time">下载时间</div>
      <div class="col col-size">大小</div>
    </div>

    <!-- 歌曲列表 -->
    <div class="song-list">
      <div class="song-item">
        <div class="col col-select">
          <input type="checkbox" checked />
        </div>
        <div class="col col-title">
          <img src="https://picsum.photos/200/200?random=113" alt="歌曲封面" class="song-cover" />
          <div class="song-info">
            <span class="song-name">如果爱</span>
            <span class="song-tag">超清母带</span>
            <span class="artist">张杰</span>
          </div>
        </div>
        <div class="col col-album">再爱我一回</div>
        <div class="col col-download-time">2025-08-26</div>
        <div class="col col-size">10.0M</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { CaretRight, Plus, StarFilled, DeleteFilled } from '@element-plus/icons-vue'
</script>

<style scoped>
/* 页面整体样式 */
.download-manage-page {
  max-width: 1440px;
  margin: 0 auto;
  padding: 24px;
  background-color: #fff;
}

/* 顶部操作栏 */
.top-actions {
  display: flex;
  gap: 12px;
  margin-bottom: 16px;
  align-items: center;
}

.play-btn {
  background-color: #ff3a3a;
  color: #fff;
  border: none;
  border-radius: 4px;
  width: 100px;
  height: 36px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

.add-btn, .collect-btn, .delete-btn {
  background-color: #f5f5f5;
  color: #333;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  padding: 8px 12px;
  display: flex;
  align-items: center;
  gap: 6px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.add-btn:hover, .collect-btn:hover, .delete-btn:hover {
  background-color: #e9e9e9;
}

.complete-btn {
  margin-left: auto;
  background-color: #fff;
  color: #333;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  padding: 8px 16px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.complete-btn:hover {
  background-color: #f5f5f5;
}

/* 已选歌曲提示 */
.selected-tip {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 12px;
  color: #666;
}

/* 列表头部 */
.list-header {
  display: flex;
  background-color: #f5f5f5;
  color: #666;
  font-weight: 500;
}

.col {
  padding: 12px;
  display: flex;
  align-items: center;
}

.col-select {
  width: 50px;
  justify-content: center;
}

.col-title {
  flex: 3;
  display: flex;
  align-items: center;
}

.col-album {
  width: 200px;
}

.col-download-time {
  width: 150px;
}

.col-size {
  width: 100px;
}

/* 歌曲条目 */
.song-item {
  display: flex;
  border-bottom: 1px solid #e5e5e5;
  transition: background-color 0.2s;
}

.song-item:hover {
  background-color: #fafafa;
}

.song-cover {
  width: 40px;
  height: 40px;
  object-fit: cover;
  border-radius: 4px;
  margin-right: 12px;
}

.song-info {
  display: flex;
  flex-direction: column;
}

.song-name {
  font-size: 14px;
  color: #333;
}

.song-tag {
  font-size: 10px;
  color: #999;
  margin-right: 4px;
}

.artist {
  font-size: 12px;
  color: #999;
}


</style>