
import { createPinia, defineStore } from 'pinia';

// 定义store接口
interface UserState {
  token: string | null;
  userInfo: {name: string}| null;
}

// 定义store 第一个参数是 Store 唯一标识，第二个参数是配置
export const useUserStore = defineStore('user', {
  state: (): UserState => ({
    token: localStorage.getItem('token') || null,// 从localStorage获取token
    userInfo: null
  }),

  // 计算属性
 getters: {
    isLogin: (state) => !!state.token // 推导为 boolean 类型
  },

  // 方法
  actions: {
    //登录 登录：参数类型明确，无返回值（void）
     // 登录：参数类型明确，无返回值（void）
    login(userData: { token: string; info: UserState['userInfo'] }): void {
      this.token = userData.token;
      this.userInfo = userData.info;
      localStorage.setItem('token', userData.token);
    },
    // 退出登录：无参数，无返回值
    logout(): void {
      this.token = null;
      this.userInfo = null;
      localStorage.removeItem('token');
    }
  },
});

// 创建 Pinia 实例并导出
const pinia = createPinia();
export default pinia;

