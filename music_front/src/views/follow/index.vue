<template>
  <div class="feed-page">
    <!-- 顶部区域 -->
    <div class="top-bar">
      <h2 class="section-title">关注</h2>
      <div class="new-dynamic">有9条新动态, <a href="#">点击查看</a></div>
      <button class="publish-btn" @click="showPublishModal = true">发布</button>
    </div>

    <!-- 新歌发布提示 -->
    <div class="new-song-tip">
      <i class="icon-calendar"></i> 近三个月有2首新歌发布
    </div>

    <!-- 动态列表 -->
    <div class="dynamic-list">
      <div class="dynamic-item">
        <img src="https://picsum.photos/300/300?random=70" alt="用户头像" class="user-avatar" />
        <div class="dynamic-content">
          <div class="user-info">
            <span class="user-name">笔记小助手</span>
            <span class="publish-time">08-19</span>
          </div>
          <div class="dynamic-text">
            <p><i class="icon-horn"></i>「音乐一响自由登场」活动结束啦～ 🌟 恭喜如下8位幸运用户：@独爱张思涵 @8月OTO @鱼Hu鱼 @温柔绿茶猫 @我不看月亮hxns @橘子果酱2-2 @_清歌 @、普大乔 获得网易云黑胶VIP周卡1张，将于30个工作日内直充至您的账户，请留意会员情况变化！</p>
            <p><i class="icon-gift"></i>另外，恭喜@drinkki_5 获得实物周边礼盒一份‼️ 奖品将随机发出，请将收货地址&收件人信息于7日内发送至@笔记小助手 私信窗口【信息收集时间截至8月21日】，逾期奖品失效，请注意查收信息～</p>
            <div class="repost-item">
              <p>@笔记小助手</p>
              <p>当生活被忙碌裹挟，总需要一段旋律来破局。</p>
              <p>有没有那么一首歌，前奏一响，</p>
              <p>就像按下了自由的开关🚀</p>
              <p>耳机里蹦出的轻快节奏，让阳光都跟着舞动！</p>
              <p><i class="icon-clock"></i> 活动时间：8月1日至8月18日</p>
              <p><i class="icon-megaphone"></i> 参与方式：带话题 #音乐一响自由登场# 发布图文笔记且携带歌曲，<i class="icon-image"></i>图片≥1张，<i class="icon-pencil"></i>文案≥5字，笔记公开可见未删除。</p>
              <p><i class="icon-check"></i> 内容方向参考：</p>
              <p><i class="icon-pin"></i> 记录旅途中的自由生命力</p>
              <p><i class="icon-pin"></i> 晒出日常生活里的惬意时刻</p>
              <p><i class="icon-music"></i> 分享那首让你挣脱束缚的歌</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 发布笔记弹窗 -->
    <div v-if="showPublishModal" class="modal-overlay">
      <div class="modal-content">
        <div class="modal-header">
          <h3>发笔记</h3>
          <button class="close-btn" @click="showPublishModal = false">×</button>
        </div>
        <div class="modal-body">
          <textarea 
            placeholder="一起聊聊音乐吧~" 
            class="note-input"
            maxlength="1000"
          ></textarea>
          <div class="input-footer">
            <div class="input-tools">
              <button class="tool-btn">#</button>
              <button class="tool-btn">@</button>
              <button class="tool-btn">😊</button>
              <button class="tool-btn"><i class="icon-image"></i></button>
            </div>
            <span class="char-count">1000</span>
          </div>
          <div class="music-option">
            <i class="icon-music"></i>
            <span>给笔记配上音乐</span>
          </div>
        </div>
        <div class="modal-footer">
          <button class="publish-modal-btn" @click="showPublishModal = false">发布</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

// 控制发布弹窗显示隐藏
const showPublishModal = ref(false);
</script>

<style scoped>
/* 页面整体样式 */
.feed-page {
  max-width: 1440px;
  margin: 0 auto;
  padding: 24px;
  background-color: #fff;
}

/* 顶部区域 */
.top-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.section-title {
  font-size: 22px;
  font-weight: 600;
  color: #1d2129;
}

.new-dynamic {
  font-size: 14px;
  color: #666;
}

.new-dynamic a {
  color: #3498db;
  text-decoration: none;
}

.publish-btn {
  background-color: #ff3a3a;
  color: #fff;
  border: none;
  border-radius: 20px;
  padding: 8px 16px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.publish-btn:hover {
  background-color: #e94343;
}

/* 新歌发布提示 */
.new-song-tip {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 12px;
  background-color: #f8f9fa;
  border-radius: 8px;
  margin-bottom: 20px;
}

.icon-calendar {
  color: #3498db;
}

/* 动态列表 */
.dynamic-list {
  margin-top: 20px;
}

.dynamic-item {
  display: flex;
  gap: 12px;
  padding: 16px;
  border-radius: 8px;
  background-color: #f8f9fa;
  margin-bottom: 16px;
}

.user-avatar {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  object-fit: cover;
}

.dynamic-content {
  flex: 1;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 8px;
}

.user-name {
  font-weight: 500;
  color: #1d2129;
}

.publish-time {
  color: #86868b;
  font-size: 14px;
}

.dynamic-text {
  font-size: 14px;
  color: #333;
  line-height: 1.6;
}

.dynamic-text i {
  margin-right: 4px;
}

.icon-horn {
  color: #ffb400;
}

.icon-gift {
  color: #ff6b6b;
}

.repost-item {
  background-color: #f0f2f5;
  border-radius: 8px;
  padding: 12px;
  margin-top: 12px;
  font-size: 14px;
  color: #4e5969;
}

.icon-clock {
  color: #9f7aea;
}

.icon-megaphone {
  color: #27ae60;
}

.icon-image {
  color: #3498db;
}

.icon-check {
  color: #27ae60;
}

.icon-pin {
  color: #e94343;
}

.icon-music {
  color: #9f7aea;
}

/* 发布笔记弹窗 */
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
  z-index: 999;
}

.modal-content {
  background-color: #fff;
  border-radius: 12px;
  width: 500px;
  max-width: 90%;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px;
  border-bottom: 1px solid #e5e5e5;
}

.modal-header h3 {
  font-size: 18px;
  font-weight: 600;
  margin: 0;
}

.close-btn {
  background: none;
  border: none;
  font-size: 24px;
  cursor: pointer;
  color: #86868b;
}

.modal-body {
  padding: 16px;
}

.note-input {
  width: 100%;
  border: 1px solid #e5e5e5;
  border-radius: 8px;
  padding: 12px;
  min-height: 120px;
  font-size: 14px;
  resize: vertical;
  outline: none;
}

.note-input:focus {
  border-color: #3498db;
}

.input-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 8px;
  font-size: 12px;
  color: #86868b;
}

.input-tools {
  display: flex;
  gap: 12px;
}

.tool-btn {
  background: none;
  border: none;
  cursor: pointer;
  color: #86868b;
  font-size: 16px;
}

.tool-btn:hover {
  color: #3498db;
}

.music-option {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-top: 12px;
  padding: 12px;
  background-color: #f8f9fa;
  border-radius: 8px;
  cursor: pointer;
}

.music-option:hover {
  background-color: #f0f2f5;
}

.modal-footer {
  padding: 16px;
  border-top: 1px solid #e5e5e5;
  display: flex;
  justify-content: center;
}

.publish-modal-btn {
  background-color: #ffb6c1;
  color: #fff;
  border: none;
  border-radius: 20px;
  padding: 8px 32px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.publish-modal-btn:hover {
  background-color: #ff9aa2;
}
</style>