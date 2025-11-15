<template>
  <el-dropdown trigger="click">
    <!-- 触发按钮（更多图标） -->
    <el-icon class="more-icon"><More /></el-icon>
    <!-- 下拉菜单内容 -->
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item :icon="CaretRight">播放</el-dropdown-item>
        <el-dropdown-item :icon="ArrowRight">下一首播放</el-dropdown-item>
        <el-dropdown-item :="Message">查看评论(24133)</el-dropdown-item>
        <el-dropdown-item :icon="Collection">收藏</el-dropdown-item>
        <el-dropdown-item :icon="Download">下载</el-dropdown-item>
        <el-dropdown-item :icon="Share">分享</el-dropdown-item>
        <el-dropdown-item :icon="ShoppingCart">购买单曲</el-dropdown-item>
        <el-dropdown-item :icon="DocumentCopy">复制链接</el-dropdown-item>
        <el-dropdown-item :icon="Remove">减少推荐</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script setup lang="ts">
import { 
  More, CaretRight, ArrowRight, Message, 
  Collection, Download, Share, 
  ShoppingCart, DocumentCopy, Remove 
} from '@element-plus/icons-vue';
</script>

<style scoped>
.more-icon {
  font-size: 20px;
  cursor: pointer;
  color: #999;
}

.more-icon:hover {
  color: #ff2d55;
}

.el-dropdown-menu {
  min-width: 160px;
}

.el-dropdown-item {
  display: flex;
  align-items: center;
}

.el-dropdown-item .el-icon {
  margin-right: 8px;
}
</style>