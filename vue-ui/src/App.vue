<template>
  <div id="app">
    <h1>学生管理系统</h1>
    <AddScore @student-added="loadScores" />
    <ScoreList ref="studentList" />
  </div>
</template>

<script>
import AddScore from './components/AddScore.vue';
import ScoreList from './components/ScoreList.vue';

export default {
  components: {
    AddScore,
    ScoreList
  },
  methods: {
    loadScores() {
      this.$refs.studentList.loadScores();
    }
  }
};
</script>

<style scoped>

</style>
