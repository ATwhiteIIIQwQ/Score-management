<template>
  <div>
    <h2>学生列表</h2>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>地址</th>
        <th>成绩</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="student in students" :key="student.id">
        <td>{{ student.id }}</td>
        <td>{{ student.name }}</td>
        <td>{{ student.age }}</td>
        <td>{{ student.address }}</td>
        <td>{{ student.score }}</td>
        <td>
          <button @click="deleteStudent(student.id)">删除</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import studentApi from '@/api/score';

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
    async deleteStudent(id) {
      if (confirm('确定删除吗？')) {
        try {
          await studentApi.deleteStudent(id);
          await this.loadStudents(); // 刷新列表
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
