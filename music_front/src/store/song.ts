import { defineStore } from 'pinia';

// 歌曲类型定义（TypeScript 类型安全）
export interface Song {
  id: string;
  title: string;
  artist: string;
  album: string;
  cover: string;
  duration: string; // 格式：mm:ss
  lyrics: { line: string }[]; // 歌词
  encyclopedia: {
    style: string;
    language: string;
    releaseTime: string;
    bpm: number;
    instrument: string;
  };
}

// 模拟歌曲数据源
const mockSongs: Song[] = [
  {
    id: '1',
    title: '剑心',
    artist: '张杰',
    album: '拾',
    cover: 'https://picsum.photos/400/400?random=10',
    duration: '04:09',
    lyrics: [
      { line: '尘封在星蕴重明的魂魄' },
      { line: '叫醒了恍惚梦魇的无措' },
      { line: '揭开这宿命的脉络' },
      { line: '逃不开 这一世的寂寞' },
      { line: '往后是阴霾 往前是山隘' },
      { line: '想逃也逃不开' },
      { line: '命运再主宰 执着的心也不会改' },
      { line: '哪管桑田 哪管沧海' },
      { line: '听琴声潇潇 该忘的忘不掉' },
      { line: '红尘 困住我年少' },
    ],
    encyclopedia: {
      style: '流行 / 影视原声',
      language: '国语',
      releaseTime: '2014-06-28',
      bpm: 88,
      instrument: '钢琴、吉他、弦乐'
    }
  },
  {
    id: '2',
    title: '起风了',
    artist: '买辣椒也用券',
    album: '起风了',
    cover: 'https://picsum.photos/400/400?random=11',
    duration: '05:23',
    lyrics: [
      { line: '这一路上走走停停' },
      { line: '顺着少年漂流的痕迹' },
      { line: '迈出车站的前一刻' },
      { line: '竟有些犹豫' },
    ],
    encyclopedia: {
      style: '流行',
      language: '国语',
      releaseTime: '2018-12-03',
      bpm: 72,
      instrument: '吉他、钢琴'
    }
  }
];

export const usePlayerStore = defineStore('player', {
  state: () => ({
    currentSong: null as Song | null, // 当前播放歌曲
    songList: mockSongs, // 歌曲列表
    isPlaying: false, // 播放状态（控制旋转/歌词滚动）
  }),
  actions: {
    // 播放指定歌曲
    playSong(song: Song) {
      this.currentSong = song;
      this.isPlaying = true;
    },
    // 切换播放/暂停
    togglePlay() {
      this.isPlaying = !this.isPlaying;
    },
    // 上一首
   prevSong() {
  if (!this.currentSong || this.songList.length === 0) return;
  const currentIdx = this.songList.findIndex(s => s.id === this.currentSong!.id);
  const prevIdx = currentIdx > 0 ? currentIdx - 1 : this.songList.length - 1;
  const prevSong = this.songList[prevIdx];
  if (prevSong) {
    this.playSong(prevSong);
  }
},
    // 下一首
nextSong() {
  if (!this.currentSong || this.songList.length === 0) return;
  const currentIdx = this.songList.findIndex(s => s.id === this.currentSong!.id);
  const nextIdx = currentIdx < this.songList.length - 1 ? currentIdx + 1 : 0;
  const nextSong = this.songList[nextIdx];
  if (nextSong) {
    this.playSong(nextSong);
  }
}}});