import service from '@/utils/request'

// 修改搜索API调用方式
export default {
    getAllStudents() {
        return service.get('/student/list')
    },
    getStudentByPage(pageNum, pageSize) {
        return service.get('/student/page', { params: { pageNum: pageNum, pageSize: pageSize }}) 
    },
    addStudent(student) {
        return service.post('/student', student)
    },
    updateStudent(id, student) {
        return service.put(`/student/${id}`, student)
    },
    deleteStudent(id) {
        return service.delete(`/student/${id}`)
    },
    searchStudents(params) {
        return service.get('/student/search', { 
            params: {
                ...params,
                pageNum: params.pageNum || 1,
                pageSize: params.pageSize || 10
            }
        })
    }
}