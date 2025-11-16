<template>
  <div class="chart-category">
    <div 
      v-for="category in categories" 
      :key="category.id" 
      class="category-item"
    >
      <img :src="category.cover" alt="榜单封面" />
      <span class="category-name">{{ category.name }}</span>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { ChartCategory } from '@/types/index';

defineProps<{
  categories: ChartCategory[];
}>();
</script>

<style scoped>
.chart-category {
  display: flex;
  gap: 20px;
  margin-bottom: 24px;
}

.category-item {
  width: 160px;
  height: 160px;
  border-radius: 8px;
  overflow: hidden;
  position: relative;
  cursor: pointer;
}

.category-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s;
}

.category-item:hover img {
  transform: scale(1.05);
}

.category-name {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: #fff;
  font-size: 20px;
  font-weight: 600;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
}
</style>