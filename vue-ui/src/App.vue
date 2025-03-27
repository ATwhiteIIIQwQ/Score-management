<template>
  <div id="app">
    <h1>学生管理系统</h1>
    <AddScore @refresh-list="loadStudents" />
    <ScoreList ref="scoreList" />
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
    loadStudents() {
      this.$refs.scoreList.loadStudents();
    }
  }
};
</script>

<style scoped>

</style>

