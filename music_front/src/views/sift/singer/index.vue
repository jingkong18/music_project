<template>
  <div class="singer-page">
    <h2 class="page-title">歌手</h2>
    <SingerFilter 
      @change-region="handleRegionChange" 
      @change-gender="handleGenderChange" 
    />
    <AlphabetNav @change-letter="handleLetterChange" />
    <div class="singer-list">
      <SingerCard 
        v-for="singer in filteredSingers" 
        :key="singer.id" 
        :singer="singer" 
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import type { Singer } from '@/types/index';
import SingerFilter from '@/views/sift/singer/SingerFilter.vue';
import AlphabetNav from '@/views/sift/singer/AlphabetNav.vue';
import SingerCard from '@/views/sift/singer/SingerCard.vue';

// 模拟歌手数据
const allSingers = ref<Singer[]>([
  { id: '1', name: 'Justin Bieber', avatar: 'https://picsum.photos/300/300?random=30', songCount: 8343, region: '欧美', gender: '男歌手', initial: 'J' },
  { id: '2', name: 'The Weeknd', avatar: 'https://picsum.photos/300/300?random=31', songCount: 4521, region: '欧美', gender: '男歌手', initial: 'T' },
  { id: '3', name: 'Charlie Puth', avatar: 'https://picsum.photos/300/300?random=32', songCount: 403, region: '欧美', gender: '男歌手', initial: 'C' },
  { id: '4', name: 'Bruno Mars', avatar: 'https://picsum.photos/300/300?random=33', songCount: 947, region: '欧美', gender: '男歌手', initial: 'B' },
  { id: '5', name: 'Drake', avatar: 'https://picsum.photos/300/300?random=34', songCount: 2116, region: '欧美', gender: '男歌手', initial: 'D' },
  { id: '6', name: 'Travis Scott', avatar: 'https://picsum.photos/300/300?random=35', songCount: 1082, region: '欧美', gender: '男歌手', initial: 'T' },
  { id: '7', name: 'Ed Sheeran', avatar: 'https://picsum.photos/300/300?random=36', songCount: 1469, region: '欧美', gender: '男歌手', initial: 'E' },
  { id: '8', name: 'SoMo', avatar: 'https://picsum.photos/300/300?random=37', songCount: 264, region: '欧美', gender: '男歌手', initial: 'S' },
  { id: '9', name: 'MXZI', avatar: 'https://picsum.photos/300/300?random=38', songCount: 341, region: '欧美', gender: '男歌手', initial: 'M' },
  { id: '10', name: 'Alan Walker', avatar: 'https://picsum.photos/300/300?random=39', songCount: 596, region: '欧美', gender: '男歌手', initial: 'A' },
  { id: '11', name: 'keshi', avatar: 'https://picsum.photos/300/300?random=40', songCount: 757, region: '欧美', gender: '男歌手', initial: 'K' },
  { id: '12', name: 'JVKE', avatar: 'https://picsum.photos/300/300?random=41', songCount: 152, region: '欧美', gender: '男歌手', initial: 'J' }
]);

// 当前筛选条件
const currentRegion = ref('欧美');
const currentGender = ref('男歌手');
const currentLetter = ref('热门');

// 处理地区筛选变更
const handleRegionChange = (region: string) => {
  currentRegion.value = region;
};

// 处理性别/类型筛选变更
const handleGenderChange = (gender: string) => {
  currentGender.value = gender;
};

// 处理字母导航变更
const handleLetterChange = (letter: string) => {
  currentLetter.value = letter;
};

// 筛选后的歌手列表
const filteredSingers = computed(() => {
  return allSingers.value.filter(singer => {
    // 地区筛选
    const regionMatch = currentRegion.value === '全部' || singer.region === currentRegion.value;
    // 性别/类型筛选
    const genderMatch = currentGender.value === '全部' || singer.gender === currentGender.value;
    // 字母筛选
    const letterMatch = currentLetter.value === '热门' || singer.initial === currentLetter.value;
    return regionMatch && genderMatch && letterMatch;
  });
});
</script>

<style scoped>
.singer-page {
  max-width: 1440px;
  margin: 0 auto;
  padding: 24px;
}

.page-title {
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 16px;
  color: #333;
}

.singer-list {
  display: flex;
  flex-wrap: wrap;
  gap: 24px;
}
</style>