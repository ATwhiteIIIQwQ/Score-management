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
    const status = errorResponse.status || 500;
    
    let message = '';
    if (status === 500) {
      message = '后端服务未启动，请检查服务器状态';
    } else if (error.message.includes('Network Error')) {
      message = '网络连接异常，请检查网络设置';
    } else {
      const backendMessage = errorResponse.data?.message;
      message = backendMessage || error.message || '请求失败';
    }

    const Error = {
      code: status,
      message: message,
      data: errorResponse.data || {}
    };

    return Promise.reject(Error);
  }
);

export default service