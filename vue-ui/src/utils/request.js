import axios from 'axios'

const service = axios.create({
  baseURL: '/system',
  timeout: 10000, // 请求超时时间
  headers: {
    'Content-Type': 'application/json;charset=UTF-8'
  }
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    const token = localStorage.getItem('userInfo')?.token
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  response => response.data,
  error => {
    const errorResponse = error.response || {};
    const backendError = errorResponse.data || {};
    const status = errorResponse.status || 500;
    
    const Error = {
      code: status,
      message: backendError.message || error.message || '请求失败',
      data: backendError
    };

    return Promise.reject(Error);
  }
);

export default service