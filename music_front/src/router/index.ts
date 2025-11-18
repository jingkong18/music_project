// src/router/index.ts
import { createRouter, createWebHashHistory } from 'vue-router'

// 创建路由实例
const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL), // 基于 hash 模式的路由
  routes: [
    {
      path: '/',
      name: 'Layout',
      redirect: '/recommend', // 重定向到推荐页面
      children: [
        {
          path: '/recommend',
          name: 'Recommend',
          component: () => import('@/views/recommend/index.vue')
        },
        {
          path: '/download',
          name: 'Download',
          component: () => import('@/views/download/index.vue')
        },
        {
          path: '/follow',
          name: 'Follow',
          component: () => import('@/views/follow/index.vue')
        },
        {
          path: '/mylike',
          name: 'MyLike',
          component: () => import('@/views/mylike/index.vue')
        },
        {
          path: '/local',
          name: 'LocalMusic',
          component: () => import('@/views/localmusic/index.vue')
        },
        {
          path: '/sift',
          name: 'Sift',
          component: () => import('@/views/sift/index.vue'),
          redirect: '/SiftView',
          children: [
            {
              path: '/playlist',
              name: 'Playlist',
              component: () => import('@/views/sift/playlist/index.vue'),

            },
            {
              path: '/SiftView',
              name: 'SiftView',
              component: () => import('@/views/sift/sift/sift.vue')
            },
            {
              path: '/ranking',
              name: 'Ranking',
              component: () => import('@/views/sift/ranking/index.vue')
            },
            {
              path: '/singer',
              name: 'Singer',
              component: () => import('@/views/sift/singer/index.vue')
            },
            {
              path: '/vip',
              name: 'Vip',
              component: () => import('@/views/sift/vip/index.vue')
            },
          ]
        },
        {
          path: '/recent',
          name: 'Recent',
          component: () => import('@/views/recent/index.vue'),
          redirect: '/recentSong',
          children: [
            {
              path: '/recentSong',
              name: 'RecentSong',
              component: () => import('@/views/recent/RencentSong/index.vue'),
            },
            {
              path: '/recentPlayList',
              name: 'RecentPlayList',
              component: () => import('@/views/recent/RecentPlayList/index.vue'),
            },
            {
              path: '/recentAlbum',
              name: 'RecentAlbum',
              component: () => import('@/views/recent/RecentAlbum/index.vue'),
            },
          ]
        },
        {
          path: '/collect',
          name: 'Collect',
          component: () => import('@/views/collect/index.vue')
        },
        {
          path: '/disk',
          name: 'Disk',
          component: () => import('@/views/disk/index.vue')
        },
        {
          path: '/playlist/:id',
          name: 'PlaylistDetail',
          component: () => import('@/views/playlistDetail/index.vue')
        },
        {
          path: '/album/:id',
          name: 'AlbumDetail',
          component: () => import('@/views/albumdetail/index.vue')
        },
        {
          path: '/song/:id',
          name: 'SongDetail',
          component: () => import('@/views/songDetail/index.vue'),
          props: true,
        }
      ]
}]
});

export default router;