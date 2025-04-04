import service from '@/utils/request'

export default {
    getAllStudents() {
        return service.get('/student/list')
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
        return service.get('/student/search', { params })
    }
};