<template>
  <!-- 轮播图 -->
  <Carrousel />
  <!-- 精彩推荐 -->
  <RecommendCarrousel />
  <!-- 排行榜 -->
  <RecommendList />
  <!-- 歌曲推荐 -->
  <RecommendSong />

</template>

<script setup lang="ts">
import Carrousel from '@/views/recommend/carrousel.vue'
import RecommendCarrousel from '@/views/recommend/recommend_carrousel.vue'
import RecommendList from '@/views/recommend/recommand_list.vue'
import RecommendSong from '@/views/recommend/recommand_song.vue'
</script>

<style scoped>

</style>