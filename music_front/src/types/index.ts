export interface Song {
  id: string;
  name: string; // 歌曲名
  singer: string; // 歌手
  album: string; // 专辑
  cover: string; // 封面图
  duration: number; // 时长（秒）
  route: string; // 跳转路由
}
export interface Playlist {
  id: string;
  cover: string; // 封面图地址
  title: string; // 歌单标题
  playCount: number; // 播放量（万）
  description: string; // 描述
  route: string; // 跳转路由
}
export interface Music {
  id: string;
  cover: string; // 封面图地址
  title: string; // 歌曲名
  artist: string; // 歌手
  label?: string; // 标签（如“超清母带”“VIP”“MV”）
  extra?: string; // 额外标识（如“原唱”）
  isVIP?: boolean; // 是否VIP歌曲
  actions?: {
    download: boolean; // 是否显示下载按钮
    like: boolean; // 是否显示点赞按钮
    more: boolean; // 是否显示更多按钮
  };
}
// types/index.ts
// 榜单分类项（如“国风榜”“民谣榜”）
export interface ChartCategory {
  id: string;
  name: string;
  cover: string; // 封面图地址
}

// 榜单歌曲项
export interface ChartSong {
  rank: number; // 排名
  title: string; // 歌曲名
  artist: string; // 歌手
  tag?: '新' | '↑' | '↓' | '-'; // 标识（新歌、上升、下降、持平）
}

// 具体榜单（如“飙升榜”“新歌榜”）
export interface Chart {
  id: string;
  name: string;
  updateInfo: string; // 更新信息
  songs: ChartSong[];
  cover: string; // 榜单封面
}
// types/index.ts
// 歌手信息
export interface Singer {
  id: string;
  name: string;
  avatar: string; // 头像地址
  songCount: number; // 单曲数
  region: '华语' | '欧美' | '日本' | '韩国' | '其他'; // 地区
  gender: '男歌手' | '女歌手' | '乐队组合'; // 性别/类型
  initial: string; // 姓名首字母（用于字母导航）
}