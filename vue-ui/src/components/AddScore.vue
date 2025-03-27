<template>
  <div>
    <h2>添加学生</h2>
    <form @submit.prevent="handleSubmit">
      <input v-model="student.id" type="number" placeholder="ID" required>
      <input v-model="student.name" placeholder="姓名" required>
      <input v-model="student.age" type="number" placeholder="年龄" required>
      <input v-model="student.address" placeholder="地址" required>
      <input v-model="student.score" placeholder="成绩" required>
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
        id: '',
        name: '',
        age: '',
        address: '',
        score:''
      }
    };
  },
  methods: {
    async handleSubmit() {
      try {
        await scoreApi.addStudent(this.student);
        this.$emit('refresh-list'); // 通知父组件刷新
        this.student = { id: '', name: '', age: '', address: '' ,score: ''}; // 清空表单
      } catch (error) {
        console.error('添加失败:', error);
      }
    }
  }
};
</script>

<style scoped>

</style>
