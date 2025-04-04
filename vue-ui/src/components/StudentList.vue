<template>
  <div class="container">
    <h2>学生列表</h2>
    <table class="table table-hover table-striped">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">学号</th>
          <th scope="col">姓名</th>
          <th scope="col">年级</th>
          <th scope="col">班级</th>
          <th scope="col">操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="student in students" :key="student.studentId">
            <td>{{ student.studentId }}</td>
            <td>{{ student.studentNum }}</td>
            <td>{{ student.studentName }}</td>
            <td>{{ student.studentGrade }}</td>
            <td>{{ student.studentClass }}</td>
            <td>
              <button class="btn btn-danger" @click="deleteStudent(student.studentId)">删除</button>
            </td>
          </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import studentApi from '@/api/student';

export default {
  data() {
    return {
      students: []
    };
  },
  mounted() {
    this.loadStudents();
  },
  methods: {
    async loadStudents() {
      try {
        const response = await studentApi.getAllStudents();
        this.students = response.data;
      } catch (error) {
        console.error('加载学生列表失败:', error);
      }
    },
    async deleteStudent(studentId) {
      if (confirm('确定删除学生吗？')) {
        try {
          await studentApi.deleteStudent(studentId);
          this.loadStudents(); // 刷新列表
        } catch (error) {
          console.error('删除失败:', error);
        }
      }
    }
  }
};
</script>

<style scoped>

</style>