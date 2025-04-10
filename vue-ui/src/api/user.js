import service from '@/utils/request'

export default {
    login(user) {
        return service.post('/user/login', user)
    },
    getAllUsers() {
        return service.get('/user/list')
    },
    getUserByPage(pageNum, pageSize) {
        return service.get('/user/page', { params: { pageNum: pageNum, pageSize: pageSize }})
    },
    addUser(user) {
        return service.post('/user', user)
    },
    updateUser(id, user) {
        return service.put(`/user/${id}`, user)
    },
    deleteUser(id) {
        return service.delete(`/user/${id}`)
    },
    searchUsers(params) {
        return service.get('/user/search', { 
            params: {
                ...params,
                pageNum: params.pageNum || 1,
                pageSize: params.pageSize || 10
            }
        })
    }
};