<template>
  <div id="app" class="container">
    <h1>学生管理系统</h1>
    <AddStudent @student-added="loadStudents" />
    <StudentList ref="studentList" />
  </div>
</template>

<script>
import AddStudent from './components/AddStudent.vue';
import StudentList from './components/StudentList.vue';

export default {
  components: {
    AddStudent,
    StudentList
  },
  methods: {
    loadStudents() {
      this.$refs.studentList.loadStudents();
    }
  }
};
</script>

<style scoped>

</style>
