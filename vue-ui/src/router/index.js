import { createRouter, createWebHistory } from 'vue-router'

const Router = createRouter({
    history: createWebHistory(), // 去掉 URL 中的 # 
    routes: [
        {
            path: '/students',
            component: () => import('@/views/StudentIndex.vue')
        },
        // {
        //     path: '/courses',
        //     component: () => import('@/views/CourseIndex.vue')
        // },
        // {
        //     path: '/scores',
        //     component: () => import('@/views/ScoreManagement.vue')
        // },
        {
            path: '/',
            redirect: '/students'
        } 
    ]
})

export default Router;