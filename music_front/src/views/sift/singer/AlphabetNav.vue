<template>
  <div class="alphabet-nav">
    <button class="nav-item" :class="{ active: currentLetter === '热门' }" @click="handleLetterChange('热门')">热门</button>
    <button 
      v-for="letter in letters" 
      :key="letter" 
      class="nav-item"
      :class="{ active: currentLetter === letter }"
      @click="handleLetterChange(letter)"
    >
      {{ letter }}
    </button>
  </div>
</template>

<script setup lang="ts">
import { ref, defineEmits } from 'vue';

const emits = defineEmits(['change-letter']);

// 字母列表
const letters = ref(['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '#']);

// 当前选中的字母
const currentLetter = ref('热门');

// 字母变更事件
const handleLetterChange = (letter: string) => {
  currentLetter.value = letter;
  emits('change-letter', letter);
};
</script>

<style scoped>
.alphabet-nav {
  display: flex;
  gap: 8px;
  margin-bottom: 16px;
  flex-wrap: wrap;
}

.nav-item {
  width: 32px;
  height: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  background: #fff;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.2s;
}

.nav-item.active {
  background: #e94343;
  color: #fff;
  border-color: #e94343;
}

.nav-item:hover {
  border-color: #ddd;
}
</style>