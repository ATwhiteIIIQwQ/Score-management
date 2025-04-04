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
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
    response => {
      return response.data; // 直接返回后端响应的数据
    },
    error => {
      if (error.response && error.response.status === 401) {
        // router.push('/login')
      }
      return Promise.reject(error)
    }
  )

export default service