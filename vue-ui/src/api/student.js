import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/system/student', // 后端 API 地址
    timeout: 5000 // 超时时间
});

// 定义所有 API 方法
export default {
    // 获取所有学生
    getAllStudents() {
        return api.get('/list');
    },

    // 添加学生
    addStudent(student) {
        return api.post('/', student);
    },

    // 修改学生
    updateStudent(id, student) {
        return api.put(`/${id}`, student);
    },

    // 删除学生
    deleteStudent(id) {
        return api.delete(`/${id}`);
    },

    // 条件查询
    searchStudents(params) {
        return api.get('/search', { params });
    }
};
