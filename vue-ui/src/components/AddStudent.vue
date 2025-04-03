<template>
  <div class="container">
    <h2>添加学生</h2>
    <form @submit.prevent="handleSubmit">
      <input v-model="student.studentNum" placeholder="学号" required>
      <input v-model="student.studentName" placeholder="姓名" required>
      <input v-model="student.studentGrade" type="number" placeholder="年级">
      <input v-model="student.studentClass" placeholder="班级">
      <button type="submit">提交</button>
    </form>
  </div>
</template>

<script>
import scoreApi from '@/api/score';

export default {
  data() {
    return {
      student: {
        studentNum: '',
        studentName: '',
        studentGrade: '',
        studentClass: ''
      }
    };
  },
  methods: {
    async handleSubmit() {
      try {
        await scoreApi.addStudent(this.student);
        this.$emit('student-added'); // 通知父组件刷新
        this.student = { studentNum: '', studentName: '', studentGrade: '', studentClass: '' }; // 清空表单
      } catch (error) {
        console.error('添加失败:', error);
      }
    }
  }
};
</script>

<style scoped>

</style>
