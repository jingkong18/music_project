<template>
  <div class="recommend-section">
    <h3 class="section-title">精选推荐</h3>
    <div class="carousel-container">
      <div class="card-list" ref="cardListRef">
        <div 
          v-for="(item, index) in recommendList" 
          :key="index" 
          class="recommend-card"
          @mouseenter="activeCard = item"
          @mouseleave="activeCard = null"
        >
          <img :src="item.img" alt="推荐卡片" class="card-img" />
          <div class="card-overlay">
            <div class="card-title">{{ item.title }}</div>
            <div class="card-desc">{{ item.desc }}</div>
          </div>
          <!-- 悬浮播放按钮（鼠标悬停显示） -->
          <div v-if="activeCard === item" class="play-btn">
            <el-icon class="play-icon"><CaretRight /></el-icon>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { CaretRight } from '@element-plus/icons-vue';

// 推荐卡片数据
const recommendList = ref([
  {
    img: 'https://picsum.photos/id/1076/300/200',
    title: '每日推荐',
    desc: '每日推荐 | 从「Black Veil Bride」听起'
  },
  {
    img: 'https://picsum.photos/id/96/300/200',
    title: '私人雷达',
    desc: '今天从《当遇见你》听起'
  },
  {
    img: 'https://picsum.photos/id/1019/300/200',
    title: '私人漫游',
    desc: '从「Voices」开启无限漫游'
  },
  {
    img: 'https://picsum.photos/id/1082/300/200',
    title: '华语流行日推',
    desc: '鼻塞、看着我眼睛、我记得你眼里的依恋'
  },
  {
    img: 'https://picsum.photos/id/1025/300/200',
    title: '华语伤感',
    desc: '华语伤感 | 深陷在情感的逆流'
  },
  {
    img: 'https://picsum.photos/id/1062/300/200',
    title: '音乐播客',
    desc: '爽疯了！DJ 阿衡必听精选，999+评论区...'
  },
  {
    img: 'https://picsum.photos/id/1074/300/200',
    title: '欧美流行日推',
    desc: '每日欧美流行推荐'
  }
]);

const cardListRef = ref<HTMLDivElement | null>(null);
const activeCard = ref<any>(null); // 存储当前悬停的卡片

</script>

<style scoped>
/* 样式与之前保持一致，无需修改 */
.recommend-section {
  padding: 20px;
}

.section-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 16px;
}

.carousel-container {
  display: flex;
  align-items: center;
  position: relative;
  overflow: hidden;
}

.card-list {
  display: flex;
  gap: 16px;
  padding: 0 16px;
  overflow-x: auto;
  scroll-snap-type: x mandatory;
  scroll-behavior: smooth;
  -webkit-overflow-scrolling: touch;
}

.card-list::-webkit-scrollbar {
  display: none;
}

.recommend-card {
  width: 300px;
  height: 180px;
  border-radius: 8px;
  overflow: hidden;
  position: relative;
  scroll-snap-align: start;
  cursor: pointer;
}

.card-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s ease;
}

.recommend-card:hover .card-img {
  transform: scale(1.05);
}

.card-overlay {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  background: linear-gradient(to top, rgba(0, 0, 0, 0.8), transparent);
  padding: 12px;
  color: #fff;
}

.card-title {
  font-size: 16px;
  font-weight: 500;
  margin-bottom: 4px;
}

.card-desc {
  font-size: 12px;
  opacity: 0.9;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.carousel-arrow {
  position: absolute;
  z-index: 10;
  background-color: rgba(0, 0, 0, 0.3);
  color: #fff;
  border-radius: 50%;
  width: 36px;
  height: 36px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.carousel-arrow:nth-of-type(1) {
  left: 0;
}

.carousel-arrow:nth-of-type(3) {
  right: 0;
}

.play-btn {
  position: absolute;
  bottom: 20px;
  right: 20px;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: rgba(255, 45, 85, 0.8);
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

.play-icon {
  color: #fff;
  font-size: 20px;
}
</style>