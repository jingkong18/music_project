<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px">
        <Aside />
      </el-aside>
      <el-container>
        <el-header>
          <Header />
        </el-header>
        <el-main>
            <router-view />
        </el-main>
        <el-footer>
          <Footer />
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import Aside from '@/views/layout/aside.vue';
import Header from '@/views/layout/header.vue';
import Footer from '@/views/layout/footer.vue';
</script>

<style scoped>

</style>
