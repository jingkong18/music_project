<template>
  <div class="play-history-page">
    <!-- 列表头部 -->
    <div class="list-header">
      <div class="col col-index">#</div>
      <div class="col col-title">标题</div>
      <div class="col col-artist">作者</div>
      <div class="col col-play-time">播放时间</div>
    </div>

    <!-- 播放历史列表 -->
    <div class="history-list">
      <div class="history-item">
        <div class="col col-index">
          <button class="play-btn">
            <i class="icon-play">▶</i>
          </button>
        </div>
        <div class="col col-title">
          <img src="https://picsum.photos/200/200?random=112" alt="歌曲封面" class="song-cover" />
          <span class="song-name">What a Day</span>
        </div>
        <div class="col col-artist">蔡徐坤</div>
        <div class="col col-play-time">刚刚</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// 可扩展：若有多个历史记录，可定义数据结构循环渲染
</script>

<style scoped>
/* 页面整体样式 */
.play-history-page {
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
}

.col-title {
  flex: 3;
  display: flex;
  align-items: center;
}

.col-artist {
  width: 200px;
  justify-content: center;
}

.col-play-time {
  width: 120px;
  justify-content: center;
}

/* 历史记录条目 */
.history-item {
  display: flex;
  border-bottom: 1px solid #e5e5e5;
  background-color: #ffffff;
  transition: background-color 0.2s;
}

.history-item:hover {
  background-color: #fafafa;
}

.song-cover {
  width: 40px;
  height: 40px;
  object-fit: cover;
  border-radius: 4px;
  margin-right: 12px;
}

.song-name {
  font-size: 14px;
  color: #333;
}

.play-btn {
  background: none;
  border: none;
  color: #999;
  font-size: 18px;
  cursor: pointer;
  transition: color 0.2s;
}

.play-btn:hover {
  color: #ff3a3a;
}

/* 图标样式 */
.icon-play::before {
  content: '▶';
}
</style>