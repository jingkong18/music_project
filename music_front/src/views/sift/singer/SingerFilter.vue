<template>
  <div class="singer-filter">
    <!-- 地区筛选 -->
    <div class="filter-group">
      <button 
        v-for="region in regions" 
        :key="region" 
        class="filter-btn"
        :class="{ active: currentRegion === region }"
        @click="handleRegionChange(region)"
      >
        {{ region }}
      </button>
    </div>
    <!-- 性别/类型筛选 -->
    <div class="filter-group">
      <button 
        v-for="gender in genders" 
        :key="gender" 
        class="filter-btn"
        :class="{ active: currentGender === gender }"
        @click="handleGenderChange(gender)"
      >
        {{ gender }}
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, defineEmits } from 'vue';


const emits = defineEmits(['change-region', 'change-gender']);

// 可选筛选项
const regions = ref(['全部', '华语', '欧美', '日本', '韩国', '其他']);
const genders = ref(['全部', '男歌手', '女歌手', '乐队组合']);

// 当前选中的筛选条件
const currentRegion = ref('欧美');
const currentGender = ref('男歌手');

// 地区筛选变更
const handleRegionChange = (region: string) => {
  currentRegion.value = region;
  emits('change-region', region);
};

// 性别/类型筛选变更
const handleGenderChange = (gender: string) => {
  currentGender.value = gender;
  emits('change-gender', gender);
};
</script>

<style scoped>
.singer-filter {
  margin-bottom: 16px;
}

.filter-group {
  display: flex;
  gap: 8px;
  margin-bottom: 12px;
}

.filter-btn {
  padding: 4px 12px;
  border: 1px solid #e5e5e5;
  border-radius: 16px;
  background: #fff;
  cursor: pointer;
  transition: all 0.2s;
}

.filter-btn.active {
  background: #fde4e4;
  color: #e94343;
  border-color: #fde4e4;
}

.filter-btn:hover {
  border-color: #ddd;
}
</style>