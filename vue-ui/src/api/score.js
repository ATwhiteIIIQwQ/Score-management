import service from '@/utils/request'

export default {
    getAllScores() {
        return service.get('/score/list')
    },
    getScoreByPage(pageNum, pageSize) {
        return service.get('/score/page', { params: { pageNum: pageNum, pageSize: pageSize }}) 
    },
    addScore(score) {
        return service.post('/score', score)
    },
    updateScore(id, score) {
        return service.put(`/score/${id}`, score)
    },
    deleteScore(id) {
        return service.delete(`/score/${id}`)
    },
    searchScores(params) {
        return service.get('/score/search', { 
            params: {
                ...params,
                pageNum: params.pageNum || 1,
                pageSize: params.pageSize || 10
            }
        })
    }
};