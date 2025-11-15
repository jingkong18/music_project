import axios, { type AxiosInstance, type InternalAxiosRequestConfig, type AxiosResponse } from 'axios';
import { ElMessage } from 'element-plus';

// 定义API响应接口
interface ApiResponse<T = any> {
  code: number;
  message: string;
  data: T;
}

// 创建axios实例
const service: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:3000/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json;charset=utf-8'
  }
});

// 从localStorage获取token，避免在拦截器中直接使用Pinia store
function getToken(): string | null {
  return localStorage.getItem('token');
}

// 清除token
function clearToken(): void {
  localStorage.removeItem('token');
}

// 请求拦截器
service.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    // 直接从localStorage获取token
    const token = getToken();
    if (token) {
      config.headers = config.headers || {};
      config.headers.Authorization = `Bearer ${token}`;
    }
    // 必须返回config对象
    return config;
  },
  (error: any) => {
    console.error('请求错误:', error);
    return Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (response: AxiosResponse<ApiResponse>) => {
    const { code, message } = response.data;
    
    if (code !== 200 && code !== 0) {
      ElMessage.error(message || '请求失败');
      
      if (code === 401) {
        clearToken();
        window.location.href = '/login';
      }
      
      return Promise.reject(new Error(message || '请求失败'));
    }
    
    return response.data.data;
  },
  (error: any) => {
    console.error('响应错误:', error);
    
    if (!error.response) {
      ElMessage.error('网络错误，请检查您的网络连接');
      return Promise.reject(error);
    }
    
    const { status, data } = error.response;
    
    switch (status) {
      case 400:
        ElMessage.error(data.message || '请求参数错误');
        break;
      case 401:
        ElMessage.error('未授权，请重新登录');
        clearToken();
        window.location.href = '/login';
        break;
      case 403:
        ElMessage.error('拒绝访问');
        break;
      case 404:
        ElMessage.error('请求的资源不存在');
        break;
      case 500:
        ElMessage.error('服务器错误');
        break;
      default:
        ElMessage.error(data.message || `请求失败: ${status}`);
    }
    
    return Promise.reject(error);
  }
);

// 封装请求方法
export const request = {
  get<T = any>(url: string, params?: any, config?: InternalAxiosRequestConfig): Promise<T> {
    return service.get(url, { params, ...config });
  },
  
  post<T = any>(url: string, data?: any, config?: InternalAxiosRequestConfig): Promise<T> {
    return service.post(url, data, config);
  },
  
  put<T = any>(url: string, data?: any, config?: InternalAxiosRequestConfig): Promise<T> {
    return service.put(url, data, config);
  },
  
  delete<T = any>(url: string, params?: any, config?: InternalAxiosRequestConfig): Promise<T> {
    return service.delete(url, { params, ...config });
  },
  
  upload<T = any>(url: string, file: File, config?: InternalAxiosRequestConfig): Promise<T> {
    const formData = new FormData();
    formData.append('file', file);
    return service.post(url, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      },
      ...config
    });
  }
};

export default service;