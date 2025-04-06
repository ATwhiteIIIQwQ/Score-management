import service from '@/utils/request'

export default {
    getAllCourses() {
        return service.get('/course/list')
    },
    getCourseByPage(pageNum, pageSize) {
        return service.get('/course/page', { params: { pageNum: pageNum, pageSize: pageSize }}) 
    },
    addCourse(course) {
        return service.post('/course', course)
    },
    updateCourse(id, course) {
        return service.put(`/course/${id}`, course)
    },
    deleteCourse(id) {
        return service.delete(`/course/${id}`)
    },
    searchCourses(params) {
        return service.get('/course/search', { params })
    }
};