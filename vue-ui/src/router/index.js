import { createRouter, createWebHistory } from 'vue-router'
import { getCurrentInstance } from 'vue'

const Router = createRouter({
    history: createWebHistory(), // 去掉 URL 中的 # 
    routes: [
        {
            path: '/students',
            component: () => import('@/views/StudentIndex.vue')
        },
        {
            path: '/courses',
            component: () => import('@/views/CourseIndex.vue')
        },
        {
            path: '/scores',
            component: () => import('@/views/ScoreIndex.vue')
        },
        {
          path: '/stu_scores',
          component: () => import('@/views/StuScoreIndex.vue')
        },
        {
            path: '/users',
            component: () => import('@/views/UserIndex.vue')
        },
        {
            path: '/login',
            component: () => import('@/views/LoginIndex.vue')
        },
        {
            path: '/',
            redirect: '/login'
        }
    ]
})

Router.beforeEach((to, from, next) => {
  const isAuthenticated = localStorage.getItem('userInfo')
  
  if (to.path !== '/login' && !isAuthenticated) {
    next('/login')
  }

  else if (to.path === '/login' && isAuthenticated) {
    next('/students')
  } 
  else {
    next()
  }
})

export default Router;